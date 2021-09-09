import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        int n, k;
        int[] nums;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        nums = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) 
            nums[i] = Integer.parseInt(st.nextToken());

        int max = 0;
        for (int i = 0; i < k; i++) 
            max += nums[i];

        int left, right = k;
        int sum = max;
        for (left = 1; right < n; left++) {
            sum -= nums[left - 1];
            sum += nums[right++];

            max = (sum > max) ? sum : max; 
        }

        System.out.println(max);
    }
}
