import java.util.HashSet;
class Solution {

    public boolean solution(String[] phone_book) {
        boolean answer = true;

        HashSet<String> set = new HashSet<>();
        for (String str : phone_book) {
            for (int i = 1; i < str.length(); i++) {
                set.add(str.substring(0, i));
            }
        }

        for (String str : phone_book) {
            if (set.contains(str)) {
                answer = true;
                break;
            }
        }

        return answer;
    }
}
public class App {
    public static void main(String[] args) throws Exception {

        String[] phone_book = new String[] {"119", "97674223", "1195524421"};
        Solution s = new Solution();
        System.out.println(s.solution(phone_book));
    }
}

// 13m