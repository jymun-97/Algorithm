import java.util.*;

class Solution {

    HashSet<ArrayList<Integer>> keys = new HashSet<>();
    String[][] table;
    int nRow, nCol;

    public int solution(String[][] relation) {
        table = relation;
        nRow = table.length;
        nCol = table[0].length;

        for (int size = 1; size <= nCol; size++) makeKey(new ArrayList<>(), size, -1);

        return keys.size();
    }

    void makeKey(ArrayList<Integer> key, int size, int pre) {
        if (key.size() == size) {
            if (!isUnique(key)) return;
            for (ArrayList<Integer> k : keys) {
                if (key.containsAll(k)) return;
            }
            keys.add((ArrayList<Integer>)key.clone());
            return;
        }

        for (int i = pre + 1; i < nCol; i++) {
            key.add(i);
            makeKey(key, size, i);
            key.remove(Integer.valueOf(i));
        }
    }

    boolean isUnique(ArrayList<Integer> key) {
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < nRow; i++) {
            String str = "";
            for (int attr : key) str += table[i][attr];

            if (set.contains(str)) return false;
            set.add(str);
        }
        return true;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        String[][] relation = { 
            {"100","ryan","music","2"},
            {"200","apeach","math","2"},
            {"300","tube","computer","3"},
            {"400","con","computer","4"},
            {"500","muzi","music","3"},
            {"600","apeach","music","2"}
        };

        System.out.println(s.solution(relation));
    }
}
