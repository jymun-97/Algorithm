import java.util.*;

class Solution {

    String[] gems;
    int nTypes;
    int[] answer = new int[2];

    public int[] solution(String[] gems) {
        this.gems = gems;

        HashSet<String> set = new HashSet<>();
        for (String gem : gems) set.add(gem);
        nTypes = set.size();

        int left = nTypes, right = gems.length;
        while (left <= right) {
            int mid = (left + right) / 2;

            if (isPossible(mid)) right = mid - 1;
            else left = mid + 1;
        }

        return answer;
    }

    boolean isPossible(int size) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < size; i++) map.put(gems[i], map.getOrDefault(gems[i], 0) + 1);

        if (map.size() == nTypes) {
            answer[0] = 1;
            answer[1] = size;
            return true;
        }

        for (int start = 1; start <= gems.length - size; start++) {
            String pre = gems[start - 1];
            if (map.get(pre) == 1) map.remove(pre);
            else map.put(pre, map.get(pre) - 1);

            int end = start + size - 1;
            map.put(gems[end], map.getOrDefault(gems[end], 0) + 1);

            if (map.size() == nTypes) {
                answer[0] = start + 1;
                answer[1] = end + 1;   
                return true;
            }
        }
        
        return false;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        String[] gems = {"a", "b", "b", "c", "a"};

        System.out.println(Arrays.toString( s.solution(gems) ));
    }
}
