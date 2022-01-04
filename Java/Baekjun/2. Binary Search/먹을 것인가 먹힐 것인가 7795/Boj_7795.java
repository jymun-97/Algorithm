import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_7795 {
    static int T, N, M;
    static int[] A, B;
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new int[N];
        B = new int[M];

        st = new StringTokenizer(br.readLine(), " ");
        for (int idx = 0; idx < N; idx++) {
            A[idx] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int idx = 0; idx < M; idx++) {
            B[idx] = Integer.parseInt(st.nextToken());
        }
    }
    static void sort() {
        Arrays.sort(B);
    }
    static int binarySearch(int left, int right, int target) {
        int result = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (B[mid] < target) {
                left = ++mid;
                result = mid;
            } else {
                right = --mid;
            }
        }
        return result;
    }
    static void solve() {
        sort();
        int answer = 0;

        for (int target : A) {
            answer += binarySearch(0, B.length - 1, target);
        }
        System.out.println(answer);
    }

    public static void main(String[] args) throws Exception {
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            input();
            solve();
        }
    }
}
