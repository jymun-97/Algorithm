import java.util.*;
import java.io.*;

class Customer {
    int id, remain, arrive;
    
    public Customer(int i, int r, int a) {
        id = i;
        remain = r;
        arrive = a;
    }
}

public class Main {

    static int N, T, W;
    static Queue<Customer> que = new LinkedList<>();
    static PriorityQueue<Customer> pq = new PriorityQueue<>((c1, c2) -> c1.arrive - c2.arrive);
    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        while (N --> 0) {
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int remain = Integer.parseInt(st.nextToken());
            que.add(new Customer(id, remain, 0));
        }
        int i = Integer.parseInt(br.readLine());
        while (i --> 0) {
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int remain = Integer.parseInt(st.nextToken());
            int arrive = Integer.parseInt(st.nextToken());
            pq.add(new Customer(id, remain, arrive));
        }
    }

    static void solve() {
        StringBuilder sb = new StringBuilder();

        Customer nowCustomer = que.poll();
        int count = 0;
        for (int time = 1; time <= W; time++) {
            if (!pq.isEmpty() && pq.peek().arrive == time) que.add(pq.poll());
            
            sb.append(nowCustomer.id).append('\n');
            nowCustomer.remain--;
            count++;

            if (nowCustomer.remain == 0) {
                if (!que.isEmpty()) nowCustomer = que.poll();
                count = 0;
            }
            if (count == T) {
                que.add(nowCustomer);
                if (!que.isEmpty()) nowCustomer = que.poll();
                count = 0;
            } 
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();

    }
}