import java.util.*;

class STUDENT{
    String sname;
    int[] marks_array;
    int total;
    float avg;
    
    void assign(){
        Scanner in = new Scanner(System.in);
        System.out.print("Enter Name: ");
        sname = in.nextLine();
        System.out.print("Enter no of subjects: ");
        int n = in.nextInt();
        marks_array = new int[n];
        System.out.println("Enter marks: ");
        for(int i=0; i<n; i++) marks_array[i] =in.nextInt();  
        in.close();   
    }

    void compute(){
        total=0;
        for(int i=0; i<marks_array.length; i++)
            total += marks_array[i];
        avg = (1.0f * total)/marks_array.length;
    }

    void display(){
        System.out.println("Name: "+sname);
        System.err.print("Marks: ");
        for(int i=0; i<marks_array.length; i++)
            System.out.print(marks_array[i]+" ");
        System.out.println("\nTotal: "+total);
        System.out.println("Average: "+avg);
    }
    public static void main(String[] args) {
        STUDENT std = new STUDENT();
        std.assign();
        std.compute();
        std.display();
    }


}