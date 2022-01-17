package com.sample.jsp.bean;

import java.io.IOException;
import java.util.ArrayList;

public class WebTree {
	public WebNode root;
	private ArrayList<WebNode> console = new ArrayList<WebNode>();
	public WebTree(WebPage rootPage){
		this.root = new WebNode(rootPage);
	}
	public void setPostOrderScore(ArrayList<Keyword> keywords) throws IOException{
		setPostOrderScore(root, keywords);
	}
	
	private void setPostOrderScore(WebNode startNode, ArrayList<Keyword> keywords) throws IOException{
		for(WebNode child : startNode.getChildren()){
			
			child.setNodeScore(keywords);
			if(child.webPage.getScore()!=0) {
				console.add(child);
			}
		}
		startNode.setNodeScore(keywords);
		}
	
	public void eularPrintTree(){
		eularPrintTree(root);
	}
	
	private void eularPrintTree(WebNode startNode){
		System.out.print("counting");
		quickSort(0, console.size()-1);
		
		for(WebNode node:console) {
			System.out.println(node.webPage.url+"|"+node.webPage.getScore());
		}
		System.out.print("finished");
		
		
	}
	public ArrayList<WebNode> getConsole() {
		return console;
	}
	public void setConsole(ArrayList<WebNode> console) {
		this.console = console;
	}
	public void swap(int aIndex, int bIndex){
		  WebNode temp = console.get(aIndex);
		  console.set(aIndex, console.get(bIndex));
		  console.set(bIndex, temp);
		 }
		   private void quickSort(int left, int right) {
		         if(left < right) { 
		             int q = partition(left, right); 
		             quickSort( left, q-1); 
		             quickSort( q+1, right); 
		         } 
		     }
		     private int partition( int left, int right) {  
		         int i = left - 1; 
		         for(int j = left; j < right; j++) { 
		             if(console.get(j).webPage.getScore() >= console.get(right).webPage.getScore()) { 
		                 i++; 
		                 swap(i, j); 
		             } 
		         } 
		         swap(i+1, right); 
		         return i+1; 
		     }
}