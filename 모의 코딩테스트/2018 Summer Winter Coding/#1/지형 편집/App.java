class Solution {

    int n, m, p, q;
    int[][] land;
    public long solution(int[][] land, int P, int Q) {
        this.land = land;
        p = P;
        q = Q;
        n = land.length;
        m = land[0].length;
        
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                min = Math.min(min, land[i][j]);
                max = Math.max(max, land[i][j]);
            }
        }

        long answer = 0;
        while (min <= max) {
            int mid = (min + max) / 2;

            long costL = calc(mid);
            long costR = calc(mid + 1);

            if (costL < costR) {
                answer = costL;
                max = mid - 1;
            }
            else {
                answer = costR;
                min = mid + 1;
            }
        }
        return answer;
    }

    long calc(int h) {
        long cost = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                long temp = land[i][j] - h;
                if (temp > 0) cost += temp * q;
                else cost -= temp * p;
            }
        }
        return cost;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        
    }
}
