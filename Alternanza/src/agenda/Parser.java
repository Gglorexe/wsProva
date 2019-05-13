package agenda;

import java.io.IOException;
import java.sql.Time;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class Parser {

    private ArrayList<Azienda> aziende;

    public Parser() {
        aziende = new ArrayList<>();
    }

    public ArrayList<Azienda> parseDocument(String filename) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory;
        DocumentBuilder builder;
        Document document;
        Element root, element;
        NodeList nodelist;
        Azienda p;
        // creazione dell’albero DOM dal documento XML
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        document = builder.parse(filename);
        root = document.getDocumentElement();
        
        nodelist = root.getElementsByTagName("impegno");
        String titoloCalendario = root.getElementsByTagName("impegno").item(0).getTextContent();
        
        System.out.print(titoloCalendario);
        
        
        if (nodelist != null && nodelist.getLength() > 0) {
            
            for(int i=0; i<nodelist.getLength(); i++) {
                element = (Element) nodelist.item(i);
                p = getAzienda(element);
                if (p != null) {
                        aziende.add(p);

                    }
                }
            }
        
        return aziende;
    }

    private Azienda getAzienda(Element el){
        Azienda azienda = null;
        
        String nome = el.getElementsByTagName("nome").item(0).getTextContent(); //getChildNodes().item(0).getTextContent(); getTextValue(elementParent, "td", 0);
        String indirizzo = el.getElementsByTagName("indirizzo").item(0).getTextContent(); //getTextValue(elementParent, "td", 1);
        String email = el.getElementsByTagName("email").item(0).getTextContent(); //getTextValue(elementParent, "td", 2);
        String tutor = el.getElementsByTagName("tutor").item(0).getTextContent(); //getTextValue(elementParent, "td", 3);
        
        azienda = new Azienda(nome, indirizzo, email, tutor);
        return azienda;
    }
    
//    private Impegno getImpegno(Element el, String nomeCalendario) {
//        Impegno impegno = null;
//
//        // cerco il primo elemento nel primo tr
//        //Element elementParent = (Element) el.getParentNode();
//        String nome = el.getElementsByTagName("nome").item(0).getTextContent(); //getChildNodes().item(0).getTextContent(); getTextValue(elementParent, "td", 0);
//        String descrizione = el.getElementsByTagName("descrizione").item(0).getTextContent(); //getTextValue(elementParent, "td", 1);
//        String data = el.getElementsByTagName("data").item(0).getTextContent(); //getTextValue(elementParent, "td", 2);
//        String luogo = el.getElementsByTagName("luogo").item(0).getTextContent(); //getTextValue(elementParent, "td", 3);
//        String aule = el.getElementsByTagName("aule").item(0).getTextContent();
//        String oraInizio = el.getElementsByTagName("oraInizio").item(0).getTextContent();
//        String oraFine = el.getElementsByTagName("oraFine").item(0).getTextContent();
//        
//        /*
//        String date[]=dataS.split("/");
//        Date data=new Date(Integer.parseInt(date[2]),Integer.parseInt(date[1]),Integer.parseInt(date[0]));
//        */
//        /* FUNZIONA MA DA IMPLEMENTARE
//        Time oraInizio=null;
//        Time oraFine=null;
//        if(!oraInizioS.equals("")){
//            String oreInizio[]=oraInizio.split(":");
//            oraInizio=new Time(Integer.parseInt(oreInizio[0]),Integer.parseInt(oreInizio[1]),0);
//            String oreFine[]=oraFine.split(":");
//            oraFine=new Time(Integer.parseInt(oreFine[0]),Integer.parseInt(oreFine[1]),0);
//        }
//        */
//        //creo l'oggetto del prof
//        impegno = new Impegno(nomeCalendario, nome, descrizione, data, luogo, aule, oraInizio, oraFine);
//        return impegno;
//
//    }

    // restituisce il valore testuale dell’elemento figlio specificato
    private String getTextValue(Element element, String tag, int child) {
        String value = null;
        NodeList nodelist;
        nodelist = element.getElementsByTagName(tag);
        if (nodelist != null && nodelist.getLength() > child) {
            Node nodeChild = nodelist.item(child).getFirstChild();
            if ((nodeChild != null) && nodeChild.hasChildNodes()) {
                value = nodeChild.getFirstChild().getNodeValue();
            }
        }
        return value;
    }

}
