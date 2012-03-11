public class State {
	private byte utility = 0;
	private byte winCondition = 4;

	public State(Board board){
		utility = utility(board);
	}
	
	public State(int util){
		this.utility = (byte) util;
	}

	/**
	 * Returns the winner of the board.
	 * @param board
	 * @return
	 */
	private byte utility(Board board){
    	for(int col=0;col<board.colCount();col++){
    		byte colWin = calcWinner(board.getColumn(col));
    		if(colWin!=0) return colWin;
    	}
    	
    	for(int row=0;row<board.rowCount();row++){
    		byte rowWin = calcWinner(board.getRow(row));
    		if(rowWin!=0) return rowWin;
    	}
    	
    	for(int lDia=0;lDia<board.diagonalCount();lDia++){
    		byte lDiaWin = calcWinner(board.getLeftDiagonal(lDia));
    		if(lDiaWin!=0) return lDiaWin;
    	}
    	
    	for(int rDia=0;rDia<board.diagonalCount();rDia++){
    		byte rDiaWin = calcWinner(board.getRightDiagonal(rDia));
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

}