class Solution {
    public String solution(String new_id) {
        return convert(new_id);
    }
    String convert(String id) {
        id = step1(id);
        System.out.println(id);
        id = step2(id);
        System.out.println(id);
        id = step3(id);
        System.out.println(id);
        id = step4(id);
        System.out.println(id);
        id = step5(id);
        System.out.println(id);
        id = step6(id);
        System.out.println(id);
        id = step7(id);
        System.out.println(id);

        return id;
    }

    String step1(String id) {
        return id.toLowerCase();
    }
    String step2(String id) {
        String result = "";
        for (char c : id.toCharArray()) {
            if ((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || c == '.' || c == '-' || c == '_') {
                result += c;
            } 
        }
        return result;
    }
    String step3(String id) {
        while (id.contains("..")) {
            id = id.replace("..", ".");
        }
        return id;
    }
    String step4(String id) {
        if (id.charAt(0) == '.') {
            id = id.substring(1);
        }
        if (id.length() > 0 && id.charAt(id.length() - 1) == '.') {
            id = id.substring(0, id.length() - 1);
        }
        return id;
    }
    String step5(String id) {
        if (id.length() == 0 || id == null) return "a";
        return id;
    }
    String step6(String id) {
        if (id.length() > 15) return step4(id.substring(0, 15));
        return id;
    }
    String step7(String id) {
        if (id.length() <= 2) {
            String last = Character.toString(id.charAt(id.length() - 1));
            String temp = id;
            for (int i = 0; i < 3 - id.length(); i++) {
                temp += last;
            }
            id = temp;
        }
        return id;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        System.out.println(s.solution("abcdefghijklmn.p"));
    }
}
