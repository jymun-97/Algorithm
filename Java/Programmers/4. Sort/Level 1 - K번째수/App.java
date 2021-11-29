import java.util.ArrayList;
import java.util.Collections;

class Solution {

    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];

        for (int i = 0; i < commands.length; i++) {
            ArrayList<Integer> list = new ArrayList<>();

            for (int idx = commands[i][0] - 1; idx < commands[i][1]; idx++)
                list.add(array[idx]);

            Collections.sort(list);
            answer[i] = list.get(commands[i][2] - 1);
        }

        return answer;
    }
}
