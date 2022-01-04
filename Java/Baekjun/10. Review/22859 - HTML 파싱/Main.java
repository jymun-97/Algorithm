import java.util.*;
import java.io.*;

public class Main {

    static String html;
    static StringBuilder sb = new StringBuilder();

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        html = br.readLine();

        html = html.substring(6, html.length() - 7);
    }
    static void solve() {
        div();

        // System.out.println(sb);
    }

    static String readNextTag() {
        String tag = "";
        for (int i = 1; html.charAt(i) != '>'; i++) {
            tag += html.charAt(i);
        }
        return tag.replaceAll(" ", "");
    }

    static void div() {
        html = html.substring(5);
        
        String div = "";
        for (int i = 0; html.charAt(i) != '>'; i++) {
            div += html.charAt(i);
        }
        String[] tokens = div.split("\"");
        sb.append("title : ").append(tokens[1]);

        html = html.replaceFirst(div, "");
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}