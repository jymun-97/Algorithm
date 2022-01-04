import java.util.HashMap;

class Solution {

    HashMap<String, Integer> map = new HashMap<>();

    public String solution(String[] participant, String[] completion) {
        String answer = "";

        for (String name : participant) {
            map.put(name, map.getOrDefault(name, 0) + 1);
        }
        for (String name : completion) {
            map.put(name, map.get(name) - 1); 
        }
        for (String name : map.keySet()) {
            if (map.get(name) == 1) {
                answer = name;
                break;
            }
        }

        return answer;
    }
}
public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}

// 15m