import java.util.*;
import java.io.*;

public class BOJ_1644 {

    static int n;
    static ArrayList<Integer> prime; 

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        prime = new ArrayList<>();
    }

    static void solve() {   
        init();
        
        int answer = 0;
        int size = prime.size();
        // two pointer로 시도하기
        for (int i = 0; i < size; i++) {
            int sum = 0;

            for (int j = i; j < size; j++) {
                sum += prime.get(j);

                if (sum > n) break;
                if (sum == n) {
                    answer++;
                    break;
                }
            }
        }
        System.out.println(answer);
    }

    static void init() {
        boolean[] erased = new boolean[n + 1];
        erased[0] = erased[1] = false;

        for (int i = 2; i * i <= n; i++) {
            if (!erased[i]) {
                for (int j = i * i; j <= n; j += i) {
                    erased[j] = true;
                } 
            }
        }
        
        for (int i = 2; i <= n; i++) {
            if (!erased[i]) prime.add(i);
        }
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}