package edu.smg;

import javax.swing.JFrame;

public class MazeApp {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);
		frame.add(new MazeControll());
		frame.setTitle("Maze");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
