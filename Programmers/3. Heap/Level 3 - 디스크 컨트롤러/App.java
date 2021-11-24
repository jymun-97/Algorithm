import java.util.*;

class Solution {
    class Task {

        int at, rt;
        public Task(int at, int rt) {
            this.at = at;
            this.rt = rt;
        }
    }
    public int solution(int[][] jobs) {
        int answer = 0;
        int n = jobs.length;

        PriorityQueue<Task> que = new PriorityQueue<>((t1, t2) -> t1.at - t2.at);
        PriorityQueue<Task> readyQue = new PriorityQueue<>((t1, t2) -> t1.rt - t2.rt);
        for (int i = 0; i < n; i++) que.add(new Task(jobs[i][0], jobs[i][1]));

        int time = 0;
        while (true) {
            // 종료 조건
            if (que.isEmpty() && readyQue.isEmpty()) break;

            // 아직 도착한 작업이 없는 경우
            if (readyQue.isEmpty()) {
                time = que.peek().at;
                while (!que.isEmpty() && que.peek().at == time) readyQue.add(que.poll());
            }

            // 지금 까지 도착한 작업 중 가장 최우선순위 작업 실행
            Task task = readyQue.poll();
            time += task.rt;
            answer += time - task.at;

            // 작업이 실행되는 동안 도착한 작업 추가
            while (!que.isEmpty() && que.peek().at <= time) readyQue.add(que.poll());
        }

        return answer / n;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();
        int[][] jobs = new int[][] { {0,4}, {0,3}, {0,2}, {0,1} };

        System.out.println(s.solution(jobs));
    }
}
