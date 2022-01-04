import java.util.HashMap;

class Node {
    HashMap<Character, Node> child;
    int count;

    public Node() { 
        count = 0;
        child = new HashMap<>(); 
    }
}
class Trie {
    Node root;

    public Trie() { root = new Node(); }

    public void insert(String word) {
        Node node = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!node.child.containsKey(c)) {
                node.child.put(c, new Node());
            }
            node = node.child.get(c);
            node.count++;
        }
    }
    public int find(String word) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            node = node.child.get(c);

            if (node.count == 1) return i + 1;
        }
        return word.length();
    }
}
class Solution {

    public int solution(String[] words) {
        int answer = 0;

        Trie trie = new Trie();
        for (String word : words) trie.insert(word);
        for (String word : words) answer += trie.find(word);
        
        return answer;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        String[] words = {"go","gone","guild"};

        System.out.println(s.solution(words));
    }
}
