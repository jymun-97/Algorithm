import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_2470 {
    static int N, ans1, ans2;
    static int[] array;
    static int min = Integer.MAX_VALUE;
    static StringBuilder sb = new StringBuilder();

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        array = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(array);
    }


    static void find() {
        int left = 0;
        int right = N - 1;

        while (left < right) {
            int sum = array[left] + array[right];

            if (Math.abs(sum) < Math.abs(min)) {
                min = sum;
                updateAnswer(left, right);
            }

            if (sum < 0) left++;
            else if (sum > 0) right--;
            else break;
        }
    }

    static void updateAnswer(int a1, int a2) {
        ans1 = array[a1];
        ans2 = array[a2];
    }

    public static void main(String[] args) throws Exception {
        input();
        find();
        sb.append(ans1).append(" ").append(ans2);
        System.out.println(sb);
    }
}
