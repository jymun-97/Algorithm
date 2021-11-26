import java.util.ArrayList;
import java.util.Collections;

class Solution {

    public String solution(int[] numbers) {
        String answer = "";

        ArrayList<String> list = new ArrayList<>();
        for (int num : numbers) {
            list.add(Integer.toString(num));
        }
        Collections.sort(list, (s1, s2) -> (s2 + s1).compareTo(s1 + s2));

        for (String num : list) answer += num;

        if (answer.charAt(0) == '0') return "0";

        return answer;
    }
}
public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();
        int[] numbers = new int[] {10,101010,0,2};

        System.out.println(s.solution(numbers));
    }
}
