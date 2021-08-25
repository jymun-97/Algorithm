import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int n, target1, target2;
    static int[] parentOf;

    static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        parentOf = new int[n + 1];

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());

            parentOf[child] = parent;
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        target1 = Integer.parseInt(st.nextToken());
        target2 = Integer.parseInt(st.nextToken());
    }

    static void solve() {
        int temp1 = parentOf[target1], temp2 = parentOf[target2];
        int depthOftarget1 = 0, depthOftarget2 = 0;

        while (temp1 != 0 || temp2 != 0) {
            if (temp1 != 0) {
                depthOftarget1++;
                temp1 = parentOf[temp1];
            }
            if (temp2 != 0)  {
                depthOftarget2++; 
                temp2 = parentOf[temp2];
            }
        }

        int low, high, depth;
        if (depthOftarget1 < depthOftarget2) {
            low = target2;
            high = target1;
            depth = depthOftarget2 - depthOftarget1;
        }
        else {
            low = target1;
            high = target2;
            depth = depthOftarget1 - depthOftarget2;
        }

        int cand1 = high;
        int cand2 = low;
        for (int i = 0; i < depth; i++) {
            cand2 = parentOf[cand2];
        }

        while (cand1 != cand2) {
            cand1 = parentOf[cand1];
            cand2 = parentOf[cand2];
        }

        sb.append(cand1).append('\n');
    }

    public static void main(String[] args) throws Exception {
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            input();
            solve();
        }

        System.out.println(sb);
    }
}
