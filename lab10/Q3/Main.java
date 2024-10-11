import java.util.Scanner;

class GenericStack<T> {
    private int top;
    private T[] elements;

    public GenericStack(int size) {
        elements = (T[]) new Object[size];
        top = -1;
    }

    public void push(T item) {
        if (top == elements.length - 1) {
            System.out.println("Stack is full");
        } else {
            elements[++top] = item;
        }
    }

    public T pop() {
        if (top == -1) {
            System.out.println("Stack is empty");
            return null;
        } else {
            return elements[top--];
        }
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void printElements() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.println(elements[i]);
        }
    }
}

class Student {
    String name;
    int id;

    Student(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" + "name='" + name + '\'' + ", id=" + id + '}';
    }
}

class Employee {
    String name;
    double salary;

    Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" + "name='" + name + '\'' + ", salary=" + salary + '}';
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of students:");
        int studentSize = sc.nextInt();
        GenericStack<Student> studentStack = new GenericStack<>(studentSize);
        for (int i = 0; i < studentSize; i++) {
            System.out.println("Enter student name and ID:");
            String name = sc.next();
            int id = sc.nextInt();
            studentStack.push(new Student(name, id));
        }

        System.out.println("Student Stack:");
        printStack(studentStack);

        System.out.println("Enter the number of employees:");
        int employeeSize = sc.nextInt();
        GenericStack<Employee> employeeStack = new GenericStack<>(employeeSize);
        for (int i = 0; i < employeeSize; i++) {
            System.out.println("Enter employee name and salary:");
            String name = sc.next();
            double salary = sc.nextDouble();
            employeeStack.push(new Employee(name, salary));
        }

        System.out.println("Employee Stack:");
        printStack(employeeStack);

        sc.close();
    }

    public static void printStack(GenericStack<?> stack) {
        stack.printElements();
    }
}
