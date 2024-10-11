import java.util.*;

class Prg1{

	public static void main(String[] args){
		Scanner in= new Scanner(System.in);
		int i, t;
		System.out.println("Enter length:");
		int l= in.nextInt();
		int a[]=new int[l];
		System.out.println("Enter "+l+" elements:");
		for(i=0;i<l;i++) a[i] = in.nextInt();
		for(i=0;i<l/2;i++){
			t=a[i];
			a[i]=a[l-i-1];
			a[l-i-1]=t;
		}
		for(i=0; i<l;i++)	
		System.out.println(a[i]);
	}
}
