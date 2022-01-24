import java.util.*;

class Solution {

    TreeSet<Long> set = new TreeSet<>();

    public long[] solution(long k, long[] room_number) {
        int n = room_number.length;
        long[] answer = new long[n];

        for (int i = 0; i < n; i++) {
            long num = room_number[i];
            answer[i] = assign(num);
        }

        return answer;
    }

    long assign(long num) {
        if (!set.contains(num)) {
            set.add(num);   
            return num;
        }

        while (set.higher(num) != null && set.higher(num) == num + 1) {
            num = set.higher(num);
        }
        set.add(++num);
        return num;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();
        long[] room_number = {1,3,4,1,3,1};

        System.out.println(Arrays.toString(s.solution(10, room_number)));
    }
}
