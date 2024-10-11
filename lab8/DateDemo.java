import java.util.Scanner;

// Custom Exception for Invalid Day
class InvalidDayException extends Exception {
    public InvalidDayException(String message) {
        super(message);
    }
}

// Custom Exception for Invalid Month
class InvalidMonthException extends Exception {
    public InvalidMonthException(String message) {
        super(message);
    }
}

// Class to handle Date operations
class CurrentDate {
    private int day;
    private int month;
    private int year;

    public void createDate() throws InvalidDayException, InvalidMonthException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter day:");
        day = scanner.nextInt();
        System.out.println("Enter month:");
        month = scanner.nextInt();
        System.out.println("Enter year:");
        year = scanner.nextInt();

        if (month < 1 || month > 12) {
            throw new InvalidMonthException("Invalid month: " + month);
        }
        if (day < 1 || day > getDaysInMonth(month, year)) {
            throw new InvalidDayException("Invalid day: " + day);
        }
        displayDate();
    }

    private int getDaysInMonth(int month, int year) {
        switch (month) {
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) ? 29 : 28;
            default:
                return 31;
        }
    }

    public void displayDate() {
        System.out.println("Current Date: " + day + "/" + month + "/" + year);
    }
}

// Main class to test CurrentDate class and exceptions
public class DateDemo {
    public static void main(String[] args) {
        CurrentDate date = new CurrentDate();
        try {
            date.createDate();
        } catch (InvalidDayException | InvalidMonthException e) {
            System.out.println(e.getMessage());
        }
    }
}