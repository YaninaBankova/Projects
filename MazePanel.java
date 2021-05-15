package edu.smg;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.FileNotFoundException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MazePanel extends JPanel{
	boolean displayPath = false;
	int margin = 3;
	boolean pathFound = false;
	
	Maze m;
	public MazePanel() {
		try {
			m = new Maze("C:\\Users\\Yaniiiiiii\\11e\\PracticeTasks_15and16\\src\\edu\\smg\\mazes.txt");
			if (MazeSolver.solveMaze(m)) {
				pathFound = true;
			} 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void findPath() {
		displayPath = true;
		if(!pathFound) {
			JOptionPane.showMessageDialog(null, "java.io.PathNotFoundException", "Error", 0);
		}
		repaint();
	}
	
	public void clearPath() {
		displayPath = false;
		repaint();
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width = getWidth() / m.maze[0].length - margin;
		int height = getHeight() / m.maze.length - margin;
		Graphics2D g2d = (Graphics2D)g;
		g2d.setStroke(new BasicStroke(2));
		for(int i = 0; i < m.maze.length; i++) {
			for(int j = 0; j < m.maze[i].length; j++) {
				int x = margin + j * (width + margin);
				int y = margin + i * (height + margin);
				if(i == m.start.y && j == m.start.x) {
					g.setColor(Color.GREEN);
					g.fillRect(x, y, width, height);
				} else if(m.maze[i][j] == 1 || m.maze[i][j] == 3) {
					g.setColor(Color.WHITE);
					g.fillRect(x, y, width, height);
				} else if(m.maze[i][j] == 0) {
					g.setColor(Color.LIGHT_GRAY);
					g.fillRect(x, y, width, height);
					g2d.setColor(Color.BLACK);
					g2d.drawLine(x, y, x + width, y + height);
					g2d.drawLine(x + width, y, x, y + height);
				} else if(m.maze[i][j] == 2) {
					g.setColor(Color.RED);
					g.fillRect(x, y, width, height);
				}
			}
		}
		if(displayPath) {
			for(Position p: m.path) {
				g.setColor(Color.GREEN);
				g.fillRect(margin + p.x * (width + margin), margin + p.y * (height + margin), width, height);
			}
			
				
		}
		
	}
}
