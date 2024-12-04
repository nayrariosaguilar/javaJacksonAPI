/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.proven.alumnesapi.serveis;

import cat.proven.alumnesapi.Alumne;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.Array;

/**
 *
 * @author cri0625
 */
public class ClientAlumnes {

    ObjectMapper objectMapper;
    Alumne alumne;
    Alumne[] alumnesArray;

    public ClientAlumnes() {
        this.objectMapper = new ObjectMapper();

    }

    public Alumne getAlum(int id) throws ProtocolException, IOException {
        try {

            //definim URL per una pagina amb http 
            URL pageURL = new URL("http://localhost:7007/alumnes/" + id);

            //amb aquesta obtindrem HTTP code 301 (redirect)
            // ens redirigeix a https...
            // URL pageURL = new URL("http://www.proven.cat/");
            //obtenir un objecte URLConnection o subclass (HttpURLConnection)
            // a URLConnection podem modificar els paràmetres abans de fer la connexió
            // URLConnection connexio = pageURL.openConnection();
            HttpURLConnection connexioHTTP = (HttpURLConnection) pageURL.openConnection();
            //podriem afegir informació a les capçaleres
            connexioHTTP.setRequestMethod("GET");
            connexioHTTP.setRequestProperty("Accept", "application/json");

            //connectem. En realitat no és necessari
            // els mètodes getInputStream, getOutputStream fan la connexio implicitament
            connexioHTTP.connect();

            //obtenim el HTTP CODE de la resposta
            System.out.println("RESPONSE CODE: " + connexioHTTP.getResponseCode());

            //obtenir el inputStream
            StringBuilder response = new StringBuilder();
            BufferedReader in = new BufferedReader(new InputStreamReader(connexioHTTP.getInputStream()));

            //llegir linia a linia i mostrar per pantalla
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
                response.append(inputLine);
            }
            alumne = objectMapper.readValue(response.toString(), Alumne.class);
            in.close();

        } catch (MalformedURLException ex) {
            System.out.println("ERROR");
        }
        return alumne;
    }

    public void postAlum(Alumne alum) {
        try {

            //definim URL per una pagina amb http 
            URL pageURL = new URL("http://localhost:7007/alumnes/");

            //amb aquesta obtindrem HTTP code 301 (redirect)
            // ens redirigeix a https...
            // URL pageURL = new URL("http://www.proven.cat/");
            //obtenir un objecte URLConnection o subclass (HttpURLConnection)
            // a URLConnection podem modificar els paràmetres abans de fer la connexió
            // URLConnection connexio = pageURL.openConnection();
            HttpURLConnection connexioHTTP = (HttpURLConnection) pageURL.openConnection();
            //podriem afegir informació a les capçaleres
            connexioHTTP.setRequestMethod("POST");
            connexioHTTP.setRequestProperty("Content-Type", "application/json");
            //connexioHTTP.setRequestProperty("Accept", "application/json");
            connexioHTTP.setDoOutput(true);

            //connectem. En realitat no és necessari
            // els mètodes getInputStream, getOutputStream fan la connexio implicitament
            String jsonAlum = objectMapper.writeValueAsString(alum);
            OutputStream os = connexioHTTP.getOutputStream();
            os.write(jsonAlum.getBytes());
            os.flush();
            connexioHTTP.connect();
            //obtenim el HTTP CODE de la resposta
            System.out.println("RESPONSE CODE: " + connexioHTTP.getResponseCode());

            //obtenir el inputStream
            connexioHTTP.disconnect();

        } catch (MalformedURLException ex) {
            System.out.println("ERROR");
        } catch (IOException ex) {
            System.out.println("OTROS");
        }
    }

    public void deleteAlum(int id) {
        try {

            //definim URL per una pagina amb http 
            URL pageURL = new URL("http://localhost:7007/alumnes/" + id);

            //amb aquesta obtindrem HTTP code 301 (redirect)
            // ens redirigeix a https...
            // URL pageURL = new URL("http://www.proven.cat/");
            //obtenir un objecte URLConnection o subclass (HttpURLConnection)
            // a URLConnection podem modificar els paràmetres abans de fer la connexió
            // URLConnection connexio = pageURL.openConnection();
            HttpURLConnection connexioHTTP = (HttpURLConnection) pageURL.openConnection();
            //podriem afegir informació a les capçaleres
            connexioHTTP.setRequestMethod("DELETE");
            connexioHTTP.setRequestProperty("Content-Type", "application/json");
            //connexioHTTP.setRequestProperty("Accept", "application/json");

            //connectem. En realitat no és necessari
            // els mètodes getInputStream, getOutputStream fan la connexio implicitament
            System.out.println("RESPONSE CODE: " + connexioHTTP.getResponseCode());

        } catch (MalformedURLException ex) {
            System.out.println("ERROR");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void listarAlumnos() {
        try {
            URL pageURL = new URL("http://localhost:7007/alumnes/");
            HttpURLConnection connexioHTTP = (HttpURLConnection) pageURL.openConnection();
            connexioHTTP.setRequestMethod("GET");
            connexioHTTP.connect();
            //mapejar el JSON amb la classe Alumne, fent servir array
            System.out.println("RESPONSE CODE: " + connexioHTTP.getResponseCode());

            //obtenir el inputStream
            StringBuilder response = new StringBuilder();
            BufferedReader in = new BufferedReader(new InputStreamReader(connexioHTTP.getInputStream()));

            //llegir linia a linia i mostrar per pantalla
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
                response.append(inputLine);
            }
            alumnesArray = objectMapper.readValue(response.toString(), Alumne[].class);
            for (Alumne al : alumnesArray) {
                System.out.println(al);
            }

            in.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

   
}
