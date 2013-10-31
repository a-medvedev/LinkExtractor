import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Probe {
    public static void main(String[] args) throws IOException {

//        System.out.print("Input filename: ");
//        Scanner input = new Scanner(System.in);
        //String str = input.nextLine();

       // File f = new File(input.nextLine());
        File f = new File("/home/tantal/bash.html");
        //Scanner s = new Scanner(f);

        BufferedReader reader = new BufferedReader(new FileReader(f));
        String s;
        StringBuilder sb = new StringBuilder();
        while ((s = reader.readLine()) != null){
            //System.out.println(reader);
            sb.append(s);
        }
        //System.out.println(sb.toString());

        Pattern p = Pattern.compile("(href=\")([\\w|_|\\|/]*)(\")");
        Matcher m = p.matcher(sb.toString());
        while(m.find()){
            //System.out.println(sb.toString());

            System.out.println(m.group(2));
        }
    }
}
