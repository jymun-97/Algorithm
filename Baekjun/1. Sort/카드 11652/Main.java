import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static long[] values;
    static StringBuilder sb = new StringBuilder();

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        values = new long[N];

        for (int i = 0; i < N; i++) {
            values[i] = Long.parseLong(br.readLine());
        }
        Arrays.sort(values);
    }

    static void count() {
        long answer = values[0];
        int count = 1;
        int maxCount = 1;

        for (int i = 1; i < N; i++) {
            count = (values[i] == values[i - 1]) ? count + 1 : 1;

            if (maxCount < count) {
                answer = values[i];
                maxCount = count;
            }
        }
        System.out.println(answer);
    }

    public static void main(String[] args) throws Exception {
        input();
        count();
    }
}
