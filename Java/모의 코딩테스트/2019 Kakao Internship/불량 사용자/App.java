import java.util.*;

class Solution {

    String[] user_id, banned_id;
    int n, answer;
    HashSet<String> selected = new HashSet<>();
    HashSet<HashSet<String>> explored = new HashSet<>();

    public int solution(String[] user_id, String[] banned_id) {
        this.user_id = user_id;
        this.banned_id = banned_id;
        this.n = banned_id.length;
        
        dfs(0);

        return answer;
    }

    void dfs(int k) {
        if (k == n) {
            for (HashSet<String> set : explored) {
                if (set.containsAll(selected)) return;
            }
            explored.add((HashSet<String>)selected.clone());
            answer++;
            return;
        }

        for (String id : user_id) {
            if (selected.contains(id)) continue;
            if (match(banned_id[k], id)) {
                selected.add(id);
                dfs(k + 1);
                selected.remove(id);
            }
        }
    }

    boolean match(String ban, String id) {
        if (ban.length() != id.length()) return false;

        for (int i = 0; i < ban.length(); i++) {
            char user_id_char = id.charAt(i);
            char banned_id_char = ban.charAt(i);

            if (banned_id_char != user_id_char && banned_id_char != '*') return false; 
        }

        return true;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id = {"fr*d*", "*rodo", "******", "******"};

        System.out.println(s.solution(user_id, banned_id));
    }
}
