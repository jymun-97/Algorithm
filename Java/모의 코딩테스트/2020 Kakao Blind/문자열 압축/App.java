class Solution {

    public int solution(String s) {
        int answer = s.length();

        for (int size = 1; size <= s.length() / 2; size++) {
            String target = s.substring(0, size);
            int count = 1;
            int length = 0;

            for (int i = size; i <= s.length() - size; i += size) {
                String temp = s.substring(i, i + size);

                if (target.equals(temp)) {
                    count++;
                }
                else {
                    if (count != 1) length += size + Integer.toString(count).length();
                    else length += size;
                    target = temp;
                    count = 1;
                }
            }
            if (count != 1) length += size + Integer.toString(count).length();
            else length += size;
            
            length += s.length() % size;
            answer = Math.min(answer, length);
        }

        return answer;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution sol = new Solution();

        String s = "aabbaccc";

        System.out.println(sol.solution(s));
    }
}
