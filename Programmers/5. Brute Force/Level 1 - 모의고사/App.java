import java.util.ArrayList;

class Solution {

    public int[] solution(int[] answers) {
        ArrayList<Integer> list = new ArrayList<>();

        int[] count = {0,0,0};
        int[] a = {1,2,3,4,5};
        int[] b = {2,1,2,3,2,4,2,5};
        int[] c = {3,3,1,1,2,2,4,4,5,5};

        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == a[i % 5]) count[0]++;
            if (answers[i] == b[i % 8]) count[1]++;
            if (answers[i] == c[i % 10]) count[2]++;
        }

        int max = Math.max(count[0], Math.max(count[1], count[2]));

        for (int i = 0; i < 3; i++) 
            if (max == count[i]) list.add(i + 1);

        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) answer[i] = list.get(i);
        return answer;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}
