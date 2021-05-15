package edu.smg;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class TicTacToePanel extends JPanel{
	
	public int board[][] = new int[3][3];
	int margin = 5;
	boolean osTurn = false;
	int mouseX;
	int mouseY;
	int clickedRow = -1;
	int clickedColumn = -1;
	boolean validClick = false;
	boolean gameEnded = false;
	int turns = 0;
	
	public TicTacToePanel() {
		this.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
				inBounds(mouseX, mouseY, getWidth()/3 - margin, getHeight()/3 - margin);
				if(validClick) {
					if(osTurn) {
						board[clickedRow][clickedColumn] = 2;
						TicTacToeControll.textLbl.setText("Player x's turn");
						osTurn = false;
					} else {
						board[clickedRow][clickedColumn] = 1;
						TicTacToeControll.textLbl.setText("Player o's turn");
						osTurn = true;
					}
					turns++;
					repaint();
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void inBounds(int mouseX, int mouseY, int width, int height) {
		
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				int x = margin + j * (width + margin);
				int y = margin + i * (height + margin);
				if((mouseX >= x) && (mouseY >= y) && (mouseX < x + width) && (mouseY < y + height) 
						&& board[i][j] == 0 && !gameEnded) {
					clickedRow = i;
					clickedColumn = j;
					validClick = true;
					return;
				}
					
			}
		}
		validClick = false;
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		int width = getWidth() / 3 - margin;
		int height = getHeight() / 3 - margin;
		g.setColor(Color.WHITE);
		g2d.setStroke(new BasicStroke(2));
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				int x = margin + j * (width + margin);
				int y = margin + i * (height + margin);
				g.fillRect(x, y, width, height);
				
				g.setColor(Color.GRAY);
				if(board[i][j] == 1) {
					g.drawLine(x, y, x + width, y + height);
					g.drawLine(x + width, y, x, y + height);
				} else if(board[i][j] == 2){
					g.drawOval(x, y, width, height);
				}
				
				g.setColor(Color.WHITE);
			}
		}
		
		g2d.setStroke(new BasicStroke(5));
		gameEnds(g2d, width, height);
	}
	
	public void gameEnds(Graphics g, int width, int height) {
		for(int i = 0; i < 3; i++) {
			if(board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != 0) {
				gameEnded = true;
				g.setColor(Color.GREEN);
				g.drawLine(0, margin + i * (height + margin) + height / 2, 
						getWidth(), margin + i * (height + margin) + height / 2);
				if(board[i][0] == 1) {
					TicTacToeControll.textLbl.setText("x wins");
				} else {
					TicTacToeControll.textLbl.setText("O wins");
				}
				return;
			}
		}
		
		for(int i = 0; i < 3; i++) {
			if(board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != 0) {
				gameEnded = true;
				g.setColor(Color.GREEN);
				g.drawLine(margin + i * (width + margin) + width / 2, 0, 
						margin + i * (width + margin) + width / 2, getHeight());
				if(board[0][i] == 1) {
					TicTacToeControll.textLbl.setText("x wins");
				} else {
					TicTacToeControll.textLbl.setText("O wins");
				}
				return;
			}
		}
		
		if(board[0][0] == board[1][1] && board[2][2] == board[1][1] && board[0][0] != 0) {
			gameEnded = true;
			g.setColor(Color.GREEN);
			g.drawLine(0, 0, getWidth(), getHeight());
			if(board[0][0] == 1) {
				TicTacToeControll.textLbl.setText("x wins");
			} else {
				TicTacToeControll.textLbl.setText("O wins");
			}
			return;
		}
		
		if(board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != 0) {
			gameEnded = true;
			g.setColor(Color.GREEN);
			g.drawLine(0, getHeight(), getWidth(), 0);
			if(board[0][2] == 1) {
				TicTacToeControll.textLbl.setText("x wins");
			} else {
				TicTacToeControll.textLbl.setText("o wins");
			}
			return;
		}
		
		if(turns == 9) {
			TicTacToeControll.textLbl.setText("DRAW");
			gameEnded = true;
		}
	}
	
}
