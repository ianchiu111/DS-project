package com.sample.jsp.bean;

import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStream;

import java.io.InputStreamReader;

import java.net.URL;

import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CallGoogle {
	private String keyword;
	private String url;
    private String content;
    private ArrayList<WebPage> URLlist = new ArrayList<WebPage>();
    public ArrayList<String> rand;
    private WebTree tree;
    private Keyword keylist;
    public CallGoogle(){  }
	
	public WebTree getTree() {
		return tree;
	}

	public void setTree(WebTree tree) {
		this.tree = tree;
	}

	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public void run() throws IOException {
		keylist = new Keyword();
		keylist.setbraveslist();
		
		constructor();
		query();
		try {
			createWebTree1();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void constructor() {
		this.url = "http://www.google.com/search?q="+keyword+"&oe=utf8&num=30";
		this.URLlist = new ArrayList<WebPage>();
		tree = new WebTree(new WebPage("root",url));
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
	
	private String fetchContent() throws IOException{
		String retVal = "";
		URL u = new URL(url);

		URLConnection conn = u.openConnection();
		conn.setRequestProperty("User-agent", "Chrome/7.0.517.44");
		InputStream in = conn.getInputStream();
		InputStreamReader inReader = new InputStreamReader(in,"utf-8");
		BufferedReader bufReader = new BufferedReader(inReader);
		String line = null;

		while((line=bufReader.readLine())!=null){
			retVal+=line;
		}
		return retVal;
	}
	public void query() throws IOException{
		if(content==null){
			content= fetchContent();
		}
		Document doc = Jsoup.parse(content);
		Elements lis = doc.select("div");
		lis = lis.select(".kCrYT");
		for(Element li : lis){
			try {
				String Url = li.select("a").get(0).attr("href");
				Url = Url.substring(7,Url.indexOf("&sa=U&ved=2ah"));
				String title = li.select("a").get(0).select(".vvjwJb").text();
				if(title.equals("")) {
					continue;
				}
				WebPage page = new WebPage(title,Url);
				URLlist.add(page);
			} 
			catch (IndexOutOfBoundsException e) {
//				e.printStackTrace();
			}
		}
	}
	public void createWebTree1() throws IOException{
		for(WebPage rootPage:URLlist) {
			WebNode child = new WebNode(rootPage);
			tree.root.addChild(child);
		    System.out.println(rootPage.name+"|"+rootPage.url);
		}
		tree.setPostOrderScore(keylist.getSelectlist());
		tree.eularPrintTree();
	}
}