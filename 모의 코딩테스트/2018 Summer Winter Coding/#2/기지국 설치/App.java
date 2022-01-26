class Solution {

    public int solution(int n, int[] stations, int w) {
        int answer = 0;

        int range = 2 * w + 1;
        int start = 1;
        for (int loc : stations) {
            int end = loc - w;
            
            if (start < end) {
                answer += (end - start) / range;
                if ((end - start) % range != 0) answer++;
            }
            start = loc + w + 1;
        }

        if (start <= n) {
            answer += (n - start + 1) / range;
            if ((n - start + 1) % range != 0) answer++;
        }

        return answer;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        int n = 6;
        int[] stations = {1};
        int w = 1;

        System.out.println(s.solution(n, stations, w));
    }
}
