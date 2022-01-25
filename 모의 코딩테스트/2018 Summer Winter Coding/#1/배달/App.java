import java.util.*;

class Info {
    int idx, dist;
    public Info(int idx, int dist) {
        this.idx = idx;
        this.dist = dist;
    }
}
class Solution {

    HashSet<Integer>[] graph;
    int[][] weight;
    int[] dist;
    int n, k;

    public int solution(int N, int[][] road, int K) {
        n = N;
        k = K;

        weight = new int[n + 1][n + 1];
        dist = new int[n + 1];
        graph = new HashSet[n + 1];

        for (int i = 1; i <= n; i++) {
            dist[i] = Integer.MAX_VALUE;
            graph[i] = new HashSet<>();
        }

        for (int[] r : road) {
            int from = r[0];
            int to = r[1];
            
            if (weight[from][to] != 0 && weight[from][to] <= r[2]) continue;
            weight[from][to] = weight[to][from] = r[2];

            graph[from].add(to);
            graph[to].add(from);
        }

        dijkstra();
        
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] <= k) answer++;
        }
        return answer;
    }

    void dijkstra() {
        PriorityQueue<Info> que = new PriorityQueue<>((o1, o2) -> o1.dist - o2.dist);
        que.add(new Info(1, 0));
        dist[1] = 0;

        while (!que.isEmpty()) {
            Info info = que.poll();
            if (dist[info.idx] < info.dist) continue;

            for (int to : graph[info.idx]) {
                if (dist[to] > dist[info.idx] + weight[info.idx][to]) {
                    dist[to] = dist[info.idx] + weight[info.idx][to];
                    que.add(new Info(to, dist[to]));
                }
            }
        }
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        int n = 5, k = 3;
        int[][] road = {{1,2,1},{2,3,3},{5,2,2},{1,4,2},{5,3,1},{5,4,2}};
        System.out.println(s.solution(n, road, k));
    }
}
