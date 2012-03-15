public class State {
	private byte utility = 0;
	private byte winCondition = 4;
	private byte jHur=0;

	public State(Board board){
		utility = utility(board);
		jHur = jHur(board);
	}
	
	public State(int util){
		this.utility = (byte) util;
	}

	/**
	 * Returns the winner of the board.
	 * The value is multiplied with 10000 to dominate the jHurs value.
	 * @param board
	 * @return
	 */
	private byte utility(Board board){
		int huroffset =  10000;
    	for(int col=0;col<board.colCount();col++){
    		byte colWin = (byte) ( calcWinner(board.getColumn(col))*huroffset);
    		if(colWin!=0) return colWin;
    	}
    	
    	for(int row=0;row<board.rowCount();row++){
    		byte rowWin = (byte) (calcWinner(board.getRow(row))*huroffset);
    		if(rowWin!=0) return rowWin;
    	}
    	
    	for(int lDia=0;lDia<board.diagonalCount();lDia++){
    		byte lDiaWin = (byte) (calcWinner(board.getLeftDiagonal(lDia))*huroffset);
    		if(lDiaWin!=0) return lDiaWin;
    	}
    	
    	for(int rDia=0;rDia<board.diagonalCount();rDia++){
    		byte rDiaWin = (byte) (calcWinner(board.getRightDiagonal(rDia))*huroffset);
    		if(rDiaWin!=0) return rDiaWin;
    	}
		
    	if(board.isFull()) return 0;
    	
		return 99;
	}
	
	/**
	 * Determines whether the board is terminal.
	 * @return
	 */
	public boolean isTerminal(){
		return utility != 99;
	}
	
	/**
	 * Wrapper for the utility function.
	 * @return
	 */
	public byte getUtility(){
		return utility;
	}
	
	/**
	 * Checks a given Coin array for a winning combination.
	 * @param coinArr
	 * @return
	 */
	private byte calcWinner(Coin[] coinArr){
		int oneSum=0;
		int twoSum=0;
		
		for (Coin coin:coinArr){
			if(coin==null){
				oneSum = 0;
				twoSum = 0;
			}
			else if(coin.isPlayerOne()){
				oneSum++;
				twoSum=0;
				if(oneSum==winCondition) return (byte) 1;
				}
			
			else if(coin.isPlayerTwo()){
				oneSum=0;
				twoSum++;
				if(twoSum==(winCondition)) return (byte) -1;
				}
		}
	
		return (byte) 0;
}
	/**
	 * Checks a given Coin array for it's max-combination.
	 * This version disregards "closed" arrays. Meaning it doesn't care for coins that are capped by the opponent.
	 * I have left the "max" parameters to make this easy to adjust by removing the reset lines.
	 * @param coinArr
	 * @return
	 */
	public byte calcSum(Coin[] coinArr){
		int oneSum=0;
		int twoSum=0;
		int oneMax=0;
		int twoMax=0;
		
		for (Coin coin:coinArr){
			if(coin==null){
				oneSum = 0;
				twoSum = 0;
			}
			else if(coin.isPlayerOne()){
				oneSum++;
				twoSum=0;
				twoMax=0;
				oneMax=ToolSet.Max(oneSum,oneMax);
			}
			
			else if(coin.isPlayerTwo()){
				oneSum=0;
				oneMax=0;
				twoSum++;
				twoMax=ToolSet.Max(twoSum, twoMax);

			}
		}
		if(oneMax>twoMax) return (byte)oneMax;
		if(twoMax>oneMax) return (byte)-twoMax;
		else return (byte) 0;
}
	/**
	 * Finds the jHur of a board.
	 * jHur is a simple heuristic that returns the number of "friendly" combinations on a board.
	 * @param board
	 * @return
	 */
	private byte jHur(Board board){
		int jHur=0;
    	for(int col=0;col<board.colCount();col++){
    		jHur += calcSum(board.getColumn(col));
    	}
    	
    	for(int row=0;row<board.rowCount();row++){
    		jHur += calcSum(board.getRow(row));
    	}
    	
    	for(int lDia=0;lDia<board.diagonalCount();lDia++){
    		jHur += calcSum(board.getLeftDiagonal(lDia));
    	}
    	
    	for(int rDia=0;rDia<board.diagonalCount();rDia++){
    		jHur += calcSum(board.getRightDiagonal(rDia));
    	}
		
    	if(board.isFull()) return 0;
    	
		return (byte) jHur;
	}
	
	/**
	 * Returns the jHur value of this state.
	 * @return
	 */
	public byte getjHur(){
		return jHur;
	}

}