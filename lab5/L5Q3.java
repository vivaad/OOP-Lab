import java.util.*;

class StudentInfo {

    private static int registrationCounter = 0;
    private static int lastYear = -1;

    private int registrationNumber;
    private String fullName;
    private GregorianCalendar dateOfJoining;
    private short semester;
    private float gpa;
    private float cgpa;

    public StudentInfo(String fullName, GregorianCalendar dateOfJoining, short semester, float gpa, float cgpa) {
        this.fullName = fullName;
        this.dateOfJoining = dateOfJoining;
        this.semester = semester;
        this.gpa = gpa;
        this.cgpa = cgpa;
        this.registrationNumber = generateRegistrationNumber(dateOfJoining);
    }

    private int generateRegistrationNumber(GregorianCalendar dateOfJoining) {
        int year = dateOfJoining.get(GregorianCalendar.YEAR);
        if (year != lastYear) {
            registrationCounter = 0;
            lastYear = year;
        }
        int yearLastTwoDigits = year % 100;
        registrationCounter++;
        return yearLastTwoDigits * 100 + registrationCounter;
    }

    public void display() {
        System.out.println("Registration Number: " + registrationNumber);
        System.out.println("Full Name: " + fullName);
        System.out.println("Date of Joining: " + dateOfJoining.get(GregorianCalendar.DAY_OF_MONTH) + "/"
                           + (dateOfJoining.get(GregorianCalendar.MONTH) + 1) + "/"
                           + dateOfJoining.get(GregorianCalendar.YEAR));
        System.out.println("Semester: " + semester);
        System.out.println("GPA: " + gpa);
        System.out.println("CGPA: " + cgpa);
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentInfo[] students = new StudentInfo[5];

        for (int i = 0; i < students.length; i++) {
            System.out.println("Enter details for student " + (i + 1) + ":");

            System.out.print("Full Name: ");
            String fullName = sc.nextLine();

            System.out.print("Year of Joining (e.g., 2022): ");
            int year = sc.nextInt();

            System.out.print("Month of Joining (1-12): ");
            int month = sc.nextInt();

            System.out.print("Day of Joining (1-31): ");
            int day = sc.nextInt();
            sc.nextLine();

            GregorianCalendar dateOfJoining = new GregorianCalendar(year, month - 1, day);

            System.out.print("Semester: ");
            short semester = sc.nextShort();

            System.out.print("GPA: ");
            float gpa = sc.nextFloat();

            System.out.print("CGPA: ");
            float cgpa = sc.nextFloat();
            sc.nextLine();

            students[i] = new StudentInfo(fullName, dateOfJoining, semester, gpa, cgpa);
        }

        System.out.println("\nStudent Records:");
        for (StudentInfo student : students) {
            student.display();
        }

        // In the main method, after reading student records:
        // Sorting by semester and CGPA
        System.out.println("\nStudent Records Sorted by Semester and CGPA:");
        sortBySemesterAndCGPA(students);
        for (StudentInfo student : students) {
            student.display();
        }

        // Sorting by name
        System.out.println("\nStudent Records Sorted by Name:");
        sortByName(students);
        for (StudentInfo student : students) {
            student.display();
        }
        sc.close();
    }

    // Method to sort by semester (ascending) and CGPA (descending)
    public static void sortBySemesterAndCGPA(StudentInfo[] students) {
        Arrays.sort(students, new Comparator<StudentInfo>() {
            @Override
            public int compare(StudentInfo s1, StudentInfo s2) {
                int semesterCompare = Short.compare(s1.semester, s2.semester);
                if (semesterCompare != 0) {
                    return semesterCompare;
                }
                return Float.compare(s2.cgpa, s1.cgpa); // Descending order of CGPA
            }
        });
    }

    // Method to sort by name (alphabetical order)
    public static void sortByName(StudentInfo[] students) {
        Arrays.sort(students, new Comparator<StudentInfo>() {
            @Override
            public int compare(StudentInfo s1, StudentInfo s2) {
                return s1.name.compareToIgnoreCase(s2.name);
            }
        });
    }

    

}
