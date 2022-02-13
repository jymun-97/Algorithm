import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, x;
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
        x = Integer.parseInt(br.readLine());
    }

    static void solve() {
        int left = 0, right = n - 1;
        int count = 0;
        
        while (left < right) {
            int sum = nums[left] + nums[right];

            if (sum > x) {
                right--;
            }
            else if (sum < x) {
                left++;
            }
            else {
                count++;
                left++;
                right--;
            }
        }
        System.out.println(count);
    }
    public static void main(String[] args) throws Exception {
        input();
        solve();
    }
}
