import java.util.*;


class BUILDING {
	private double footage;
	private int stories;

	double getFootage(){
		return footage;
	}

	int getStories(){
		return stories;
	}

	void setFootage(double footage){
		this.footage = footage;
	}
	void setStories(int stories){
		this.stories = stories;
	}

	void display (){
		System.out.println("square footage of Building: = "+footage);
		System.out.println("Number of stories: "+stories);
	}
}

class House extends BUILDING {
	private int bedrooms;
	private int baths;

	House (double footage, int stories, int bedrooms, int baths){
		setFootage(footage);
		setStories(stories);
		this.bedrooms = bedrooms;
		this.baths = baths;
	}

	void display (){
		super.display();
		System.out.println("number of bedrooms: "+bedrooms);
		System.out.println("number of baths: "+baths);
	}
}

class School extends BUILDING{
	private int classrooms;
	private String gradeLevel;

	School (double footage, int stories, int classrooms, String gradeLevel){
		setFootage(footage);
		setStories(stories);
		this.classrooms = classrooms;
		this.gradeLevel = gradeLevel;
	}
	void display (){
		super.display();
		System.out.println("number of classrooms: "+classrooms);
		System.out.println("grade level of the school: "+gradeLevel);
	}
}

class Building2 {

	public static void main (String [] args){
		
		Scanner sc = new Scanner (System.in);

		double footage;
		int stories;

		System.out.println("how many square foot of building: ");
		footage = sc.nextDouble();
		System.out.println("number of stories: ");
		stories = sc.nextInt();

		BUILDING b1 = new BUILDING();
		b1.setFootage(footage);
		b1.setStories(stories);
		System.out.println("footage of the building: "+b1.getFootage());
		System.out.println("stories in the building: "+b1.getStories());

		int classrooms;
		String gradeLevel;

		System.out.println("square footage of School: ");
		footage = sc.nextDouble();
		System.out.println("number of stories in school");
		stories = sc.nextInt();
		System.out.println("number of classrooms in school: ");
		classrooms = sc.nextInt();
		sc.nextLine()
;		System.out.println("grade level of the school: ");
		gradeLevel = sc.nextLine();
		School s1 = new School(footage, stories, classrooms, gradeLevel);
		s1.display();

		int bedrooms, baths; 
		System.out.println("square footage of house: ");
		footage = sc.nextDouble();
		System.out.println("number of stories in house");
		stories = sc.nextInt();
		System.out.println("number of bedrooms in house: ");
		bedrooms = sc.nextInt();
		System.out.println("number of baths in house: ");
		baths = sc.nextInt();

		House h1 = new House (footage, stories, bedrooms,baths);
		h1.display();
		

	}
}

