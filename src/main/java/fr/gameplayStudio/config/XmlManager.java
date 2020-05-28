package fr.gameplayStudio.config;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class XmlManager {

    XmlConstants xmlConstants=new XmlConstants();

    public void getSettingsValue(){
        final String xmlConfigPath = "F:/Mes Documents/OpenClassroom/project/Escape Game ONLINE/src/main/java/fr/gameplayStudio/config/config.xml";
        try {
            final DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            final DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            final Document doc = dBuilder.parse(new File(xmlConfigPath));
            doc.getDocumentElement().normalize();

            final Element root = doc.getDocumentElement();

            final NodeList rootNodes = root.getChildNodes();
            final int nbRootNode = rootNodes.getLength();

            for (int i = 0; i < nbRootNode; i++) {
                if (rootNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    final Element config = (Element) rootNodes.item(i);
                    final Element dev = (Element)  config.getElementsByTagName("dev").item(0);
                    final Element secret = (Element) config.getElementsByTagName("secret").item(0);
                    final Element attempt = (Element) config.getElementsByTagName("attempt").item(0);

                    xmlConstants.setDevMode(Boolean.valueOf(dev.getTextContent()));
                    xmlConstants.setTailleCombinaison(Integer.valueOf(secret.getTextContent()));
                    xmlConstants.setTentative(Integer.valueOf(attempt.getTextContent()));
                }
            }
        } catch (final ParserConfigurationException e) {
            e.printStackTrace();
        } catch (final SAXException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
    public void setDevValue(String value){

        final String xmlConfigPath = "F:/Mes Documents/OpenClassroom/project/Escape Game ONLINE/src/main/java/fr/gameplayStudio/config/config.xml";
        try {
            final DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            final DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            final Document doc = dBuilder.parse(new File(xmlConfigPath));
            doc.getDocumentElement().normalize();

            final Element root = doc.getDocumentElement();

            final NodeList rootNodes = root.getChildNodes();
            final int nbRootNode = rootNodes.getLength();

            for (int i = 0; i < nbRootNode; i++) {
                if (rootNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    final Element config = (Element) rootNodes.item(i);
                    final Element dev = (Element)  config.getElementsByTagName("dev").item(0);
                    dev.setTextContent(value);
                    xmlConstants.setDevMode(Boolean.valueOf(value));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

