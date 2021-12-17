import java.util.*;

class Solution {

    class Food {
        int index, time;

        public Food(int index, int time) {
            this.index = index;
            this.time = time;
        }
    }
    public int solution(int[] food_times, long k) {
        int n = food_times.length, target = 0;
        long max = 0L;
        ArrayList<Food> list = new ArrayList<>();
        ArrayList<Food> cand = new ArrayList<>();

        for (int i = 0; i < food_times.length; i++) {
            list.add(new Food(i + 1, food_times[i]));
            max += food_times[i];
        }
        if (max <= k) return -1;

        Collections.sort(list, (o1, o2) -> o1.time - o2.time);
        int pre = 0;
        for (int i = 0; i < n; i++) {
            Food food = list.get(i);
            long need = (long)(food.time - pre) * (n - i);
            if (need < k) {
                pre = food.time;
                k -= need;
            }
            else {
                k %= n - i;
                for (int j = i; j < n; j++) cand.add(list.get(j));
                break;
            }
        }
        Collections.sort(cand, (o1, o2) -> o1.index - o2.index);
        return cand.get((int)k).index;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        int[] food_times = {3,5,1,6,5,3};

        System.out.println(s.solution(food_times, 20));
    }
}
