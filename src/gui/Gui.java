package gui;

import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;

public class Gui{

	protected JFrame frame;
	protected Container pane;

	public Gui(){
		this.frame = new JFrame();
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.pane = this.frame.getContentPane();	
	}

	public void init(){
		this.frame.pack();
		this.frame.setVisible(true);	
	}

	public void update(){

	}

	public void showResults(String statsString){		
		JTextArea ta = new JTextArea(10, 10);
		ta.setText(statsString);
		JOptionPane.showMessageDialog(null, ta);
	}
		
}

