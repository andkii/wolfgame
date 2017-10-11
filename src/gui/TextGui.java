package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TextGui extends Gui{

	private JLabel boardLabel;
	private JLabel statsLabel;

	public TextGui(){
		super();
	}

	public void init(String gameString, String statsString){

		boardLabel = new JLabel();
		boardLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 6));
		boardLabel.setText(gameString);
		pane.add(boardLabel, BorderLayout.WEST);

		statsLabel = new JLabel();
		statsLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
		statsLabel.setText(statsString);
		pane.add(statsLabel, BorderLayout.EAST);

		super.init();
	}

	public void update(String gameString, String statsString){
		boardLabel.setText(gameString);
		statsLabel.setText(statsString);

		boardLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 6 + 2*frame.getSize().height/500));
		statsLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
		pane.getSize();
	}
		
}

