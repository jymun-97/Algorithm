import java.util.ArrayList;
import java.util.Collections;

class Solution {

    class Stage implements Comparable<Stage> {
        int index;
        double rate;
        public Stage(int index, double rate) {
            this.index = index;
            this.rate = rate;
        }
        @Override
        public int compareTo(Stage other) {
            if (rate < other.rate) return 1;
            else if (rate > other.rate) return -1;
            else return index - other.index;
        }
    }
    public int[] solution(int N, int[] stages) {
        
        final int MAX = 200000;
        int[] nTry = new int[MAX + 3];
        int[] nowTry = new int[MAX + 2];
        ArrayList<Stage> list = new ArrayList<>();
        
        for (int stage : stages) nowTry[stage]++;
        for (int i = 1; i <= MAX; i++) {
            nTry[1] += nowTry[i];
            nTry[i + 1] -= nowTry[i];
        }
        for (int i = 1; i <= MAX + 1; i++) nTry[i + 1] += nTry[i];
        
        for (int i = 1; i <= N; i++) list.add(new Stage(i, (double)nowTry[i] / nTry[i]));
        Collections.sort(list);
        int[] answer = new int[list.size()];
        
        for (int i = 0; i < list.size(); i++) answer[i] = list.get(i).index;
        return answer;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}
