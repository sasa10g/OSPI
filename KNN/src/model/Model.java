package model;

import java.awt.Color;
import java.util.ArrayList;

import geometry.Circle;
import geometry.Point;

public class Model {

	private ArrayList<Point> points = new ArrayList<Point>();
	private ArrayList<Circle> circles = new ArrayList<Circle>();
	private ArrayList<Color> colors = new ArrayList<Color>();
	private ArrayList<Point> clusters = new ArrayList<Point>();

	public Model() {

	}

	public ArrayList<Point> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<Point> shapes) {
		this.points = shapes;
	}

	public ArrayList<Circle> getCircles() {
		return circles;
	}

	public void setCircles(ArrayList<Circle> circles) {
		this.circles = circles;
	}

	public ArrayList<Color> getColors() {
		return colors;
	}

	public void setColors(ArrayList<Color> colors) {
		this.colors = colors;
	}

	public ArrayList<Point> getClusters() {
		return clusters;
	}

	public void setClusters(ArrayList<Point> clusters) {
		this.clusters = clusters;
	}

	
}
