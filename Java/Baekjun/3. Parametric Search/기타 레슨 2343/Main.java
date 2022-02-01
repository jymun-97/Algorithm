import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] lectures;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        lectures = new int[N];
        
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            lectures[i] = Integer.parseInt(st.nextToken());
        }
    }

    static boolean isValuable(int size) {
        int count = 0;
        int sum = 0;

        for (int i = 0; i < N; i++) {
            if (sum + lectures[i] >= size) {
                count++;
                sum = lectures[i];
            }
            else {
                sum += lectures[i];
            }
        }
        return count >= M;
    }

    static void solve() {
        int left = lectures[0];
        for (int lecture : lectures) {
            if (left < lecture) left = lecture;
        }
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
    public static void main(String[] args) throws Exception {
        input();
        solve();
    }
}
