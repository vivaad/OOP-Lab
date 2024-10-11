import java.util.*;

class Prg1{

	int max1(int x, int y, int z){
		if(x>y){
			if(x>z){
				return x;
			}
			return z;
		}
		if(y>z){
			return y;
		}
		return z;
		
		
	}

	int max2(int x, int y, int z){
		if(x>y && x>z){
			return x;
		} else if(y>x && y>z){
			return y;
		}else if(z>x && z>y){
			return z;
		} else {
			System.out.println("Error!!");
			return -1;
		}
	}

	public static void main(String[] args){
		int x, y, z;
		Scanner in = new Scanner(System.in);

		System.out.println("Enter 3 numbers");
		x = in.nextInt(); y = in.nextInt(); z = in.nextInt();

		Prg1 obj = new Prg1();
		int m1=obj.max1(x, y, z);
		System.out.println(m1);

		int m2=obj.max2(x, y, z);
		System.out.println(m2);
		in.close();
	}
}