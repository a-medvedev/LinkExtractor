import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Probe {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://kstatida.ru");
        URLConnection conn = url.openConnection();
        InputStream is = conn.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        String s;
        StringBuilder sb = new StringBuilder();
        while ((s = reader.readLine()) != null){
            sb.append(s);
        }

        Pattern p = Pattern.compile("(href=\")([\\p{Graph}]*)(\")");
        Matcher m = p.matcher(sb.toString());
        while(m.find()){
            //Разбивать по знаку вопроса, чтобы отделить параметры ссылки от самой ссылки.
            //Добавить алгоритмы распознавания файлов, отсев одинаковых ссылок
            System.out.println(m.group(2).split("[?]")[0]);

        }
    }

    private void fromFile() throws IOException {
        File f = new File("/home/tantal/bash.html");

        BufferedReader reader = new BufferedReader(new FileReader(f));
        String s;
        StringBuilder sb = new StringBuilder();
        while ((s = reader.readLine()) != null){
            sb.append(s);
        }

        Pattern p = Pattern.compile("(href=\")([\\p{Graph}]*)(\")");
        Matcher m = p.matcher(sb.toString());
        while(m.find()){
            //Разбивать по знаку вопроса, чтобы отделить параметры ссылки от самой ссылки.
            //Добавить алгоритмы распознавания файлов, отсев одинаковых ссылок
            System.out.println(m.group(2).split("[?]")[0]);

        }
    }
}
