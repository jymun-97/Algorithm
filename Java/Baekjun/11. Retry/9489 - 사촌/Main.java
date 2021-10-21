import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int n, k, root;
    static ArrayList<ArrayList<Integer>> listOfDepth;
    static int[] parentOf;

    static void input() throws IOException {
        parentOf = new int[n + 1];
        listOfDepth = new ArrayList<>();
        listOfDepth.add(new ArrayList<>());
        listOfDepth.get(0).add(-1);

        StringTokenizer st = new StringTokenizer(br.readLine());
        root = Integer.parseInt(st.nextToken());
        int depth = 1, pre = root;
        int count = 1;
        ArrayList<Integer> list = new ArrayList<>();

        while (st.hasMoreTokens()) {
            int num = Integer.parseInt(st.nextToken());

            if (num == pre + 1) {
                parentOf[num] = parentOf[pre];
                list.add(num);
            }
            else {
                if (count == listOfDepth.get(depth - 1).size()) {
                    listOfDepth.add(list);
                    list = new ArrayList<>();
                    count = 0;

                    parentOf[num] = listOfDepth.get(depth - 1).get(count);
                    depth++;
                }
                else {
                    parentOf[num] = listOfDepth.get(depth - 1).get(count++);
                    list.add(num);
                }
            }
            pre = num;
        }
    }

    static void solve() {
        int ans = 0;
        for (ArrayList<Integer> list : listOfDepth) {
            if (!list.contains(k)) continue;

            for (int num : list) {
                if (parentOf[k] != parentOf[num]) ans++;
            }
        }
        sb.append(ans).append('\n');
    }

    public static void main(String[] args) throws Exception {
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            if (n == 0 && k == 0) break;

            input();
            solve();
        }
        System.out.println(sb.toString());
    }
}