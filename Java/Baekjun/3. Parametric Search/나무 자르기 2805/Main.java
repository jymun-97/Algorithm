import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[] heights;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        heights = new int[n];
        m = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }
    }

    static boolean isValuable(int height) {
        long sum = 0;

        for (int i = 0; i < n; i++) {
            if (heights[i] > height) {
                sum += heights[i] - height;
            }
        }
        return sum >= m;
    }

    static void find() {
        int answer = 0;
        int left = 0;
        int right = 2000000000;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (isValuable(mid)) {
                left = mid + 1;
                answer = mid;
            }
            else {
                right = mid - 1;
            }
        }
        System.out.println(answer);
    }

    public static void main(String[] args) throws Exception {
        input();
        find();
    }
}
