import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
        public static void main(String[] args) throws Exception {
        int n, k;
        ArrayList<Integer> targets = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            if (Integer.parseInt(st.nextToken()) == 1) {
                targets.add(i);
            }
        }

        int start = 0;
        int min = n + 1;
        while (start + k - 1 < targets.size()) {
            min = Integer.min(min, targets.get(start + k - 1) - targets.get(start) + 1);
            start++;
        }

        if (min == n + 1)
            min = -1;
        System.out.println(min);    
    }
}
