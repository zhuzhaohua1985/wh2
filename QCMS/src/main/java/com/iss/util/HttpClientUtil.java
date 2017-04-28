package com.iss.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.iss.entity.AreasBarEntity;

/**
 * http接口处理
 * @author gxie
 *
 */
public class HttpClientUtil {

	 /// 发起一个HTTP请求（以POST方式）
    /// </summary>
    /// <param name="url"></param>
    /// <param name="param"></param>
    /// <returns></returns>
    /*public static String HttpPost(String url, String param)
    {
    	HttpClient client = new HttpClient();
    	PostMethod method = new PostMethod(url);
    	method.addParameter("param", param);
    	HttpMethodParams param = method.getParams();
    	
    	String resultJson = HttpPost(url, param);
    	
    	
    	return resultJson;
    }*/
	
	public static void main(String[] args){
		String url = "http://localhost:8088/province/";
		HttpClient client = new HttpClient();
		
    	PostMethod method = new PostMethod(url);
    	
    	try {
			client.executeMethod(method);
			System.out.println(method.getStatusLine());
			InputStream stream = method.getResponseBodyAsStream(); 
			BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));  
		    StringBuffer buf = new StringBuffer();  
		    String line;  
		    while (null != (line = br.readLine())) {  
		         buf.append(line).append("\n");  
		    }
		    JsonParser parse = new JsonParser();
            JsonObject json = (JsonObject) parse.parse(buf.toString());
            System.out.println("message:" + json.get("message").toString());
            JsonObject values =  json.get("values").getAsJsonObject();
            String result = values.get("stat").toString();
            try {
				List<AreasBarEntity> list = JsonUtil.toList(result,AreasBarEntity.class);
				for (AreasBarEntity areasBarEntity : list) {
					System.out.println(areasBarEntity.getBarId());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                        
//	            System.out.println("reason:" + json.get("reason").getAsString());
//	            JsonObject result = json.get("result").getAsJsonObject();
//	            JsonObject today = result.get("today").getAsJsonObject();
//	            System.out.println("weak:" + today.get("week").getAsString());
//	            System.out.println("weather:" + today.get("weather").getAsString());
		     System.out.println(buf.toString()); 
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
