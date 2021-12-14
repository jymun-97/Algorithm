import java.util.*;

class Solution {

    int n, answer = 0;
    int[] info;
    ArrayList<Integer>[] graph;

    public int solution(int[] info, int[][] edges) {
        n = info.length;
        this.info = info;

        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            
            graph[from].add(to);
        }

        dfs(0, 0, 0, new ArrayList<>(0));
        return answer;
    }

    void dfs(int from, int nSheep, int nWolf, ArrayList<Integer> list) {
        nSheep += 1 - info[from];
        nWolf += info[from];
        if (nSheep <= nWolf) return;

        answer = Math.max(answer, nSheep);

        ArrayList<Integer> next = new ArrayList<>();
        next.addAll(list);

        for (int to : graph[from]) next.add(to);
        next.remove(Integer.valueOf(from));

        for (int to : next) dfs(to, nSheep, nWolf, next);
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        int[] info = {0,1,0,1,1,0,1,0,0,1,0};
        int[][] edges = {{0,1},{0,2},{1,3},{1,4},{2,5},{2,6},{3,7},{4,8},{6,9},{9,10}};

        System.out.println(s.solution(info, edges));
    }
}
