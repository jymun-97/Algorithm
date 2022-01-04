import java.util.*;

class Page implements Comparable<Page> {
    int index, baseScore;
    double linkScore, matchScore;
    String url;
    ArrayList<String> links;

    public Page(
        int index, 
        String url,
        ArrayList<String> links,
        ArrayList<String> words,
        String target
    ) {
        this.index = index;
        this.url = url;
        this.links = links;

        for (String word : words) {
            if (word.equals(target)) baseScore++;
        }
    }
    @Override
    public boolean equals(Object o) {
        Page other = (Page)o;
        return url.equals(other.url);
    }

    @Override
    public int compareTo(Page other) {
        if (matchScore > other.matchScore) return -1;
        else if (matchScore < other.matchScore) return 1;
        else return index - other.index;
    }
}
class Solution {

    public int solution(String word, String[] pages) {
        HashMap<String, Page> map = new HashMap<>();
        ArrayList<Page> list = new ArrayList<>();
        int index = 0;
        String target = word.toLowerCase();

        for (String page : pages) {
            page = page.toLowerCase();

            String home = getHomeUrl(page);
            String body = getBody(page);
            ArrayList<String> links = getLinks(body);
            ArrayList<String> words = getWords(body);

            map.put(home, new Page(index++, home, links, words, target));
            list.add(map.get(home));
        }

        for (Page from : list) {
            for (String link : from.links) {
                if (!map.containsKey(link)) continue;

                Page to = map.get(link);
                to.linkScore += (double)from.baseScore / from.links.size();
            }
        }
        for (Page page : list) page.matchScore = page.baseScore + page.linkScore;

        Collections.sort(list);
        return list.get(0).index;
    }

    String getHomeUrl(String page) {
        String pattern = "<meta property=\"og:url\" content=\"https://";
        int start = page.indexOf(pattern) + pattern.length();
        int end = page.indexOf("\"/", start);

        return page.substring(start, end);
    }

    String getBody(String page) {
        int start = page.indexOf("<body>") + "<body>".length();
        int end = page.indexOf("</body>");

        return page.substring(start, end);
    }

    ArrayList<String> getLinks(String body) {
        ArrayList<String> links = new ArrayList<>();
        String pattern = "<a href=\"https://";

        while (body.contains(pattern)) {
            int start = body.indexOf(pattern) + pattern.length();
            int end = body.indexOf("\">", start);

            links.add(body.substring(start, end));
            body = body.substring(end);
        }
        return links;
    }

    ArrayList<String> getWords(String body) {
        String startPattern = "<a href=";
        String endPattern = "</a>";

        while (body.contains(startPattern)) {
            int start = body.indexOf(startPattern);
            int end = body.indexOf(endPattern, start) + endPattern.length();
            String target = body.substring(start, end);

            body = body.replace(target, "");
        }

        String[] words = body.split("[^a-z]");
        return new ArrayList<String>(Arrays.asList(words));
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        String[] pages = {"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"};

        System.out.println(s.solution("blind", pages));
    }
}
