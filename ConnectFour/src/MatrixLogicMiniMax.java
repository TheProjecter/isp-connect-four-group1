

public class MatrixLogicMiniMax {
	int counter =0;
	int cutoff=100000;
	int winCondition=2;
	
	public MatrixLogicMiniMax(){
	}
	int MinimaxDecision(Board board){
		int v=Integer.MIN_VALUE;
		int choice=0;
		for (int a: Actions(board)){
			int b=Max(v,MinValue((Result(board,a,1))));
			if(b>v) {
				v=b;
				choice=a;
				}
		}
		System.out.println("After "+counter+" evaluations.");
		counter=0;
		System.out.println("I have chosen to play: "+choice);
		System.out.println("For an expected outcome of: "+v);
		return choice;
	}
	
	
	int MaxValue(Board board){
		counter++;
		if(TerminalTest(board)) {
			return Utility(board);
		}
		int v = Integer.MIN_VALUE;
		for (int a:Actions(board)){
			v=Max(v,MinValue((Result(board,a,1))));
			System.out.println("Attempting this:"+Result(board,a,1));
			if(counter>cutoff){break;}
		}
		return v;
	}
	int MinValue(Board board){
		counter++;
		if(TerminalTest(board)){		
			return Utility(board);
		}
		int v = Integer.MAX_VALUE;
		for (int a:Actions(board)){
			v=Min(v,MaxValue((Result(board,a,-1))));
			System.out.println("Attempting this:\n"+Result(board,a,1));
			if(counter>cutoff){break;}
		}
		return v;
	}
	
	private int calcSum(int[] N){
		int sum=0;
		int max=0;
		for (int i=0;i<N.length;i++){
			if(sum>=0 && N[i]>0)
				sum+=1;
			else if(sum<0 && N[i]>0)
				sum=1;
			else if(sum>=0 && N[i]<0)
				sum=-1;
			else if(sum<0 && N[i]<0)
				sum-=1;
			else if(N[i]==0){
				sum=0;
			}
			if(Math.abs(sum)>max) max = sum;
		}
		return max;
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
	Board Result(Board org,int a,int playerID){
		Board board = (Board) org.clone();
		board.add(a, playerID);
		return board;
	}
	
	
	//Actions returns the number of the playable cols
	int[] Actions(Board board){
		return board.openColums();
	}
	
	boolean TerminalTest(Board board){
		return Utility(board)!=99;
	}
	
	int Utility(Board board){
    	for(int col=0;col<board.colCount();col++){
    		if(calcSum(board.getColumn(col))>=winCondition){
    			return 1;
    		}
    		if(calcSum(board.getColumn(col))<=-winCondition){
    			return -1;
    		}
    		
    	}
    	
    	for(int row=0;row<board.rowCount();row++){
    		if(calcSum(board.getRow(row))>=winCondition){
    			return 1;
    		}
    		if(calcSum(board.getRow(row))<=-winCondition){
    			return -1;
    		}
    	
        for(int leftDiagonal=0; leftDiagonal < board.diagonalCount();leftDiagonal++){
        	if(calcSum(board.getLeftDiagonal(leftDiagonal))>=winCondition){
        		return 1;
        	}
        	if(calcSum(board.getLeftDiagonal(leftDiagonal))<=-winCondition){
        		return -1;
        	}
        		
        }
        
        for(int rightDiagonal=0; rightDiagonal < board.diagonalCount();rightDiagonal++){
        	if(calcSum(board.getRightDiagonal(rightDiagonal))>=winCondition){
        		return 1;
        	}
        	if(calcSum(board.getRightDiagonal(rightDiagonal))<=-winCondition){
        		return -1;
        	}
        		
       	}
    		
    	}
    	if(board.isFull()) return 0;
		return 99;
	}


}
