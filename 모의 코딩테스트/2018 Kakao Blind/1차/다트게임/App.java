class Solution {

    public int solution(String dartResult) {
        String[] tokens = split(dartResult);

        int[][] results = new int[4][3];
        for (int i = 1; i <= 3; i++) {
            String token = tokens[i - 1];
            
            int baseScore = Integer.parseInt(token.replaceAll("[^0-9]", ""));
            int bonus = token.contains("S") ? 1 : token.contains("D") ? 2 : 3;
            int option = token.contains("*") ? 2 : token.contains("#") ? -1 : 1;

            results[i][0] = baseScore;
            results[i][1] = bonus;
            results[i][2] = option;
            if (option != -1) results[i - 1][2] *= option;
        }

        int answer = 0;
        for (int i = 1; i <= 3; i++) {
            int score = (int)Math.pow(results[i][0], results[i][1]) * results[i][2];
            answer += score;
        }

        return answer;
    }

    String[] split(String dartResult) {
        String[] results = new String[3];
        String str = "";
        int index = 0;
        boolean flag = false;
        for (char c : dartResult.toCharArray()) {
            if (Character.isDigit(c)) {
                if (flag) {
                    flag = false;
                    results[index++] = str;
                    str = c + "";
                }
                else str += c;
            }
            else {
                flag = true;
                str += c;
            }
        }
        results[index] = str;
        return results;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();
        
        String dartResult = "1D2S#10S";
        
        System.out.println(s.solution(dartResult));
    }
}
