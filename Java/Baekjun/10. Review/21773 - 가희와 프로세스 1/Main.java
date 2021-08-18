import java.util.*;
import java.io.*;

class Process implements Comparable<Process> {
    int id, remain, priority;
    public Process(int i, int r, int p) {
        id = i;
        remain = r;
        priority = p;
    } 
    public void run() {
        remain--;
        priority--;
    }
    public int compareTo(Process p) {
        if (priority != p.priority) return p.priority - priority;
        else return id - p.id;
    }
}

public class Main {

    static int n, t;
    static PriorityQueue<Process> que = new PriorityQueue<>();

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        t = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        while (n --> 0) {
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int remain = Integer.parseInt(st.nextToken());
            int priority = Integer.parseInt(st.nextToken());

            que.add(new Process(id, remain, priority));
        }
    }

    static void solve() {
        StringBuilder sb = new StringBuilder();

        int time = 1;
        while (time <= t && !que.isEmpty()) {
            Process process = que.poll();
            process.run();
            sb.append(process.id).append('\n');

            if (process.remain != 0) que.add(process);
            time++;
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}