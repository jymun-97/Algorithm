import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Boj_20291 {
    static int n;
    static ArrayList<String> files = new ArrayList<>();

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), ".");
            st.nextToken();
            files.add(st.nextToken());
        }

        Collections.sort(files);
    }

    static void solve() {
        StringBuilder sb = new StringBuilder();
        String preFile = files.get(0);
        int count = 1;

        for (int i = 1; i < n; i++) {
            String file = files.get(i);
            if (preFile.equals(file)) {
                count++;
            }
            else {
                sb.append(preFile).append(' ').append(count).append('\n');
                count = 1;
            }
            preFile = file;
        }
        sb.append(preFile).append(' ').append(count).append('\n');
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }
}
