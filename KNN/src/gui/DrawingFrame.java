package gui;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Model;
import ctrl.Controller;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class DrawingFrame extends JFrame {

	private JPanel contentPane;
	
	private View pnlDrawing;
	//private JFrame pnlDrawing;
	private JTextField txtClusters;
	private JTextField txtKNN;
	
	private Controller controller;
	private Model model;
	private JTextField txtpX;
	private JTextField txtpY;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DrawingFrame frame = new DrawingFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DrawingFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		pnlDrawing = new View();
		//pnlDrawing = new JFrame();
		pnlDrawing.setBounds(0, 33, 900, 700);
		pnlDrawing.setBackground(Color.WHITE);
		contentPane.add(pnlDrawing);
		
		JPanel toolBar = new JPanel();
		toolBar.setBounds(0, 0, 900, 40);
		contentPane.add(toolBar);
		toolBar.setBackground(new Color(169, 169, 169));
		toolBar.setLayout(null);
		
		JButton btnLoadData = new JButton("Load Data");
		btnLoadData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.loadData();
				
			}
		});
		btnLoadData.setBounds(6, 3, 104, 29);
		toolBar.add(btnLoadData);
		
		JLabel lblClusters = new JLabel("Clusters:");
		lblClusters.setBounds(128, 9, 63, 14);
		toolBar.add(lblClusters);
		
		txtClusters = new JTextField();
		txtClusters.setBounds(191, 6, 57, 20);
		toolBar.add(txtClusters);
		txtClusters.setColumns(10);
		
		JLabel lblKNN = new JLabel("KNN:");
		lblKNN.setBounds(260, 9, 45, 14);
		toolBar.add(lblKNN);
		
		txtKNN = new JTextField();
		txtKNN.setBounds(300, 6, 57, 20);
		toolBar.add(txtKNN);
		txtKNN.setColumns(10);
		
		JLabel lblPointX = new JLabel("Point X:");
		lblPointX.setBounds(469, 9, 57, 14);
		toolBar.add(lblPointX);
		
		txtpX = new JTextField();
		txtpX.setBounds(517, 6, 57, 20);
		toolBar.add(txtpX);
		txtpX.setColumns(10);
		
		
		JLabel lblPointY = new JLabel("Point Y:");
		lblPointY.setBounds(587, 9, 57, 14);
		toolBar.add(lblPointY);
		
		txtpY = new JTextField();
		txtpY.setBounds(635, 6, 63, 20);
		toolBar.add(txtpY);
		txtpY.setColumns(10);
		
		JButton btnClustering = new JButton("START!");
		btnClustering.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int clusters,knn,x = 0,y = 0;
				try {
					clusters=Integer.parseInt(txtClusters.getText());
					knn=Integer.parseInt(txtKNN.getText());
					x=Integer.parseInt(txtpX.getText());
					y=Integer.parseInt(txtpY.getText());
					if(clusters<=0 || knn<=0  || x<=0  || y<=0) {
						throw new NumberFormatException();
					}		
					controller.kMeans(clusters, knn,x,y);
					clusters=0;
					knn=0;
					x=0;
					y=0;
				} catch(NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
					
				}
				
			}
		});
		btnClustering.setBounds(730, 3, 164, 29);
		toolBar.add(btnClustering);
	}

	public View getPnlDrawing() {
		return pnlDrawing;
	}

	public void setPnlDrawing(View pnlDrawing) {
		this.pnlDrawing = pnlDrawing;
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

	public JTextField getTxtpX() {
		return txtpX;
	}

	public void setTxtpX(JTextField txtpX) {
		this.txtpX = txtpX;
	}

	public JTextField getTxtpY() {
		return txtpY;
	}

	public void setTxtpY(JTextField txtpY) {
		this.txtpY = txtpY;
	}

	
}
