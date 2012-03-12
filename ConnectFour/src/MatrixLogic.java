public class MatrixLogic implements IGameLogic {
    private Board board = new Board(0,0);
    private MiniMaxAB minmax = new MiniMaxAB();
    private int playerID;
    
    public MatrixLogic() {
        //TODO Write your implementation for this method
    }
	
    public void initializeGame(int x, int y, int playerID) {
        this.board = new Board(x,y);
        this.playerID = playerID;
    }
	
    public Winner gameFinished() {
    	StateEvolved test = new StateEvolved(this.board);
    	
    	if (test.getUtility() == 0){
    		return Winner.TIE;
    	}
    	if (test.getUtility() == test.getPlayerWin()){
    		return Winner.PLAYER1;
    	}
    	if (test.getUtility()== -test.getPlayerWin()){
    		return Winner.PLAYER2;
    	}
    	return Winner.NOT_FINISHED;
    }
    
    public void insertCoin(int column, int playerID) {
        //TODO Write your implementation for this method
    	board.add(column, playerID);
    }

    public int decideNextMove() {
		System.out.println("Thinking...");
    	return minmax.ABsearch(this.board);
    }
}
