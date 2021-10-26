import java.util.*;
import java.io.*;

public class Main {

    static int n, m;
    static int[] nums;
    static boolean[] visit;
    static StringBuilder answer = new StringBuilder();
    static HashSet<String> exist = new HashSet<>();
    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        nums = new int[n];
        visit = new boolean[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) nums[i] = Integer.parseInt(st.nextToken());

    }

    static void solve() {
        Arrays.sort(nums);
        rec_func(0, "");
        System.out.println(answer.toString());
    }

    static void rec_func(int k, String selected) {
        if (k == m) {
            if (exist.contains(selected)) return;
            
            exist.add(selected);
            answer.append(selected).append('\n');
            return;
        }

        for (int i = 0; i < n; i++) {
            if (visit[i]) continue;

            visit[i] = true;
            rec_func(k + 1, selected + nums[i] + ' ');
            visit[i] = false;
        }
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}