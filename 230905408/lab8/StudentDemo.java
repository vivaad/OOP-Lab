// Custom Exception for Seats Filled
class SeatsFilledException extends Exception {
    public SeatsFilledException(String message) {
        super(message);
    }
}

// Student class definition
class Student {
    private String registrationNumber;

    public Student(String registrationNumber) throws SeatsFilledException {
        this.registrationNumber = registrationNumber;
        validateRegistrationNumber();
    }

    private void validateRegistrationNumber() throws SeatsFilledException {
        String year = registrationNumber.substring(0, 2);
        int currentYear = Integer.parseInt(year);
        int maxAllowed = currentYear * 100 + 25; // XX25
        int regNum = Integer.parseInt(registrationNumber.substring(2));

        if (regNum > maxAllowed) {
            throw new SeatsFilledException("Seats are filled for registration number: " + registrationNumber);
        }
    }
}

// Main class to test Student class and exceptions
public class StudentDemo {
    public static void main(String[] args) {
        try {
            Student student1 = new Student("2320"); // valid registration
            Student student2 = new Student("2327"); // this should throw an exception
        } catch (SeatsFilledException e) {
            System.out.println(e.getMessage());
        }
    }
}
