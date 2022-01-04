import java.util.*;

class Solution {

    public long solution(String expression) {
        long answer = 0;

        ArrayList<Long> numbers = new ArrayList<>();
        ArrayList<Character> operations = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(expression.replaceAll("[^0-9]", " "));
        while (st.hasMoreTokens()) {
            numbers.add(Long.parseLong(st.nextToken()));
        }
        for (char op : expression.replaceAll("[0-9]", "").toCharArray()) {
            operations.add(op);
        }

        String[] orders = { "+-*", "+*-", "-*+", "-+*", "*-+", "*+-" };
        for (String order : orders) {
            ArrayList<Long> nums = (ArrayList<Long>)numbers.clone();
            ArrayList<Character> opers = (ArrayList<Character>)operations.clone();

            for (char oper : order.toCharArray()) {
                while (opers.contains(oper)) {
                    int index = opers.indexOf(oper);

                    long left = nums.get(index);
                    long right = nums.get(index + 1);
                    long result = calc(oper, left, right);

                    opers.remove(index);
                    nums.remove(index);
                    nums.remove(index);
                    nums.add(index, result);
                }
            }

            answer = Math.max(answer, Math.abs(nums.get(0)));
        }

        return answer;
    }

    long calc(char oper, long left, long right) {
        switch (oper) {
            case '+': return left + right;
            case '-': return left - right;
            default: return left * right;
        }
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        String expression = "100-200*300-500+20";

        System.out.println(s.solution(expression));

    }
}
