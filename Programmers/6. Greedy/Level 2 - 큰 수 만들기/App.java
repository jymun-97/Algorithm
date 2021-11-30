class Solution {
    public String solution(String number, int k) {

        StringBuilder answer = new StringBuilder(number);
        int left = 0, right = left + 1;
        while (k > 0) {
            while (right < number.length() && number.charAt(left) >= number.charAt(right)) right++;

            if (right - left <= k) {
                answer.replace(left, right, " ");
                k -= right - left;
                left = right;
                right++;
            }
            else {
                left++;
                right = left + 1;
            }
        }

        return answer.toString().replaceAll(" ", "");
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        String number = "4177252841";
        int k = 4;

        System.out.println(s.solution(number, k));
    }
}
