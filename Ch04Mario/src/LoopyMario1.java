
public class LoopyMario1 extends MarioWorld{
	
	public void animate(){
		myX=0;
		myY=150;
	/** ***********  PUT YOUR CODE IN HERE ********** **/
		jump(); //make mario jump
		
		//change your position
		myX+=5;
		drawMario(false);
		
		//walks mario to edge of cliff
		while(myX<245){
			myX+=10;
			drawMario(false);
		}
		
		//makes mario jump 5 times
		for(int i=1;i<6;i+=1)
			jump();
		drawMario(false);
		
		//makes mario fall off cliff
		while(myX<280){
			myX+=10;
			drawMario(true);
		}
		
		while(myY<320){
			myY+=10;
			drawMario(true);
		}
		
		drawMario(false);
		
		//walks mario over to lucky blocks
		while(myX<540){
			myX+=10;
			drawMario(false);
		}
		
		//makes mario jump 3 times at first block
		for(int j=0;j<3;j++)
			jump();
		
		//walks mario to second block
		while(myX<560){
			myX+=10;
			drawMario(false);
		}
		
		//jumps mario random amount of times at second block
		for(int k=0;k<100;k=k+(int)((Math.random())*10))
			jump();
			
		drawMario(false);
	/** ***************** **************** **/
	}//DON'T DELETE THIS BRACKET!!!!!!!!!!!
	
	
	//constructor
	public LoopyMario1(String bgNm){super(bgNm);}	
	public static void main(String[] args) {new LoopyMario1("background.png").animate();}
}
