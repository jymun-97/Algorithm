import java.util.*;
class Solution {
    int[] parentOf;
    public int solution(int n, int[][] costs) {
        int answer = 0;
        parentOf = new int[n];
        for (int i = 0; i < n; i++) parentOf[i] = i;

        Arrays.sort(costs, ((a1, a2) -> { return a1[2] - a2[2]; }));
        for (int i = 0; i < costs.length; i++) {
            int from = costs[i][0];
            int to = costs[i][1];
            int weight = costs[i][2];

            if (find(from) == find(to)) continue;
            answer += weight;
            union(from, to);
        }
        return answer;
    }

    int find(int node) {
        if (parentOf[node] == node) return node;
        return find(parentOf[node]);
    }

    void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a < b) parentOf[b] = a;
        else parentOf[a] = b;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();
        int n = 6;
        int[][] costs = {{0,1,40}, {0,5,30}, {5,1,20}, {4,0,60}, {4,2,70}, {5,3,80}, {1,2,100}};

        System.out.println(s.solution(n, costs));
    }
}
