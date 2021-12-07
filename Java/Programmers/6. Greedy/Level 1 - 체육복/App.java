import java.util.*;

class Solution {

    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        int[] count = new int[n + 1]; // i번 학생이 가지고 있는 체육복 수
        for (int i = 1; i <= n; i++) count[i] = 1; // 최초 모두 1개씩 보유

        for (int i : lost) count[i]--; // 도난 당한 학생은 1씩 감소
        for (int i : reserve) count[i]++; // 여벌이 있으면 1씩 증가

        Arrays.sort(lost);
        for (int i : lost) {
            if (count[i] != 0) continue; // 도난 당했지만 여벌이 있어 1개가 남았다면 굳이 빌리지 않음
            if (i - 1 >= 1 && count[i - 1] > 1) { // 앞사람이 여벌이 있다면
                count[i - 1]--; // 앞사람 1개 감소
                count[i]++;     // 1개 빌림
                continue;
            }
            if (i + 1 <= n && count[i + 1] > 1) { // 앞사람은 여벌이 없고 뒷사람이 여벌이 있다면
                count[i + 1]--; // 뒷사람 1개 감소
                count[i]++;     // 1개 빌림
            }
        }

        for (int i = 1; i <= n; i++) 
            if (count[i] > 0) answer++; // 1개 이상 가지고 있는 학생의 수가 정답

        return answer;
    }
}

public class App {

    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        int n = 5;
        int[] lost = {4,2};
        int[] reserve = {3,5};

        System.out.println(s.solution(n, lost, reserve));
    }
}
