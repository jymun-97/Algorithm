import java.util.ArrayList;
import java.util.HashMap;

class Solution {

    public int[] solution(String msg) {
        ArrayList<Integer> result = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        for (char c = 'A'; c <= 'Z'; c++) map.put(Character.toString(c), c - 'A' + 1);

        int left = 0, right = msg.length();
        while (left < msg.length()) {
            String target = msg.substring(left, right);
            if (map.containsKey(target)) {
                result.add(map.get(target));
                if (right < msg.length() - 1) map.put(msg.substring(left, right + 1), map.size() + 1);

                left = right;
                right = msg.length();
            }
            else right--;
        }

        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) answer[i] = result.get(i);
        
        return answer;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        System.out.println(s.solution("KAKAO"));
    }
}
