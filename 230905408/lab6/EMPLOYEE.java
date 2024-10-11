import java.util.*;

class Employee{
	String Ename;
	int Eid;
	int basic;
	double DA;
	double gross_salary;
	double net_salary;
	double IT;

	Employee (){
		Ename = "Unknown";
		Eid = 000;
		basic = 0;
	}

	Employee (String name, int id, int basic){
		Ename = name;
		Eid = id;
		this.basic = basic;
	}

	void compute_net_salary (){
		DA = 0.52 * basic;
		gross_salary = basic + DA;
		IT = 0.3*gross_salary;
		net_salary = gross_salary - IT;
	}

	void display (){
		System.out.println("name of the employee: "+Ename);
		System.out.println("ID of the employee: "+Eid);
		System.out.println("basic: "+basic);
		System.out.println("DA ="+DA);
		System.out.println("gross salary of the employee: "+gross_salary);
		System.out.println("net salary of the employee: "+net_salary);
		System.out.println("income tax paid my the employee: "+IT);
	}
}

class PartTimeEmployee extends Employee {
	int hoursWorked;
	final static double hourlyRate = 500;

	PartTimeEmployee (String name, int id, int basic, int hoursWorked){
		super (name, id, basic);
		this.hoursWorked = hoursWorked;
	}

	void compute_net_salary (){
		gross_salary = hoursWorked*hourlyRate;
		DA = gross_salary;
		IT = 0.3*gross_salary;
		net_salary = gross_salary - IT;
	}

	void display (){
		super.display();
		System.out.println("hours worked by employee: "+hoursWorked);
		System.out.println("hourly rate: "+hourlyRate);
	}
}	

class FullTimeEmployee extends Employee {
	double bonus;
	double deductions;
	
	FullTimeEmployee (String name, int id, int basic, double bonus, double deductions){
		super (name, id, basic);
		this.bonus = bonus;
		this.deductions = deductions;
	}

	void compute_net_salary (){
		super.compute_net_salary();
		net_salary += net_salary + bonus - deductions;
	}

	void display (){
		super.display();
		System.out.println("Bonus: "+bonus);
		System.out.println("Deductions: "+deductions);
	}
}
	
class EMPLOYEE{

	public static void main (String [] args){

		Scanner sc = new Scanner(System.in);

		String name;
		int id;
		int basic;
		int hoursWorked;

		System.out.println("Enter name of the employee: ");
		name = sc.nextLine();
		System.out.println("Enter ID of the employee: ");
		id = sc.nextInt();
		System.out.println("Enter the basic: ");
		basic = sc.nextInt();
		System.out.println("Enter hours worked by employee: ");
		hoursWorked = sc.nextInt();
		sc.nextLine();

		PartTimeEmployee e1 = new PartTimeEmployee (name, id, basic,hoursWorked);

		e1.compute_net_salary();
		e1.display();

		double bonus, deductions;

		System.out.println("Enter name of the employee: ");
		name = sc.nextLine();
		System.out.println("Enter ID of the employee: ");
		id = sc.nextInt();
		System.out.println("Enter the basic: ");
		basic = sc.nextInt();
		System.out.println("Enter the bonus: ");
		bonus = sc.nextDouble();
		System.out.println("Enter the deductions: ");
		deductions = sc.nextDouble();

		FullTimeEmployee e2 = new FullTimeEmployee (name, id, basic, bonus, deductions);

		e2.compute_net_salary();
		e2.display();


	}
}
