import java.util.*;

class File implements Comparable<File> {
    String name, head, tail;
    int num, index;
    public File(String file, int index) {
        this.name = file;
        this.index = index;
        split();
    }
    private void split() {
        String[] tokens = name.split("[0-9]+");

        head = tokens[0].toLowerCase();
        tail = tokens.length == 1 ? "" : name.substring(name.indexOf(tokens[1], head.length()));
        num = Integer.parseInt(name.substring(head.length(), tail.equals("") ? name.length() : name.indexOf(tail, head.length())));
    }
    public int compareTo(File other) {
        if (!head.toLowerCase().equals(other.head.toLowerCase())) return head.toLowerCase().compareTo(other.head.toLowerCase());
        if (num != other.num) return num - other.num;
        return index - other.index;
    }
}
class Solution {

    public String[] solution(String[] files) {
        String[] answer = {};

        File[] arr = new File[files.length];
        for (int i = 0; i < files.length; i++) arr[i] = new File(files[i], i);
        Arrays.sort(arr);
        
        answer = new String[files.length];
        for (int i = 0; i < answer.length; i++) answer[i] = arr[i].name;

        return answer;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();
        String[] files = {"foo9.txt", "foo010bar020.zip", "F-15"};

        System.out.println(Arrays.toString(s.solution(files)));
    }
}
