class Solution {

    public String solution(int n, int t, int m, int p) {
        StringBuilder sb = new StringBuilder();

        int num = 0;
        int now = 1;
        p = p % m;
        while (sb.length() < t) {
            String conv = convert(n, num++);
            for (int i = 0; i < conv.length() && sb.length() < t; i++) {
                if ((now++) % m == p) sb.append(conv.charAt(i));
            }
        }
        
        return sb.toString();
    }

    String convert(int n, int num) {
        String result = "";

        if (num == 0) return "0";
        while (num > 0) {
            int mod = num % n;
            if (mod < 10) result = mod + result;
            else result = (char)('A' - 10 + mod) + result;
            num /= n;
        }
        return result;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        System.out.println(s.solution(16, 16, 2, 2));
    }
}
