import java.util.StringTokenizer;

class Solution {

    public int solution(int n, int k) {
        int answer = 0;

        StringTokenizer st = new StringTokenizer(convert(n, k), "0");
        while (st.hasMoreTokens()) {
            long target = Long.parseLong(st.nextToken());
            if (isPrime(target)) answer++;
        }

        return answer;
    }

    String convert(int n, int k) {
        String result = "";
        while (n > 0) {
            result = n % k + result;
            n /= k;
        }
        return result;
    }

    boolean isPrime(long n) {
        if (n == 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();
        System.out.println(s.solution(437674, 3));
    }
}
