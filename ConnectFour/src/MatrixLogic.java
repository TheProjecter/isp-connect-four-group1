
public class MatrixLogic implements IGameLogic {
    private int x = 0;
    private int y = 0;
    private int playerID;
    private Board board = new Board(0,0);
    
    public MatrixLogic() {
        //TODO Write your implementation for this method
    }
	
    public void initializeGame(int x, int y, int playerID) {
        this.x = x;
        this.y = y;
        this.playerID = playerID;
        this.board = new Board(x,y);
    }
	
    public Winner gameFinished() {
        //TODO Write your implementation for this method
    	for(int col=0;col<x;col++){
    		if(calcSum(board.getColumn(col))>=4){
    			return Winner.PLAYER1;
    		}
    		if(calcSum(board.getColumn(col))<=-4){
    			return Winner.PLAYER2;
    		}
    		
    	}
    	
    	for(int row=0;row<y;row++){
    		if(calcSum(board.getRow(row))>=4){
    			return Winner.PLAYER1;
    		}
    		if(calcSum(board.getRow(row))<=-4){
    			return Winner.PLAYER2;
    		}
    		
    	}
        return Winner.NOT_FINISHED;
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
			if(Math.abs(sum)>max) max = sum;
		}
		return max;
	}
    

    public void insertCoin(int column, int playerID) {
        //TODO Write your implementation for this method
    	if(playerID==2) playerID=-1;
    	board.add(column, playerID);
    }

    public int decideNextMove() {
        //TODO Write your implementation for this method
        return 0;
    }

}
