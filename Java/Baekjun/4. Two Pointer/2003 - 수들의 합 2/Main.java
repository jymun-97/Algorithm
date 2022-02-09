import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[] nums;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        nums = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        int left, right = 0;
        int sum = 0;
        int count = 0;

        for (left = 0; left < n; left++) {
            if (left != 0) sum -= nums[left - 1];
            while (right < n && sum < m) 
                sum += nums[right++];

            if (sum == m) count++;
        }

        System.out.println(count);
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }
}
