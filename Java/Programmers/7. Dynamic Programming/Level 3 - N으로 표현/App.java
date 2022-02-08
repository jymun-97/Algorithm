import java.util.HashSet;

class Solution {

    HashSet<Integer>[] set; 

    public int solution(int n, int number) {
        set = new HashSet[9];
        for (int i = 1; i < 9; i++) set[i] = new HashSet<>();

        set[1].add(n);
        int num = 0;
        for (int k = 1; k <= 8; k++) {
            num = n + num * 10;
            set[k].add(num);
            for (int i = 1; i < k; i++) {
                for (int x : set[i]) {
                    for (int y : set[k - i]) {
                        if (x + y <= 32000) set[k].add(x + y);
                        if (x - y > 0)      set[k].add(x - y);
                        if (x * y <= 32000) set[k].add(x * y);
                        if (x % y == 0)     set[k].add(x / y);
                    }
                }
            }
        }

        for (int i = 1; i <= 8; i++) {
            if (set[i].contains(number)) return i;
        }

        return -1;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        System.out.println(s.solution(5, 12));
    }
}
