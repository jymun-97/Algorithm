import java.util.*;

class Node {
    Node up, down;
    boolean isRemoved;
}

class Solution {

    public String solution(int n, int k, String[] cmd) {
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++)
            nodes[i] = new Node();
        for (int i = 0; i < n; i++) {
            if (i > 0)
                nodes[i].up = nodes[i - 1];
            if (i < n - 1)
                nodes[i].down = nodes[i + 1];
        }

        Stack<Node> stack = new Stack<>();
        Node now = nodes[k];
        for (String command : cmd) {
            char c = command.charAt(0);

            switch (c) {
                case 'C':
                    stack.push(now);
                    now.isRemoved = true;
                    if (now.up != null) now.up.down = now.down;
                    if (now.down != null) now.down.up = now.up;

                    if (now.down == null)
                        now = now.up;
                    else
                        now = now.down;
                    break;

                case 'Z':
                    Node target = stack.pop();
                    target.isRemoved = false;
                    if (target.up != null) target.up.down = target;
                    if (target.down != null) target.down.up = target;
                    break;

                default:
                    int range = Integer.parseInt(command.substring(2));
                    for (int i = 0; i < range; i++) {
                        if (c == 'U')
                            now = now.up;
                        else
                            now = now.down;
                    }
                    break;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (nodes[i].isRemoved)
                sb.append('X');
            else
                sb.append('O');
        }

        return sb.toString();
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        int n = 8;
        int k = 2;
        String[] cmd = { "D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C" };

        System.out.println(s.solution(n, k, cmd));
    }
}
