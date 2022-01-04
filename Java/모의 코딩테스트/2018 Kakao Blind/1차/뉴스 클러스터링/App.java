import java.util.HashMap;

class Solution {

    public int solution(String str1, String str2) {
        final int MUL = 65536;

        HashMap<String, Integer> set1 = new HashMap<>();
        HashMap<String, Integer> set2 = new HashMap<>();

        str1 = str1.toLowerCase().replaceAll("[^a-z]", " ");
        String[] tokens = str1.split(" ");
        for (String token : tokens) {
            for (int i = 0; i < token.length() - 1; i++) {
                String str = token.charAt(i) + "" + token.charAt(i + 1);
                set1.put(str, set1.getOrDefault(str, 0) + 1);
            }
        }

        str2 = str2.toLowerCase().replaceAll("[^a-z]", " ");
        tokens = str2.split(" ");
        for (String token : tokens) {
            for (int i = 0; i < token.length() - 1; i++) {
                String str = token.charAt(i) + "" + token.charAt(i + 1);
                set2.put(str, set2.getOrDefault(str, 0) + 1);
            }
        }

        System.out.println("Set 1");
        for (String str : set1.keySet()) System.out.println(str + ": " + set1.get(str));
        System.out.println("\nSet 2");
        for (String str : set2.keySet()) System.out.println(str + ": " + set2.get(str));

        int intersection = 0, union = 0;
        for (String str : set1.keySet()) {
            if (set2.containsKey(str)) {
                intersection += Math.min(set1.get(str), set2.get(str));
            }
        }
        for (String str : set1.keySet()) union += set1.get(str);
        for (String str : set2.keySet()) union += set2.get(str);
        union -= intersection;
        
        System.out.println(intersection + ", " + union);
        return intersection == union ? MUL : (int)((double)intersection / union * MUL);
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        String str1 = "e=m*c^2";
        String str2 = "e=m*c^2";

        System.out.println(s.solution(str1, str2));
    }
}
