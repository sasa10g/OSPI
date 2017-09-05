package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Circle{

	private Point center;
	private int radius;
	private Color lineColor=Color.BLACK;

	public Circle() {

	}
	
	public Circle(Point center,int radius, Color lineColor) {
		this.center=center;
		this.radius=radius;	
		this.lineColor=lineColor;

	}
	
	public Circle(Color lineColor,Color fillColor,Point center,int radius) {
		this.lineColor=lineColor;
		this.center=center;
		this.radius=radius;	
	}

	public String toString() {
		return center+","+radius;
	}

	public double getArrea() {
		return 0;
	}

	public void draw(Graphics g) {
		g.setColor(lineColor);
		g.drawOval(center.getX()-radius, center.getY()-radius, radius*2, radius*2);
		//g.setColor(fillColor);
		//g.fillOval(center.getX()-radius+1, center.getY()-radius+1, radius*2-2, radius*2-2);
		
	}
	
	public void selected(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect(center.getX()-radius-2, center.getY(), 4, 4);
		g.drawRect(center.getX()+radius-2, center.getY(), 4, 4);
		g.drawRect(center.getX(), center.getY()-radius-2, 4, 4);
		g.drawRect(center.getX(), center.getY()+radius-2, 4, 4);
	}

	public boolean contains(int x,int y) {
		if(center.distance(new Point(x,y))<this.radius)
			return true;
		else
			return false;
	}

	public int getRadius() {
		return radius;
	}
	
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	public Point getCenter() {
		return center;
	}
	
	public void setCenter(Point center) {
		this.center = center;
	}
	
	public void moveFor(int x, int y) {
		center.moveFor(x, y);

	}
	
	public void moveOn(int x, int y) {
		center.moveOn(x, y);
	}
}
