import java.util.*;

class Solution {

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 1;

        Queue<Integer> runQueue = new LinkedList<>();
        Queue<Integer> readyQueue = new LinkedList<>();
        for (int truck : truck_weights) readyQueue.add(truck);

        int sum = truck_weights[0];
        runQueue.add(readyQueue.poll());

        while (!readyQueue.isEmpty()) {
            if (runQueue.size() == bridge_length) {
                int out = runQueue.poll();
                sum -= out;
            }

            if (runQueue.size() < bridge_length && sum + readyQueue.peek() <= weight) {
                int in = readyQueue.poll();
                runQueue.add(in);
                sum += in;
            }
            else runQueue.add(0);

            answer++;
        }

        return answer + bridge_length;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        int bridge_length = 2;
        int weight = 10;
        int[] truck_weights = new int[] {7,4,5,6};

        System.out.println(s.solution(bridge_length, weight, truck_weights));
    }
}
