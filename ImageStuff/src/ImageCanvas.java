import java.awt.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;
public class ImageCanvas extends JPanel{
	private BufferedImage img; //the image that appears on this canvas
	//dimensions of the image that is showing on the screen
	private int imgWidth, imgHeight;
	
	
	private static final int TYPE = BufferedImage.TYPE_INT_ARGB_PRE;
	
	/** ***************** PIXEL FUNCTIONS ****************** **/
	 public static final int A=0, R=1, G=2, B=3;

	 //returns only the red value of the pixel
	 //   EX: pixel = 0x004f2ca5 --> returns 0x0000004f
	 public int howRed(int pixel){return (pixel & 0x00ff0000)>>16;}

	 //returns only the green value of the pixel
	 //   EX: pixel = 0x004f2ca5 --> returns 0x0000002c 
	 public int howGreen(int pixel){return (pixel & 0x0000ff00)>>8;}
	 
	 //returns only the blue value of the pixel
	 //   EX: pixel = 0x004f2ca5 --> returns 0x000000a5
	 public int howBlue(int pixel){return (pixel & 0x000000ff);}
	 
	 //returns a new pixel with the specified alpha
	 //    red, green, and blue values
	 //  EX:  combine( 0x00000000, 0x0000004f, 0x0000002c, 0x000000a5) --> 0x004f2ca5
	 public int combine(int a, int r, int g, int b){
		 a=a<<24;
		 r=r<<16;
		 g=g<<8;
		 return a+r+g+b;
	 }
    /** ***************************************************   **/
	public ImageCanvas(){
		super();
		this.setBackground(Color.gray);
		this.setPreferredSize(new Dimension(400,400));
		img = new BufferedImage(200,200,TYPE);
		imgWidth = img.getWidth();
		imgHeight = img.getHeight();
		
	}
	
	public BufferedImage getImage(){return img;}
	public void setImage(File file){
		try{ 
			img = ImageIO.read((file));
			MediaTracker mt = new MediaTracker(new Component(){});
			mt.addImage(img, 0);
			mt.waitForAll();
		}
		catch(Exception ex){ex.printStackTrace();}
		imgWidth = img.getWidth();
		imgHeight = img.getHeight();
		//pix = imgToArray();
		this.setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
	}
	

	// *********************Easy pixel manips************************
	
	public void red(){
		int[][] pixelz=imgToArray();
		for(int r=0;r<pixelz.length;r++)
			for(int c=0;c<pixelz[0].length;c++)
				pixelz[r][c]=pixelz[r][c] & 0x00ff0000;
		arrayToImg(pixelz);
	}
	public void green(){
		int[][] pixelz=imgToArray();
		for(int r=0;r<pixelz.length;r++)
			for(int c=0;c<pixelz[0].length;c++)
				pixelz[r][c]=pixelz[r][c] & 0x0000ff00;
		arrayToImg(pixelz);
	}
	public void blue(){
		int[][] pixelz=imgToArray();
		for(int r=0;r<pixelz.length;r++)
			for(int c=0;c<pixelz[0].length;c++)
				pixelz[r][c]=pixelz[r][c] & 0x000000ff;
		arrayToImg(pixelz);
	}
	
	public void negative(){
		int[][] pixelz=imgToArray();
		for(int r=0;r<pixelz.length;r++)
			for(int c=0;c<pixelz[0].length;c++){
				int red=255-howRed(pixelz[r][c]);
				int green=255-howGreen(pixelz[r][c]);
				int blue=255-howBlue(pixelz[r][c]);
				pixelz[r][c]=combine(0,red,green,blue);
			}
		arrayToImg(pixelz);
	}
	
	public void greyscale(){
		int[][] pixelz=imgToArray();
		for(int r=0;r<pixelz.length;r++)
			for(int c=0;c<pixelz[0].length;c++){
				int red=howRed(pixelz[r][c]);
				int green=howGreen(pixelz[r][c]);
				int blue=howBlue(pixelz[r][c]);
				int avg=(red+green+blue)/3;
				pixelz[r][c]=combine(0,avg,avg,avg);
			}
		arrayToImg(pixelz);
	}
	
