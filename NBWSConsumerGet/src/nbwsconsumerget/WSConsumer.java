/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nbwsconsumerget;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;
import org.w3c.dom.Document;
import com.jcabi.xml.*;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import org.json.JSONObject;

/**
 *
 * @author scuola
 */
public class WSConsumer {

    private String result;
    private String parseResult;
    // http://www.gerriquez.com/web-service-comuni-italiani.html
    private String prefix = "http://localhost:8080/WS_Server";

    WSConsumer() {
        result = "";
        parseResult ="";
    }

    public String getResult() {
        return result;
    }
    
    public String getParseResult() {
        return parseResult;
    }

    public int getUtente(String nome, String cognome, boolean descr) throws Exception {
        int status = 0;
        result = "";
        String resultParse = "";

        prefix += "/getUtente/";
        try {
            URL serverURL;
            HttpURLConnection service;
            BufferedReader input;
            String url = "";
            if(descr){
                url = prefix
                    + URLEncoder.encode(nome + "_" + cognome, "UTF-8");
                url+= "?descr=si";
            }
            else{
               url = prefix
                    + URLEncoder.encode(nome + "_" + cognome, "UTF-8"); 
            }       
            serverURL = new URL(url);
            service = (HttpURLConnection) serverURL.openConnection();
            // impostazione header richiesta
            service.setRequestProperty("Host", "localhost:8080");
            service.setRequestProperty("Accept", "application/text");
            service.setRequestProperty("Accept-Charset", "UTF-8");
            // impostazione metodo di richiesta GET
            service.setRequestMethod("GET");
            // attivazione ricezione
            service.setDoInput(true);
            // connessione al web-service
            service.connect();
            // verifica stato risposta
            status = service.getResponseCode();
            if (status != 200) {
                return status; // non OK
            }
            // apertura stream di ricezione da risorsa web
            input = new BufferedReader(new InputStreamReader(service.getInputStream(), "UTF-8"));
            // ciclo di lettura da web e scrittura in result
            String line;
            String result = "";
            while ((line = input.readLine()) != null) {
                result += line;
                //parsing su line
            }
            parseResult = parseJSON(result);
            //System.out.println(parseJSON(result));
            input.close();

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(WSConsumer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(WSConsumer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WSConsumer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
        //return resultParse;
    }
    
    public int getAllUtenti(){
        int status = 0;
        result = "";
        String resultParse = "";

        prefix += "/getAll/*";
        try {
            URL serverURL;
            HttpURLConnection service;
            BufferedReader input;
            String url = "";    
            serverURL = new URL(url);
            service = (HttpURLConnection) serverURL.openConnection();
            // impostazione header richiesta
            service.setRequestProperty("Host", "localhost:8080");
            service.setRequestProperty("Accept", "application/text");
            service.setRequestProperty("Accept-Charset", "UTF-8");
            // impostazione metodo di richiesta GET
            service.setRequestMethod("GET");
            // attivazione ricezione
            service.setDoInput(true);
            // connessione al web-service
            service.connect();
            // verifica stato risposta
            status = service.getResponseCode();
            if (status != 200) {
                return status; // non OK
            }
            // apertura stream di ricezione da risorsa web
            input = new BufferedReader(new InputStreamReader(service.getInputStream(), "UTF-8"));
            // ciclo di lettura da web e scrittura in result
            String line;
            String result = "";
            while ((line = input.readLine()) != null) {
                result += line;
                //parsing su line
            }
            parseResult = parseJSON(result);
            //System.out.println(parseJSON(result));
            input.close();

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(WSConsumer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(WSConsumer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WSConsumer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
        
    }
    
    public int addUtente(String nome, String cognome, String numero, String descr){
        int status = 0;
        result = "";
        String resultParse = "";

        try {
            URL serverURL;
            HttpURLConnection service;
            BufferedReader input;
            String url = "";
            //url = prefix + "?nome="+nome+"&cognome="+cognome+"&numero="+numero+
            //       "&descr="+descrizione;    
            url = prefix + "/addUtente";
            serverURL = new URL(url);
            
            service = (HttpURLConnection) serverURL.openConnection();
            // impostazione metodo di richiesta GET
            service.setRequestMethod("POST");
            service.setRequestProperty("Content-Type", 
           "application/json");
            // impostazione header richiesta
            //service.setRequestProperty("Host", "localhost:8080");
            //service.setRequestProperty("Accept", "application/text");
            //service.setRequestProperty("Accept", "application/json");
            //service.setRequestProperty("Content-Type", "application/json; utf-8");
            //service.setRequestProperty("Accept-Charset", "UTF-8");
            
            // attivazione ricezione
            service.setUseCaches(false);
            service.setDoInput(true);
            service.setDoOutput(true);
            
            // connessione al web-service
            service.connect();
            // verifica stato risposta
            status = service.getResponseCode();
            if (status != 200) {
                return status; // non OK
            }
            OutputStream os = service.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            String jsonString = CreateJSONString(nome, cognome, numero, descr);
            osw.write(jsonString);
            os.close();
            osw.close();
            
            // apertura stream di ricezione da risorsa web
            input = new BufferedReader(new InputStreamReader(service.getInputStream(), "UTF-8"));
            // ciclo di lettura da web e scrittura in result
            String line;
            String result = "";
            while ((line = input.readLine()) != null) {
                result += line;
                //parsing su line
            }
            parseResult = parseJSON(result);
            //System.out.println(parseJSON(result));
            input.close();

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(WSConsumer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(WSConsumer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WSConsumer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
        //return resultParse;
    }
    
    public String CreateJSONString(String nome, String cognome, String numero, String descr){
        JSONObject obj = new JSONObject();
        obj.put("nome:", nome);
        obj.put("cognome:", cognome);
        obj.put("numero:", numero);
        obj.put("descrizione:", descr);
        return obj.toString();
    }

    public String parseJSON(String stringa) {
        JSONObject obj = new JSONObject(stringa);
        String result = "";
        String nome = obj.getString("name:");
        result = "Nome: " + nome + "\n";
        String cognome = obj.getString("cognome:");
        result += "Cognome: " + cognome + "\n";
        String numero = obj.getString("telefono:");
        result += "Numero: " + numero + "\n";
        
        //String result = "Nome: " + nome + "- Numero: " + numero;
        try{
            String descr = obj.getString("descrizione:");
            result += "Descrizione: "+ descr + "\n";
        }catch(Exception e){
            return result;
        }

        return result;
    }

//    public int get(String paramater, String value) {
//        int status = 0;
//        result = "";
//
//        try {
//            URL serverURL;
//            HttpURLConnection service;
//            BufferedReader input;
//
//            String url = prefix
//                    + URLEncoder.encode(paramater, "UTF-8") + "="
//                    + URLEncoder.encode(value, "UTF-8");
//            serverURL = new URL(url);
//            service = (HttpURLConnection) serverURL.openConnection();
//            // impostazione header richiesta
//            service.setRequestProperty("Host", "localhost:8080");
//            service.setRequestProperty("Accept", "application/text");
//            service.setRequestProperty("Accept-Charset", "UTF-8");
//            // impostazione metodo di richiesta GET
//            service.setRequestMethod("GET");
//            // attivazione ricezione
//            service.setDoInput(true);
//            // connessione al web-service
//            service.connect();
//            // verifica stato risposta
//            status = service.getResponseCode();
//            if (status != 200) {
//                return status; // non OK
//            }
//            // apertura stream di ricezione da risorsa web
//            input = new BufferedReader(new InputStreamReader(service.getInputStream(), "UTF-8"));
//            // ciclo di lettura da web e scrittura in result
//            String line;
//            while ((line = input.readLine()) != null) {
//
//            }
//            input.close();
//
//            //PARSER XMLLL
//        } catch (UnsupportedEncodingException ex) {
//            Logger.getLogger(WSConsumer.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (MalformedURLException ex) {
//            Logger.getLogger(WSConsumer.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(WSConsumer.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return status;
//    }

    void printResult() {
        String[] arrOfStr = result.split("\",\"");

        for (int i = 0; i < arrOfStr.length; i++) {
            System.out.println(arrOfStr[i]);
        }
    }
}
