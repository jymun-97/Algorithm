import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static int[] limits = new int[3];
    static final int A = 0, B = 1, C = 2;
    static boolean[][][] visit;
    static boolean[] check = new boolean[201];
    static ArrayList<Integer> list = new ArrayList<>();

    static class State {
        int a, b, c;
        int na, nb, nc;

        public State(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
            init();
        }

        public void init() {
            na = a; nb = b; nc = c;
        }
        public boolean isVisit() {
            return visit[a][b][c];
        }

        public State pour(int FROM, int from, int TO, int to) {
            init();
            if (from == 0) return null;
            if (from > limits[TO] - to) {
                from -= limits[TO] - to;
                to = limits[TO];
            } else {
                to += from;
                from = 0;
            }
            
            mapFrom(FROM, from);
            mapTo(TO, to);

            return new State(na, nb, nc);
        }

        private void mapFrom(int FROM, int from) {
            switch (FROM) {
                case 0: 
                    na = from;
                    break;
                case 1: 
                    nb = from;
                    break;
                case 2: 
                    nc = from;
                    break;
                default:
                    break;
            }
        }
        private void mapTo(int TO, int to) {
            switch (TO) {
                case 0: 
                    na = to;
                    break;
                case 1: 
                    nb = to;
                    break;
                case 2: 
                    nc = to;
                    break;
                default:
                    break;
            }
        }
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 3; i++) {
            limits[i] = Integer.parseInt(st.nextToken());
        }
        visit = new boolean[limits[A] + 1][limits[B] + 1][limits[C] + 1];
    }

    static void solve() {
        State firstState = new State(0, 0, limits[C]);
        dfs(firstState);

        Collections.sort(list);
        StringBuilder sb = new StringBuilder();
        
        for (int amount : list) {
            sb.append(amount).append(' ');
        }
        System.out.println(sb);
    }

    static void dfs(State state) {
        visit[state.a][state.b][state.c] = true;
        if (state.a == 0 && !check[state.c]) {
            list.add(state.c);
            check[state.c] = true;
        }

        State newState = state.pour(A, state.a, B, state.b);
        if (newState != null && !newState.isVisit()) dfs(newState);

        newState = state.pour(A, state.a, C, state.c);
        if (newState != null && !newState.isVisit()) dfs(newState);

        newState = state.pour(B, state.b, A, state.a);
        if (newState != null && !newState.isVisit()) dfs(newState);

        newState = state.pour(B, state.b, C, state.c);
        if (newState != null && !newState.isVisit()) dfs(newState);

        newState = state.pour(C, state.c, A, state.a);
        if (newState != null && !newState.isVisit()) dfs(newState);

        newState = state.pour(C, state.c, B, state.b);
        if (newState != null && !newState.isVisit()) dfs(newState);
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }
}
