import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;

        Arrays.sort(times);
        long left = 0, right = (long)times[times.length - 1] * n;
        
        while (left <= right) {
            long mid = (left + right) / 2;

            long count = 0;
            for (int time : times) count += mid / time;

            if (count < n) left = mid + 1;
            else {
                right = mid - 1;
                answer = mid;
            }
        }
        return answer;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();
        int n = 5;
        int[] times = {1, 2};

        System.out.println(s.solution(n, times));
    }
}
