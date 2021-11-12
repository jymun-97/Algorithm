import java.util.*;
import java.io.*;

public class Main {
    static int n, k;
    static int[] nums;
    static ArrayList<Integer> a = new ArrayList<>();

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        nums = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) nums[i] = Integer.parseInt(st.nextToken());
    }

    static void pre() {
        for (int i = 1; i <= n; i++) {
            if (nums[i] % 2 == 1) continue;

            int sum = 0;
            while (i <= n && nums[i] % 2 == 0) {
                sum++;
                i++;
            }
            a.add(sum);
        }
    }

    static void solve() {
        pre();
        if (a.size() <= k + 1) {
            int ans = 0;
            for (int num : a) ans += num;

            System.out.println(ans);
            return;
        }

        int max = 0;
        for (int i = 0; i <= k; i++) max += a.get(i);

        int left = 1, right = k + 1;
        int sum = max - a.get(0);
        while (right < a.size()) {
            sum += a.get(right);

            if (sum > max) max = sum;
            sum -= a.get(left++);
            right++;
        }

        System.out.println(max);
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}