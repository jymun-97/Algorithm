import java.util.Arrays;

class Solution {

    int n;
    int max = 0;
    int myScore = 0, otherScore = 0;
    int[] info;
    int[] cand = new int[11];
    int[] answer = new int[11];

    public int[] solution(int n, int[] info) {
        this.n = n;
        this.info = info;

        rec_func(10, 0);

        return max == 0 ? new int[] {-1} : answer;
    }

    void rec_func(int score, int count) { // 현재 score 점수 과녁을 고려, 이전 까지 count개의 활을 쏨
        if (score == 0) {
            if (max <= myScore - otherScore) {
                cand[10] = n - count;

                if (max == myScore - otherScore) {
                    for (int i = 10; i >= 0; i--) {
                        if (answer[i] > cand[i]) return;
                        else if (answer[i] == cand[i]) continue;
                        else break;
                    }
                }
                max = myScore - otherScore;
                answer = cand.clone();
            }
            return;
        }

        if (count + info[10 - score] + 1 <= n) {
            myScore += score;
            cand[10 - score] = info[10 - score] + 1;
            rec_func(score - 1, count + info[10 - score] + 1);
            myScore -= score;
            cand[10 - score] = 0;
        }
        
        if (info[10 - score] > 0) otherScore += score;
        rec_func(score - 1, count);
        if (info[10 - score] > 0) otherScore -= score;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        int n = 9;
        int[] info = {0,0,1,2,0,1,1,1,1,1,1};

        System.out.println(Arrays.toString(s.solution(n, info)));
    }
}
