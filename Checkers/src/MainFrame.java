import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;

public class MainFrame extends JFrame{

	int numRows=6;
	int numcols=6;
	Spot[][] board = new Spot[numRows][numcols];
	CardLayout cards;
	GameBoardPanel gamePanel;
	ArrayList<Spot> selectedSpots=new ArrayList<Spot>();

	public MainFrame() {
		super("Checkers");
		cards=new CardLayout();
		setLayout(cards);

		initBoard();
		gamePanel=new GameBoardPanel(board);
		gamePanel.addSelectionListener(new SelectionListener() {
			@Override
			public void selectionMade(int row, int col) {
				Spot spot = board[row][col];
				spot.toggleSelected();
				if (spot.isSelected()){
					selectedSpots.add(spot);
				}
				else selectedSpots.remove(spot);
				System.out.println(selectedSpots.size());
				if (selectedSpots.size()==2){
					movePiece(selectedSpots.get(0),selectedSpots.get(1));
				}
				System.out.println(board[row][col].isSelected());
				gamePanel.redrawBoard(board);
			}
		});


		setMinimumSize(new Dimension(800,700));
		add(gamePanel);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void initBoard(){
		for (int row=0;row<numRows;row++){
			for (int col=0;col<numcols;col++){
				board[row][col]=new Spot(row,col);
				if (row<2){
					board[row][col].placePiece(new Piece(Piece.RED));
				}
				else if (row>=numRows-2){
					board[row][col].placePiece(new Piece(Piece.BLACK));
				}
			}
		}
	}

	private void movePiece(Spot start, Spot end){
		Piece piece=start.piece;
		if (piece==null) return;
		ArrayList<Spot> possibleMoves=piece.getPossibleMoves();
		//if (possibleMoves.contains(end))
	}

}
