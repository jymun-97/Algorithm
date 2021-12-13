import java.util.LinkedList;
import java.util.Queue;

class Solution {

    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        Queue<String> cache = new LinkedList<>();

        if (cacheSize == 0) return cities.length * 5;
        for (String city : cities) {
            city = city.toLowerCase();

            if (cache.contains(city)) {
                answer++;
                cache.remove(city);
                cache.add(city);
            }
            else {
                answer += 5;

                if (cache.size() == cacheSize) cache.poll();
                cache.add(city);
            }
        }
        return answer;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}
