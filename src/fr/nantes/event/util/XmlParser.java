package fr.nantes.event.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class XmlParser {
	public final static XmlParser instance = new XmlParser();
	private XmlParser(){}
	
	public ArrayList<Map<String, String>> getStadiumFromOpenData(String dataUrl){
		ArrayList<Map<String, String>> data = new ArrayList<Map<String,String>>();
		Element dataElem = getFirstElement(dataUrl);
		List listEtudiants = dataElem.getChildren("element");

        //On crée un Iterator sur notre liste
        Iterator i = listEtudiants.iterator();

        while(i.hasNext()){
        	Map<String,String> stadium =new HashMap<String, String>();
        	Element courant = (Element)i.next();
           
        	Element geoElem = courant.getChild("geo");
        	String name = geoElem.getChild("name").getText();
        	stadium.put("name", name);
           	stadium.put("address", courant.getChild("ADRESSE").getText());
           	stadium.put("categorie", courant.getChild("LIBCATEGORIE").getText());

           	// get the geolocalisation data in this form: [ 47.1929657069178 , -1.53691739665354]
           	String latLong = courant.getChild("_l").getText();
           	
           	// Delete the "[" and "]" to obtain this : 47.1929657069178 , -1.53691739665354
           	latLong = latLong.replaceAll("\\[", "").replaceAll("\\]", "");
           	
           	// Split 47.1929657069178 , -1.53691739665354 in a table
           	String[] tabLatLong = latLong.split("\\,", -1);
           	
           	String latitude = tabLatLong.length>=1 ? tabLatLong[0] : "";
           	String longitude = tabLatLong.length>=2 ? tabLatLong[1] : "";
           	
           	stadium.put("latitude", latitude.trim());
           	stadium.put("longitude", longitude.trim());
           	
           	if(name.length()<=60) data.add(stadium);
        }
		return data;
	}
	
	public Element getFirstElement(String urlString){
		URL url;
		Element allElements = null;
		try {
			url = new URL(urlString);
			URLConnection yc = url.openConnection();
			yc.setConnectTimeout(60000);  //60 Seconds
			yc.setReadTimeout(60000);  //60 Seconds
			
	        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
	        String inputLine = in.readLine();
	       //System.out.print("xml content: "+inputLine);
	        in.close();
	       
	        if(inputLine != null){
		        ByteArrayInputStream stream = new ByteArrayInputStream(inputLine.getBytes());
		        SAXBuilder sxb = new SAXBuilder();
		        Document xmlDoc = sxb.build(stream);
		        
		        return xmlDoc.getRootElement().getChild("data");
	        }
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return allElements;
	}
}
