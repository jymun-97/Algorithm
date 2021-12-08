import java.util.*;

class Solution {

    HashMap<String, Integer> map = new HashMap<>();
    String[] airports;
    ArrayList<Integer>[] graph;
    int[][] visit;
    int n, nTickets, start;
    boolean find = false;
    Stack<String> route = new Stack<>();

    public String[] solution(String[][] tickets) {
        TreeSet<String> set = new TreeSet<>();
        for (String[] ticket : tickets) {
            set.add(ticket[0]);
            set.add(ticket[1]);
        }
        n = set.size();
        
        nTickets = tickets.length;
        visit = new int[n][n];
        graph = new ArrayList[n];
        airports = set.toArray(new String[n]);
        set.clear();
        
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            String airport = airports[i];
            map.put(airport, i);
            if (airport.equals("ICN")) start = i;
        }

        for (String[] ticket : tickets) {
            int from = map.get(ticket[0]);
            int to = map.get(ticket[1]);
            graph[from].add(to);
        }

        for (String[] ticket : tickets) {
            visit[map.get(ticket[0])][map.get(ticket[1])]++;
        }

        for (int i = 0; i < n; i++) Collections.sort(graph[i]);

        route.push(airports[start]);
        dfs(start, 0);
        return route.toArray(new String[n]);
    }

    void dfs(int from, int count) {
        if (count == nTickets) {
            find = true;   
            return;
        }

        for (int to : graph[from]) {
            if (visit[from][to] > 0) {
                route.push(airports[to]);
                visit[from][to]--;
                
                dfs(to, count + 1);
                if (find) return;

                route.pop();
                visit[from][to]++;
            }
        }
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        String[][] tickets = {{"ICN", "BOO"}, {"ICN", "COO"}, {"COO", "DOO"}, {"DOO", "COO"}, {"BOO", "DOO"}, {"DOO", "BOO"}, {"BOO", "ICN"}, {"COO", "BOO"}};

        System.out.println(Arrays.toString(s.solution(tickets)));
    }
}
