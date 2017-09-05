package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Point {

	private int x;
	private int y;
	private Color lineColor=Color.BLACK;
	private boolean visited, noise, cluster;
	private ArrayList<Point> points = new ArrayList<Point>();
	private double distanceFromCheckPoint;
	Point c;
	//List<Point> clustersPoint;

	public Point() {

	}

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Point(int x, int y,Color lineColor) {
		this.x = x;
		this.y = y;
		this.lineColor = lineColor;
	}

	
	public void setToCluster(Point c, Point p) {
		
	}
	
	public String toString() {
		return x + "," + y;
	}

	public void moveOn(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void moveFor(int x, int y) {
		this.x += x;
		this.y += y;
	}

	public void draw(Graphics g) {
		g.setColor(lineColor);
		g.drawLine(x, y - 1, x, y + 1);
		g.drawLine(x - 1, y, x + 1, y);
	}

	public void selected(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect(x - 2, y - 2, 4, 4);
	}

	public boolean contains(int x, int y) {
		int dx = this.x - x;
		int dy = this.y - y;
		if (Math.abs(dx) < 3 && Math.abs(dy) < 3)
			return true;
		else
			return false;
	}

	public double distance(Point p) {
		int dx = this.x - p.x;
		int dy = this.y - p.y;
		return Math.sqrt(dx * dx + dy * dy);
	}

	public Color getLineColor() {
		return lineColor;
	}

	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}


	public boolean isCluster() {
		return cluster;
	}

	public void setCluster(boolean cluster) {
		this.cluster = cluster;
	}

	public Point getHisCluster() {
		return c;
	}

	public void setToCluster(Point c) {
		this.c = c;
	}

	public ArrayList<Point> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<Point> points) {
		this.points = points;
	}

	public double getDistanceFromCheckPoint() {
		return distanceFromCheckPoint;
	}

	public void setDistanceFromCheckPoint(double distanceFromCheckPoint) {
		this.distanceFromCheckPoint = distanceFromCheckPoint;
	}

	

	

	
}
