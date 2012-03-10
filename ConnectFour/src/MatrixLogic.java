
public class MatrixLogic implements IGameLogic {
    private int playerID;
    private Board board = new Board(0,0);
    private MatrixLogicMiniMaxAB minmax = new MatrixLogicMiniMaxAB();
    
    public MatrixLogic() {
        //TODO Write your implementation for this method
    }
	
    public void initializeGame(int x, int y, int playerID) {
        this.playerID = playerID;
        this.board = new Board(x,y);
    }
	
    public Winner gameFinished() {
    	State test = new State(this.board);
    	
    	if (test.getUtility() == 0){
    		return Winner.TIE;
    	}
    	if (test.getUtility() == 1){
    		return Winner.PLAYER1;
    	}
    	if (test.getUtility()== -1){
    		return Winner.PLAYER2;
    	}
    	return Winner.NOT_FINISHED;
    }
    
    public void insertCoin(int column, int playerID) {
        //TODO Write your implementation for this method
    	board.add(column, playerID);
    }

    public int decideNextMove() {
    	return minmax.ABsearch(this.board);
    }
}
