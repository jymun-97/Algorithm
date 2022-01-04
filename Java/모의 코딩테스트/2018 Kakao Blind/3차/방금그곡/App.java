class Solution {

    public String solution(String m, String[] musicinfos) {
        final String NONE = "(None)"; 
        String answer = NONE;
        int answer_play_length = 0;

        m = convert(m);
        for (String info : musicinfos) {
            String[] tokens = info.split(",");
            int play_length = toMinute(tokens[1]) - toMinute(tokens[0]);
            String title = tokens[2];
            String base = convert(tokens[3]);
            int music_length = base.length();

            String music = base;
            if (play_length > music_length) {
                music += base;
                while (music.length() <= m.length()) music += base;
            }
            else music = base.substring(0, play_length);
            
            if (music.contains(m)) {
                if (answer_play_length < play_length) {
                    answer = title;
                    answer_play_length = play_length;
                }
            } 
        }

        return answer;
    }

    int toMinute(String time) {
        String[] tokens = time.split(":");
        return Integer.parseInt(tokens[0]) * 60 + Integer.parseInt(tokens[1]);
    }

    String convert(String str) {
        StringBuilder sb = new StringBuilder(str);
        while (sb.toString().contains("#")) {
            int target = sb.indexOf("#") - 1;
            sb.deleteCharAt(target + 1);
            sb.setCharAt(target, Character.toLowerCase(sb.charAt(target)));
        }
        return sb.toString();
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        String m = "ABC#";
        String[] musicinfos = {"12:00,12:05,HELLO,XXXXXABC#", "13:00,13:14,WORLD,ABC#DEF#"};

        System.out.println(s.solution(m, musicinfos));
    }
}
