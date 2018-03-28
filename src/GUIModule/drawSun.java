package GUIModule;

import processing.core.PApplet;
import processing.core.PImage;

public class drawSun extends PApplet{
	
	PImage img;
	
	public void setup()
	{
		size(400, 400);	//Set canvas size
		background(255);	//Set canvas colour
		stroke(0);	//Set pen colour
		img = loadImage("C:/Users/RAHUL/Desktop/new.jpg", "jpg");
		img.resize(0, height);	//Resize loaded image to full height of canvas
		image(img, 0, 0);	//Display image
	}
	
	public void draw()
	{
		
		int[] color = sunColorSec(second());	//Calculate colour code for sun
		// second() is a built in method which returns number of sec ellapsed sice the last minute of your computer clock
		fill(color[0], color[1], color[2]);	//Set sun colour
		ellipse(width/4, height/5, width/4, height/5);	//Draw sun
		
	}
	
	public int[] sunColorSec(float seconds)
	{
		int[] rgb = new int[3];
		//scale the brightness of the yellow based on the seconds. 
		//30 seconds is black and 0 second is bright yellow.
		
		float diffFrom30 = Math.abs(30-seconds);
		float ratio = diffFrom30/30;
		rgb[0] = (int)(255*ratio);
		rgb[1] = (int)(255*ratio);
		rgb[2] = 0;
		
		return rgb;
	}
}
