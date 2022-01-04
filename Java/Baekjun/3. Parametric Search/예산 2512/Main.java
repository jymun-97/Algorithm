import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, total;
    static int[] budgets;


    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        budgets = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            budgets[i] = Integer.parseInt(st.nextToken());
            total += budgets[i];
        }

        M = Integer.parseInt(br.readLine());
    }

    static boolean isValuable(int target) {
        int sum = 0;

        for (int budget : budgets) {
            if (budget < target)
                sum += budget;
            else
                sum += target;
        }
        return sum <= M;
    }

    static void solve() {
        if (preCheck()) {
            System.out.println(budgets[N - 1]);
            return;
        }
        
        int left = 1;
        int right = 1000000000;
        int answer = left;

        while (left <= right) {
            int mid = (left + right) / 2;
            
            if (isValuable(mid)) {
                answer = mid;
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        System.out.println(answer);
    }

    static boolean preCheck() {
        if (total <= M) {
            Arrays.sort(budgets);
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }
}
