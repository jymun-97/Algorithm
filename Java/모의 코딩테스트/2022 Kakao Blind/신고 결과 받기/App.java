import java.util.*;

class Solution {

    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];

        HashMap<String, Integer> index = new HashMap<>();
        HashMap<String, HashSet<String>> set = new HashMap<>(); // 나를 신고한 사람들 목록

        for (int i = 0; i < id_list.length; i++) {
            index.put(id_list[i], i);
            set.put(id_list[i], new HashSet<>());
        }

        for (String info : report) {
            StringTokenizer st = new StringTokenizer(info);
            String from = st.nextToken();
            String to = st.nextToken();

            set.get(to).add(from);
        }

        for (int i = 0; i < id_list.length; i++) {
            if (set.get(id_list[i]).size() >= k) {
                for (String id : set.get(id_list[i])) {
                    answer[index.get(id)]++;
                }
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
