import java.util.*;

class Info {
    int idx, dist;
    int state;
    public Info(int i, int d, int s) {
        idx = i;
        dist = d;
        state = s;
    }
}
class Edge {
    int to, weight;
    boolean isReverse;
    public Edge(int t, int w, boolean reverse) {
        to = t;
        weight = w;
        isReverse = reverse;
    }
}

class Solution {

    HashSet<Edge>[] graph;
    HashMap<Integer, Integer> trapIndex = new HashMap<>();
    int[][] dist;
    int n, end, answer = Integer.MAX_VALUE;

    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        this.end = end;
        this.n = n;
        graph = new HashSet[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new HashSet<>();
        for (int i = 0; i < traps.length; i++) trapIndex.put(traps[i], i);

        dist = new int[(int)Math.pow(2, traps.length)][n + 1]; 
        for (int i = 0; i < dist.length; i++) {
            for (int j = 1; j <= n; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int[] road : roads) {
            int from = road[0];
            int to = road[1];
            int weight = road[2];

            graph[from].add(new Edge(to, weight, false));
            graph[to].add(new Edge(from, weight, true));
        }
        
        dijkstra(start);
        return answer;
    }

    void dijkstra(int start) {
        PriorityQueue<Info> que = new PriorityQueue<>((o1, o2) -> o1.dist - o2.dist);
        que.add(new Info(start, 0, 0));
        dist[0][start] = 0;

        while (!que.isEmpty()) {
            Info info = que.poll();
            if (dist[info.state][info.idx] < info.dist) continue;
            if (info.idx == end) {
                answer = Math.min(answer, info.dist);
                continue;
            }

            for (Edge e : graph[info.idx]) {
                boolean isReverse = isReverse(info.idx, e.to, info.state);
                if (isReverse ^ e.isReverse) continue;

                int state = info.state;
                if (trapIndex.containsKey(e.to)) state ^= (1 << trapIndex.get(e.to));

                if (dist[state][e.to] > dist[info.state][info.idx] + e.weight) {
                    dist[state][e.to] = dist[info.state][info.idx] + e.weight;
                    que.add(new Info(e.to, dist[state][e.to], state));
                }
            }
        }
    }

    boolean isReverse(int from, int to, int state) {
        if (!trapIndex.containsKey(from) && !trapIndex.containsKey(to)) return false;
        if (trapIndex.containsKey(from) && !trapIndex.containsKey(to)) return (state & (1 << trapIndex.get(from))) == (1 << trapIndex.get(from));
        if (!trapIndex.containsKey(from) && trapIndex.containsKey(to)) return (state & (1 << trapIndex.get(to))) == (1 << trapIndex.get(to));
        return (state & (1 << trapIndex.get(from))) == (1 << trapIndex.get(from)) ^ (state & (1 << trapIndex.get(to))) == (1 << trapIndex.get(to));
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        int n = 3;
        int start = 1;
        int end = 3;
        int[][] roads = {{1,2,2},{3,2,3}};
        int[] traps = {2};

        System.out.println(s.solution(n, start, end, roads, traps));
    }
}
