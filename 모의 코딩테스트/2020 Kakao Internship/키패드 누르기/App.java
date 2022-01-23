class Solution {

    public String solution(int[] numbers, String hand) {
        StringBuilder sb = new StringBuilder();

        int lr = 3, lc = 0;
        int rr = 3, rc = 2;

        for (int num : numbers) {
            if (num % 3 == 1) {
                sb.append('L');
                lr = (num - 1) / 3;
                lc = 0;
            }
            else if (num != 0 && num % 3 == 0) {
                sb.append('R');
                rr = (num - 1) / 3;
                rc = 2;
            }
            else {
                int row = (num == 0) ? 3 : (num - 1) / 3;
                int col = 1;

                int ld = Math.abs(lr - row) + Math.abs(lc - col);
                int rd = Math.abs(rr - row) + Math.abs(rc - col);

                if (ld < rd || (ld == rd && hand.equals("left"))) {
                    sb.append('L');
                    lr = row;
                    lc = col;
                }
                else {
                    sb.append('R');
                    rr = row;
                    rc = col;
                }
            }
        }

        return sb.toString();
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        int[] numbers = {7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2};
        String hand = "left";

        System.out.println(s.solution(numbers, hand));
    }
}
