import java.util.*;

class Solution {

    public int[] solution(String s) {
        String[] tokens = s.substring(2, s.length() - 2).split("\\},\\{");

        ArrayList<String> list = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();

        for (String token : tokens) {
            StringTokenizer st = new StringTokenizer(token, ",");

            while (st.hasMoreTokens()) {
                String target = st.nextToken();
                
                if (!map.containsKey(target)) list.add(target);
                map.put(target, map.getOrDefault(target, 0) + 1);
            }
        }

        Collections.sort(list, new Comparator<String>() {
            @Override 
            public int compare(String s1, String s2) {
                return map.get(s2) - map.get(s1);
            }
        });

        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) answer[i] = Integer.parseInt(list.get(i));

        return answer;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution sol = new Solution();

        String s = "{{2},{2,1},{2,1,3},{2,1,3,4}}";
        System.out.println(Arrays.toString(sol.solution(s)));
    }
}
