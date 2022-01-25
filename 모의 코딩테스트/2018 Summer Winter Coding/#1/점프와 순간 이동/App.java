import java.util.*;

class Solution {

    public int solution(int n) {
        int answer = 0;
        
        while (n >= 1) {
            if (n % 2 == 1) answer++;
            n /= 2;
        }
        
        return answer;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        
    }
}
