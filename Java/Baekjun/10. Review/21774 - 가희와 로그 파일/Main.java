import java.util.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {

    static int n, q;
    static ArrayList<Long>[] list;
    static SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    static StringBuilder sb = new StringBuilder();
    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        list = new ArrayList[7];
        for (int i = 1; i <= 6; i++) list[i] = new ArrayList<>();

        try {
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine(), "#");
                String str = st.nextToken();
                Date date = format.parse(str);
                int level = Integer.parseInt(st.nextToken());
                list[level].add(date.getTime());
            }
            for (int i = 1; i <= 6; i++) Collections.sort(list[i]);

            while (q --> 0) {
                st = new StringTokenizer(br.readLine(), "#");
                Long date1 = format.parse(st.nextToken()).getTime();
                Long date2 = format.parse(st.nextToken()).getTime();
                int l = Integer.parseInt(st.nextToken());

                int count1 = 0, count2 = 0;
                for (int level = l; level <= 6; level++) {
                    count1 += lower_cnt(date1, level);
                    count2 += higher_cnt(date2, level);
                }
                sb.append(n - count2 - count1).append('\n');
            }
        } catch (ParseException e) { }
    }

    static int lower_cnt(long target, int level) {
        int l = 0, r = list[level].size() - 1;
        int ans = 0;

        while (l <= r) {
            int mid = (l + r) / 2;

            if (list[level].get(mid) > target) {
                r = mid - 1;
                ans = mid;
            }
            else {
                l = mid + 1;
            }
        }
        return ans;
    }
    
    static int higher_cnt(long target, int level) {
        int l = 0, r = list[level].size() - 1;
        int ans = 0;

        while (l <= r) {
            int mid = (l + r) / 2;

            if (list[level].get(mid) < target) {
                l = mid + 1;
                ans = mid;
            }
            else {
                r = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) throws Exception {

        input();
        System.out.println(sb);

        
    }
}