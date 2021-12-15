import java.util.*;

class Solution {

    ArrayList<Integer>[][][][] list;
    HashMap<String, Integer> map = new HashMap<>();
    HashMap<String, Integer> ans = new HashMap<>();

    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];

        init();
        Arrays.sort(info, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                int aScore = Integer.parseInt(a.split(" ")[4]);
                int bScore = Integer.parseInt(b.split(" ")[4]);

                return aScore - bScore;
            }
        });
        for (String line : info) {
            StringTokenizer st = new StringTokenizer(line);
            int lang = map.get(st.nextToken());
            int job = map.get(st.nextToken());
            int career = map.get(st.nextToken());
            int food = map.get(st.nextToken());
            int score = Integer.parseInt(st.nextToken());

            insert(lang, job, career, food, score);
        }

        int i = 0;
        for (String line : query) {
            if (ans.containsKey(line)) {
                answer[i++] = ans.get(line);
                continue;
            }

            StringTokenizer st = new StringTokenizer(line);
            int lang = map.getOrDefault(st.nextToken(), 0);
            st.nextToken();
            int job = map.getOrDefault(st.nextToken(), 0);
            st.nextToken();
            int career = map.getOrDefault(st.nextToken(), 0);
            st.nextToken();
            int food = map.getOrDefault(st.nextToken(), 0);
            int score = Integer.parseInt(st.nextToken());

            answer[i] = binarySearch(list[lang][job][career][food], score);
            ans.put(line, answer[i++]);
        }

        return answer;
    }

    int binarySearch(ArrayList<Integer> a, int score) {
        int n = a.size();
        int left = 0, right = n - 1;
        int target = 0;

        if (n > 0 && score > a.get(right)) return 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            
            if (a.get(mid) < score) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
                target = mid;
            }
        }
        return n - target;
    }

    void init() {
        list = new ArrayList[4][3][3][3];
        for (int a = 0; a < 4; a++) {
            for (int b = 0; b < 3; b++) {
                for (int c = 0; c < 3; c++) {
                    for (int d = 0; d < 3; d++) {
                        list[a][b][c][d] = new ArrayList<>();
                    }
                }
            }
        }

        map.put("java", 1);
        map.put("python", 2);
        map.put("cpp", 3);
        map.put("backend", 1);
        map.put("frontend", 2);
        map.put("junior", 1);
        map.put("senior", 2);
        map.put("pizza", 1);
        map.put("chicken", 2);
    }

    void insert(int lang, int job, int career, int food, int score) {
        list[lang][job][career][food].add(score);
        list[0][0][0][0].add(score);
        
        list[0][0][0][food].add(score);
        list[0][0][career][0].add(score);
        list[0][job][0][0].add(score);
        list[lang][0][0][0].add(score);
        
        list[0][0][career][food].add(score);
        list[0][job][0][food].add(score);
        list[0][job][career][0].add(score);
        list[lang][0][0][food].add(score);
        list[lang][0][career][0].add(score);
        list[lang][job][0][0].add(score);
        
        list[0][job][career][food].add(score);
        list[lang][0][career][food].add(score);
        list[lang][job][0][food].add(score);
        list[lang][job][career][0].add(score);
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};

        System.out.println(Arrays.toString(s.solution(info, query)));
    }
}
