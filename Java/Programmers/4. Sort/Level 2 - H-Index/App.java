import java.util.*;

class Solution {

    public int solution(int[] citations) {
        int answer = 0;
        Arrays.sort(citations);

        int left = 0, right = citations[citations.length - 1];
        while (left <= right) {
            int mid = (left + right) / 2;

            if (isPossible(citations, mid)) {
                left = mid + 1;
                answer = mid;
            }
            else right = mid - 1;
        }
        return answer;
    }

    public boolean isPossible(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        int count = -1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (arr[mid] >= target) {
                right = mid - 1;
                count = mid;
            }
            else left = mid + 1;
        }

        if (count == -1) return false;
        return arr.length - count >= target;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();
        int[] citations = new int[] {1,2,3,4,5};
        
        System.out.println(s.solution(citations));
    }
}
