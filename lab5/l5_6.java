import java.util.Scanner;
class NumberFormatter {
    String number;
    String formattedNumber = "";
    
    NumberFormatter(String number) {
        this.number = number;
    }
    void addCommas() {
        int length = number.length();
        int commaPosition = length % 3;
        for (int i = 0; i < length; i++) {
            formattedNumber += number.charAt(i);
            if ((i + 1) % 3 == commaPosition && i != length - 1) {
                formattedNumber += ",";
                }
        }
    }
    void display() {
    System.out.println("Formatted Number: " + formattedNumber);
    }
}
public class l5_6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number string: ");
        String number = sc.nextLine();
        NumberFormatter nf = new NumberFormatter(number);
        nf.addCommas();
        nf.display();
    }
}