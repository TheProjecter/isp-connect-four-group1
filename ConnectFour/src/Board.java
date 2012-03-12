import java.util.Arrays;


public class Board implements Cloneable{
	
	public Column[] board = new Column[0];
	private int rows = 0;
	private int columns = 0;
	private int winCondition = 4;
	
	
	/**
	 * Constructs the board.
	 * @param column
	 * @param row
	 */
	public Board(int column, int row){
		this.board = new Column[column];
		this.rows = row;
		this.columns = column;
		for (int i=0;i<column;i++){
			this.board[i]=new Column(row);
		}
	}
	
	/**
	 * The clone implementation for a board.
	 */
	@Override
	protected Object clone(){
		Board clone = new Board(this.columns,this.rows);
		clone.board = new Column[this.colCount()];
		for(int i=0;i<this.colCount();i++){
				clone.board[i] = (Column) this.board[i].clone();
		}
		
		return clone;
	}
	
    /**
    *
    * @param object
    * @return Returns true if the object passed to the method is equal to
    * to the caller object.
    */
   @Override
    public boolean equals(Object object) {
       if (object == null) return(false); //Null reference pointer, return false
       if (object == this) return(true); //Same object, return true
       if (!(object instanceof Board)) return(false); //Not same class, return false
       
       Board B = (Board) object; //Cast to Board
       
       
       if (this.columns != B.columns) return false;
       if (this.rows != B.rows) return false;
       
       for (int x = 0; x< this.columns; x++) {
    	   if (this.board[x].N != B.board[x].N) return false;
    	   for (int y = 0;  y < this.rows; y++) {
    		   if (this.board[x].col[y]==null){
    			   if(this.board[x].col[y] == this.board[x].col[y]) continue;
    			   else return false;
    		   }
    		   if (!(this.board[x].col[y].equals(B.board[x].col[y]))) return false;
    	   }
       }

       return true;
    }
   
   @Override
   public int hashCode() {
	   boolean[] boolArray = new boolean[this.columns*this.rows*2];
	   int counter = -2;
	   
	   for (int x = 0; x< this.columns; x++) {
		   
    	   for (int y = 0;  y < this.rows; y++) {
    		   counter+= 2;
    		   
    		   
    		   // [false, false] = no coin
    		   if (this.board[x].col[y] == null) continue;
    		   
    		   int playerID;
    		   
    		   // [true,true] = player 1 coin
    		   if (this.board[x].col[y].isPlayerOne()) {
    			   boolArray[counter] = true;
    			   boolArray[counter+1] = true;
    		   }
    		   
    		   // [true, false] = player 2 coin
    		   else if (this.board[x].col[y].isPlayerTwo()) {
    			   boolArray[counter] = true;
    			   boolArray[counter+1] = false;
    		   }
    		   
    	   }
       }
	   
	return Arrays.hashCode(boolArray);
	   
   }
	
	/**
	 * Converts the board to a string. Inverting the direction of the columns.
	 */
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
	
	
	/**
	 * Returns an array of integers representing the open columns in the board. 
	 * @return
	 */
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
	
	/**
	 * Checks whether every column in the board is at max. capacity.
	 * @return
	 */
	boolean isFull(){
		return this.openColums().length==0;
	}
	
	/**
	 * Adds a coin belonging to PlayerID to a column in the the board.
	 * @param col
	 * @param PlayerID
	 */
	public void add(int col,int PlayerID){
		this.board[col].add(PlayerID);
	}
	
	/**
	 * Returns an array of Coins representing column x.
	 * @param x
	 * @return
	 */
	public Coin[] getColumn(int x){
		return this.board[x].col;
	}
	
	/**
	 * Returns an array of Coins representing row x.
	 * @param x
	 * @return
	 */
	public Coin[] getRow(int x){
		Coin[] row = new Coin[this.colCount()];
		for (int i =0;i<board.length;i++){
			row[i] = board[i].col[x];
		}
		return row;
	}

	
	/**
	 * Returns a Coin array representing the x'th left diagonal.
	 * Starting from the lowest left corner and ending in the top right corner.
	 * @param x
	 * @return
	 */
	public Coin[] getLeftDiagonal(int x){
		int diagonalSize;
		
		if (rows<columns)
			diagonalSize = rows;
		else
			diagonalSize = columns;
		

				
		Coin[] diagonal = new Coin[diagonalSize];
		
		for(int colIt = 0 ; colIt < diagonalSize; colIt++){
			
			int coinToGet = colIt+rows-winCondition-x;
			
			if (!((coinToGet >= rows) || (coinToGet < 0))){
				Column currentColumn = this.board[colIt];
				diagonal[colIt] = currentColumn.get(coinToGet);
			}
		}
		return diagonal;
	}		

	/**
	 * Returns a Coin array representing the x'th right diagonal
	 * Starting from the top left corner and ending in the bottom right corner.
	 * @param x
	 * @return
	 */
	public Coin[] getRightDiagonal(int x){
		int diagonalSize;
		
		if (rows<columns)
			diagonalSize = rows;
		else
			diagonalSize = columns;
		
		
		Coin[] diagonal = new Coin[diagonalSize];

		for(int colIt = 0 ; colIt < diagonalSize; colIt++){
			int coinToGet = x+(winCondition-1)-colIt;
			if (!((coinToGet >= rows) || (coinToGet < 0))){
				Column currentColumn = this.board[colIt];
				diagonal[colIt] = currentColumn.get(coinToGet);
			}
		}
		return diagonal;
	}

	/**
	 * Returns an integer representing the number of diagonals in the board.
	 * @return
	 */
 	public int diagonalCount(){
		return rows+columns-2*(winCondition-1)+1;
	}
 	
	/**
	 * Returns an integer representing the number of rows in the board.
	 * @return
	 */
	int rowCount(){
		return this.rows;
	}
	
	/**
	 * Returns an integer representing the number of columns in the board.
	 * @return
	 */
	int colCount(){
		return this.columns;
	}
	
	public int getWinCondition(){
		return winCondition;
	}
	}
