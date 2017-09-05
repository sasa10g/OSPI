package ctrl;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import geometry.Circle;
import geometry.Point;
import gui.DrawingFrame;
import gui.View;
import model.Model;

public class Controller {

	private Model model;
	private View view;
	private DrawingFrame frame;
	private JFileChooser fileChooser = new JFileChooser(); // extenduje
															// jdialog,za
															// biranje fajla

	public Controller() {

	}

	public void normalizaton(ArrayList<Point> points) {
		float maxX = 0;
		float maxY = 0;

		for (Point p : points) { // trazi najveci X i najveci Y,zbog normalne
									// raspodele
			if (p.getX() > maxX) {
				maxX = p.getX();
			}
			if (p.getY() > maxY) {
				maxY = p.getY();
			}
		}
		for (Point p : points) { // crtanje tacaka u normalnoj raspodeli
			p = new Point((int) (p.getX() / maxX * 800), (int) (p.getY() / maxY * 600));
			model.getPoints().add(p);
		}
		view.repaint();
	}

	public void loadData() {

		int click = fileChooser.showOpenDialog(frame.getPnlDrawing());

		if (click == JFileChooser.APPROVE_OPTION) {
			if (fileChooser.getSelectedFile().toString().endsWith(".csv")) {
				File file = fileChooser.getSelectedFile(); // getujemo taj fajl
															// na koji smo
															// kliknuli
				Point p;
				BufferedReader bf;
				ArrayList<Point> points = new ArrayList<Point>();
				try {
					bf = new BufferedReader(new FileReader(file)); // bufferreader
																	// ne moze
																	// da cita
																	// fajl,filereader
																	// moze
					String line;
					float x, y;
					while ((line = bf.readLine()) != null) { // string line je
																// jednak jednom
																// redu i dok ne
																// dodje do
																// kraja ide
																// kroz redove
						List<String> lineList = Arrays.asList(line.split(",")); // stavlja
																				// u
																				// listu
																				// stringova
																				// ceo
																				// red,
																				// pojedinacno
																				// svaki
																				// string
																				// odvojen
																				// zarezom

						x = Float.parseFloat(lineList.get(0));
						y = Float.parseFloat(lineList.get(1));
						p = new Point((int) x, (int) y, Color.BLACK);
						points.add(p);

					}
					normalizaton(points);

				} catch (IOException e) {

					e.printStackTrace();

				} finally {

				}

			} else {
				JOptionPane.showMessageDialog(null, "Must load a csv file!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}


	public void kMeans(int clusters, int knn, int pX, int pY) {
		Color rndC;
		Point p;
		int x, y;
		Random rnd = new Random();
		for (int i = 0; i < clusters; i++) {
			float r = rnd.nextFloat();
			float g = rnd.nextFloat();
			float b = rnd.nextFloat();
			rndC = new Color(r, g, b);
			x = rnd.nextInt(800);
			y = rnd.nextInt(600);
			p = new Point(x, y, rndC);
			model.getClusters().add(p);
			Circle c = new Circle(p, 23, rndC);
			model.getCircles().add(c);
		}

		double distance = 9000;

		for (Point point : model.getPoints()) {
			for (Point cluster : model.getClusters()) {

				if (point.distance(cluster) < distance) {
					distance = point.distance(cluster);
					point.setToCluster(cluster);
					point.setLineColor(cluster.getLineColor());
				}
			}
			distance = 9000;
		}
		Point checkPoint = new Point(pX, pY);
		
		// model.getPoints().add(checkPoint);
		// ArrayList<Double> distances= new ArrayList<>();

		for (Point pt : model.getPoints()) {
			pt.setDistanceFromCheckPoint(checkPoint.distance(pt));
			// dst = checkPoint.distance(pt);
			// distances.add(dst);
		}

		//double dst = 9000;
		//ArrayList<Point> localPoints = new ArrayList<>();
		Point temp;

		for (int i = 0; i < model.getPoints().size(); i++) {
			for (int j = 1; j < model.getPoints().size() - i; j++) {

				if (model.getPoints().get(j - 1).getDistanceFromCheckPoint() > model.getPoints().get(j)
						.getDistanceFromCheckPoint()) {
					temp = model.getPoints().get(j - 1);
					model.getPoints().set(j - 1, model.getPoints().get(j));
					model.getPoints().set(j, temp);
				}

			}
		}
		frame.getTxtpX().setText(String.valueOf(model.getPoints().get(1).getDistanceFromCheckPoint()));
		//frame.getTxtpY().setText(String.valueOf(model.getPoints().get(model.getPoints().size()-1).getDistanceFromCheckPoint()));
		
		ArrayList<Point> localPoints = new ArrayList<>();
		for(int f=0;f<knn;f++) {
			localPoints.add(model.getPoints().get(f));
		}
		
		 int toCluster=23;
		ArrayList<Integer> ints = new ArrayList<>();
		Point localCluster;
		for(Point ptt: localPoints) {
			for(int i=0;i<model.getClusters().size();i++) {
				localCluster=ptt.getHisCluster();
				if(localCluster.equals(model.getClusters().get(i))) {
					ints.add(i);
				}
				
			}
		}
		
		ArrayList<Integer> occurence = new ArrayList<Integer>();
		
		//1101----------------1,3,0,0
		for(int i=0;i<model.getClusters().size();i++) {
			occurence.add(Collections.frequency(ints, i));
		}
		
		int max = Collections.max(occurence);//3
		for(int i=0;i<occurence.size();i++) {
			int local=occurence.get(i);
			if(local==max) {//=3
				toCluster=i;
				
				
			}
		}
		
	        
		
		// toCluster = 0;
		//	frame.getTxtpX().setText(String.valueOf(toCluster));
		checkPoint.setLineColor(model.getClusters().get(toCluster).getLineColor());
		model.getPoints().add(checkPoint);
		Circle c = new Circle(checkPoint,35,checkPoint.getLineColor());
		model.getCircles().add(c);

		view.repaint();

	}

	
	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
		if (this.view != null)
			this.view.setModel(model);

	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
		view.setController(this);
	}

	public DrawingFrame getFrame() {
		return frame;
	}

	public void setFrame(DrawingFrame frame) {
		this.frame = frame;
	}

}