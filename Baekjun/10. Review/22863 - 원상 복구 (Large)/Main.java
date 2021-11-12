import java.util.*;
import java.io.*;

class State {
    int[] arr;

    public State(int[] s) {
        arr = s.clone();  
    }

    @Override
    public boolean equals(Object o) {
        State other = (State)o;
        return Arrays.equals(arr, other.arr);
    }
    @Override
    public String toString() {
        String s = "";
        for (int i = 1; i < arr.length; i++) s += Integer.toString(arr[i]) + " ";
        return s;
    }
}
public class Main {

    static int n;
    static long k;

    static int[] d, s, ans;
    static boolean[] visit;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Long.parseLong(st.nextToken());

        d = new int[n + 1];
        s = new int[n + 1];
        ans = new int[n + 1];
        visit = new boolean[n + 1];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) s[i] = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) d[Integer.parseInt(st.nextToken())] = i;
    }

    static void solve() {
        for (int i = 1; i <= n; i++) {
            if (visit[i]) continue;

            int[] cycle = new int[n + 1];
            int next = i, size = 0;
            while (!visit[next]) {
                cycle[size++] = next;
                visit[next] = true;
                next = shuffle(next);
            }

            for (int idx = 0; idx < size; idx++) {
                int target = cycle[idx];
                ans[target] = s[cycle[(int)((idx + k) % size)]];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) sb.append(ans[i]).append(' ');
        System.out.println(sb);
    }

    static int shuffle(int idx) { return d[idx]; } 

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}