import java.util.*;
import java.io.*;

public class BOJ_2981 {

    static int n;
    static int[] nums;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        nums = new int[n];

        for (int i = 0; i < n; i++) nums[i] = Integer.parseInt(br.readLine());
    }

    static void solve() {
        Arrays.sort(nums);

        int target = nums[1] - nums[0];
        for (int i = 2; i < n; i++) {
            target = gcd(target, nums[i] - nums[i - 1]);
        }

        ArrayList<Integer> list = new ArrayList<>();
        list.add(target);
        for (int i = 2; i <= Math.sqrt(target); i++) {
            if (target % i == 0) {
                list.add(i);
                
                if (i < target / i) list.add(target / i);
            }
        }

        Collections.sort(list);
        StringBuilder sb = new StringBuilder();

        for (int i : list) sb.append(i).append(' ');
        System.out.println(sb);
    }

    static int gcd(int a, int b) {
        while (b != 0) {
            int mod = a % b;
            a = b;
            b = mod;
        }
        return a;
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}