package com.ai.util;

import java.io.FileReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class ScriptEngineUtil {
	
	public static String callJs() throws Exception{
		String result="";
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
		 
		String jsFileName =  System.getProperty("user.dir")+"\\src\\login.js";   // ��ȡjs�ļ�   
		FileReader reader = new FileReader(jsFileName);   // ִ��ָ���ű�   
		engine.eval(reader);   
		if(engine instanceof Invocable) {    
			Invocable invoke = (Invocable)engine;    // ����merge��������������������    
			String res=(String) invoke.invokeFunction("test", "1");    
			System.out.println(res);
		}   
		reader.close();  
		return "";
	}
	public static void main(String[] args) throws Exception {
		callJs();
	}
}
