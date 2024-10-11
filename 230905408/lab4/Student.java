import java.util.Scanner;

class Student {
    String sname;
    int[] marks_array;
    int total;
    double avg;

    Student() {
        System.out.println("Default Constructor");
        this.sname = "As";
        this.marks_array = new int[0];
        this.total = 0;
        this.avg = 0.0;
        System.out.println("Default Values - Name: " + sname + ", Total: " + total + ", Average: " + avg);
    }

    Student(String name, int[] marks) {
        this.sname = name;
        this.marks_array = marks;
        compute();
        System.out.println("Parameterized Constructor");
    }

    void assign(String name, int[] marks) {
        this.sname = name;
        this.marks_array = marks;
        compute();
    }

    void compute() {
        if (marks_array == null || marks_array.length == 0) {
            total = 0;
            avg = 0.0;
            return;
        }

        total = 0;
        for (int mark : marks_array) {
            total += mark;
        }
        avg = (double) total / marks_array.length;
    }

    void display() {
        System.out.println("Student Name: " + sname);
        System.out.print("Marlass Student {\n" + //
                        "    String sks: ");
        for (int mark : marks_array) {
            System.out.print(mark + " ");
        }
        System.out.println();
        System.out.println("Total Marks: " + total);
        System.out.println("Average Marks: " + avg);
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Creating a student default constructor:");
        Student student1 = new Student();
        student1.display();
        
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();
        scanner.nextLine();
        
        int[] marks = new int[numSubjects];
        
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter marks for subject " + (i + 1) + ": ");
            marks[i] = scanner.nextInt();
        }
        
        Student student2 = new Student(name, marks);
        System.out.println("Creating a student using the parameterized constructor:");
        student2.display();

        scanner.close();
    }
}
