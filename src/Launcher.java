import java.net.MalformedURLException;
import java.net.URL;

public class Launcher {
    public static void main(String[] args) throws MalformedURLException {
        URL u = new URL("http://kstatida.ru");
        Extractor e = new Extractor();
        for(URL u1 : e.extract(u, 2)){
            System.out.println(u1.toString());
        }
    }
}
