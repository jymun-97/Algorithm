import java.util.*;

class Solution {

    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);

        int answer = 0;
        int n = A.length;

        int a = 0;
        for (int b = 0; b < n; b++) {   
            if (A[a] < B[b]) {
                a++;
                answer++;
            }
        }
        return answer;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        
    }
}
