import java.util.*;
import java.io.*;

public class Main {

    static int n, m;
    static File[] files;
    static HashSet<String> set = new HashSet<>();

    static class File implements Comparable<File>{
        String name, extension;
        public File(String fullName) {
            StringTokenizer st = new StringTokenizer(fullName, ".");
            name = st.nextToken();
            extension = st.nextToken();
        }
    
        public int compareTo(File other) {
            if (!name.equals(other.name)) return name.compareTo(other.name);
            else {
                if (set.contains(extension) && !set.contains(other.extension)) return -1;
                else if (!set.contains(extension) && set.contains(other.extension)) return -1;
                else return extension.compareTo(other.extension);
            }
        }
        @Override
        public String toString() { return name + "." + extension; }
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        files = new File[n];

        for (int i = 0; i < n; i++) files[i] = new File(br.readLine());
        for (int i = 0; i < m; i++) set.add(br.readLine());
    }

    static void solve() {
        Arrays.sort(files);
        StringBuilder sb = new StringBuilder();

        for (File file : files) sb.append(file.toString()).append('\n');
        System.out.println(sb); 
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}