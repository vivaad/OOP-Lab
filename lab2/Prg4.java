import java.util.*;

class Prg4{

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		System.out.println("Enter m and n");
		int m=in.nextInt(), n=in.nextInt();
		int a[][]= new int[m][n];
		int b[][]= new int[m][n];
		int c[][]= new int[m][n];
		int i, j;
		System.out.print("Enter "+(2*m*n)+" values:");
		for(i=0; i<m; i++){
			for(j=0; j<n;j++){
				a[i][j]=in.nextInt();
				b[i][j]=in.nextInt();
			}
		}
		for(i=0; i<m; i++){
			for(j=0; j<n;j++){
				c[i][j]=a[i][j]+b[i][j];
			}
		}
		for(i=0; i<m; i++){
			for(j=0; j<n;j++){
				System.out.print(c[i][j]+" ");
			}
			System.out.println();
		}
	}
}