
public class Board implements Cloneable{
	
	public Column[] board = new Column[0];
	private int rows = 0;
	private int columns = 0;
	
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
	
	public int[] getColumn(int x){
		return this.board[x].col;
	}

	public int[] getRow(int x){
		int[] row = new int[this.colCount()];
		for (int i =0;i<board.length;i++){
			row[i] = board[i].col[x];
		}
		return row;
	}
	
	public int[][] getDiagonals(String leftOrRight){
		int diagonalSize;
		
		if (rows>columns)
			diagonalSize = rows;
		else
			diagonalSize = columns;
		
		int diagonalCount = rows+columns-7; 
		
		int[][] diagonals = new int[diagonalCount][diagonalSize];
		if (leftOrRight == "left"){
			int start = columns-4;
			int stop = 4-rows;
			for(int diaIt = start ; diaIt <= stop ; diaIt--){
				for(int colIt = 0 ; colIt < columns; colIt++){
					int coinToGet = diaIt+colIt;
					if (!((coinToGet > rows) || (coinToGet < 0))){
						Column currentColumn = this.board[colIt];
						diagonals[diaIt][colIt] = currentColumn.get(coinToGet);
					}
				}
			}
		}
		
		else if (leftOrRight == "right"){
			int start = 3;
			int stop = rows+columns-5;
			for(int diaIt = start; diaIt <= stop ; diaIt++){
				for(int colIt = 0 ; colIt < columns; colIt++){
					int coinToGet = diaIt+colIt;
					if (!((coinToGet > rows) || (coinToGet < 0))){
						Column currentColumn = this.board[colIt];
						diagonals[diaIt][colIt] = currentColumn.get(coinToGet);
					}
				}
			}
		}
		return diagonals;
	}
		

	public int[] getLeftDiagonal(int diaNumber){
		int diagonalSize;
		
		if (rows<columns)
			diagonalSize = rows;
		else
			diagonalSize = columns;
		

				
		int[] diagonal = new int[diagonalSize];
		
		for(int colIt = 0 ; colIt < diagonalSize; colIt++){
			
			int coinToGet = colIt+rows-4-diaNumber;
			
			if (!((coinToGet >= rows) || (coinToGet < 0))){
				Column currentColumn = this.board[colIt];
				diagonal[colIt] = currentColumn.get(coinToGet);
			}
		}
		return diagonal;
	}		

	public int[] getRightDiagonal(int diaNumber){
		int diagonalSize;
		
		if (rows<columns)
			diagonalSize = rows;
		else
			diagonalSize = columns;
		
		
		int[] diagonal = new int[diagonalSize];

		for(int colIt = 0 ; colIt < diagonalSize; colIt++){
			int coinToGet = diaNumber+3-colIt;
			if (!((coinToGet >= rows) || (coinToGet < 0))){
				Column currentColumn = this.board[colIt];
				diagonal[colIt] = currentColumn.get(coinToGet);
			}
		}
		return diagonal;
	}

 	public int diagonalCount(){
		return rows+columns-7;
	}
	
	int rowCount(){
		return this.rows;
	}
	
	int colCount(){
		return this.columns;
	}
	
	
	
	public class Column implements Cloneable{
		int[] col = new int[0];
		int N=0;
		
				
		public Column(int y){
			this.col = new int[y];
			this.N = 0;
		}
		
		@Override
		protected Object clone(){
			Column clone = new Column(this.col.length); 
			for (int i=0;i<this.col.length;i++){
				clone.col[i] =this.col[i];
					if(this.col[i]!=0)clone.N+=1;
			}
			
			return clone;
		}
		
		public String toString(){
			String n="";
			for (int i:col){
				n+=i+"\n";
			}
			return n;
		}
		
		boolean isFull(){
			return N==col.length;
		}
		
		void add(int PlayerID){
			if(!this.isFull()){
				col[N]=PlayerID;
				N++;				
			}
			
		}
		
		int get(int place){
			return col[place];
		}
				
	}

}
