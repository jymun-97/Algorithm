import java.util.*;

class Solution {

    ArrayList<ArrayList<Integer>> orders = new ArrayList<>();
    int nDist, nWeak, answer = Integer.MAX_VALUE;

    public int solution(int n, int[] weak, int[] dist) {
        if (weak.length == 1) return 1;

        ArrayList<ArrayList<Integer>> routes = new ArrayList<>();
        nDist = dist.length;

        nWeak = weak.length;
        for (int i = 0; i < nWeak; i++) {
            ArrayList<Integer> route = new ArrayList<>();
            for (int j = 0; j < nWeak; j++) route.add(weak[(i + j) % nWeak]);

            routes.add(route);
        }
        
        makeOrder(0, new ArrayList<Integer>(), dist);
        
        for (ArrayList<Integer> route : routes) {
            for (ArrayList<Integer> order : orders) {
                check(route, order);
            }
        }
        return (answer == Integer.MAX_VALUE) ? -1 : answer;
    }

    void makeOrder(int k, ArrayList<Integer> selected, int[] dist) {
        if (k == nDist) {
            ArrayList<Integer> order = new ArrayList<>();
            for (int i : selected) order.add(dist[i]);
            orders.add(order);
            return;
        }

        for (int i = 0; i < nDist; i++) {
            if (selected.contains(i)) continue;

            selected.add(i);
            makeOrder(k + 1, selected, dist);
            selected.remove(Integer.valueOf(i));
        }
    }

    void check(ArrayList<Integer> route, ArrayList<Integer> order) {
        int now = 0, next;
        int dist_idx = 0;
        while (dist_idx < nDist && now < route.size()) {
            next = now + 1;
            while (next < nWeak && route.get(next) - route.get(now) <= order.get(dist_idx)) {
                next++;
            }
            now = next;
            dist_idx++;

            if (now == nWeak && dist_idx < answer) answer = dist_idx;
        }
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        int n = 12;
        int[] weak = {1,5,6,10};
        int[] dist = {1,2,3,4};

        System.out.println(s.solution(n, weak, dist));
    }
}
