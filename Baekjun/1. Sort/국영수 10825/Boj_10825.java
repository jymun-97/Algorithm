import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_10825 {
    static class Score implements Comparable<Score> {
        String name;
        int korean, english, math;

        public Score(String n, int k, int e, int m) {
            name = n;
            korean = k;
            english = e;
            math = m;
        }

        public int compareTo(Score other) {
            if (korean != other.korean) return other.korean - korean;

            if (english != other.english) return english - other.english;

            if (math != other.math) return other.math - math;

            return name.compareTo(other.name);
        }
    }

    static StringBuilder sb = new StringBuilder();
    static Score[] scores;
    static int N;

    static void input() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(reader.readLine());
        scores = new Score[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
            scores[i] = new Score(st.nextToken(), 
                Integer.parseInt(st.nextToken()), 
                Integer.parseInt(st.nextToken()), 
                Integer.parseInt(st.nextToken())
            );
        }
    }

    static void sort() {
        Arrays.sort(scores);
    }

    static void print() {
        for (Score score : scores) {
            sb.append(score.name).append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        sort();
        print();
    }
}
