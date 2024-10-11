import java.util.Scanner;
class STUDENT {
    String sname;
    int n, total;
    int[] marks_array;
    float avg;
    STUDENT() {
        Scanner scn = new Scanner(System.in);
        sname = "Paapi";
        n = 3;
        System.out.println("Enter the marks(default):");
        marks_array = new int[n];
        for (int i = 0; i < n; i++) {
            marks_array[i] = scn.nextInt();
        }
    }
    STUDENT(String sname, int n) {
        Scanner scn = new Scanner(System.in);
        this.sname = sname;
        this.n = n;
        System.out.println("Enter the marks:");
        marks_array = new int[n];
        for (int i = 0; i < this.n; i++) {
            marks_array[i] = scn.nextInt();
        }
    }
    void display() {
        System.out.println("Name: " + sname);
        for (int i = 0; i < n; i++) {
            System.out.println("Subject " + (i + 1) + ": " + marks_array[i]);
        }
        System.out.println("Total: " + total);
        System.out.println("Avg: " + avg);
    }
    void compute() {
        total = 0;
        for (int i = 0; i < n; i++) {
            total += marks_array[i];
        }
        avg = (float) total / n;
    }
}
class ScienceStudent extends STUDENT {
    private int practicalMarks;
    ScienceStudent() {
        super();
        practicalMarks = 0;
    }
    ScienceStudent(String sname, int n, int practicalMarks) {
        super(sname, n);
        this.practicalMarks = practicalMarks;
    }
    @Override
    void compute() {
        super.compute();
        total += practicalMarks;
        avg = (float) total / (n + 1);
    }
    void displayPracticalMarks() {
        System.out.println("Practical Marks: " + practicalMarks);
    }
    @Override
    void display() {
        super.display();
        displayPracticalMarks();
    }
}
class ArtsStudent extends STUDENT {
    private String electiveSubject;
    ArtsStudent() {
        super();
        electiveSubject = "None";
    }
    ArtsStudent(String sname, int n, String electiveSubject) {
        super(sname, n);
        this.electiveSubject = electiveSubject;
    }
    @Override
    void display() {
        super.display();
        System.out.println("Elective Subject: " + electiveSubject);
    }
}
public class l6_1 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        STUDENT S1 = new STUDENT();
        S1.compute();
        System.out.println("\nEnter details for ScienceStudent:");
        System.out.print("Name: ");
        String scName = scn.nextLine();
        System.out.print("Number of subjects: ");
        int scNumSubjects = scn.nextInt();
        System.out.print("Practical marks: ");
        int practicalMarks = scn.nextInt();
        ScienceStudent S2 = new ScienceStudent(scName, scNumSubjects, practicalMarks);
        S2.compute();
        System.out.println("\nEnter details for ArtsStudent:");
        scn.nextLine();
        System.out.print("Name: ");
        String artsName = scn.nextLine();
        System.out.print("Number of subjects: ");
        int artsNumSubjects = scn.nextInt();
        scn.nextLine();
        System.out.print("Elective Subject: ");
        String electiveSubject = scn.nextLine();
        ArtsStudent S3 = new ArtsStudent(artsName, artsNumSubjects, electiveSubject);
        S3.compute();
        S1.display();
        S2.display();
        S3.display();
    }
}