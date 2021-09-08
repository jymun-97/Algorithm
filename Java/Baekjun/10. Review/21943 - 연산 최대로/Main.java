import java.util.*;
import java.io.*;

public class Main {

    static int n, p, q, ans = Integer.MIN_VALUE;
    static int[] nums;
    static ArrayList<Integer>[] group;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        nums = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) nums[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        p = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        group = new ArrayList[q + 1];
        for (int i = 0; i <= q; i++) group[i] = new ArrayList<>();
    }

    static void solve() {
        insert(0);
        System.out.println(ans);
    }

    static void insert(int idx) {
        if (idx == n) {
            for (int i = 0; i <= q; i++) {
                if (group[i].size() == 0) return;
            }
            calc();
            return;
        }
        
        for (int i = 0; i <= q; i++) {
            group[i].add(nums[idx]);
            insert(idx + 1);
            group[i].remove(group[i].indexOf(nums[idx]));
        }
    }

    static void calc() {
        int result = 1;
        for (int i = 0; i <= q; i++) {
            int sum = 0;
            for (int num : group[i]) sum += num;
            result *= sum;
        }
        if (ans < result) ans = result;
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}