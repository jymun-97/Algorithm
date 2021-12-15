import java.util.*;

class Solution {

    HashMap<String, Integer>[] count;

    public String[] solution(String[] orders, int[] course) {
        count = new HashMap[11];
        for (int i = 2; i <= 10; i++) count[i] = new HashMap<>();

        for (String order : orders) {
            char[] arr = order.toCharArray();
            Arrays.sort(arr);
            String new_order = new String(arr);
            for (int length : course) {
                combination("", new_order, length);
            }
        }

        ArrayList<String> answer = new ArrayList<>();
        for (int length : course) {
            int max = 0;
            ArrayList<String> cand = new ArrayList<>();
            for (String key : count[length].keySet()) {
                int cnt = count[length].get(key);
                System.out.println(key + ": " + cnt);
                if (cnt < 2) continue;

                if (cnt > max) {
                    cand.clear();
                    cand.add(key);
                    max = cnt;
                }
                else if (cnt == max) {
                    cand.add(key);
                }
            }
            answer.addAll(cand);
        }

        Collections.sort(answer);
        return answer.toArray(new String[answer.size()]);
    }

    void combination(String base, String str, int length) {
        if (base.length() == length) {
            count[length].put(base, count[length].getOrDefault(base, 0) + 1);
            return;
        }

        for (int i = 0; i < str.length(); i++) {
            String temp = base + str.charAt(i);
            String next = str.substring(i + 1);

            combination(temp, next, length);
        }
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        String[] orders = {"XYZ", "XWY", "WXA"};
        int[] course = {2,3,4};

        System.out.println(Arrays.toString(s.solution(orders, course)));
    }
}
