package GUIModule;

import processing.core.PApplet;

public class MyDisplay extends PApplet{
	
	public void setup()
	{
		size(400, 400);		//size of the applet
		background(200, 200, 200);
	}
	
	public void draw()
	{
		fill(255, 255, 0);	//Change colour of ellipse
		ellipse(200, 200, 390, 390);
		fill(0, 0, 0);
		ellipse(120, 130, 50, 70);
		ellipse(280, 130, 50, 70);
		
		noFill();	//Arc is not filled with colour
		arc(200, 280, 75, 75, 0, PI);
		
	}

}
