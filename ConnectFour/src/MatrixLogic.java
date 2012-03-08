
public class MatrixLogic implements IGameLogic {
    private int x = 0;
    private int y = 0;
    private int playerID;
    private Board board = new Board(0,0);
    private MatrixLogicMiniMaxAB minmax = new MatrixLogicMiniMaxAB();
    
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
    	if (minmax.Utility(this.board)==0){
    		return Winner.TIE;
    	}
    	if (minmax.Utility(this.board)==1){
    		return Winner.PLAYER1;
    	}
    	if (minmax.Utility(this.board)==-1){
    		return Winner.PLAYER2;
    	}
    	return Winner.NOT_FINISHED;
    }


    

    public void insertCoin(int column, int playerID) {
        //TODO Write your implementation for this method
    	if(playerID==2) playerID=-1;
    	board.add(column, playerID);
    }

    public int decideNextMove() {
        //TODO Write your implementation for this method

    	return minmax.ABsearch(this.board);

    }
    
    public int filltest(){
       	if(this.board.board[1].isFull())
    		return 1;
    	return 0;
    	
    }
    

}
