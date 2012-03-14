public class WeakPowerLogic implements IGameLogic {
    private Board board = new Board(0,0);
    private int playerID;
    private int opponentID;
    
    public WeakPowerLogic() {
        //TODO Write your implementation for this method
    }
	
    public void initializeGame(int x, int y, int playerID) {
        this.board = new Board(x,y);
        if(playerID==1){
            this.playerID = 1;
            this.opponentID = 2;        	
        }
        else if(playerID==2){
            this.playerID = 2;
            this.opponentID = 1;        	        	
        }
        System.out.println("I am player "+playerID);
        System.out.println("My opponent is player "+opponentID);
    }
	
    public Winner gameFinished() {
    	StateEvolved test = new StateEvolved(this.board);

    	if (test.getUtility() == test.getPlayerWin()){
    		if(playerID==1) return Winner.PLAYER1;
    		else if(playerID==2) return Winner.PLAYER2;
    	}
    	if (test.getUtility() == -test.getPlayerWin()){
    		if(playerID==1) return Winner.PLAYER2;
    		else if(playerID==2) return Winner.PLAYER1;
    	}
    	if (board.isFull()){
    	    System.out.println(board.isFull());
    	    System.out.println("There is a tie.");
    		return Winner.TIE;
    	}
    	return Winner.NOT_FINISHED;
    }
    
    public void insertCoin(int column, int playerID) {
        //TODO Write your implementation for this method
    	if (playerID == this.playerID) board.add(column, 1);
    	else if(playerID==opponentID) board.add(column, 2);
    }

    public int decideNextMove() {
		System.out.println("Weak mind thinking...");
		MMABWeakDepthLtm minmax = new MMABWeakDepthLtm();
	    int a = minmax.ABsearch(this.board);
	    System.out.println("Done thinking.");
    	return a;
    }
}
