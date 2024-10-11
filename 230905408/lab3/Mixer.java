import java.util.*;

public class Mixer {
    int a[];
    
    // Accepts array input from user
    void accept() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter length:");
        int n = in.nextInt();
        a = new int[n];
        System.out.println("Enter elements in ascending order:");
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
    }

    // Mixes two Mixer objects into a new sorted Mixer object
    Mixer mix(Mixer other) {
        Mixer result = new Mixer();
        result.a = new int[this.a.length + other.a.length];

        // Merge arrays
        int i = 0, j = 0, k = 0;
        while (i < this.a.length && j < other.a.length) {
            if (this.a[i] <= other.a[j]) {
                result.a[k++] = this.a[i++];
            } else {
                result.a[k++] = other.a[j++];
            }
        }

        // Copy remaining elements
        while (i < this.a.length) {
            result.a[k++] = this.a[i++];
        }
        while (j < other.a.length) {
            result.a[k++] = other.a[j++];
        }

        return result;
    }

    // Displays the array
    void display() {
        for (int value : a) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {        
        Mixer A = new Mixer();
        System.out.println("Enter details for the first array:");
        A.accept();
        
        Mixer B = new Mixer();
        System.out.println("Enter details for the second array:");
        B.accept();
        
        Mixer C = A.mix(B);
        System.out.println("Merged and sorted array:");
        C.display();
    }
}
