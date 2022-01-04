import java.util.Arrays;

class Solution {

    public int solution(int[] d, int budget) {
        Arrays.sort(d);
        
        int i = 0;
        for (i = 0; i < d.length; i++) {
            budget -= d[i];
            if (budget < 0) break;
        }

        return i;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        int[] d = {100};
        int budget = 101;
        System.out.println(s.solution(d, budget));
    }
}
