interface Series{
	public int getNext();
	public void reset();
	public void setStart(int i);
}
class ByTwos implements Series{
	int s;
	int x;
	ByTwos(int i){
		this.s = i;
		this.x = i;
	}
	public int getNext(){
		x+=2;
		return x; 
	}
	public void reset(){
		x = s;
	}
	public void setStart(int i){
		s = i;
	}
}
public class third{
	public static void main(String[] args){
		ByTwos b = new ByTwos(0);
		System.out.println(b.getNext());
		System.out.println(b.getNext());
		System.out.println(b.getNext());
		System.out.println(b.getNext());
	}
}