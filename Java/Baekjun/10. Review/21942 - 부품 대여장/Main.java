import java.util.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {

    static int n, penalty, period;
    static HashMap<String, String> map = new HashMap<>();
    static HashMap<String, Long> target = new HashMap<>();

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        period = toPeriod(st.nextToken());
        penalty = Integer.parseInt(st.nextToken());

        while (n --> 0) {
            st = new StringTokenizer(br.readLine());
            String date = st.nextToken() + " " + st.nextToken();
            String info = st.nextToken() + " " + st.nextToken();

            if (map.containsKey(info)) {
                check(info, date);
                map.remove(info);
            }
            else {
                map.put(info, date);
            }
        }
    }

    static void check(String info, String returnDate) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date return_date = format.parse(returnDate);
            Date loan_date = format.parse(map.get(info));
            
            long diff = (return_date.getTime() - loan_date.getTime()) / 1000 / 60;
            
            if (diff > period) {
                String name = info.split(" ")[1];
                long money = (diff - period) * penalty;
                target.put(name, target.getOrDefault(name, 0L) + money);
            }
        } catch (ParseException e) { }
    }   

    static int toPeriod(String s) {
        StringTokenizer st = new StringTokenizer(s, "/");
        int dd = Integer.parseInt(st.nextToken());
        
        String temp = st.nextToken();
        st = new StringTokenizer(temp, ":");
        int hh = Integer.parseInt(st.nextToken());
        int mm = Integer.parseInt(st.nextToken());

        return dd * 24 * 60 + hh * 60 + mm;
    }

    static void solve() {
        ArrayList<String> list = new ArrayList<>();
        
        for (String name : target.keySet()) {
            list.add(name);
        }
        Collections.sort(list);

        if (list.size() == 0) {
            System.out.println(-1);
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (String name : list) {
            sb.append(name).append(' ').append(target.get(name)).append('\n');
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}