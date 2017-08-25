
public class Blah {
    public static void main(String[] args) {
          int x = 5, y = 10, z;
          System.out.println("HELLO");
          z=fun(x,y);
          System.out.println("z:"+z);
          z = fun (y,x);
    }//end main

    public static int fun(int bob, int fred){
          System.out.println("bob:"+bob);
          System.out.println("fred:"+fred);
          return (bob+fred);
     }
}
