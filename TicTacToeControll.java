package edu.smg;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TicTacToeControll extends JPanel{
	private TicTacToePanel ticTacToePanel = new TicTacToePanel();
	public static JLabel textLbl = new JLabel("Player x's turn", SwingConstants.CENTER);
	
	public TicTacToeControll() {
		setLayout(new BorderLayout());
		add(ticTacToePanel, BorderLayout.CENTER);
		add(textLbl, BorderLayout.NORTH);
	}
}
