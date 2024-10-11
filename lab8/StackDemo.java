// Custom Exception for Stack Push operation
class PushException extends Exception {
    public PushException(String message) {
        super(message);
    }
}

// Custom Exception for Stack Pop operation
class PopException extends Exception {
    public PopException(String message) {
        super(message);
    }
}

// Stack class definition
class Stack {
    private int[] arr;
    private int top;
    private int capacity;

    public Stack(int size) {
        arr = new int[size];
        capacity = size;
        top = -1;
    }

    public void push(int value) throws PushException {
        if (top == capacity - 1) {
            throw new PushException("Stack is full. Cannot push " + value);
        }
        arr[++top] = value;
    }

    public int pop() throws PopException {
        if (top == -1) {
            throw new PopException("Stack is empty. Cannot pop");
        }
        return arr[top--];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == capacity - 1;
    }
}

// Main class to demonstrate the Stack class and exceptions
public class StackDemo {
    public static void main(String[] args) {
        Stack stack = new Stack(3);

        try {
            stack.push(10);
            stack.push(20);
            stack.push(30);
            // This push should throw an exception
            stack.push(40);
        } catch (PushException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("Popped: " + stack.pop());
            System.out.println("Popped: " + stack.pop());
            System.out.println("Popped: " + stack.pop());
            // This pop should throw an exception
            System.out.println("Popped: " + stack.pop());
        } catch (PopException e) {
            System.out.println(e.getMessage());
        }
    }
}
