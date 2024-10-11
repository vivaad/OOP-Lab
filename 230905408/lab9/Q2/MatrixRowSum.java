import java.util.Random;
import java.util.Scanner;

class RowSumThread extends Thread {
    private int[] row;
    private int rowIndex;
    private int sum;

    public RowSumThread(int[] row, int rowIndex) {
        this.row = row;
        this.rowIndex = rowIndex;
    }

    @Override
    public void run() {
        sum = 0;
        for (int value : row) {
            sum += value;
        }
        System.out.println("Sum of row " + rowIndex + ": " + sum);
    }

    public int getSum() {
        return sum;
    }
}

public class MatrixRowSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of rows: ");
        int rows = sc.nextInt(); // Number of rows
        System.out.print("Enter the number of columns: "); // Added missing semicolon
        int cols = sc.nextInt(); // Number of columns
        int[][] matrix = new int[rows][cols];
        RowSumThread[] threads = new RowSumThread[rows];

        // Initialize the matrix with random integers
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextInt(10); // Random integers between 0 and 9
            }
        }

        // Create and start threads
        for (int i = 0; i < rows; i++) {
            threads[i] = new RowSumThread(matrix[i], i);
            threads[i].start();
        }

        // Wait for all threads to finish and calculate the total sum
        int totalSum = 0;
        try {
            for (RowSumThread thread : threads) {
                thread.join(); // Wait for thread to finish
                totalSum += thread.getSum(); // Add the row sum to total sum
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Display the total sum
        System.out.println("Total sum of the matrix: " + totalSum);
        sc.close(); // Close the scanner to prevent resource leaks
    }
}
