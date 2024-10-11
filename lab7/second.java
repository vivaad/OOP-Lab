import myPackages.p1.Maximum;

public class second{
	public static void main(String[] args){
		System.out.println(Maximum.max(5, 10));
		System.out.println(Maximum.max((float)5.1, (float)10.2));
		int[] a = {1, 2, 3};
		int[][] b = {{1, 2, 3}, {1, 2, 3}, {1, 2, 4}};
		System.out.println(Maximum.max(a));
		System.out.println(Maximum.max(b));
	}
}