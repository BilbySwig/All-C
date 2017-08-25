//package TerribleTrianglesForKids;

import java.awt.Color;
import javax.swing.*;

public class TerribleTriangles extends TriangleGUI{

	/******   use mrMain like it is your main    ****/
	public void mrMain(){
		/**  pretend that these variables have been declared **/
		/**  but DON'T uncomment them!!!   **/
		//double xA, yA, xB, yB, xC, yC;
		double sideAB, sideAC, sideBC;
		
		//you can pretty things up if you want
		this.setFont("Arial",12);
		this.setFontColor( Color.GREEN);
		this.setBackgroundColor(Color.GRAY);
		this.setLineColor( new Color(255,255,0) );
		this.setGridColor(Color.MAGENTA);
		
		sideAB =deestonce(xA, yA, xB, yB);
		sideAC =deestonce(xA, yA, xC, yC);
		sideBC =deestonce(xB, yB, xC, yC);
		//this is how your print onto the GUI (rather than in the console)
		windowprint("Use this instead of System.out.print.");
		windowprintln("Or use this if you...");
		windowprintln("what a line break");
		windowprintln("Side\tLength\tSlope");
		windowprintln("AB\t"+ roundIt(sideAB)+"\t"+ roundIt(Slope(xA, yA, xB, yB)));
		windowprintln("AC\t"+ roundIt(sideAC)+"\t"+ roundIt(Slope(xA, yA, xB, yB)));
		windowprintln("BC\t"+ roundIt(sideBC)+"\t"+ roundIt(Slope(xB, yB, xC, yC)));
		windowprintln("Angles:");
		windowprintln("A= "+Angles(sideAC, sideAB, sideBC));
		windowprintln("B= "+Angles(sideAB, sideBC, sideAC));
		windowprintln("C= "+Angles(sideAC, sideBC, sideAB));
		
		if(Angles(sideAC, sideBC, sideAB)!=90)
			if(Angles(sideAC, sideAB, sideBC)!=90)
				if(Angles(sideAB, sideBC, sideAC)!=90)
					if(Angles(sideAC, sideBC, sideAB)<90)
						if(Angles(sideAC, sideAB, sideBC)<90)
							if(Angles(sideAB, sideBC, sideAC)<90)
								windowprint("Acute ");
							else
								windowprint("Obtuse ");
						else
							windowprint("Obtuse ");
					else
						windowprint("Obtuse ");
				else
					windowprint("Right ");
			else
				windowprint("Right ");
		else
			windowprint("Right ");
		if(sideAB!=sideAC)
			if(sideAB!=sideBC)
				windowprint("Scalene \n");
			else
				windowprint("Isosceles \n");
		else
			windowprint("Isosceles \n");
		
		windowprintln("Perimeter: "+ (roundIt(sideAB+sideAC+sideBC)));	
		windowprintln("Area: "+ Hero(sideAB, sideAC, sideBC));
	}//end mrMain
	
	
	//a gift from Mr. Reed
	/*Preconditions:  num is any double
	 * Postconditions:  the number that is returned represents num 
	 *                  ROUNDED to the 100th place  */
	public static double roundIt( double num){
		if(Double.isInfinite(num)) //don't round infinity!
			return Math.abs(num);
		//otherwise, round the number to 2 decimals & return
		return (int)Math.round(num*100)/100.0;
	}//end rounder
	
	/* Preconditions: x1, y1 and x2, y2 are points on the graph that can be any double.
	 * Postconditions: you will get the distance between two points
	 */
	public static double deestonce (double x1, double y1, double x2, double y2){
		double chgx, chgy;
		chgx=x2-x1;
		chgy=y2-y1;
		return Math.sqrt((chgx*chgx)+(chgy*chgy));
	}//end deestonce
	
	/*
	 * Preconditions: x1, y1 and x2, y2 are points on the graph that can be any real number
	 * Postconditions: This gives the slope of the line segment between two points
	 */
	public static double Slope (double x1, double y1, double x2, double y2){
	double deltay, deltax;
	deltay=y2-y1;
	deltax=x2-x1;
	return deltay/deltax;
	}//end Slope
	
	/* 
	 * Preconditions: sidea, sideb, and sidec are the possitive side lengths of the triangle
	 *Postconditions:gives angle measure in degrees of one of the triangle's angles
	 */
	public static double Angles(double sidea, double sideb, double sidec){
		double num, denom, uncos;
		num= sidec*sidec-sideb*sideb-sidea*sidea;
		denom= -2*sidea*sideb;
		uncos=Math.acos(num/denom);
		return roundIt(uncos* (180/Math.PI));
	}//end Angles
	
	/*Preconditions: sidea, sideb, and sidec are the lengths of the triangle's sides
	 * Postconditions:gives the area of the triangle
	 */
	public static double Hero(double sidea, double sideb,double  sidec){
		double s= (sidea+sideb+sidec)/2;
		return roundIt(Math.sqrt(s*(s-sidea)*(s-sideb)*(s-sidec)));
	}	
		
	
	//Don't touch this main, thanks :)
	public static void main(String[] args){ new TerribleTriangles();}	
}//end program
//MMXIV