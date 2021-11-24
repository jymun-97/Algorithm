import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = {0, 0};
        TreeSet<Integer> set = new TreeSet<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        for (String str : operations) {
            StringTokenizer st = new StringTokenizer(str);
            String command = st.nextToken();
            int num = Integer.parseInt(st.nextToken());

            if (command.equals("I")) {
                set.add(num);
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
            else {
                if (set.isEmpty()) continue;

                if (num == 1) {
                    if (map.get(set.last()) == 1) set.remove(set.last());
                    else map.put(set.last(), map.getOrDefault(set.last(), 0) + 1);
                }
                else {
                    if (map.get(set.first()) == 1) set.remove(set.first());
                    else map.put(set.first(), map.getOrDefault(set.first(), 0) + 1);
                }
            }
        }
        
        if (!set.isEmpty()) {
            answer[0] = set.last();
            answer[1] = set.first();
        }
        return answer;
    }
}

public class App {

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}
