import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[] a;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        a = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) a[i] = Integer.parseInt(st.nextToken());
    
        while (m --> 0) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            switch (command) {
                case 1:
                    func1(x, y);
                    break;
                case 2:
                    func2(x, y);
                    break;
                case 3:
                    func3(x, y);
                    break;
                default:
                    func4(x, y);
                    break;
            }
        }
    }

    static void func1(int i, int x) {
        a[i] = x;
    }

    static void func2(int l, int r) {
        for (int i = l; i <= r; i++) {
            a[i] = 1 - a[i];
        }
    }

    static void func3(int l, int r) {
        for (int i = l; i <= r; i++) {
            a[i] = 0;
        }
    }

    static void func4(int l, int r) {
        for (int i = l; i <= r; i++) {
            a[i] = 1;
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= n; i++) sb.append(a[i]).append(' ');
        System.out.println(sb);
    }
}