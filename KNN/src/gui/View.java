package gui;

import geometry.Circle;
import geometry.Point;

import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.JPanel;

import ctrl.Controller;
import model.Model;

public class View extends JPanel {

	private Controller controller;
	private Model model;
	
	public View() {
		
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Iterator<Point> it = model.getPoints().iterator();
		while(it.hasNext())
			it.next().draw(g);
		
		Iterator<Circle> itt = model.getCircles().iterator();
		while(itt.hasNext())
			itt.next().draw(g);
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}
}
