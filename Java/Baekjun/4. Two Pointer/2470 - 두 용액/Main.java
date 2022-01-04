import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[] nums;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        nums = new int[n];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);
    }

    static void solve() {
        int left = 0, right = n - 1;
        int min = Integer.MAX_VALUE;
        int ans1 = nums[left], ans2 = nums[right];

        while (left < right) {
            int sum = nums[left] + nums[right];
            
            if (Math.abs(sum) < min) {
                min = Math.abs(sum);
                ans1 = nums[left];
                ans2 = nums[right];
            }

            if (sum < 0) {
                left++;
            }
            else if (sum > 0) {
                right--;
            }
            else {
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(ans1).append(" ").append(ans2);
        System.out.println(sb);
    }
    public static void main(String[] args) throws Exception {
        input();
        solve();
    }
}
