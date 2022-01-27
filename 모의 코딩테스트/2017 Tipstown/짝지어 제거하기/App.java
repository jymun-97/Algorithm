import java.util.Stack;

class Solution

{
    public int solution(String s)
    {
        if (s.length() % 2 == 1) return 0;

        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == c) {
                stack.pop();
                continue;
            }
            stack.push(c);
        }

        return (stack.isEmpty()) ? 1 : 0;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        System.out.println(s.solution("ababbaba"));
    }
}
