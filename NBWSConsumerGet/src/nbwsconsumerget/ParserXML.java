/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nbwsconsumerget;

import java.io.IOException;
import javax.lang.model.element.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.NodeList;

import java.util.Vector;

/**
 *
 * @author Loris
 */
public class ParserXML {
    Vector<Contatto> listContatto;
    int numEl;
    
    public ParserXML(){
        listContatto = new Vector<Contatto>();
        numEl = 0;
    }
    
    public void parseString(String stringxml) throws org.xml.sax.SAXException, IOException, ParserConfigurationException{
        DocumentBuilderFactory factory;
        DocumentBuilder builder;
        Element root, element;
        NodeList nodelist;
        
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        
        
    }

    

    
}
