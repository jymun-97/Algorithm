import java.util.HashSet;

class Solution {

    public int solution(String skill, String[] skill_trees) {
        int answer = 0;

        HashSet<String> set = new HashSet<>();
        for (int i = 0; i <= skill.length(); i++) set.add(skill.substring(0, i));

        for (String target : skill_trees) {
            target = target.replaceAll("[^" + skill + "]", "");
            for (String s : set) {
                if (target.equals(s)) {
                    answer++;
                    break;
                }
            }
        }
        return answer;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        String skill = "CBD";
        String[] skill_trees = {"BACDE", "CBADF", "AECB", "BDA"};

        System.out.println(s.solution(skill, skill_trees));
    }
}
