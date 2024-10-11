import java.util.Arrays;
import java.util.Scanner;

class SwapElements {

    public static <T> void swap(T[] array, int pos1, int pos2) {
        T temp = array[pos1];
        array[pos1] = array[pos2];
        array[pos2] = temp;
    }

    public static int binarySearch(Integer[] array, int key) {
        int index = Arrays.binarySearch(array, key);
        if (index >= 0) {
            return index;
        } else {
            System.out.println("Element " + key + " not found in the array.");
            return -1;
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the size of the array: ");
        int size = sc.nextInt();

        Integer[] array = new Integer[size];
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < size; i++) {
            array[i] = sc.nextInt();
        }

        Arrays.sort(array);

        System.out.println("Enter the first element to swap: ");
        int first = sc.nextInt();

        System.out.println("Enter the second element to swap: ");
        int second = sc.nextInt();

        int pos1 = binarySearch(array, first);
        int pos2 = binarySearch(array, second);

        if (pos1 != -1 && pos2 != -1) {
            swap(array, pos1, pos2);
        }

        System.out.println("Array after swapping:");
        for (int i : array) {
            System.out.println(i + " ");
        }
    }
}
