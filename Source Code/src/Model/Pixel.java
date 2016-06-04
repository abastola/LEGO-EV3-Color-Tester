/*
 * Name: Arjun Bastola 
 * Project: 5 / AI
5.	Class: Pixel
Description:
•	Stores detail for each Pixel
•	Store x-coordinate, y-coordinate and  value of color


 */


package Model;

public class Pixel {
	public float ValueOfColor; //color Value
	public float X; // x-axis
	public float Y; //y-axis
	
	//Default Constructor
	public Pixel() {
		ValueOfColor=(float) 9.87654;
		X=0;
		Y=0;
		
	}
	
	//Constructor with All Three Values
	public Pixel(double d, int x, int y){
		ValueOfColor=(float) d;
		X=x;
		Y=y;
		
	}

}
