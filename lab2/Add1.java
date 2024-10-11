
class Add1{
	public static void main(String[]args){
		int i, j;
		int a[][]= new int[10][];
		for(i=0;i<10;i++){
			a[i] = new int[i];
		}
		for(i=0;i<10;i++){
			for(j=0;j<i;j++){
				a[i][j]=i+j;
			}
		}
		for(i=0;i<10;i++){
			for(j=0;j<i;j++){
				System.out.print(a[i][j]);
			}
			System.out.println();
		}
	}
}