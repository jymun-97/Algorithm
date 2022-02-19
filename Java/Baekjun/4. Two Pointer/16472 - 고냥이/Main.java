import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n, kind;
    static String str;
    static int[] count = new int[26];

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        str = br.readLine();
    }

    static void solve() {
        int left = 0, right = 0;
        int max = 0;

        while (right < str.length()) {
            add(str.charAt(right));

            while (kind > n) {
                remove(str.charAt(left));
                left++;
            }

            max = Integer.max(max, right - left + 1);
            right++;
        }

        System.out.println(max);
    }

    static void add(char c) {
        count[c - 'a']++;
        if (count[c - 'a'] == 1) 
            kind++;
    }
    static void remove(char c) {
        count[c - 'a']--;
        if (count[c - 'a'] == 0)
            kind--;
    }
    public static void main(String[] args) throws Exception {
        input();
        solve();
    }
}
