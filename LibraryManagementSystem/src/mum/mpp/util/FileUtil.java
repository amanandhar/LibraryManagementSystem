package mum.mpp.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Helper class for reading and writing files.
 */
public class FileUtil {

    /**
     * The character set. UTF-8 works good for windows, mac and Umlaute.
     */
    private static final Charset CHARSET = Charset.forName("UTF-8");

    /**
     * Reads the specified file and returns the content as a String.
     * 
     * @param file
     * @return
     * @throws IOException thrown if an I/O error occurs opening the file
     */
    public static String readFile(File file) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();

        BufferedReader reader = Files.newBufferedReader(file.toPath(), CHARSET);

        String line = null;
        while ((line = reader.readLine()) != null) {
            stringBuffer.append(line);
        }

        reader.close();

        return stringBuffer.toString();
    }

    /**
     * Saves the content String to the specified file.
     * 
     * @param content
     * @param file
     * @throws IOException thrown if an I/O error occurs opening or creating the file
     */
    public static void saveFile(String content, File file) throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(file.toPath(), CHARSET);
        writer.write(content, 0, content.length());
        writer.close();
    }
    
    public static Map<String,String> isValid(String filePath, String username, String password) {
    	Map<String, String>  map = new HashMap<>();
    	try {
    		File inputFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("user");
            for (int temp = 0; temp < nList.getLength(); temp++) {
               Node nNode = nList.item(temp);
               if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                  Element eElement = (Element) nNode;
                  if(eElement.getElementsByTagName("username").item(0).getTextContent().equals(username)) {
	               	   if(eElement.getElementsByTagName("password").item(0).getTextContent().equals(password)) {
	               		   map.put("username", eElement.getElementsByTagName("username").item(0).getTextContent());
	               		   map.put("password", eElement.getElementsByTagName("password").item(0).getTextContent());
	               		   map.put("role", eElement.getElementsByTagName("role").item(0).getTextContent());
	               		   return map;
	               	   }
                  }
               }
            }
    	}
    	catch (Exception e) {
            e.printStackTrace();
        }
    	return null;     
    }
}