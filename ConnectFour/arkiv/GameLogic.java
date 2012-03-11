
public class GameLogic implements IGameLogic {
    private int x = 0;
    private int y = 0;
    private int playerID;
    
    
    //Consstructor
    public GameLogic() {
        //TODO Write your implementation for this method
    }
	
    public void initializeGame(int x, int y, int playerID) {
        this.x = x;
        this.y = y;
        this.playerID = playerID;
        //TODO Write your implementation for this method
    }
	
    public Winner gameFinished() {
        //TODO Write your implementation for this method
        return Winner.NOT_FINISHED;
    }


    public void insertCoin(int column, int playerID) {
        //TODO Write your implementation for this method
    	System.out.println(column);
    	System.out.println(playerID);
    }

    public int decideNextMove() {
        //TODO Write your implementation for this method
    	System.out.println("Next move decision");
    	return (int) (Math.random()*x);
    }

}
