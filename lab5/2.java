import java.util.Scanner;

class Student {

    public static String getInit(String name) {
        StringBuilder init = new StringBuilder();
        boolean nextInit = true;

        for (char c : name.toCharArray()) {
            if (Character.isWhitespace(c)) {
                nextInit = true;
            } else if (nextInit) {
                init.append(c);
                nextInit = false;
            }
        }
        return init.toString();
    }

    public static String noSpace(String name) {
        StringBuilder result = new StringBuilder();
        for (char c : name.toCharArray()) {
            if (!Character.isWhitespace(c)) {
                result.append(c);
            }
        }
        return result.toString();
    }

    public static void printSubNames(String names, String sub) {
        int start = 0;

        while (start < names.length()) {
            int end = names.indexOf(',', start);
            if (end == -1) {
                end = names.length();
            }

            String name = names.substring(start, end).trim();
            if (name.contains(sub)) {
                System.out.println(name);
            }
            
            start = end + 1;
        }
    }

    public static String sortNames(String names) {
        String[] arr = names.split("\\s*,\\s*");
        java.util.Arrays.sort(arr, String.CASE_INSENSITIVE_ORDER);
        return String.join(", ", arr);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter names separated by commas:");
        String names = sc.nextLine();

        System.out.println("Enter substring to search for:");
        String sub = sc.nextLine();

        System.out.println("Initials of names:");
        for (String name : names.split("\\s*,\\s*")) {
            System.out.println(name.trim() + " -> " + getInit(name.trim()));
        }

        System.out.println("\nNames with whitespace removed:");
        for (String name : names.split("\\s*,\\s*")) {
            System.out.println(name.trim() + " -> " + noSpace(name.trim()));
        }

        System.out.println("\nNames containing '" + sub + "':");
        printSubNames(names, sub);

        System.out.println("\nSorted names:");
        System.out.println(sortNames(names));

        sc.close();
    }
}
