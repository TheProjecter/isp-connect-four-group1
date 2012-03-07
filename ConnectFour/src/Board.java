
public class Board implements Cloneable{
	
	public Column[] board = new Column[0];
	private int rows = 0;
	private int columns = 0;
	
	public Board(int x, int y){
		this.board = new Column[x];
		this.rows = y;
		this.columns =x;
		for (int i=0;i<y;i++){
			this.board[i]=new Column(y);
		}
	}
	
	@Override
	protected Object clone(){
		Board clone = new Board(this.columns,this.rows);
		clone.board = this.board;
		
		return clone;
	}
	
	public String toString(){
		String n="";
		for (int i=0;i<this.rowCount();i++){
			
		for (int c=0;c<this.colCount();c++){
			n+=this.board[c].col[i];
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
		for (int i=0;i<this.colCount();i++){
			int count = 0;
			if(!this.board[i].isFull())
				openCols[count]=i;
				count+=1;
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
		int[] row = new int[board.length];
		for (int i =0;i<board.length;i++){
			row[i] = board[i].col[x];
		}
		return row;
	}
	
	int rowCount(){
		return this.rows;
	}
	
	int colCount(){
		return this.columns;
	}
	
	
	
	public class Column{
		int[] col = new int[0];
		int N=0;
		
		public Column(int y){
			this.col = new int[y];
			this.N = 0;
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
				
	}

}
