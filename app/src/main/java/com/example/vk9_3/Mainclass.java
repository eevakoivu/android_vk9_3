package com.example.vk9_3;

import android.os.StrictMode;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Mainclass {
    //haluttu URL: ID ja pvm
    //https://www.finnkino.fi/xml/Schedule/?area=ID&dt=pp.kk.vvvv
    //esitystiedot kun teatteri on valittu ListViewviin
    //HUOM! ID muutokset

    public ArrayList<Theaterinfo> readXML() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ArrayList<Theaterinfo> arrayList = null;

        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            String urlString = "https://www.finnkino.fi/xml/TheatreAreas/";
            Document doc = builder.parse(urlString);
            doc.getDocumentElement().normalize();
            //System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getDocumentElement().getElementsByTagName("TheatreArea");

            arrayList = new ArrayList<Theaterinfo>(); //luodaan lista olioista

            for(int i=0; i<nList.getLength(); i++){
                Node node = nList.item(i); //yhden teatterin tiedot

                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;

                    int ID = Integer.valueOf(element.getElementsByTagName("ID").item(0).getTextContent());
                    String name = element.getElementsByTagName("Name").item(0).getTextContent();

                    if(i>0){ // ei lisätä tietoja "Valitse alue/teatteri"
                        arrayList.add(new Theaterinfo(name, ID)); //lisätään olio listalle
                    }

                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public void readmoviesXML(int did ) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ArrayList<Theaterinfo> arrayList = null;

        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            String urlString = "https://www.finnkino.fi/xml/Schedule/?area="+arrayList.get(id).getID()+"&dt=pp.kk.vvvv";
            Document doc = builder.parse(urlString);
            doc.getDocumentElement().normalize();
            //System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getDocumentElement().getElementsByTagName("TheatreArea");

            arrayList = new ArrayList<Theaterinfo>(); //luodaan lista olioista

            for(int i=0; i<nList.getLength(); i++){
                Node node = nList.item(i); //yhden teatterin tiedot

                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;

                    int ID = Integer.valueOf(element.getElementsByTagName("ID").item(0).getTextContent());
                    String name = element.getElementsByTagName("Name").item(0).getTextContent();

                    if(i>0){ // ei lisätä tietoja "Valitse alue/teatteri"
                        arrayList.add(new Theaterinfo(name, ID)); //lisätään olio listalle
                    }

                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
