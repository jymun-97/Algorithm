class Solution {
    public String solution(String p) {
        return create(p);
    }
    String create(String p) {
        if (isCorrect(p)) return p;
        
        StringBuilder sb = new StringBuilder();

        String[] tokens = split(p);
        String u = tokens[0];
        String v = tokens[1];

        if (isCorrect(u)) {
            sb.append(u).append(create(v));
            return sb.toString();
        }
        else {
            sb.append("(").append(create(v)).append(")").append(reverse(u));
            return sb.toString();
        }
    }

    String[] split(String p) {
        int open = 0, close = 0;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(') open++;
            else close++;

            if (open == close) {
                String u = p.substring(0, i + 1);
                String v = (i == p.length() - 1) ? "" : p.substring(i + 1);

                return new String[] {u, v};
            }
        }
        return new String[] {p, ""};
    }

    boolean isCorrect(String p) {
        int open = 0, close = 0;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(') open++;
            else close++;

            if (open < close) return false;
        }
        return true;
    }

    String reverse(String u) {
        u = u.substring(1, u.length() - 1);
        StringBuilder sb = new StringBuilder();

        for (char c : u.toCharArray()) {
            if (c == '(') sb.append(")");
            else sb.append("(");
        }
        
        return sb.toString();
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        System.out.println(s.solution("()))((()"));
    }
}
