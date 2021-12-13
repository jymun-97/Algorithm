import java.util.Arrays;

class Solution {

    public int solution(String[] lines) {
        int[][] info = new int[lines.length][2];

        for (int i = 0; i < lines.length; i++) {
            String[] tokens = lines[i].split(" ");
            
            int t = (int)(Double.parseDouble(tokens[2].substring(0, tokens[2].length() - 1)) * 1000);
            int end = convert(tokens[1]);
            int start = end - t + 1;

            info[i][0] = start;
            info[i][1] = end;
        }
        int answer = 0;
        for (int i = 0; i < info.length; i++) {
            int count = 1;
            int left = info[i][1];
            int right = left + 1000;

            for (int j = i + 1; j < info.length; j++) {
                int nextStart = info[j][0];
                if (nextStart < right) count++;
            }
            if (answer < count) answer = count;
        }

        return answer;
    }

    int convert(String time) {
        String[] tokens = time.split(":");
        return Integer.parseInt(tokens[0]) * 3600 * 1000 +
            Integer.parseInt(tokens[1]) * 60 * 1000 +
            (int)(Double.parseDouble(tokens[2]) * 1000);
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        String[] lines = {"2016-09-15 20:59:57.421 0.351s", "2016-09-15 20:59:58.233 1.181s", "2016-09-15 20:59:58.299 0.8s", "2016-09-15 20:59:58.688 1.041s", "2016-09-15 20:59:59.591 1.412s", "2016-09-15 21:00:00.464 1.466s", "2016-09-15 21:00:00.741 1.581s", "2016-09-15 21:00:00.748 2.31s", "2016-09-15 21:00:00.966 0.381s", "2016-09-15 21:00:02.066 2.62s"};

        System.out.println(s.solution(lines));
    }
}
