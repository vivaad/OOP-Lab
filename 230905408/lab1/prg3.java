import java.util.*;

class Prg3{
	static double fact(int n){
		if(n==1)
			return 1.0;

		return n*fact(n-1);
	}

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		System.out.println("Enter n and r:");
		int n=in.nextInt();
		int r= in.nextInt();
		double nCr = fact(n)/(fact(n-r)*fact(r));
		System.out.println("nCr: "+nCr);
		in.close();
	}
}