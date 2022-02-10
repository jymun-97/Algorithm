import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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

        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(nums);
    }

    static void solve() {
        if (m == 0) {
            System.out.println(0);
            return;
        }
        int left = 0, right = 0;
        int min = Integer.MAX_VALUE;
        
        while (right < n && left < n) {
            int diff = nums[right] - nums[left];

            if (diff >= m) {
                min = Integer.min(min, diff);
                left++;
            }
            else {
                right++;
            }
        }
        System.out.println(min);
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();   
    }
}
