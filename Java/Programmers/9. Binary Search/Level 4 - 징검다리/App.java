import java.util.*;

class Solution {
    public int solution(int distance, int[] arr, int n) {
        int answer = 0;
        ArrayList<Integer> rocks = new ArrayList<>();
        Arrays.sort(arr);

        for (int loc : arr) rocks.add(loc);
        rocks.add(distance);
        
        int left = 0, right = distance;
        while (left <= right) {
            int mid = (left + right) / 2;

            int erase = 0;
            int start = 0;

            for (int target : rocks) {
                if (target - start < mid) erase++;
                else start = target;
            }

            if (erase > n) right = mid - 1;
            else {
                left = mid + 1;
                answer = mid;
            }
        }

        return answer;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        int distance = 25;
        int[] rocks = {2,14,11,21,17};
        int n = 2;

        System.out.println(s.solution(distance, rocks, n));
    }
}
