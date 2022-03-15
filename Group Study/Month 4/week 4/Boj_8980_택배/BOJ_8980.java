import java.util.*;
import java.io.*;

public class BOJ_8980 {
    static class Info implements Comparable<Info> {
        int from, to, amount;

        public Info(int from, int to, int amount) {
            this.from = from;
            this.to = to;
            this.amount = amount;
        }

        @Override
        public int compareTo(Info other) {
            if (to == other.to) return from - other.from;
            return to - other.to;
        }
    }
    static int n, max;
    static int[] amountAt;
    static ArrayList<Info> infos = new ArrayList<Info>();

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        max = Integer.parseInt(st.nextToken());
        amountAt = new int[n + 1];

        int m = Integer.parseInt(br.readLine());
        while (m --> 0) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int amount = Integer.parseInt(st.nextToken());

            infos.add(new Info(from, to, amount));
        }
    }

    static void solve() {
        Collections.sort(infos);

        int answer = 0;
        for (Info info : infos) {
            int limit = getLimit(info);

            info.amount = limit;
            answer += limit;
            load(info);
        }
        System.out.println(answer);
    }

    static int getLimit(Info info) {
        int target = 0;
        for (int i = info.from; i < info.to; i++) {
            if (amountAt[i] == max) return 0;
            target = Math.max(target, amountAt[i]);
        }
        return Math.min(info.amount, max - target);
    }   

    static void load(Info info) {
        for (int i = info.from; i < info.to; i++) {
            amountAt[i] += info.amount;
        }
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}