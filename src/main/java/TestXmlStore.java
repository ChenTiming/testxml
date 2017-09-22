/**
 * Created by chentm on 2016/9/20.
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class TestXmlStore {
//    public static String filePath = "C:\\Users\\chentm\\.jenkins\\userCredential.xml";
    public static String filePath = "/home/userCredentials.xml";


    public static void main(String args[]){
 ;
        System.out.println("--------");

        //writeXml();
        //parseXml();
        //add("chentm","test_1","11102");
        getIds("chentm");
        getIds("chentm");
        getIds("chentm");
        getIds("chentm");
//        delete("chentm","test_1","11102");
//        update("chentm","test_2","xxxxx");
    }

    public static void getIds(String userName){
        List<String> stringList = new ArrayList<String>();
        InputStream inputStream = null;
        try {
            File file = new File(filePath);
            if(!file.exists()){

            }
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(inputStream);
            inputStream.close();
            Element rootElement = document.getRootElement();
            List<Element> entryElements = rootElement.elements();
            for(Element element : entryElements){
                if(element.element("name").getText().equals(userName)){
                    List<Element> credentialElements = element.element("list").elements();
                    for(Element credentialElement: credentialElements){
                        stringList.add(credentialElement.element("ID").getText());
                    }
                    break;
                }
            }
            System.out.println("IDS size is " + stringList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("get ok");

    }


    public static void add(String userName,String credentialUserName,String ID){
        InputStream inputStream;
        try {
            File file = new File(filePath);
            if(!file.exists()){
                System.out.println("file not exist");
                Document document = DocumentHelper.createDocument();

                Element rootElement = document.addElement("modules");
                Element entryElement = rootElement.addElement("entry");
                entryElement.addElement("name").setText(userName);
                Element credentialElement = entryElement.addElement("list").addElement("credential");
                credentialElement.addElement("name").setText(credentialUserName);
                credentialElement.addElement("ID").setText(ID);
                Writer fileWriter = new FileWriter(filePath);
                OutputFormat o = OutputFormat.createPrettyPrint();

                XMLWriter xmlWriter = new XMLWriter(fileWriter, o);
                xmlWriter.write(document);
                xmlWriter.flush();
                xmlWriter.close();
            }
            else {
                inputStream = new FileInputStream(file);
                SAXReader saxReader = new SAXReader();
                Document document = saxReader.read(inputStream);

                Element rootElement = document.getRootElement();
                List<Element> elementList = rootElement.elements();

                boolean userNameExist = false;
                for (Element e : elementList) {
                    Element nameElement = e.element("name");
                    if (nameElement != null && nameElement.getText().equals(userName)) {
                        userNameExist = true;
                        Element list = e.element("list");
                        if (list != null) { //不考虑相同的credentialName， credential 添加的时候，会做果过滤
                            Element credentialElement = list.addElement("credential");
                            credentialElement.addElement("name").setText(credentialUserName);
                            credentialElement.addElement("ID").setText(ID);
                        }
                    }
                }

                if (!userNameExist) {
                    Element entryElement = rootElement.addElement("entry");
                    entryElement.addElement("name").setText(userName);
                    Element credentialElement = entryElement.addElement("list").addElement("credential");
                    credentialElement.addElement("name").setText(credentialUserName);
                    credentialElement.addElement("ID").setText(ID);
                }
                Writer fileWriter = new FileWriter(filePath);
                OutputFormat o = OutputFormat.createPrettyPrint();
                //o.set
                XMLWriter xmlWriter = new XMLWriter(fileWriter, o);
                xmlWriter.write(document);
                xmlWriter.flush();
                xmlWriter.close();
                inputStream.close();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch ( DocumentException e){
            e.printStackTrace();
        }
        catch (IOException e){

        }
    }

    public static void delete(String userName,String credentialUserName,String ID){

        File file = new File(filePath);
        InputStream inputStream;
        try {
            if(!file.exists()){
                Document document = DocumentHelper.createDocument();

                Element rootElement = document.addElement("modules");
                Element entryElement = rootElement.addElement("entry");
                entryElement.addElement("name").setText(userName);
                Element credentialElement = entryElement.addElement("list").addElement("credential");
                credentialElement.addElement("name").setText(credentialUserName);
                credentialElement.addElement("ID").setText(ID);
                Writer fileWriter = new FileWriter("foo.xml");
                OutputFormat o = OutputFormat.createPrettyPrint();

                XMLWriter xmlWriter = new XMLWriter(fileWriter, o);
                xmlWriter.write(document);
                xmlWriter.flush();
                xmlWriter.close();
            }
            else {
                inputStream = new FileInputStream(file);
                SAXReader saxReader = new SAXReader();
                Document document = saxReader.read(inputStream);

                Element rootElement = document.getRootElement();
                List<Element> elementList = rootElement.elements();

                boolean userNameExist = false;
                for (Element e : elementList) {
                    Element nameElement = e.element("name");
                    if (nameElement != null && nameElement.getText().equals(userName)) {
                        userNameExist = true;
                        Element list = e.element("list");
                        List<Element> credentialElememts = list.elements();
                        for(Element credentialElement : credentialElememts){
                            Element credentialNameElement = credentialElement.element("name");
                            Element credentialIdElement = credentialElement.element("ID");
                            if(credentialNameElement.getText().equals(credentialUserName) &&
                                    credentialIdElement.getText().equals(ID)){
                                credentialElememts.remove(credentialElement);
                                break;
                            }
                        }

                    }
                }

                if (!userNameExist) {
                    return;
                }
                Writer fileWriter = new FileWriter(filePath);
                OutputFormat o = OutputFormat.createPrettyPrint();
                //o.set
                XMLWriter xmlWriter = new XMLWriter(fileWriter, o);
                xmlWriter.write(document);
                xmlWriter.flush();
                xmlWriter.close();
                inputStream.close();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch ( DocumentException e){
            e.printStackTrace();
        }
        catch (IOException e){

        }
    }

    public static void update(String userName,String credentialUserName,String ID){
        InputStream inputStream;
        try {
            File file = new File(filePath);
            if(!file.exists()){
                return;
            }
            else {
                inputStream = new FileInputStream(file);
                SAXReader saxReader = new SAXReader();
                Document document = saxReader.read(inputStream);

                Element rootElement = document.getRootElement();
                List<Element> elementList = rootElement.elements();

                boolean userNameExist = false;
                for (Element e : elementList) {
                    Element nameElement = e.element("name");
                    if (nameElement != null && nameElement.getText().equals(userName)) {
                        userNameExist = true;
                        Element list = e.element("list");
                        List<Element> credentialElememts = list.elements();
                        for(Element credentialElement : credentialElememts){
                            Element credentialNameElement = credentialElement.element("name");
                            if(credentialNameElement.getText().equals(credentialUserName)){
                                credentialElement.element("ID").setText(ID);
                            }
                        }
                    }
                }

                if (!userNameExist) {
                    return;
                }
                Writer fileWriter = new FileWriter(filePath);
                OutputFormat o = OutputFormat.createPrettyPrint();
                //o.set
                XMLWriter xmlWriter = new XMLWriter(fileWriter, o);
                xmlWriter.write(document);
                xmlWriter.flush();
                xmlWriter.close();
                inputStream.close();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch ( DocumentException e){
            e.printStackTrace();
        }
        catch (IOException e){

        }
    }
}
