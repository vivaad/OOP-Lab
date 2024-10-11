import java.util.Scanner;

class Student {

    public static String extractInitials(String name) {
        String initials = "";
        boolean nextIsInitial = true;

        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (Character.isWhitespace(c)) {
                nextIsInitial = true;
            } else if (nextIsInitial) {
                initials += c;
                nextIsInitial = false;
            }
        }
        return initials;
    }

    public static String removeWhitespace(String name) {
        String result = "";
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (!Character.isWhitespace(c)) {
                result += c;
            }
        }
        return result;
    }

    public static void listNamesContainingSubstring(String names, String substring) {
        int start = 0;

        while (start < names.length()) {
            int end = names.indexOf(',', start);
            if (end == -1) {
                end = names.length();
            }

            String name = names.substring(start, end).trim();
            if (name.contains(substring)) {
                System.out.println(name);
            }
            
            start = end + 1;
        }
    }

    public static String sortNamesAlphabetically(String names) {
        String[] namesArray = new String[100];
        int count = 0;
        int start = 0;

        while (start < names.length()) {
            int end = names.indexOf(',', start);
            if (end == -1) {
                end = names.length();
            }

            namesArray[count] = names.substring(start, end).trim();
            count++;
            start = end + 1;
        }

        for (int i = 0; i < count - 1; i++) {
            for (int j = i + 1; j < count; j++) {
                if (namesArray[i].compareToIgnoreCase(namesArray[j]) > 0) {
                    String temp = namesArray[i];
                    namesArray[i] = namesArray[j];
                    namesArray[j] = temp;
                }
            }
        }

        String result = "";
        for (int i = 0; i < count; i++) {
            if (i > 0) {
                result += ", ";
            }
            result += namesArray[i];
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter names separated by commas:");
        String names = sc.nextLine();

        System.out.println("Enter substring to search for:");
        String substring = sc.nextLine();

        System.out.println("Initials of names:");
        int start = 0;
        while (start < names.length()) {
            int end = names.indexOf(',', start);
            if (end == -1) {
                end = names.length();
            }

            String name = names.substring(start, end).trim();
            System.out.println(name + " -> " + extractInitials(name));

            start = end + 1;
        }

        System.out.println("\nNames with whitespace removed:");
        start = 0;
        while (start < names.length()) {
            int end = names.indexOf(',', start);
            if (end == -1) {
                end = names.length();
            }

            String name = names.substring(start, end).trim();
            System.out.println(name + " -> " + removeWhitespace(name));

            start = end + 1;
        }

        System.out.println("\nNames containing '" + substring + "':");
        listNamesContainingSubstring(names, substring);

        System.out.println("\nSorted names:");
        System.out.println(sortNamesAlphabetically(names));

        sc.close();
    }
}
