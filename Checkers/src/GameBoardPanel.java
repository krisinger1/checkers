import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class GameBoardPanel extends JPanel {
	JLabel[][] boardLabels;
	int numRows;
	int numCols;
	Point point;
	int numSelectedSquares=0;
	JLabel startSquare;
	JLabel endSquare;
	SelectionListener listener;

	public GameBoardPanel(Spot[][] board) {
		numRows=board.length;
		numCols=board[0].length;
		boardLabels = new JLabel[numRows][numCols];
		setLayout(null);
		drawBoard(board);
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				point = e.getPoint();
				Component component = getComponentAt(point);
				for (int row=0; row<numRows;row++){
					for (int col=0; col<numCols; col++){
						if (component.equals(boardLabels[row][col])){
							//selectSquare(row,col);
							if (listener!=null) listener.selectionMade(row, col);
						}
					}
				}
			}
		});
	}

	public void drawBoard(Spot[][] board){
		for (int row=0; row<numRows;row++){
			for (int col=0; col<numCols; col++){
				JLabel square=new JLabel();
				square.setOpaque(true);
				square.setBounds(col*75+100,row*75+100,75,75);
				square.setBackground((row+col)%2==0?Color.red:Color.blue);
				square.setIcon(board[row][col].getImage());
				boardLabels[row][col]=square;
				add(square);
			}
		}
	}

	public void redrawBoard(Spot[][] board){
		for (int row=0; row<numRows;row++){
			for (int col=0; col<numCols; col++){
				JLabel square=boardLabels[row][col];
				if (board[row][col].isSelected()){
					square.setBackground((row+col)%2==0?new Color(255,255,200):new Color(200,255,200));
				}
				else {
					square.setBackground((row+col)%2==0?Color.red:Color.blue);
				}
				//square.setOpaque(true);
				///square.setBounds(col*100,row*100,100,100);
				//square.setBackground((row+col)%2==0?Color.red:Color.blue);
				square.setIcon(board[row][col].getImage());
				//square.setBorder(board[row][col].isSelected()?BorderFactory.createLineBorder(Color.green,4):null);
				
			}
		}
	}

	private void selectSquare(int row, int col){
		Border greenBorder = BorderFactory.createLineBorder(Color.green,4);
		Border magentaBorder = BorderFactory.createLineBorder(Color.magenta,4);
		if (numSelectedSquares==0){
			boardLabels[row][col].setBorder(greenBorder);
			startSquare = boardLabels[row][col];
			numSelectedSquares++;
		}
		else if (numSelectedSquares==1){
			if (boardLabels[row][col].getBorder()==null){
				boardLabels[row][col].setBorder(magentaBorder);
				endSquare = boardLabels[row][col];
				numSelectedSquares++;
			}
			else {
				boardLabels[row][col].setBorder(null);
				startSquare=null;
				numSelectedSquares--;
			}
		}
		else if (numSelectedSquares==2){
			endSquare.setBorder(null);
			endSquare=null;
			numSelectedSquares--;
			if (boardLabels[row][col].getBorder()==null){
				boardLabels[row][col].setBorder(magentaBorder);
				endSquare = boardLabels[row][col];
				numSelectedSquares++;
			}
		}

	}

	public void addSelectionListener(SelectionListener listener){
		this.listener=listener;
	}
}


