package com.sample.jsp.bean;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class WordCounter {
	private String urlStr;
    private String content;
    
    public WordCounter(String urlStr){
    	this.urlStr = urlStr;
    }
    private String fetchContent() throws IOException{
    	String retVal = "";
    	try {
			URL u = new URL(urlStr);

			URLConnection conn = u.openConnection();

			conn.setRequestProperty("User-agent", "Chrome/7.0.517.44");

			InputStream in = conn.getInputStream();

			InputStreamReader inReader = new InputStreamReader(in,"utf-8");

			BufferedReader bufReader = new BufferedReader(inReader);
			String line = null;

			while((line=bufReader.readLine())!=null)
			{
				retVal+=line;

			}
		} catch (IOException e) {
			
		}
    	
		return retVal;
    }
    
    public int countKeyword(String keyword) throws IOException{
		if (content == null){
		    content = fetchContent();
		}
		
		//To do a case-insensitive search, we turn the whole content and keyword into upper-case:
		content = content.toUpperCase();
		keyword = keyword.toUpperCase();
	
		int retVal = 0;
		int fromIdx = 0;
		int found = -1;
	
		while ((found = content.indexOf(keyword, fromIdx)) != -1){
		    retVal++;
		    fromIdx = found + keyword.length();
		}
	
		return retVal;
    }
    
}
