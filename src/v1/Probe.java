package v1;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Probe {
    public static void main(String[] args) {

    }

    private void URLTest() throws MalformedURLException {
        URL rambler = new URL("http://www.rambler.ru/mail/inbox");
        System.out.println(rambler.getHost());
        System.out.println(rambler.getRef());
        System.out.println(rambler.getProtocol());
        System.out.println();
        System.out.println();
        System.out.println();
    }

    private void fromURL() throws IOException{
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
