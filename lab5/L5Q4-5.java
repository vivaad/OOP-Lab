import java.util.Arrays;
import java.util.Comparator;

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

public static void main(){
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
}

void display() {
    System.out.println("Registration Number: " + regNumber);
    System.out.println("Full Name: " + fullName);
    System.out.println("Date of Joining: " + dateOfJoining.getTime());
    System.out.println("Semester: " + semester);
    System.out.println("GPA: " + gpa);
    System.out.println("CGPA: " + cgpa);
    }
    static void sortBySemesterAndCGPA(STUDENT[] students, int n) {
    for (int i = 0; i < n - 1; i++) {
    for (int j = i + 1; j < n; j++) {
    if (students[i].semester > students[j].semester ||
    (students[i].semester == students[j].semester &&
    students[i].cgpa < students[j].cgpa)) {
    STUDENT temp = students[i];
    students[i] = students[j];
    students[j] = temp;
    }
    }
    }
    }