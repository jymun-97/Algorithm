import java.util.*;
import java.io.*;

class Q implements Comparable<Q> {
    int num, level;
    public Q(int n, int l) {
        num = n; 
        level = l;
    }
    public int compareTo(Q q) {
        if (level == q.level) return num - q.num;
        else return level - q.level;
    }
}

public class Main {

    static int n, m;
    static TreeSet<Q> set = new TreeSet<>();
    static TreeSet<Q>[] setOf;
    static HashMap<Integer, Integer> map = new HashMap<>();
    static HashMap<Integer, Integer> map_algo = new HashMap<>();
    static StringBuilder sb = new StringBuilder();

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        setOf = new TreeSet[101];
        for (int i = 1; i <= 100; i++) setOf[i] = new TreeSet<>();

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int level = Integer.parseInt(st.nextToken());
            int algo = Integer.parseInt(st.nextToken());

            set.add(new Q(num, level));
            setOf[algo].add(new Q(num, level));
            map.put(num, level);
            map_algo.put(num, algo);
        }

        m = Integer.parseInt(br.readLine());
        while (m --> 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String commend = st.nextToken();

            if (commend.equals("recommend")) {
                recommend(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }
            else if (commend.equals("recommend2")) {
                recommend2(Integer.parseInt(st.nextToken()));
            }
            else if (commend.equals("recommend3")) {
                recommend3(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }
            else if (commend.equals("add")) {
                add(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }
            else {
                solved(Integer.parseInt(st.nextToken()));
            }
        }
    }

    static void recommend(int algo, int x) {
        if (x == 1) sb.append(setOf[algo].last().num).append('\n');
        else sb.append(setOf[algo].first().num).append('\n');
    }

    static void recommend2(int x) {
        if (x == 1) sb.append(set.last().num).append('\n');
        else sb.append(set.first().num).append('\n');
    }

    static void recommend3(int x, int level) {
        Q q = new Q(0, level);
        Q target;
        if (x == 1) target = set.higher(q);
        else target = set.lower(q);
        
        sb.append((target == null) ? -1 : target.num).append('\n');
    }

    static void add(int num, int level, int algo) {
        Q q = new Q(num, level);
        set.add(q);
        setOf[algo].add(q);

        map.put(num, level);
        map_algo.put(num, algo);
    }

    static void solved(int num) {
        Q target = new Q(num, map.get(num));

        set.remove(target);
        setOf[map_algo.get(num)].remove(target);
    }

    public static void main(String[] args) throws Exception {

        input();
        System.out.println(sb);
    }
}