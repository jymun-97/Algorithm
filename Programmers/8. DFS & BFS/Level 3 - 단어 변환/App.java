import java.util.*;

class Solution {

    HashSet<Integer>[] graph;
    String[] words;
    boolean[] visit;
    int n, target = -1, answer = Integer.MAX_VALUE;

    public int solution(String begin, String target, String[] w) {
        n = w.length;
        words = new String[n + 1];
        graph = new HashSet[n + 1];
        visit = new boolean[n + 1];

        for (int i = 0; i <= n; i++) {
            graph[i] = new HashSet<>();
            words[i] = (i == 0) ? begin : w[i - 1];
        }
        for (int i = 0; i <= n; i++) {
            String from = words[i];
            if (from.equals(target)) this.target = i;

            for (int j = i + 1; j <= n; j++) {
                String to = words[j];
                if (isConnected(from, to)) {
                    graph[i].add(j);
                    graph[j].add(i);
                }
            }
        }
        if (this.target == -1) return 0;
        dfs(0, 0);
        
        return answer;
    }

    boolean isConnected(String from, String to) {
        int count = 0;
        for (int i = 0; i < from.length(); i++) {
            if (from.charAt(i) == to.charAt(i)) count++;
        }
        return count == from.length() - 1;
    }

    void dfs(int from, int move) {
        if (from == target) {
            answer = (answer > move) ? move : answer;
            return;
        }

        visit[from] = true;
        for (int to : graph[from]) {
            if (!visit[to]) {
                dfs(to, move + 1);
            }
        }
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();
        String begin = "hit";
        String target = "cog";
        String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};

        System.out.println(s.solution(begin, target, words));
    }
}
