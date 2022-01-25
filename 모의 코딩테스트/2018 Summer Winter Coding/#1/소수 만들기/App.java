class Solution {
    int answer = 0;
    int[] nums;

    public int solution(int[] nums) {
        this.nums = nums.clone();
        
        dfs(0, -1, 0);

        return answer;
    }

    void dfs(int k, int pre, int sum) {
        if (k == 3) {
            if (isPrime(sum)) answer++;
            return;
        }

        for (int i = pre + 1; i < nums.length; i++) {
            dfs(k + 1, i, sum + nums[i]);
        }
    }

    boolean isPrime(int num) {
        if (num <= 1) return false;

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();
        int[] nums = {1,2,3,4};
        System.out.println(s.solution(nums));
    }
}
