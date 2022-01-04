import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static HashSet<String> set = new HashSet<>();
    static StringBuilder sb = new StringBuilder();

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        while (n --> 0) set.add(br.readLine());
        while (m --> 0) {
            st = new StringTokenizer(br.readLine(), ",");

            while (st.hasMoreTokens()) {
                String key = st.nextToken();
                set.remove(key);
            }

            sb.append(set.size()).append('\n');
        }
    }

    public static void main(String[] args) throws Exception {

        input();
        System.out.println(sb);
    }
}