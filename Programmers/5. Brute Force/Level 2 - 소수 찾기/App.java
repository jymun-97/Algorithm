import java.util.HashSet;

class Solution {

    HashSet<Integer> set = new HashSet<>();
    String nums;
    boolean[] visit;
    int n, answer;

    public int solution(String numbers) {
        nums = numbers;
        n = numbers.length();
        for (int size = 1; size <= n; size++) {
            visit = new boolean[n];
            makeNum(size, 0, "");
        }
        return answer;
    }

    void makeNum(int size, int k, String num) {
        if (k == size) {
            int target = Integer.parseInt(num);
            if (!set.contains(target) && isPrime(target)) {
                answer++;
                set.add(target);
            }
            return;
        }

        for (int i = 0; i < n; i++) {
            if (visit[i]) continue;

            num += Character.toString(nums.charAt(i));
            visit[i] = true;
            makeNum(size, k + 1, num);
            num = num.substring(0, num.length() - 1);
            visit[i] = false;
        }
    }

    boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        System.out.println(s.solution("011"));
    }
}
