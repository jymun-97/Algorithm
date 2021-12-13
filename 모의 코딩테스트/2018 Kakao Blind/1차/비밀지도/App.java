import java.util.Arrays;

class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];

        for (int i = 0; i < n; i++) {
            answer[i] = "";
            long num1 = Long.parseLong(Integer.toBinaryString(arr1[i]));
            long num2 = Long.parseLong(Integer.toBinaryString(arr2[i]));

            String line = Long.toString(num1 + num2);
            String temp = "";
            for (int j = 0; j < n - line.length(); j++) temp += "0";

            line = temp + line;

            for (char c : line.toCharArray()) {
                if (c == '0') answer[i] += " ";
                else answer[i] += "#";
            }
        }

        return answer;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        int n = 6;
        int[] arr1 = {46, 33, 33 ,22, 31, 50};
        int[] arr2 = {27 ,56, 19, 14, 14, 10};

        System.out.println(Arrays.toString(s.solution(n, arr1, arr2)));
    }
}
