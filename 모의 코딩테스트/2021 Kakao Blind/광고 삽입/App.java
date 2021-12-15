import java.util.*;

class Solution {

    public String solution(String play_time, String adv_time, String[] logs) {
        int play = toSec(play_time);
        int adv = toSec(adv_time);

        int[] arr = new int[play + 2];
        for (String log : logs) {
            StringTokenizer st = new StringTokenizer(log, "-");
            
            int start = toSec(st.nextToken());
            int end = toSec(st.nextToken());

            arr[start] += 1;
            arr[end] += -1;
        }

        for (int i = 0; i <= play; i++) arr[i + 1] += arr[i];

        long max = 0;
        int left = 0;
        int ans = left;
        for (int i = 0; i < adv; i++) max += arr[i];
        
        long sum = max - arr[left++];
        for (int right = adv; right <= play; right++) {
            sum += arr[right];
            if (max < sum) {
                max = sum;
                ans = left;
            }
            sum -= arr[left++];
        }

        return toTime(ans);
    }

    int toSec(String time) {
        StringTokenizer st = new StringTokenizer(time, ":");

        int hour = Integer.parseInt(st.nextToken());
        int minute = Integer.parseInt(st.nextToken());
        int sec = Integer.parseInt(st.nextToken());

        return hour * 3600 + minute * 60 + sec;
    }

    String toTime(int sec) {
        String time = String.format("%02d", sec / 3600) + ":";

        sec %= 3600;
        time += String.format("%02d", sec / 60) + ":";

        sec %= 60;
        time += String.format("%02d", sec);

        return time;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        String play_time = "10:00:00";
        String adv_time = "01:00:00";
        String[] logs = {"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"};

        System.out.println(s.solution(play_time, adv_time, logs));
    }
}
