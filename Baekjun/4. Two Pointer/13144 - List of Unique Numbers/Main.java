import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[] nums;
    static boolean[] isExist = new boolean[100000 + 1];

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        nums = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        int left, right = 1;
        long count = 0;

        isExist[nums[0]] = true;
        for (left = 0; left < n; left++) {
            if (left > 0) {
                isExist[nums[left - 1]] = false;
            }
            while (right < n) {
                if (isExist[nums[right]]) {
                    break;
                }
                else {
                    isExist[nums[right]] = true;
                    right++;
                }
            }
            count += (long)right - left;
        }
        System.out.println(count);
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }
}
