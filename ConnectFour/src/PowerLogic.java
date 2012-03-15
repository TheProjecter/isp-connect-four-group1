/**
 * The PowerLogic class is an implementation of the IGameLogic interface 
 * that uses depth-limited search and alpha-beta pruning.
 */
public class PowerLogic implements IGameLogic {
    private Board board = new Board(0,0); //Data structure to save current state
    private int newPlayerID;
    private int playerID;
    private int opponentID;
    
    
    /**
     * The method initializes initial state and sets internal variables.
     */
    public void initializeGame(int x, int y, int playerID) {
    	this.playerID = playerID;
        this.board = new Board(x,y);
        if(playerID==1){
            this.newPlayerID = 1;
            this.opponentID = 2;        	
        }
        else if(playerID==2){
            this.newPlayerID = 2;
            this.opponentID = 1;        	        	
        }
        System.out.println("I am player "+playerID);
        System.out.println("My opponent is player "+opponentID);
    }
	
    /**
     * Returns winner from Winner enum. Either PLAYER1, PLAYER2, TIE or NOT_FINISHED
     */
    public Winner gameFinished() {
    	State test = new State(this.board);

    	if (test.getUtility() == test.getPlayerWin()){
    		if(newPlayerID==1) return Winner.PLAYER1;
    		else if(newPlayerID==2) return Winner.PLAYER2;
    	}
    	if (test.getUtility() == -test.getPlayerWin()){
    		if(newPlayerID==1) return Winner.PLAYER2;
    		else if(newPlayerID==2) return Winner.PLAYER1;
    	}
    	if (board.isFull()){
    	    System.out.println(board.isFull());
    	    System.out.println("There is a tie.");
    		return Winner.TIE;
    	}
    	return Winner.NOT_FINISHED;
    }
    
    
    /**
     * Insert a coin in the column specified for the player specified. 
     */
    public void insertCoin(int column, int playerID) {
        System.out.println("I am player "+this.playerID);

    	if (playerID == this.newPlayerID) {
    		System.out.println("I insert a coin in column #"+column+" for me.");
    		board.add(column, 1);
    	}
    	else if(playerID==opponentID) {
    		board.add(column, 2);
    		System.out.println("I insert a coin in column #"+column+" for my opponent.");
    	}
    }

    /**
     * The method creates a new MiniMaxAB object and performs a search based on the current state of the board.
     * It returns an int signifying the column that should be added a coin to.
     */
    public int decideNextMove() {
		System.out.println(board);
		System.out.println("Strong mind thinking...");
	    MiniMaxAB minmax = new MiniMaxAB();
	    int a = minmax.ABsearch(this.board);
	    System.out.println("Done thinking.");
    	return a;
    }
}
