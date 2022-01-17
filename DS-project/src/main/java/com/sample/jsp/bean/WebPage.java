package com.sample.jsp.bean;

import java.io.IOException;
import java.util.ArrayList;

public class WebPage {
	public String url;
	public String name;
	public WordCounter counter;
	public double score;
	
	public WebPage(String name,String url){
		this.name=name;
		this.url = url;
		this.counter = new WordCounter(url);
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public double getScore() {
		return score;
	}
	public void setScore(ArrayList<Keyword> keywords) throws IOException{
		score = 0;
		for(Keyword k : keywords){	
			score+=counter.countKeyword(k.name)*k.getScore();
		}
	}
}