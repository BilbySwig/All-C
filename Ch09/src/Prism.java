public class Prism extends Rectangle{
  private int height;

  public Prism(int l, int w, int h){
    super(l,w); 
    height = h;
  }
  public int volume(){
    return super.area() * height;
  }
  public int area(){
	  return 2*super.area()+2*(length*height)+2*(width*height);
  }
  
  public String toString(){
 	return super.toString()+" Height: "+height;
 	}
}
