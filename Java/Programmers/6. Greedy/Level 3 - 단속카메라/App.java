import java.util.ArrayList;
import java.util.Arrays;

class Solution {

    class Group {
        int count;
        int start, end;

        public Group(int s, int e) {
            start = s;
            end = e;
        }
    }

    public int solution(int[][] routes) {
        Arrays.sort(routes, (a1, a2) -> { return a1[0] - a2[0]; });
        ArrayList<Group> groups = new ArrayList<>();
        for (int i = 0; i < routes.length; i++) {
            int s = routes[i][0];
            int e = routes[i][1];

            boolean flag = false;
            for (Group group : groups) {
                if (group.end >= s) {
                    group.count++;
                    group.start = Math.max(group.start, s);
                    group.end = Math.min(group.end, e);

                    flag = true;
                    break;
                }
            }
            if (!flag) groups.add(new Group(s, e));
        }
        return groups.size();
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();
        int[][] routes = {{-20,-15}, {-14,-5},{-18,-13},{-5,-3}};

        System.out.println(s.solution(routes));
    }
}
