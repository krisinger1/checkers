import javax.swing.ImageIcon;

public class Spot {
	private int row;
	private int col;
	Piece piece;
	private boolean selected;

	public Spot(int row, int col){
		this.row=row;
		this.col=col;
		selected=false;
	}

	public int getRow(){
		return row;
	}

	public int getCol(){
		return col;
	}

	public void placePiece(Piece piece) {
		this.piece=piece;
		piece.setLocation(this);
	}

	public ImageIcon getImage(){
		if (piece!=null){
			return piece.getImage();
		}
		return new ImageIcon("");
	}

	public boolean isSelected(){
		return selected;
	}

	public void toggleSelected(){
		selected=!selected;
	}
}
