import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_1920 {
    static int N, M;
    static int[] array, targets;
    static StringBuilder sb = new StringBuilder();
    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        array = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }
        
        M = Integer.parseInt(br.readLine());
        targets = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            targets[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(array);
    }

    static int isExist(int target) {
        int left = 0;
        int right = N - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (array[mid] < target) left = mid + 1;
            else if (array[mid] > target) right = mid - 1;
            else return 1;
        }
        return 0;
    }

    static void solve() {
        for (int target : targets) {
//            sb.append(isExist(target)).append("\n");
            if (Arrays.binarySearch(array, target) >= 0) sb.append(1).append("\n");
            else sb.append(0).append("\n");
        }

        System.out.println(sb);
    }

    static void print() {
        for (int a : array) System.out.print(a + " ");

        System.out.println();

        for (int target : targets) System.out.println(target + " ");
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
        print();
    }
}
