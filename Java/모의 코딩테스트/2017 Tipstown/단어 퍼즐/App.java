import java.util.*;

class Solution {

    public int solution(String[] strs, String t) {
        long[] dp = new long[t.length()];
        for (int i = 0; i < t.length(); i++) dp[i] = Integer.MAX_VALUE;
        HashSet<String> set = new HashSet<>();
        for (String str : strs) set.add(str);

        for (int i = 0; i < t.length(); i++) {
            for (int j = i; j > i - 5; j--) {
                if (j < 0) break;

                String cand = t.substring(j, i + 1);
                if (!set.contains(cand)) continue;

                dp[i] = (j == 0) ? 1 : Math.min(dp[i], dp[j - 1] + 1);
            }
        }

        return dp[t.length() - 1] == Integer.MAX_VALUE ? -1 : (int)dp[t.length() - 1];
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        String[] strs = {"ba","na","n","a"};
        String t = "banana";

        System.out.println(s.solution(strs, t));
    }
}
