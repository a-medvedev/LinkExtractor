package v2;

import java.net.URL;
import java.util.List;

public class Extractor{
    //Удаляет дубликаты в списке URL'ов
    private List<URL> removeDuplicates(List<URL> links){

    }

    //Удаляет дубликаты в списке строк
    private List<String> removeDuplicates(List<String> links){

    }

    //преобразует поэлементно, если возможно, список строк в список URL
    private List<URL> toURLList(List<String> links){

    }

    //преобразует относительные ссылки к абсолютным
    public List<URL> castToAbsolute(List<String> links, URL root){

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
