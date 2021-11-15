import java.util.PriorityQueue;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;

        PriorityQueue<Integer> que = new PriorityQueue<>();
        for (int s : scoville) que.add(s);

        while (!que.isEmpty()) {
            if (que.peek() >= K) break;
            if (que.size() == 1) {
                answer = -1;
                break;
            }
            int mix = que.poll() + que.poll() * 2;
            que.add(mix);
            answer++;
        }
        return answer;
    }
}