import java.util.*;
import java.io.*;

public class Main {
    static int n, x;
    static int[] nums;
    static ArrayList<Integer> targets = new ArrayList<>();

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        nums = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        x = Integer.parseInt(br.readLine());
    }

    static void solve() {
        for (int num : nums) {
            check(num);
        }
        double sum = 0;
        int count = targets.size();

        for (int target : targets) sum += target;
        System.out.println(sum / count);
    }

    static void check(int num) {
        int b = num;
        int a = x;
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        if (a == 1) targets.add(num);
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}