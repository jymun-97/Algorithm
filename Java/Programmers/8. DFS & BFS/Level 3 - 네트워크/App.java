import java.util.ArrayList;

class Solution {

    ArrayList<Integer>[] graph;
    boolean[] visit;
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visit = new boolean[n];
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int from = i;
            for (int to = 0; to < n; to++) {
                if (computers[from][to] == 1 && from != to) {
                    graph[from].add(to);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                dfs(i);
                answer++;
            }
        }
        return answer;
    }

    public void dfs(int from) {
        visit[from] = true;
        for (int to : graph[from]) {
            if (!visit[to]) {
                dfs(to);
            }
        }
    }
}
