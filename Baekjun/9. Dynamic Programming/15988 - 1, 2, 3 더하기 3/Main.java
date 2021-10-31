import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static long[] dy = new long[1000000 + 1];

    static void prepro() {
        dy[1] = 1;
        dy[2] = 2;
        dy[3] = 4;

        for (int i = 4; i <= 1000000; i++) {
            dy[i] = (dy[i-1] + dy[i-2] + dy[i-3]) % 1000000009L;
        }
    }
    
    public static void main(String[] args) throws Exception {
        prepro();
        int t = Integer.parseInt(br.readLine());
        while (t --> 0) {
            sb.append(dy[Integer.parseInt(br.readLine())]).append('\n');
        }
        System.out.println(sb);
    }
}