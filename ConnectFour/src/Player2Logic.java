
public class Player2Logic implements IGameLogic {
    private Board board = new Board(0,0);
    private MiniMaxABDepthFirst minmax = new MiniMaxABDepthFirst();
    private int playerID;
    
    public Player2Logic() {
        //TODO Write your implementation for this method
    }
	
    public void initializeGame(int x, int y, int playerID) {
        this.board = new Board(x,y);
        this.playerID = playerID;
    }
	
    public Winner gameFinished() {
    	
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
		System.out.println("Thinking...");
    	return minmax.ABsearch(this.board);
    }
}
