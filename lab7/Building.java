package com.course.structure;
public class Building{
	int sqFootage, stories;
	public Building(int sqFootage, int stories){
		this.sqFootage = sqFootage;
		this.stories = stories;
	}

	public int getSqFootage(){ return sqFootage;}
	public void setSqFootage(int sqFootage){ this.sqFootage = sqFootage; }
	public int getStories(){ return stories;}
	public void setStories(int stories) { this.stories = stories; }
}
