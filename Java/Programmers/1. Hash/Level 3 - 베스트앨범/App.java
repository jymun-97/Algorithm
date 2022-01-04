import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

class Solution {
    class Genre implements Comparable<Genre> {
        ArrayList<Music> musics = new ArrayList<>();
        String name;
        long playCount;

        public Genre(String name) {
            this.name = name;
        }
        public int compareTo(Genre other) {
            if (playCount < other.playCount) return 1;
            else return -1;
        }
    }

    class Music implements Comparable<Music> {
        int id, plays;
        public Music(int id, int plays) {
            this.id = id;
            this.plays = plays;
        }
        public int compareTo(Music other) {
            if (plays != other.plays) return other.plays - plays;
            return id - other.id; 
        }
    }

    public int[] solution(String[] genres, int[] plays) {
        int n = genres.length, idx = 0;
        ArrayList<Integer> answer = new ArrayList<>();
        ArrayList<Genre> list = new ArrayList<>();
        HashMap<String, Integer> toIndex = new HashMap<>();

        for (int i = 0; i < n; i++) {
            if (!toIndex.containsKey(genres[i])) {
                toIndex.put(genres[i], idx++);
                list.add(new Genre(genres[i]));
            }
            Genre genre = list.get(toIndex.get(genres[i]));
            Music music = new Music(i, plays[i]);

            genre.musics.add(music);
            genre.playCount += music.plays;
        }
        
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            Genre genre = list.get(i);
            Collections.sort(genre.musics);
            if (genre.musics.size() > 0) answer.add(genre.musics.get(0).id);
            if (genre.musics.size() > 1) answer.add(genre.musics.get(1).id);
        }

        for (Genre genre : list) {
            System.out.println("Genre: " + genre.name + ", Count: " + genre.playCount);
            for (Music music : genre.musics) {
                System.out.print(music.id + " ");
            }
            System.out.println();
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();
        int[] result = s.solution(new String[] {"classic", "pop", "classic", "classic", "pop"}, new int[] {500, 600, 150, 800, 2500});
        System.out.println(Arrays.toString(result));
    }
}

// 1h 10m