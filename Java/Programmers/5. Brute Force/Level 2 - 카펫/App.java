import java.util.Arrays;

class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];

        for (int h = 1; h <= Math.sqrt(yellow); h++) {
            if (yellow % h != 0) continue;
            int w = yellow / h;

            if ((h + w + 2) * 2 == brown) {
                answer[0] = w + 2;
                answer[1] = h + 2;
                break;
            }
        }
        return answer;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();
        int brown = 16;
        int yellow = 9;

        int[] answer = s.solution(brown, yellow);
        System.out.println(Arrays.toString(answer));
    }
}
