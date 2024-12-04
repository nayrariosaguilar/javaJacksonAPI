package cat.proven.alumnesapi.serveis;
import cat.proven.alumnesapi.Alumne;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.*;
import java.io.*;
import java.util.Map;
import java.util.List;

/**
 * Llegir un recurs per http:
 * - instanciant la URL
 * - obrint la connexió
 * - llegir del inputStream de la URL
 * 
 * https://docs.oracle.com/javase/8/docs/api/java/net/URL.html
 * https://docs.oracle.com/javase/8/docs/api/java/net/HttpURLConnection.html
 * 
 * @author mercedes
 */
public class ClienteHTTP {
  
    
    public static void main(String[] args)  {
      
      
        //get
        Alumne exampleAl = new Alumne(0, "123456cc", "nay", "rios", "nayrios@gmail.com", "7932222", 2);
          ClientAlumnes client = new ClientAlumnes();
       // client.postAlum(exampleAl);
       // client.deleteAlum(1);
       
        
        
}
}
