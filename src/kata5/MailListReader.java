package kata5;


import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailListReader{

    public static List<String> read(String fileName) {
        BufferedReader br = null;
        List<String> mails = new ArrayList<String>();
        try {
            br = new BufferedReader(new FileReader(new File(fileName)));
            String linea;
            while ((linea = br.readLine()) != null) {
            if (linea.contains("@")){
                mails.add(linea);
            }
            else{
                continue;
            }
            
        }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return mails;
    }
}
