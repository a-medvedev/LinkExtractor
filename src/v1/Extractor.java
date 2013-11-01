package v1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Extractor {

    public List<URL> extract(URL root, int depth){
        List<URL> current = new ArrayList<>(), result = new ArrayList<>();
        List<String> extracted = new ArrayList<>();
        List<URL> converted = new ArrayList<URL>();

        URL domain; //для хранения т
        try {
            domain = new URL(root.getProtocol() + "://" + root.getHost() + "/");
        } catch (MalformedURLException e) {
            System.out.println("Кажется, что-то пошло не так.");
            return null;
        }

        current.add(root);
        for (int i = 1; i <= depth; i++){
            System.out.println("Глубина " + i);

            //перебираем каждый URL, экстрагируем ссылки, добавляем их в общую копилку
            for(URL u : current){
                try {
                    URLConnection conn = u.openConnection();
                    //получаем тип содержимого по открытому соединению
                    String type = conn.getContentType();
                    //нас интересует только гипертекстовое содержимое
                    if(type.toLowerCase().contains("text") && type.toLowerCase().contains("html")){

                        InputStream is = conn.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

                        String s;
                        StringBuilder sb = new StringBuilder();
                        while ((s = reader.readLine()) != null){
                            sb.append(s);
                        }

                        //Pattern p = Pattern.compile("(href=\")([\\p{Graph}]*)(\")");
                        Pattern p = Pattern.compile("(<a[\\w|\\s]*href=\")([\\p{Graph}]*)(\")([\\w|\\s]*>)");
                        Matcher m = p.matcher(sb.toString());
                        while(m.find()){
                            String extracted_URL = m.group(2).split("[?]")[0];
                            if (!extracted.contains(extracted_URL)){
                                extracted.add(extracted_URL);
                            }
                        }
                        extracted = validate(extracted, u.toString());
                        converted = convertToURLList(extracted, false);
                        converted.removeAll(current);
                    }
                } catch (IOException e) {
                    //Соединение не открыто
                }
            }
            current = filter(converted, domain);
            result.addAll(current);
        }

        return result;
    }

    private List<URL> convertToURLList(List<String> links, boolean verbose){
        if (links.isEmpty()){
            System.out.println("Нечего конвертировать. Список пуст.");
            return null;
        }

        List<URL> result = new ArrayList<URL>();

        //counter - общее кол-во ссылок, passed -  удачно сконвертированные ссылки, failed - несконвертированные ссылки
        int counter = 0, passed = 0, failed = 0;
        for(String s : links){
            counter ++;
            URL u;
            try {
                u = new URL(s);
                result.add(u);
                passed++;
            } catch (MalformedURLException e) {
                if (verbose){
                    System.out.println("Невозможно преобразовать: " + s);
                }
                failed++;
            }
        }
        if (verbose){
            System.out.println("Всего: " + counter + "; Удачно: " + passed + "; Неудачно: " + failed);
        }

        return result;
    }

    //Функция для приведения экстрагированных URL к виду <protocol://><domain><path><parameters>
    private List<String> validate(List<String> links, String root_URL){
        List<String> result = new ArrayList<String>();
        for (String url : links){
            String wellfomed_URL;
            if (url.startsWith("#") || (url.startsWith("/#"))){
                continue;
            } else if (url.startsWith("http://") || url.startsWith("https://")){
                if(url.contains("#")){
                    url = (url.toString().split("#"))[0];
                }
                wellfomed_URL = url;
            } else if(url.startsWith("/")){
                if(url.contains("#")){
                    url = (url.toString().split("#"))[0];
                }
                wellfomed_URL = root_URL + url;
            } else {
                continue;
            }
            if (!result.contains(wellfomed_URL)){
                result.add(wellfomed_URL);
            }

        }
        return result;
    }

    //Фильтрует ссылки не относящиеся к этому домену
    private List<URL> filter(List<URL> links, URL domain){
        List<URL> result = new ArrayList<URL>();
        String str_domain = domain.getHost();
        if (str_domain.split(".").length > 2){
            int length = str_domain.split(".").length;
            str_domain = str_domain.split(".")[length-2] + str_domain.split(".")[length-2];
        }
        for (URL u : links){
            if (u.toString().contains(str_domain)){
                result.add(u);
            }
        }
        return result;
    }
}
