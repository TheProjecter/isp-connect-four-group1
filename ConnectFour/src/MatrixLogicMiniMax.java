

public class MatrixLogicMiniMax {
	
	public MatrixLogicMiniMax(){
	}
	int MinimaxDecision(Board board){
		int max=0;
		for (int a: Actions(board)){
			if(MinValue(Result(board,a,-1))>max){
				max=a;
			}
		}
		return max;
	}
	
	
	int MaxValue(Board board){
		if(TerminalTest(board)) return Utility(board);
		int v = Integer.MIN_VALUE;
		for (int a:Actions(board)){
			v=Max(v,MinValue((Result(board,a,-1))));
		}
		return v;
	}
	int MinValue(Board board){
		if(TerminalTest(board)) return Utility(board);
		int v = Integer.MAX_VALUE;
		for (int a:Actions(board)){
			v=Min(v,MaxValue((Result(board,a,1))));
		}
		return v;
	}
	
	int Max(int a, int b){
		if(a<b){
			return b;
		}
		return a;
	}
	int Min(int a, int b){
		if(a>b){
			return b;
		}
		return a;
	}
	
	//The resulting board of playerID adding to a column a in a board s
	Board Result(Board s,int a,int playerID){
		Board s1=(Board) s.clone();
		s1.add(a, playerID);
		return s1;
	}
	
	
	//Actions returns the number of the playable cols
	int[] Actions(Board board){
		return board.openColums();
	}
	
	boolean TerminalTest(Board board){
		if(board.isFull()) return true;
	  	for(int col=0;col<board.colCount();col++){
    		if(Math.abs(MatrixLogic.calcSum(board.getColumn(col)))>=4)
    			return true;}    	
    	for(int row=0;row<board.rowCount();row++){
    		if(Math.abs(MatrixLogic.calcSum(board.getRow(row)))>=4)
    			return true;}    		
        return false;
	}
	
	int Utility(Board board){
		
    	for(int col=0;col<board.colCount();col++){
    		if(MatrixLogic.calcSum(board.getColumn(col))>=4){
    			return 1;
    		}
    		if(MatrixLogic.calcSum(board.getColumn(col))<=-4){
    			return -1;
    		}
    		
    	}
    	
    	for(int row=0;row<board.rowCount();row++){
    		if(MatrixLogic.calcSum(board.getRow(row))>=4){
    			return 1;
    		}
    		if(MatrixLogic.calcSum(board.getRow(row))<=-4){
    			return -1;
    		}
    		
    	}
    	if(board.isFull()) return 0;
		return 99;
	}


}
