
public class Board implements Cloneable{
	
	public Column[] board = new Column[0];
	private int rows = 0;
	private int columns = 0;
	private int winCondition = 4;
	
	public Board(int column, int row){
		this.board = new Column[column];
		this.rows = row;
		this.columns = column;
		for (int i=0;i<column;i++){
			this.board[i]=new Column(row);
		}
	}
	
	@Override
	protected Object clone(){
		Board clone = new Board(this.columns,this.rows);
		clone.board = new Column[this.colCount()];
		for(int i=0;i<this.colCount();i++){
				clone.board[i] = (Column) this.board[i].clone();
		}
		
		return clone;
	}
	
	public String toString(){
		String n="";
		for (int i=0;i<this.rowCount();i++){
			
		for (int c=0;c<this.colCount();c++){
			n+=this.board[c].col[this.rowCount()-i-1]+"\t";
		}
		n+="\n";
		}
		return n;
	}
	
	public int[] openColums(){
		
		//First an iteration to determine the number of open cols.
		int open = 0;		
		for (int i=0;i<this.colCount();i++){
			if(!this.board[i].isFull())
				open+=1;
		}
		
		//Then an iteration to add each open col-number to an array
		int[] openCols = new int[open];
		int count = 0;
		for (int i=0;i<this.colCount();i++){	
			if(!this.board[i].isFull()){
				openCols[count]=i;
				count++;}
		}
		return openCols;
	}
	
	boolean isFull(){
		return this.openColums().length==0;
	}
		
	public void add(int col,int PlayerID){
		this.board[col].add(PlayerID);
	}
	
	public Coin[] getColumn(int x){
		return this.board[x].col;
	}

	public Coin[] getRow(int x){
		Coin[] row = new Coin[this.colCount()];
		for (int i =0;i<board.length;i++){
			row[i] = board[i].col[x];
		}
		return row;
	}

	public Coin[] getLeftDiagonal(int diaNumber){
		int diagonalSize;
		
		if (rows<columns)
			diagonalSize = rows;
		else
			diagonalSize = columns;
		

				
		Coin[] diagonal = new Coin[diagonalSize];
		
		for(int colIt = 0 ; colIt < diagonalSize; colIt++){
			
			int coinToGet = colIt+rows-winCondition-diaNumber;
			
			if (!((coinToGet >= rows) || (coinToGet < 0))){
				Column currentColumn = this.board[colIt];
				diagonal[colIt] = currentColumn.get(coinToGet);
			}
		}
		return diagonal;
	}		

	public Coin[] getRightDiagonal(int diaNumber){
		int diagonalSize;
		
		if (rows<columns)
			diagonalSize = rows;
		else
			diagonalSize = columns;
		
		
		Coin[] diagonal = new Coin[diagonalSize];

		for(int colIt = 0 ; colIt < diagonalSize; colIt++){
			int coinToGet = diaNumber+(winCondition-1)-colIt;
			if (!((coinToGet >= rows) || (coinToGet < 0))){
				Column currentColumn = this.board[colIt];
				diagonal[colIt] = currentColumn.get(coinToGet);
			}
		}
		return diagonal;
	}

	public Coin[] getCertain(int x, String Certain){
//		switch (Certain){
//        case "row":
//        return getRow(x);
//        case "column":
//        return getColumn(x);
//        case "rightDiagonal":
//        return getRightDiagonal(x);
//        case "leftDiagonal":
//        return getLeftDiagonal(x);
//		}
		return null;
	}

	int certainCount(String Certain){
//		switch (Certain){
//        case "row":
//        return rowCount();
//        case "column":
//        return colCount();
//        case "rightDiagonal":
//        return diagonalCount();
//        case "leftDiagonal":
//        return diagonalCount();
//		}
		return -1;
	}	
	
 	public int diagonalCount(){
		return rows+columns-2*(winCondition-1)+1;
	}
	
	int rowCount(){
		return this.rows;
	}
	
	int colCount(){
		return this.columns;
	}
	
	}
