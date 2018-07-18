package com.ai.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class StringUtils {
	
	/**
	 * �������tƥ����΂��ַ���
	 * @param content ��ƥ�����
	 * @param regex ���t���_ʽ
	 * @return
	 */
	public static String getStringByregEx(String content,String regex){
		String result="";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(content);
		    while(matcher.find()){
		    	result=matcher.group().intern();
		    }
		    return result;
	}
	
	/**
	   * �õ�ͼƬ�ֽ��� �����С
	   * */
	  public static byte[] readStream(InputStream inStream) throws Exception{	
	    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
	    byte[] buffer = new byte[1024];	  
	    int len = -1;	
	    while((len = inStream.read(buffer)) != -1){	  
	      outStream.write(buffer, 0, len);	  
	    }	  
	    outStream.close();	  
	    inStream.close();
	    return outStream.toByteArray();	  
	  }

	  public static String readXml(String key,String uri){
		  	try {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();//�õ����� DOM �������Ĺ�����  
				DocumentBuilder builder = factory.newDocumentBuilder();//�õ� DOM ����������  
				Document document = builder.parse(uri);
				Element e = document.getDocumentElement(); 
				return e.getAttribute(key);
			} catch ( Exception e) {
				// TODO Auto-generated catch block
				return "";
			}
	  }
	  public static String bytesToHex(byte[] bytes) {
	        StringBuilder buf = new StringBuilder(bytes.length * 2);
	        for(byte b : bytes) { // ʹ��String��format��������ת��
	            buf.append(String.format("%02x", new Integer(b & 0xff)));
	        }

	        return buf.toString();
	    }
}
