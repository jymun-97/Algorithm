import java.util.HashSet;

class Solution {

    public int[] solution(int n, String[] words) {
        int[] answer = {0, 0};
        HashSet<String> set = new HashSet<>();

        String pre = words[0];
        if (pre.length() <= 1) return new int[] {1, 0};
        set.add(pre);

        for (int i = 1; i < words.length; i++) {
            if (!isValid(pre, words[i], set)) {
                answer[0] = i % n + 1;
                answer[1] = i / n + 1;
                break;
            }
            pre = words[i];
            set.add(pre);
        }

        return answer;
    }

    boolean isValid(String pre, String word, HashSet<String> set) {
        return word.length() > 1 && pre.charAt(pre.length() - 1) == word.charAt(0) && !set.contains(word);
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}
