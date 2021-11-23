import java.util.*;

class Solution {
    class Task {
        int id, priority;
        public Task(int id, int priority) {
            this.id = id;
            this.priority = priority;
        }
    }

    public int solution(int[] priorities, int location) {
        int answer = 1;
        int n = priorities.length;
        
        Queue<Task> readyQue = new LinkedList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for (int i = 0; i < n; i++) {
            readyQue.add(new Task(i, priorities[i]));
            pq.add(priorities[i]);
        }

        while (!readyQue.isEmpty()) {
            
            Task task = readyQue.poll();

            if (task.priority == pq.peek()) {
                if (task.id == location) break;
                pq.poll();
                answer++;
            }
            else readyQue.add(task);
        }

        return answer;
    }
}

public class App {

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}
