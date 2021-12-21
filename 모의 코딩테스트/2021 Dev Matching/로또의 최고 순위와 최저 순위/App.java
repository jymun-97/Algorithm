import java.util.HashSet;

class Solution {

    public int[] solution(int[] lottos, int[] win_nums) {
        HashSet<Integer> win = new HashSet<>();
        for (int num : win_nums) win.add(num);

        int hit = 0, count = 0;
        for (int num : lottos) {
            if (num == 0) count++;
            if (win.contains(num)) hit++;
        }

        if (hit == 0) hit++;
        int highest = (count == 6) ? 1 : 7 - hit - count;
        int lowest = 7 - hit;
        return new int[] {highest, lowest};
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}
