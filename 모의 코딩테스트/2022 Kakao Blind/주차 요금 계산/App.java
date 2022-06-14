import java.util.*;

class Solution {

    public int[] solution(int[] fees, String[] records) {
        HashSet<String> set = new HashSet<>();
        HashMap<String, Integer> time = new HashMap<>();
        HashMap<String, String> inTime = new HashMap<>();

        for (String record : records) {
            StringTokenizer st = new StringTokenizer(record);

            String t = st.nextToken();
            String car = st.nextToken();
            String act = st.nextToken();

            set.add(car);
            if (act.equals("IN")) {
                inTime.put(car, t);
            }
            else {
                time.put(car, time.getOrDefault(car, 0) + calc(inTime.get(car), t));
                inTime.remove(car);
            }
        }
        
        for (String car : inTime.keySet()) {
            time.put(car, time.getOrDefault(car, 0) + calc(inTime.get(car), "23:59"));
        }

        ArrayList<String> cars = new ArrayList<>();
        for (String car : set) cars.add(car);
        
        Collections.sort(cars);

        int[] answer = new int[cars.size()];
        for (int i = 0; i < cars.size(); i++) {
            answer[i] = fees[1];
            int stacked_time = time.get(cars.get(i));

            if (stacked_time > fees[0]) {
                int temp = (stacked_time - fees[0]) % fees[2] == 0 ? (stacked_time - fees[0]) / fees[2] : (stacked_time - fees[0]) / fees[2] + 1;
                answer[i] += temp * fees[3];
            }
        }

        return answer;
    }

    int calc(String in, String out) {
        StringTokenizer st = new StringTokenizer(in, ":");
        int in_min = Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());

        st = new StringTokenizer(out, ":");
        int out_min = Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());

        return out_min - in_min;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        int[] fees = {180,5000,10,600};
        String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};

        System.out.println(Arrays.toString(s.solution(fees, records)));
    }
}
