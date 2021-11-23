import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int n = prices.length;
        int[] answer = new int[n];

        for (int i = 0; i < n - 1; i++) {
            int target = prices[i];
            for (int j = i + 1; j < n; j++) {
                answer[i]++;
                if (target > prices[j]) break;
            }
        }
        
        return answer;
    }
}

public class App {

    public static void main(String[] args) throws Exception {
        Solution s = new Solution();
        int[] answer = s.solution(new int[] {3,4,6,9,8,2,1,3,7,5});

        System.out.println(Arrays.toString(answer));
    }
}
