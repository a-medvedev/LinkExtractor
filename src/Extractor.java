import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Extractor {

    public List<URL> extract(URL root, int depth){
        List<URL> current = new ArrayList<>(), result = new ArrayList<>();
        List<String> extracted = new ArrayList<>();

        current.add(root);
        for (int i = 1; i == depth; i++){
            System.out.println("Глубина " + i);

            //перебираем каждый URL, экстрагируем ссылки, добавляем их в общую копилку
            for(URL u : current){
                try {
                    URLConnection conn = u.openConnection();
                    //получаем тип содержимого по открытому соединению
                    String type = conn.getContentType();
                    //нас интересует только гипертекстовое содержимое
                    if(type.toLowerCase().contains("text") && type.toLowerCase().contains("html")){
                        Scanner input = new Scanner(conn.getInputStream()); //для построчного чтения страницы
                        StringBuilder contentBuilder = new StringBuilder(); //для соединения всех строк в одну

                        //Склеиваем страницу, пока есть из чего
                        while (input.hasNextLine()){
                            contentBuilder.append(input.nextLine());
                        }
                        String content = contentBuilder.toString(); //Страмица целиком

                    }
                } catch (IOException e) {
                    //Соединение не открыто
                }
            }
        }
    }

    private List<URL> convertToURLList(List<String> links){
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
                System.out.println("Невозможно преобразовать: " + s);
                failed++;
            }
        }
        System.out.println("Всего: " + counter + "Удачно: " + passed + "Неудачно: " + failed);
        return result;
    }
}
