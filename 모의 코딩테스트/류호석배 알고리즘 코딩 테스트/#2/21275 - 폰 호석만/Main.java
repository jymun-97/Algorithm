import java.util.*;
import java.io.*;

public class Main {
    static String a, b;
    static int A, B, aMin, bMin;
    static long X;
    static long[] candA = new long[37], candB = new long[37];

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        a = st.nextToken();
        b = st.nextToken();

        char tempA = '1', tempB = '1';
        for (int i = 0; i < a.length(); i++) if (a.charAt(i) > tempA) tempA = a.charAt(i);
        for (int i = 0; i < b.length(); i++) if (b.charAt(i) > tempB) tempB = b.charAt(i);

        aMin = toInt(tempA) + 1;
        bMin = toInt(tempB) + 1;
    }

    static void solve() {
        for (int i = 2; i <= 36; i++) {
            if (i >= aMin) candA[i] = toDigit(a, i);
            if (i >= bMin) candB[i] = toDigit(b, i);
        }
        
        int count = 0;
        for (int i = aMin; i <= 36; i++) {
            for (int j = bMin; j <= 36; j++) {
                if (candA[i] == candB[j]) {
                    count++;
                    X = candA[i];
                    A = i;
                    B = j;
                    if (A == B) count--;
                }
            }
        }
        if (count == 0) System.out.println("Impossible");
        else if (count == 1) System.out.println(X + " " + A + " " + B);
        else System.out.println("Multiple");
    }

    static long toDigit(String str, int demi) {
        long result = 0;
        for (int i = 0; i < str.length(); i++) {
            int num = toInt(str.charAt(i));
            result += num * (long)Math.pow(demi, str.length() - i - 1);
        }
        return result;
    }

    static int toInt(char c) {
        if (c >= '0' && c <= '9') {
            return c - 48;
        }
        else {
            return c - 87;
        }
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}