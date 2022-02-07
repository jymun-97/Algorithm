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
        int count = 0;
        for (int i = 0; i < n; i++) {
            int target = nums[i];

            int left = 0;
            int right = n - 1;

            while (left < right) {
                if (left == i) {
                    left++; 
                    continue;
                }
                if (right == i) {
                    right--;
                    continue;
                }

                int sum = nums[left] + nums[right];
                if (sum > target) right--;
                else if (sum < target) left++;
                else {
                    count++;
                    break;
                }
            }
        }
        System.out.println(count);
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }
}
