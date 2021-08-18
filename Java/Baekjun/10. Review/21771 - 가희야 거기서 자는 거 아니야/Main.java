import java.util.*;
import java.io.*;

public class Main {
    static int maxP, pCount;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        st.nextToken(); st.nextToken();
        maxP = Integer.parseInt(st.nextToken()) * Integer.parseInt(st.nextToken());

        for (int i = 0; i < row; i++) {
            String line = br.readLine();
            for (int j = 0; j < col; j++) {
                if (line.charAt(j) == 'P') pCount++;
            }
        }
    }

    static void solve() {  
        int result = 0; 
        if (pCount < maxP) result = 1;

        System.out.println(result);
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}