	public void sepia(){
		int[][] pixelz=imgToArray();
		for(int r=0;r<pixelz.length;r++)
			for(int c=0;c<pixelz[0].length;c++){
				int red=howRed(pixelz[r][c]);
				int green=howGreen(pixelz[r][c]);
				int blue=howBlue(pixelz[r][c]);
				int newRed=(int)Math.round((red*.393)+(green*.769)+(blue*.189));
				int newGreen=(int)Math.round((red*.349)+(green*.686)+(blue*.168));
				int newBlue=(int)Math.round((red*.272)+(green*.534)+(blue*.131));
				if(newRed>255)
					newRed=255;
				if(newGreen>255)
					newGreen=255;
				if(newBlue>255)
					newBlue=255;
				pixelz[r][c]=combine(0,newRed,newGreen,newBlue);
			}
		arrayToImg(pixelz);
	}
	
	
	// *********************END Easy pixel manips************************
	
	// ********************KERNEL STUFF *********************************
	public void blur(){
		int [][] orig= imgToArray();
		int [][] blur=new int[imgHeight][imgWidth];
		int red=0, green=0,blue=0;
		for(int r=1;r<imgHeight-1;r++)
			for(int c=1;c<imgWidth-1;c++){
				red=0; 
				green=0; 
				blue=0;
				for(int i=r-1;i<r+2;i++)
					for(int j=c-1;j<c+2;j++){
						red+=howRed(orig[i][j])/9;
						green+=howGreen(orig[i][j])/9;
						blue+=howBlue(orig[i][j])/9;
					}
				blur[r][c]=combine(0,red,green,blue);		
			}
		arrayToImg(blur);			
	}
	
	public void sharpen(){
		int [][] orig= imgToArray();
		int [][] sharp=new int[imgHeight][imgWidth];
		int red=0, green=0, blue=0;
		for(int r=1;r<imgHeight-2;r++)
			for(int c=1;c<imgWidth-2;c++){
				red=0; 
				green=0; 
				blue=0;
				for(int rr=r-1;rr<r+2;rr++)
					for(int cc=c-1;cc<c+2;cc++){
						if(rr-r==0 && cc-c==0){//middle
							red+=17*howRed(orig[rr][cc])/9;
							green+=17*howGreen(orig[rr][cc])/9;
							blue+=17*howBlue(orig[rr][cc])/9;
							

						}else{
							red-=(1*howRed(orig[rr][cc]))/9;
							green-=(1*howGreen(orig[rr][cc]))/9;							
							blue-=(1*howBlue(orig[rr][cc]))/9;
						}
					}
				if(red>255)
					red=255;
				if(green>255)
					green=255;
				if(blue>255)
					blue=255;
				
				if(red<0)
					red=0;
				if(green<0)
					green=0;
				if(blue<0)
					blue=0;
				
				sharp[r][c]=combine(0,red,green,blue);
				//done looking at all the neighbors
				//time to combine into a pixel
			}		
		arrayToImg(sharp);
	}

	public void transform(double[][] matrix){
		int[][] orig=imgToArray();
														
		int maxX;
		int maxY; 	
		int minX=0,minY=0;
				
		
		
		
		
		minX=(int)Math.min(Math.min((imgHeight*matrix[0][1]),(imgWidth*matrix[0][0]+imgHeight*matrix[0][1])),Math.min(0, (imgWidth*matrix[0][0])));
		minY=(int)Math.min(Math.min((imgHeight*matrix[1][1]),(imgWidth*matrix[1][0]+imgHeight*matrix[1][1])),Math.min(0, (imgWidth*matrix[1][0])));
		if(minX<0)
			minX*=-1;
		if(minY<0)
			minY*=-1;
		
		maxX=(int)Math.max(Math.max((imgHeight*matrix[0][1]),(imgWidth*matrix[0][0]+imgHeight*matrix[0][1])),(imgWidth*matrix[0][0]))+minX;
		maxY=(int)Math.max(Math.max((imgHeight*matrix[1][1]),(imgWidth*matrix[1][0]+imgHeight*matrix[1][1])),(imgWidth*matrix[1][0]))+minY;
		
		int[][] trans=new int[maxY][maxX];
		int x, y;
		
		
		
		for(int r=0;r<imgHeight;r++)
			for(int c=0;c<imgWidth;c++){
				x=(int)(c*matrix[0][0]+r*matrix[0][1])+minX;
				y=(int)(c*matrix[1][0]+r*matrix[1][1])+minY;
				if(x>=0 && y>=0 && x<trans[0].length && y<trans.length)
					trans[y][x]=orig[r][c];
			}
		
		
		
		arrayToImg(trans);
	}
	
