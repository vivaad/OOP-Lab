import java.util.*;


class Prg3{

	static boolean isPrime(int n){
		int i;
		for(i=2; i<Math.round(Math.sqrt(n)); i++)
			if(n%i==0)
				return false;
		return true;
	}

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int i, n;
		System.out.println("Enter length:");
		n=in.nextInt();
		int a[]= new int[n];
		System.out.println("Enter "+n+" numbers:");
		for(i=0;i<n; i++) a[i]=in.nextInt();
		for(i=0;i<n; i++) 
			if(isPrime(a[i]))
				System.out.println(a[i]);
	}
}