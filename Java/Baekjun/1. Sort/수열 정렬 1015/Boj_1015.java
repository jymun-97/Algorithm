import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_1015 {
    static class Elem implements Comparable<Elem> {
        int value, index;

        public Elem(int value, int index) {
            this.value = value;
            this.index = index;
        }

        public int compareTo(Elem other) {
            return value - other.value;
        }
    }

    static int N;
    static Elem[] arr;
    static StringBuilder sb = new StringBuilder();

    static void input() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(reader.readLine());
        arr = new Elem[N];
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");

        for (int i = 0; i < N; i++) {
            arr[i] = new Elem(Integer.parseInt(st.nextToken()), i);
        }

        Arrays.sort(arr);
    }

    static void printResult() {
        int[] P = new int[N];

        for (int i = 0; i < N; i++) {
            P[arr[i].index] = i;
        }

        for (int p : P) {
            sb.append(p).append("\n");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        printResult();
    }
}