	public void stretchHoriz(double factor){
		double[][] trans={{factor,0},{0,1}};
		transform(trans);
	}
	
	public void stretchVert(double factor){
		double[][] trans={{1,0},{0,factor}};
		transform(trans);
	}
	
	
	/* **************MATRIX STUFF ********************************* */
	public void resize(double ratio){
		
	}
	
	public void rotate(double angle){
		double[][] rot={{Math.cos(Math.toRadians(angle)),-1*Math.sin(Math.toRadians(angle))},{Math.sin(Math.toRadians(angle)), Math.cos(Math.toRadians(angle))}};
		transform(rot);
	}
	
	public void paint(Graphics g){
		super.paint(g);
		((Graphics2D)g).drawImage(img,null,0,0);
		//g.drawImage(img, 0, 0, null);
	}
	
	
/**  **************** START WITH THESE!  ************** **/
	//Postconditions:  all of the pixels from the original image have been stored
	//  into a 2d array and that 2d array has been returned
	public int[][] imgToArray(){
		//this puts the pixels into a 1d array.  You want to move them into a 2d array
		int[] pix = img.getRGB(0, 0, imgWidth, imgHeight, null, 0, imgWidth);
		//now do stuff
		
		int[][] pix2D=new int[imgHeight][imgWidth];
		
		int index=0;
		for(int i=0; i<imgHeight;i++)
			for(int j=0; j<imgWidth;j++){
				pix2D[i][j]=pix[index];
				index++;
			}
				
		//this is not what you want to return at all
		return pix2D;
	}
	
	//Postconditions:  the pixel values from the given 2d array have been loaded onto
	//  the image
	//HINT:  use this function--> img.setRGB(x,y,val); IMPORTANT:  this function works in an x,y coordinate system (NOT a ROW, COL world)
	public void arrayToImg(int[][] pix){
		imgWidth = pix[0].length;
		imgHeight = pix.length;
		this.setPreferredSize(new Dimension(imgWidth,imgHeight));
		img = new BufferedImage(imgWidth,imgHeight,img.getType());
		//Write code below this comment
		/*
		Your best friend is :  img.setRGB(x,y,val);
		This lets you "paint" onto the image
		You tell it where (x,y) you want to paint
		And what color (pixel value) you want to paint there!
		*/
		for(int r=0;r<pix.length;r++)
			for(int c=0;c<pix[0].length;c++)
				img.setRGB(c,r,pix[r][c]);
		this.repaint();
	}
	
	//have kids do this first!  just take the pixels and replace them
	public void mirror(boolean vert){
		int[][] orig = imgToArray();
		int[][] result=new int[orig.length][orig[0].length];
		//do stuff
		/** after your have written imageToArray & arrayToImg, do mirror stuff here **/
		if(vert)
			for(int r=0;r<orig.length;r++)
				for(int c=0;c<orig[0].length;c++){
					result[r][c]=orig[orig.length-1-r][c];
			}
		else
			for(int r=0;r<orig.length;r++)
				for(int c=0;c<orig[0].length;c++){
					result[r][c]=orig[r][orig[0].length-1-c];
			}
		//now put the result on the image
		arrayToImg(result); //MIGHT use a 2nd 2d array here		
	}
	
	
	
	
	
	
	
	
	
	//******dummy function for testing **************/
	public void tester(){
		int[][] blah = imgToArray();
		arrayToImg(blah);
			
		double[][] transform={{Math.cos(Math.toRadians(30)),-1*Math.sin(Math.toRadians(30))},{Math.sin(Math.toRadians(30)),Math.cos(Math.toRadians(30))}};
		//double[][] transform={{1,0},{0,2}};
		transform(transform);
		
		/*
	    for(int y=0; y<blah.length; y++)
		for(int x=0; x<blah[0].length; x++){
	 	    int red = howRed(blah[y][x]);
		    int green = howGreen(blah[y][x]);
		    int blue = howBlue(blah[y][x]);
		    blah[y][x] = combine(0,red,green,blue);
		}
	   arrayToImg(blah);	
	   */
	   
	   System.out.println("Done testing");
	
	}


/**  ***************************************************  **/
}
