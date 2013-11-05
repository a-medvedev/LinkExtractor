package v2;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Extractor{
    //Удаляет дубликаты в списке URL'ов
    private List<URL> removeDuplicates(List<URL> links){
        Set<URL> links_set = new HashSet<URL>(links);
        List<URL> result = new ArrayList<URL>(links_set);
        return result;
    }

    //Удаляет дубликаты в списке строк
    private List<String> removeDuplicates(List<String> links){
        Set<String> links_set = new HashSet<String>(links);
        List<String> result = new ArrayList<String>(links_set);
        return result;
    }

    //преобразует поэлементно, если возможно, список строк в список URL
    private List<URL> toURLList(List<String> links){
        if (links.isEmpty()){
            System.out.println("Нечего конвертировать. Список пуст.");
            return null;
        }

        List<URL> result = new ArrayList<URL>();

        for(String s : links){
            URL u;
            try {
                u = new URL(s);
                result.add(u);
            } catch (MalformedURLException e) {
                System.out.println("Невозможно преобразовать: " + s);
            }
        }

        return result;
    }

    //преобразует относительные ссылки к абсолютным
    public List<URL> castToAbsolute(List<String> links, URL root){
        List<String> result = new ArrayList<String>();
        for (String url : links){
            String wellformed_URL;

            if (url.startsWith("#") || (url.startsWith("/#"))){
                continue;
            } else if (url.startsWith("http://") || url.startsWith("https://")){
                wellformed_URL = url;
            } else if(url.startsWith("/")){
                wellformed_URL = root + url;
            } else {
                continue;
            }
            if (!result.contains(wellformed_URL)){
                result.add(wellformed_URL);
            }

        }
        return toURLList(result);
    }

    //получает ссылки по указанному адресу
    public List<URL> extract(URL start, int depth){

    }

    //удаляет относительные ссылки из массива
    public List<URL> deleteRelativeLinks(List<String> links){

    }

    //удаляет ссылки на другие сайты из списка
    public List<URL> filter(List<URL> links, URL domain){

    }

    //возвращает ссылки на другие сайты
    public List<URL> getForeign(List<URL> links, URL domain){

    }

    //проверяет, существует ли ссылка. Возвращает список существующих ссылок
    public List<URL> validate(List<URL> links){

    }

    //обрезает якоря и параметры в списке ссылок
    public List<URL> trim(List<URL> links){

    }
}
