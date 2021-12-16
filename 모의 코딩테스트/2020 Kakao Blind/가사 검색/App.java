import java.util.*;

class Node {
    HashMap<Character, Node> children;
    int count;

    public Node() {
        children = new HashMap<>();
        count = 0;
    }
}

class Trie {
    Node root;

    public Trie() {
        root = new Node();
    }
    public void insert(String str) {
        Node node = root;
        for (char c : str.toCharArray()) {
            node.count++;
            if (!node.children.containsKey(c)) node.children.put(c, new Node());
            node = node.children.get(c);
        }
    }
    public int getCount(String str) {
        Node node = root;
        for (char c : str.toCharArray()) {
            if (c == '?') break;
            if (!node.children.containsKey(c)) return 0;
            node = node.children.get(c);
        }
        return node.count;
    }
}
class Solution {
    public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];

        Trie[] tries = new Trie[100001];
        Trie[] reverse_tries = new Trie[100001];

        for (int i = 0; i <= 100000; i++) {
            tries[i] = new Trie();
            reverse_tries[i] = new Trie();
        }

        for (String word : words) {
            int n = word.length();
            
            tries[n].insert(word);
            reverse_tries[n].insert(new StringBuilder(word).reverse().toString());
        }

        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];
            int n = query.length();

            if (query.charAt(0) == '?') {
                query = new StringBuilder(query).reverse().toString();
                answer[i] = reverse_tries[n].getCount(query);
            }
            else answer[i] = tries[n].getCount(query);
        }

        return answer;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
        String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};

        System.out.println(Arrays.toString(s.solution(words, queries)));
    }
}