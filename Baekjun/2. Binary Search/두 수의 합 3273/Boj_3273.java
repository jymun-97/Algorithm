import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Boj_3273 {
    static int N, sum, count;
    static HashMap<Integer, Integer> hashMap = new HashMap<>();
    static int[] nums;

    
    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            hashMap.put(num, i);
            nums[i] = num;
        }

        sum = Integer.parseInt(br.readLine());
    }
    static void solve() {
        for (int i = 0; i < N; i++) {
            int target = sum - nums[i];
            if (hashMap.containsKey(target)) {
                if (i < hashMap.get(target)) count++;
            }
        }
        System.out.println(count);
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }
}
