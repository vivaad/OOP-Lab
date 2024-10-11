import java.util.*;

class Prg2{

	int reverse(int n){
		int r=0;
		while(n>0){
			r = r*10 + n%10;
			n/=10;
		}
		return r;
	}

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		System.out.println("Enter a number: ");
		int n= in.nextInt();
		Prg2 obj = new Prg2();
		int r= obj.reverse(n);
		System.out.println(r==n?"Palindrone":"Not Palindrone");
		in.close();
	}
}