package com.ai.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import com.alibaba.fastjson.JSONObject;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
/**
 * �����ģ��
 * @author dddddd
 *
 */
public class webClient {
	/**
	 * �õ�һ�������
	 * @return
	 */
	private static WebClient  web=null;
	public static WebClient getWebClient(){
		if(web==null)
			web=new WebClient();
		//��������
		// 1 ����JS
		web.getOptions().setJavaScriptEnabled(true);
		// 2 ����Css���ɱ����Զ���������CSS������Ⱦ
		web.getOptions().setCssEnabled(false);
		//3 �����ͻ����ض���
		web.getOptions().setRedirectEnabled(true);
		// 4 ���д���ʱ���Ƿ��׳��쳣
		web.getOptions().setThrowExceptionOnScriptError(false);
		// 5 ���ó�ʱ
		web.getOptions().setTimeout(50000);
		//6 ���ú���֤��
		//webClient.getOptions().setUseInsecureSSL(true);
		//7 ����Ajax
		//webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		//8����cookie
		web.getCookieManager().setCookiesEnabled(true);
		return web;
	} 
	public static JSONObject db2String(String url){
		System.out.println(url);
			Page Pa;
			try {
				Pa = web.getPage(url);
				 InputStream rs = Pa.getWebResponse().getContentAsStream();
			        byte[] bytes = StringUtils.readStream(rs);
			        bytes=ZLibUtils.decompress(bytes);
			        String str=new String(bytes,"UTF-8");
			        System.out.println(str);
			        return JSONObject.parseObject(str);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return null;
			}
	}
	public static String getpage(String url){
		try {
			return web.getPage(url).getWebResponse().getContentAsString();
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			return "";
		}
	}
}
