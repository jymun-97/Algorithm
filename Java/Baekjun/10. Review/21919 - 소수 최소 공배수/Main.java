import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Main {

    static int n;
    static int[] nums;
    static boolean[] visit = new boolean[1000001];
    static HashSet<Integer> prime = new HashSet<>();
    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        nums = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) nums[i] = Integer.parseInt(st.nextToken());

    }
    static void solve() {
        for (int num : nums) {
            if (isPrime(num)) {
                prime.add(num);
            }
        }
        if (prime.size() == 0) {
            System.out.println(-1);
            return;
        }

        long ans = 1;
        for (int p : prime) ans *= p;
        System.out.println(ans);
    }

    static boolean isPrime(int num) {
        if (visit[num]) return true;

        for (int i = 2; i <= Math.sqrt((double)num); i++) {
            if (num % i == 0) return false;
        }

        visit[num] = true;
        return true;
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}