import java.util.*;

class Info {
    int idx, dist;
    public Info(int i, int d) {
        idx = i;
        dist = d;
    }
}
class Edge {
    int to, weight;
    public Edge(int t, int w) {
        to = t;
        weight = w;
    }
}
class Solution {

    ArrayList<Edge>[] graph;
    int[][] dist;
    int n, s, a, b;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;

        this.n = n;
        this.s = s;
        this.a = a;
        this.b = b;        
        dist = new int[n + 1][n + 1];
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int[] fare : fares) {
            int from = fare[0];
            int to = fare[1];
            int weight = fare[2];

            graph[from].add(new Edge(to, weight));
            graph[to].add(new Edge(from, weight));
        }

        for (int i = 1; i <= n; i++) dijkstra(i);

        for (int point = 1; point <= n; point++) {
            int pay = dist[s][point] + dist[point][a] + dist[point][b];
            answer = Math.min(answer, pay);
        }

        return answer;
    }

    void dijkstra(int start) {
        for (int i = 1; i <= n; i++) dist[start][i] = Integer.MAX_VALUE;
        boolean[][] visit = new boolean[n + 1][n + 1];

        PriorityQueue<Info> que = new PriorityQueue<>((o1, o2) -> o1.dist - o2.dist);
        dist[start][start] = 0;
        que.add(new Info(start, dist[start][start]));
        visit[start][start] = true;

        while (!que.isEmpty()) {
            Info info = que.poll();

            if (dist[start][info.idx] < info.dist) continue;

            for (Edge e : graph[info.idx]) {
                if (!visit[e.to][info.idx] && dist[start][e.to] > dist[start][info.idx] + e.weight) {
                    dist[start][e.to] = dist[start][info.idx] + e.weight;
                    que.add(new Info(e.to, dist[start][e.to]));
                    visit[info.idx][e.to] = true;
                }
            }
        }
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution sol = new Solution();

        int n = 6;
        int s = 4;
        int a = 6;
        int b = 2;
        int[][] fares = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};

        System.out.println(sol.solution(n, s, a, b, fares));
    }
}
