
import java.util.*;

public class ShapeTester {

	public static void main(String[] args) {
		ArrayList<Shape> shapes = new ArrayList();
		//add new Shape objects to the array
		
		
		
		//print out their areas
		System.out.println("Areas: ");
		for(Shape s:shapes)
			System.out.println(s.area());
		
		
		//print out their perimeters
		System.out.println("Perimeters: ");
		for(Shape s:shapes)
			System.out.println(s.perimeter());
		
	}//end main

}


