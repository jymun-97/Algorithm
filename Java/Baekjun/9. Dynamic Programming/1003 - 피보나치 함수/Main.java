import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int[][] dy = new int[41][2];
    static void preprocess() {
        dy[0][0] = 1;
        dy[0][1] = 0;
        dy[1][0] = 0;
        dy[1][1] = 1;

        for (int i = 2; i < 41; i++) {
            dy[i][0] = dy[i - 1][0] + dy[i - 2][0];
            dy[i][1] = dy[i - 1][1] + dy[i - 2][1];
        }
    }

    public static void main(String[] args) throws Exception {
        preprocess();
        int t = Integer.parseInt(br.readLine());

        while (t --> 0) {
            int n = Integer.parseInt(br.readLine());
            sb.append(dy[n][0]).append(' ').append(dy[n][1]).append('\n');
        }

        System.out.println(sb);
    }
}