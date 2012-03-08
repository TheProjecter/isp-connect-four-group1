
public class stateEval {
	static int winCondition=4;

	private static int calcSum(int[] N){
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
	
	public static int Max(int a, int b){
		if(a<b){
			return b;
		}
		return a;
	}
	public static int Min(int a, int b){
		if(a>b){
			return b;
		}
		return a;
	}
	
	//The resulting board of playerID adding to a column a in a board s
	public static Board Result(Board org,int a,int playerID){
		Board board = (Board) org.clone();
		board.add(a, playerID);
		return board;
	}
	
	
	//Actions returns the number of the playable cols
	public static int[] Actions(Board board){
		return board.openColums();
	}
	
	public static boolean TerminalTest(Board board){
		return Utility(board)!=99;
	}
	
	public static int Utility(Board board){
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
    		
    	if(board.isFull()) return 0;
		return 99;
	}

}
