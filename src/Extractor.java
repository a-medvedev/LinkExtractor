import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Extractor {
    private URL current;
    private List<String> extracted_links = new ArrayList<String>();


    Extractor(String url) throws MalformedURLException {
        if (url.isEmpty()){
            throw new MalformedURLException();
        } else {
            current = new URL(url);
            extracted_links.add(current.toString());
        }
    }

    public List<String> extract(List<String> links){
        List<String>
    }
}
