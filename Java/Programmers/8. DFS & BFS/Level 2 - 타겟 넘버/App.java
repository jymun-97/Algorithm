class Solution {

    int[] numbers;
    int target, n, answer = 0;
    
    public int solution(int[] numbers, int target) {
        this.numbers = numbers.clone();
        this.target = target;
        n = numbers.length;

        dfs(numbers[0], 1);
        dfs(-numbers[0], 1);

        return answer;
    }

    void dfs(int num, int idx) {
        if (idx == n) {
            answer = (num == target) ? answer + 1 : answer;
            return;
        }

        for (int i = 0; i < 2; i++) {
            int next = numbers[idx] * (int)Math.pow(-1, i);
            dfs(num + next, idx + 1);
        }
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();
        
        int[] numbers = {1,1,1,1,1};
        int target =3;
        
        System.out.println(s.solution(numbers, target));
    }
}
