import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

public class Piece {
	private ImageIcon image;
	private boolean selected;
	private int row;
	private int col;
	private Spot spot;
	static final int RED = 1;
	static final int BLACK = 0;
	private int color;

	public Piece(int color){
		this.color=color;
		if (color==RED){
			image=new ImageIcon("images/checker-red.png");
		}
		else {
			image=new ImageIcon("images/checker-black.png");
		}
		selected=false;
	}

	public void setLocation(Spot spot){
		row=spot.getRow();
		col=spot.getCol();
		this.spot=spot;

	}

	public int getRow(){
		return row;
	}

	public int getCol(){
		return col;
	}

	public void move(int toRow, int toCol){

	}

//	public boolean isLegalMove(int toRow, int toCol){
//
//	}

	public ArrayList<Spot> getPossibleMoves(){
		ArrayList<Spot> moves= new ArrayList<Spot>();
		if(color==RED){
			moves.add(new Spot(row+1, col+1));
			moves.add(new Spot(row+1, col-1));
			moves.add(new Spot(row+2, col+2));
			moves.add(new Spot(row+2, col-2));

		}
		else {
			moves.add(new Spot(row-1, col+1));
			moves.add(new Spot(row-1, col-1));
			moves.add(new Spot(row-2, col+2));
			moves.add(new Spot(row-2, col-2));
		}
		return moves;
	}

	public ImageIcon getImage(){
		return image;
	}

	public void setSelected(boolean selected){
		this.selected=selected;
	}

	public boolean isSelected(){
		return selected;
	}

//	public void toggleSelected(){
//		selected = !selected;
//	}
}
