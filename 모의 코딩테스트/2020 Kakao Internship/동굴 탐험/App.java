import java.util.*;

class Solution {

    HashSet<Integer>[] graph;
    int[] parentOf;

    public boolean solution(int n, int[][] path, int[][] order) {
        graph = new HashSet[n];
        parentOf = new int[n];
        
        for (int i = 0; i < n; i++) graph[i] = new HashSet<>();
        for (int[] p : path) {
            int from = p[0];
            int to = p[1];

            graph[from].add(to);
            graph[to].add(from);
        }
        bfs();

        for (int[] o : order) {
            int from = o[0];
            int to = o[1];

            graph[from].add(to);
            graph[parentOf[to]].remove(to);
        }

        HashSet<Integer> visit = new HashSet<>();
        Queue<Integer> que = new LinkedList<>();
        que.add(0);
        visit.add(0);

        while (!que.isEmpty()) {
            int from = que.poll();

            for (int to : graph[from]) {
                if (visit.contains(to)) return false;
                
                que.add(to);
                visit.add(to);
            }
        }

        return visit.size() == n;
    }

    void bfs() {
        Queue<Integer> que = new LinkedList<>();
        que.add(0);

        while (!que.isEmpty()) {
            int from = que.poll();

            for (int to : graph[from]) {
                graph[to].remove(from);
                parentOf[to] = from;
                que.add(to);
            }
        }
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        int n = 9;
        int[][] path = {{0,1},{0,3},{0,7},{8,1},{3,6},{1,2},{4,7},{7,5}};
        int[][] order = {{8,7},{6,5},{4,1}};

        System.out.println(s.solution(n, path, order));
    }
}
