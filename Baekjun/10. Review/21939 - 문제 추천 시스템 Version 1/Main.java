import java.util.*;
import java.io.*;

class Q implements Comparable<Q> {
    int num, level;
    public Q(int n, int l) {
        num = n;
        level = l;
    }
    public int compareTo(Q q) {
        if (level < q.level) {
            return -1;
        }
        else if (level > q.level) {
            return 1;
        }
        else {
            return num - q.num;
        }
    }
}

public class Main {

    static int n, m;
    static TreeSet<Q> list = new TreeSet<>();
    static boolean flag = true;
    static StringBuilder sb = new StringBuilder();
    static HashMap<Integer, Integer> map = new HashMap<>();

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int level = Integer.parseInt(st.nextToken());

            list.add(new Q(num, level));
            map.put(num, level);
        }
        
        m = Integer.parseInt(br.readLine());
        while (m --> 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            
            if (command.equals("recommend")) {
                int choice = Integer.parseInt(st.nextToken());
                recommend(choice);
            }
            else if (command.equals("add")) {
                int num = Integer.parseInt(st.nextToken());
                int level = Integer.parseInt(st.nextToken());
                add(num, level);
            }
            else {
                int num = Integer.parseInt(st.nextToken());
                solved(num);
            }
        }
    }

    static void recommend(int choice) {
        if (choice == 1) {
            sb.append(list.last().num).append('\n');
        }
        else {
            sb.append(list.first().num).append('\n');
        }
    }

    static void add(int num, int level) {
        list.add(new Q(num, level));
        map.put(num, level);
    }
    
    static void solved(int num) {
        list.remove(new Q(num, map.get(num)));
        map.remove(num);
    }

    public static void main(String[] args) throws Exception {

        input();
        System.out.println(sb);
    }
}