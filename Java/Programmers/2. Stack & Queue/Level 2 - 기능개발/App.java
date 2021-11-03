import java.util.ArrayList;
import java.util.Arrays;

class Solution {

    public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> answer = new ArrayList<>();
        int n = progresses.length;
        int[] done = new int[n];

        for (int i = 0; i < n; i++) {
            int remain = 100 - progresses[i];
            done[i] = remain / speeds[i];
            if (remain % speeds[i] > 0) done[i]++;
        }

        int left = 0, right = 1;
        while (right < n && left <= right) {
            if (done[left] <= done[right]) {
                answer.add(right - left);
                left = right;
            }
            right++;
        }
        answer.add(right - left);

        return answer.stream().mapToInt(i -> i).toArray();
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        ArrayList<Integer> answer = new ArrayList<>();
        int[] done = new int[] {3, 3, 1};
        int n = done.length;

        int left = 0, right = 1;
        while (right < n && left <= right) {
            if (done[left] < done[right]) {
                answer.add(right - left);
                left = right;
            }
            right++;
        }
        answer.add(right - left);
        int[] result = answer.stream().mapToInt(i -> i).toArray();

        System.out.println(Arrays.toString(result));
    }
}
