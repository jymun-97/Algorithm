import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Solution {

    public int solution(int n, int[][] edge) {
        int answer = 1;

        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        int[] dist = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = -1;
        }

        for (int[] e : edge) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        Queue<Integer> que = new LinkedList<>();
        que.add(1);
        int max = dist[1] = 0;

        while (!que.isEmpty()) {
            int from = que.poll();

            for (int to : graph[from]) {
                if (dist[to] == -1) {
                    que.add(to);
                    dist[to] = dist[from] + 1;
                    
                    if (dist[to] == max) answer++;
                    if (dist[to] > max) {
                        max = dist[to];
                        answer = 1;
                    }
                }
            }
        }

        return answer;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        int n = 6;
        int[][] edge = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};

        System.out.println(s.solution(n, edge));
    }
}
