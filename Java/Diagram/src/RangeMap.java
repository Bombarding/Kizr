//Authors: Mariah Post, Alex Steel
//Date: 2-18-2016
//Robot Range Map
 
import java.awt.*;
import java.applet.*;
import java.lang.Math;
 
public class RangeMap extends Applet
{
private static final Color BLUE = null;
	//Establish variables
	int [] polygonX;
	int [] polygonY;
	int robotPositionX;
	int robotPositionY;
	//int robotPositionZ;
	double headingDirection;
	Polygon p;
 
	int N; //number of sensors
 
	int [] robot;
 
	public RangeMap()
	{
		//polygonX = new int[4];
		//polygonY = new int[4];
	}
 
	public void paint(Graphics g)
	{	
		setSize(1280,720); //Set frame size
		getValues();
		drawRangeMap(g);
	}
 
	public void getValues()
	{
		/*polygonX[0] = 200;
		polygonX[1] = 600;
		polygonX[2] = 600;
		polygonX[3] = 200;
 
		polygonY[0] = 100;
		polygonY[1] = 100;
		polygonY[2] = 500;
		polygonY[3] = 500;*/
 
		robotPositionX = 150;
		robotPositionY = 150;
 
		headingDirection = 0;
 
		N = 30;
	}
 
	public void drawRangeMap(Graphics g)
	{
		//Draw map of room
		//int x[] = polygonX;
		//int y[] = polygonY;
		int [] px = {150,50,256};
		int [] py = {0,103,44,131};
		p = new Polygon(px, py, 14);
		g.drawPolygon(p);
 
		//Draw circle for robot location
		g.drawOval(robotPositionX - 5, robotPositionY - 5, 10, 10);
 
//Draw line segment for heading direction
		headingDirection(g, robotPositionX, robotPositionY, 20, (headingDirection / 360) * 2 * Math.PI);
 
//Draw sensors
		drawRadialLines(g, robotPositionX, robotPositionY);
	}
 
	public void headingDirection(Graphics g, int x, int y, int r, double rads)
	{
		int a = x - robotPositionX + r;
		int b = y - robotPositionY;
 
		double newX = (a * Math.cos(rads) - b * Math.sin(rads)) + robotPositionX;
		double newY = (b * Math.cos(rads) + a * Math.sin(rads)) + robotPositionY;
 
		int newNewX = (int)Math.round(newX);
		int newNewY = (int)Math.round(newY);
		g.setColor(BLUE);
		g.drawLine(x, y, newNewX, newNewY);
	}
 
	public void drawRadialLines(Graphics g, int x, int y)
	{
 
		int startX = x;
		int startY = y;
 
		double r = 2 * Math.PI / N; //angle
		for (int i = 0; i<N; i++)
		{
			int length = 1;
			do
			{
				x = (int)(startX + length * Math.cos(r * i));
				y = (int)(startY + length * Math.sin(r * i));
				length++;
			}
			while(p.contains(x, y));
			g.setColor(BLUE);
			g.drawLine(startX, startY, x, y);
		}
	}
 
}