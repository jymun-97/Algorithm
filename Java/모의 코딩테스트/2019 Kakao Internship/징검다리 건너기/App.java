import java.util.Arrays;

class Solution {

    public int solution(int[] stones, int k) {
        int answer = 0;

        long left, right;
        left = right = 0;
        for (int stone : stones) if (right < stone) right = stone;

        while (left <= right) {
            long mid = (left + right) / 2;

            if (isPossible(stones, k, mid)) {
                answer = (int)mid;
                left = mid + 1;
            }
            else right = mid - 1;
        }

        return answer;
    }

    boolean isPossible(int[] stones, int k, long n) {
        int[] arr = stones.clone();
        for (int i = 0; i < arr.length; i++) arr[i] -= n - 1;

        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) count = 0;
            else {
                count++;
                if (count >= k) return false;
            }
        }

        return true;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        int[] stones = {2,4,5,3,2,1,4,2,5,1};

        System.out.println(s.solution(stones, 3));
    }
}
