package com.course.structure;

public class School extends Building{
	int classroom;
	String grade;
	public School(int sqFootage, int stories, int classroom, String grade){
		super(sqFootage, stories);
		this.classroom = classroom;
		this.grade = grade;
	}
	public int getClassroom(){ return classroom;}
	public void setClassroom(int classroom) { this.classroom = classroom; }
	public String getGrade(){ return grade;}
	public void setGrade(String grade){ this.grade = grade; }
}