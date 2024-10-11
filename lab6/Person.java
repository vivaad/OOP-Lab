import java.util.*;

class PERSON {
	private String name;
	private int DOB;

	PERSON (String name, int DOB){
		this.name = name;
		this.DOB = DOB;
	}

	void display (){
		System.out.println("name of the person : "+name);
		System.out.println("date of birth : "+DOB);
	}
}

class CollegeGraduate extends PERSON{
	private double GPA;
	private int DOG;

	double getGPA(){
		return GPA;	
	}

	int getDOJ(){
		return DOG;
	}

	CollegeGraduate (String name, int DOB, double GPA, int DOG){
		super (name, DOB);
		this.GPA = GPA;
		this.DOG = DOG;
	}	

	void display (){
		super.display();
		System.out.println("GPA of person who is a student: "+GPA);
		System.out.println("Date of graduation of the person who is a student: "+DOG);
	}
}	
class Person {

	public static void main (String [] args){

	Scanner sc = new Scanner (System.in);

	String name;
	int DOB;

	System.out.println("Enter name of the person: ");
	name = sc.nextLine();
	System.out.println("Enter date of birth in DD/MM/YYYY: ");
	DOB = sc.nextInt();
	sc.nextLine();

	PERSON p1 = new PERSON (name, DOB);
	p1.display();

	double GPA;
	int DOG;

	System.out.println("Enter name of the student: ");
	name = sc.nextLine();
	System.out.println("Enter Date of Birth in DD/MM/YYYY: ");
	DOB = sc.nextInt();
	System.out.println("Enter GPA of student: ");
	GPA = sc.nextDouble();
	System.out.println("Enter Year of graduation: ");
	DOG = sc.nextInt();

	CollegeGraduate p2 = new CollegeGraduate (name, DOB, GPA, DOG);
	p2.display();


	}


}