import java.util.*;

class Solution {

    public String solution(int n, int t, int m, String[] timetable) {
        int max = n * m;
        int start = 540;
        int[] arrives = new int[n];
        PriorityQueue<Integer> que = new PriorityQueue<>();

        for (int i = 0; i < n; i++) arrives[i] = start + i * t;
        for (String time : timetable) que.add(toMinute(time));

        String answer = toString(arrives[n - 1]);
        int total = 0;
        int latest = 0;
        for (int i = 0; i < n; i++) {
            int arrive = arrives[i];
            int crew = 0;

            while (crew < m && !que.isEmpty()) {
                if (que.peek() <= arrive) {
                    crew++;
                    total++;
                    latest = que.poll();

                    if (total == max) return toString(latest - 1);
                }
                else break;
            }
            if (i == n - 1 && crew == m) return toString(latest - 1);
        }

        return answer;
    }

    public int toMinute(String time) {
        StringTokenizer st = new StringTokenizer(time, ":");
        return Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());
    }

    public String toString(int minute) {
        return String.format("%02d", minute / 60) + ":" + String.format("%02d", (minute % 60));
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        int n = 2;
        int t = 10;
        int m = 2;
        String[] timetable = {"09:10", "09:09", "08:00"};

        System.out.println(s.solution(n, t, m, timetable));
    }
}
