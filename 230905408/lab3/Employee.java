import java.util.*;

public class Employee {
    String Ename; int Eid;
    float Basic, DA, Gross, Net;

    void read(){
        Scanner in = new Scanner(System.in);
        System.out.print("Enter Name: ");
        Ename = in.nextLine();
        System.out.print("Enter ID: ");
        Eid= in.nextInt();
        System.out.print("Enter Salary");
        Basic = in.nextFloat();
    }

    void compute(){
        DA = 0.52f*Basic; Gross = DA+Basic;
        Net = 0.7f*Gross; 
    }

    void display(){
        System.out.println("Name:"+Ename+"\nID:"+Eid+"\nNet Salary:"+Net);
    }
    public static void main(String[] args) {
        Scanner en = new Scanner(System.in);
        System.out.println("Enter n:");
        int n= en.nextInt();
        Employee emp = new Employee();
        for(int i=0; i<n;i++){
            emp.read();
            emp.compute();
            emp.display();
        }
    }
}
