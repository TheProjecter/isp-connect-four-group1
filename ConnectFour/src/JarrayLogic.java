
public class JarrayLogic implements IGameLogic {
    private int x = 0;
    private int y = 0;
    private int playerID;
    private jArray[] board = new jArray[0];
    
    
    
    
    //Consstructor
    public JarrayLogic() {
        //TODO Write your implementation for this method
    }
	
    public void initializeGame(int x, int y, int playerID) {
        this.x = x;
        this.y = y;
        this.playerID = playerID;
        //TODO Write your implementation for this method
        
        //Make jArrays:
        board = new jArray[x];
        for (int i=0;i<board.length;i++){
        	board[i] = new jArray(y);
        }
    }
	
    public Winner gameFinished() {
        //TODO Write your implementation for this method
        return Winner.NOT_FINISHED;
    }


    public void insertCoin(int column, int playerID) {
        //TODO Write your implementation for this method
    	board[column].enqueue(playerID);
    	System.out.println("col:\n" +board[0].toString());
    	System.out.println("sum:" + board[0].getSum());
    	System.out.println("______________");
    }

    public int decideNextMove() {
        //TODO Write your implementation for this method
    	return (int) (Math.random()*x);
    }

}
