class Solution {
    public int solution(int n, int a, int b)
    {
        int answer = 1;
        
        while (Math.abs(a / 2 - b / 2) != 1 || Math.abs(a - b) != 1) {
            a = (a + 1) / 2;
            b = (b + 1) / 2;

            answer++;
        }

        return answer;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        System.out.println(s.solution(8, 2, 3));
    }
}
