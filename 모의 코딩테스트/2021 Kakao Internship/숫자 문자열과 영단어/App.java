import java.util.HashMap;

class Solution {

    HashMap<String, String> map = new HashMap<>();

    public int solution(String s) {
        init();

        for (String word : map.keySet()) {
            s = s.replaceAll(word, map.get(word));
        }

        return Integer.parseInt(s);
    }

    void init() {
        map.put("zero", "0");
        map.put("one", "1");
        map.put("two", "2");
        map.put("three", "3");
        map.put("four", "4");
        map.put("five", "5");
        map.put("six", "6");
        map.put("seven", "7");
        map.put("eight", "8");
        map.put("nine", "9");
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution sol = new Solution();

        System.out.println(sol.solution("123"));
    }
}
