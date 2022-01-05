import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

class Word implements Comparable<Word> {
    String word;
    int length;
    
    public Word(String word) {
        this.word = word;
        length = word.length();
    }

    public int compareTo(Word other) {
        if (length == other.length) return word.compareTo(other.word);
        return length - other.length;
    }
}


public class Boj_1181 {
    static int n;
    static ArrayList<Word> words = new ArrayList<>();

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < n; i++) {
            words.add(new Word(br.readLine()));
        }
    }

    static void solve() {
        Collections.sort(words);
        String preWord = "";

        StringBuilder sb = new StringBuilder();
        for (Word word : words) {
            if (!word.word.equals(preWord))
                sb.append(word.word).append('\n');
            
            preWord = word.word;
        }

        System.out.println(sb);
    }
    public static void main(String[] args) throws Exception {
        input();
        solve();
    }
}
