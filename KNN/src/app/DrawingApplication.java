package app;

import ctrl.Controller;
import gui.DrawingFrame;
import gui.View;
import model.Model;

public class DrawingApplication {

	public static void main(String[] args) {
		DrawingFrame frame = new DrawingFrame();
		View view = frame.getPnlDrawing();
		Model model = new Model();
		Controller controller = new Controller();
		controller.setView(view);
		controller.setModel(model);
		controller.setFrame(frame);
		frame.setController(controller);
		frame.setVisible(true);
	}

}
