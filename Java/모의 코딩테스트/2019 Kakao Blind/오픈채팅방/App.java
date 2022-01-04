import java.util.*;
class Solution {
    public String[] solution(String[] record) {
        ArrayList<String> answer = new ArrayList<>();
        ArrayList<String> logs = new ArrayList<>();
        HashMap<String, String> nameOf = new HashMap<>();

        for (String log : record) {
            StringTokenizer st = new StringTokenizer(log);
            String act = st.nextToken();
            String id = st.nextToken();

            if (act.equals("Enter") || act.equals("Change")) {
                String name = st.nextToken();
                nameOf.put(id, name);

                if (act.equals("Change")) continue;
            }
            
            StringBuilder sb = new StringBuilder();
            sb.append(act).append(" ").append(id);
            logs.add(sb.toString());
        }

        for (String log : logs) {
            StringTokenizer st = new StringTokenizer(log);
            String act = st.nextToken();
            String id = st.nextToken();
            
            StringBuilder sb = new StringBuilder(nameOf.get(id));
            if (act.equals("Enter")) {
                sb.append("님이 들어왔습니다.");
            }
            else {
                sb.append("님이 나갔습니다.");
            }
            answer.add(sb.toString());
        }

        return answer.toArray(new String[answer.size()]);
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};

        System.out.println(Arrays.toString(s.solution(record)));
    }
}
