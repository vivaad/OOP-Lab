import com.course.structure.Building;
import com.course.structure.House;
import com.course.structure.School;
public class first{
	public static void main(String[] args){
		Building b = new Building(1000, 2);
		House h = new House(1000, 2, 2, 2);
		School s = new School(10000, 3, 10, "High");
		System.out.println(b.getSqFootage() + " " + h.getStories() + " "+  s.getGrade());
	}
}
