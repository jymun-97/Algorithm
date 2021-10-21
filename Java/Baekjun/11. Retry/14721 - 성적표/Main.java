import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[] arrx, arry;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arrx = new int[n];
        arry = new int[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arrx[i] = Integer.parseInt(st.nextToken());
            arry[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        long min = Long.MAX_VALUE;
        int[] ans = new int[2];

        for (int a = 1; a <= 100; a++) {
            for (int b = 1; b <= 100; b++) {
                long cand = rss(a, b);
                if (cand < min) {
                    min = cand;
                    ans[0] = a;
                    ans[1] = b;
                    
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(ans[0]).append(' ').append(ans[1]);
        
        System.out.println(sb.toString());
    }

    static long rss(int a, int b) {
        long result = 0;
        for (int i = 0; i < n; i++) {
            int x = arrx[i];
            int y = arry[i];
            result += (y*y - 2*a*x*y - 2*b*y + a*a*x*x + 2*a*b*x + b*b);
        }

        return result < 0 ? Long.MAX_VALUE : result;
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}