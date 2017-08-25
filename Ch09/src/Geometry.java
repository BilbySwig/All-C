
public class Geometry {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Rectangle r= new Rectangle(3,4);
		Prism p=new Prism(1,2,30);
		Rectangle confused=new Prism(1,1,1);
		
		System.out.println(r.area());
		System.out.println(p.area());
		System.out.println(confused.area());
	}

}
