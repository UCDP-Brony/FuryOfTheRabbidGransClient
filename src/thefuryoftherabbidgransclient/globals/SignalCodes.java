/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thefuryoftherabbidgransclient.globals;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author TheDoctor
 */
public class SignalCodes {
    private static SignalCodes instance = null;
    private Document lDoc;
    private Document gDoc;
    
    public static SignalCodes getInstance(){
        if(instance == null){
            instance = new SignalCodes();
        }
        return instance;
    }
    
    private SignalCodes(){
        try{
            File languageFile = new File("./src/ressources/config/lang.xml");
            this.lDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(languageFile);
            this.lDoc.getDocumentElement().normalize();
                                    
            String language = lDoc.getElementsByTagName("Langue").item(0).getTextContent();
            File xmlFile = new File("./src/ressources/config/"+language+"/signals.xml");
            this.gDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlFile);
            this.gDoc.getDocumentElement().normalize();                     
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(SignalCodes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getTextFromSignalCode(String signalCode){
        return this.gDoc.getElementsByTagName(signalCode).item(0).getTextContent();
    }
}