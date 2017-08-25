public class Rectangle {
  protected int length;
  protected int width;
  public Rectangle(){length=0; width = 0;}
  public Rectangle(int l, int w){
    length = l;
    width = w;
  }
  public int getLength(){return length;}
  public int getWidth(){return width;}
  public void setLength(int l){length=l;}
  public void setWidth(int w){width = w;}
  public int area(){return length * width;}
  public String toString(){
	  return "Length: "+length+" Width: "+width;
  }
}
