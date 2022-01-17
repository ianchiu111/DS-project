package com.sample.jsp.bean;

import java.util.ArrayList;
import java.util.HashMap;

public class Keyword {
	 public String name;
	 private int score;
	 private ArrayList<Keyword> braveslist = new ArrayList<Keyword>();
	 
	 private ArrayList<Keyword> selectlist = braveslist;
	 public Keyword(String name,int score){
	  this.name = name;
	  this.score = score;
	 }
	 public Keyword() {	 
	 }
	 public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public ArrayList<Keyword> getSelectlist() {
		return selectlist;
	}
	public void setSelectlist(String select) {
		this.selectlist = braveslist;
	}
	public ArrayList<Keyword> getbraveslist() {
		return braveslist;
	}
	public void setbraveslist() {
		braveslist.add(new Keyword("braves", 2));
		braveslist.add(new Keyword("Atlanta", 8));
		braveslist.add(new Keyword("Baseball", 3));
		braveslist.add(new Keyword("Major League Baseball", 7));
		braveslist.add(new Keyword("MLB", 7));
		braveslist.add(new Keyword("Ronald Acuña Jr.", 6));
		braveslist.add(new Keyword("America", 2));
		braveslist.add(new Keyword("Truist Park", 5));
		braveslist.add(new Keyword("Freddie Freeman", 6));
		braveslist.add(new Keyword("Gwinnett Stripers", 3));
		braveslist.add(new Keyword("2021championship", 4));
		braveslist.add(new Keyword("Tom Glavine", 5));
	}
	public void setName(String name) {
	  this.name = name;
	}
	public String getName() {
	  return name;
	}
	@Override
	public String toString(){
		return "["+name+","+"]";
	}
	public static void main(String[] args) {
		System.out.println(" ");
	}
}