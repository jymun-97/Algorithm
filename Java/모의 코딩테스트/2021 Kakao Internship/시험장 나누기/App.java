import java.util.*;

class Solution {

    int[][] graph;
    int[] dp;
    int[] num, parentOf;
    int root, k;

    public int solution(int k, int[] num, int[][] links) {
        this.num = num;
        this.k = k;

        int n = num.length;
        graph = new int[n][2];
        dp = new int[n];
        parentOf = new int[n];
        
        for (int i = 0; i < n; i++) parentOf[i] = -1;
        for (int i = 0; i < n; i++) {
            int left = links[i][0];
            int right = links[i][1];

            graph[i][0] = left;
            graph[i][1] = right;

            if (left != -1) parentOf[left] = i;
            if (right != -1) parentOf[right] = i;
        }

        for (int i = 0; i < n; i++) {
            if (parentOf[i] == -1) {
                root = i;
                break;
            }
        }

        dfs(root);

        int left = 1, right = dp[root];  
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (getResult(root, mid) < k) {
                answer = mid;
                right = mid - 1;
            }
            else left = mid + 1;
        }

        return answer;
    }

    int dfs(int from) {

        dp[from] = num[from];
        for (int i = 0; i < 2; i++) {
            if (graph[from][i] != -1) dp[from] += dfs(graph[from][i]);
        }
        return dp[from];
    }

    long getResult(int node, int cand) {
        if (node == -1) return 0;
        if (num[node] > cand) return Integer.MAX_VALUE;
        
        int left = graph[node][0];
        int right = graph[node][1];
        
        long lr = getResult(left, cand);
        long rr = getResult(right, cand);
        
        if (dp[node] <= cand) return 0;

        return Integer.MAX_VALUE;
    }

    boolean isValuable(int from, int to, int cand) {
        return dp[from] - dp[to] >= cand;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        int k = 4;
        int[] num = {6,9,7,5};
        int[][] links = {{-1, -1}, {-1, -1}, {-1, 0}, {2, 1}};

        System.out.println(s.solution(k, num, links));
    }
}
