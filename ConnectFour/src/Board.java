
public class Board {
	
	Column[] board = new Column[0];
	
	public Board(int x, int y){
		this.board = new Column[x];
		for (int i=0;i<x;i++){
			this.board[i]=new Column(y);
		}
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
	
	
	
	
	private class Column{
		int[] col = new int[0];
		int N=0;
		
		public Column(int y){
			this.col = new int[y];
			this.N = 0;
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
