import java.util.*;
class EMPLOYEE {
    String name;
    int id;
    EMPLOYEE() {
        name = "Unknown";
        id = 0;
    }
    EMPLOYEE(String name, int id) {
        this.name = name;
        this.id = id;
    }
    void display() {
        System.out.println("Name: " + name + ", ID: " + id);
    }
}

public class Main {
    public static void main(String[] args) {
        EMPLOYEE emp1 = new EMPLOYEE();
        emp1.display(); // Output: Name: Unknown, ID: 0
        Scanner in = new Scanner(System.in);
        System.out.println("Enter name and id:");
        EMPLOYEE emp2 = new EMPLOYEE(in.nextLine(), in.nextInt());
        emp2.display(); // Output: Name: Alice, ID: 123
        in.close();
    }
}
