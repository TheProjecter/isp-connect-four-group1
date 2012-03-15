
public class State {
	private int utility = 0;
	private int winCondition;
	private boolean isFull = false;
	private int playerWin =  1000000;
	private int expoOne = 10;
	private int expoTwo = 10;
	
	public State(Board board){
		this.winCondition = board.getWinCondition();
		if(board.isFull()){
			this.isFull = true;
		}
		this.utility = setUtility(board);
		if(utility==99) utility = this.calcBoardHeuristic(board);
	}
	
	public State(Board board, int playerID){
		if(playerID == 1){expoOne=30; expoTwo=10;}
		else{expoOne=10; expoTwo=30;}
		this.winCondition = board.getWinCondition();
		if(board.isFull()){
			this.isFull = true;
		}
		this.utility = setUtility(board);
		if(utility==99) utility = this.calcBoardHeuristic(board);
	}
	
	public State(int util){
		this.utility = (byte) util;
	}

	/**
	 * Returns the winner of the board.
	 * @param board
	 * @return
	 */
	private int setUtility(Board board){
    	for(int col=0;col<board.colCount();col++){
    		int colWin = calcWinner(board.getColumn(col));
    		if(colWin!=0) return colWin;
    	}
    	
    	for(int row=0;row<board.rowCount();row++){
    		int rowWin = calcWinner(board.getRow(row));
    		if(rowWin!=0) return rowWin;
    	}
    	
    	for(int lDia=0;lDia<board.diagonalCount();lDia++){
    		int lDiaWin = calcWinner(board.getLeftDiagonal(lDia));
    		if(lDiaWin!=0) return lDiaWin;
    	}
    	
    	for(int rDia=0;rDia<board.diagonalCount();rDia++){
    		int rDiaWin = calcWinner(board.getRightDiagonal(rDia));
    		if(rDiaWin!=0) return rDiaWin;
    	}
		    	
		return 99;
	}
	
	/**
	 * Determines whether the board is terminal.
	 * @return
	 */
	public boolean isTerminal(){
		return (utility==playerWin || utility == -playerWin || this.isFull);
	}
	
	/**
	 * Wrapper for the utility function.
	 * @return
	 */
	public int getUtility(){
		return utility;
	}
	
	public int getPlayerWin(){
		return playerWin;
	}
	/**
	 * Checks a given Coin array for a winning combination.
	 * @param coinArr
	 * @return
	 */
	private int calcWinner(Coin[] coinArr){
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
				if(oneSum == winCondition) return playerWin;
				}
			
			else if(coin.isPlayerTwo()){
				oneSum=0;
				twoSum++;
				if(twoSum==(winCondition)) return -playerWin;
				}
		}
	
		return 0;
}

	private int calcBoardHeuristic(Board b){
		int bHeuristic = 0;
    	for(int x=0;x<b.colCount();x++){
    		Coin[] colX = b.getColumn(x);
    		bHeuristic += this.calcCoinHeuristic(colX);
    	}
    	
    	for(int y=0;y<b.rowCount();y++){
    		Coin[] rowY = b.getRow(y);
    		bHeuristic += this.calcCoinHeuristic(rowY);
    	}
    	
    	for(int l=0;l<b.diagonalCount();l++){
    		Coin[] diaL= b.getLeftDiagonal(l);
    		bHeuristic += this.calcCoinHeuristic(diaL);
    	}
    	
    	for(int r=0;r<b.diagonalCount();r++){
    		Coin[] diaR = b.getRightDiagonal(r);
    		bHeuristic += this.calcCoinHeuristic(diaR);
    	}
		
    	return bHeuristic;
	}
	
	/**
	 * Calculates the heuristic of a given coin array.
	 * The function checks if there is a possibility for a winning condition,
	 * the closer the player is, the higher the power of ten times the player value
	 * is added to the heuristics.
	 * E.g. if playerOne has three coins in a row followed by one null value, the added value
	 * to the heuristic is 1*10^0+1*10^1+1*10^2 = 1+10+100 = 111. 
	 * Vice versa -1*10^0+-1*10^1+-1*10^2 = -1+-10+-100 = -111 is added to the heuristic.
	 * @param coinArr
	 * @return
	 */
	private int calcCoinHeuristic(Coin[] coinArr){
		int[] oneHeu = new int[winCondition];
		int[] twoHeu = new int[winCondition];
		int oneCount = 0;
		int twoCount = 0;
		int heuristic = 0;
		
		for (Coin coin:coinArr){
			if(coin==null){
				oneCount++;
				twoCount++;
			}
			else if(coin.isPlayerOne()){
				oneHeu[oneCount] = 1;
				oneCount++;
				twoCount=0;
			}
			else if(coin.isPlayerTwo()){
				twoHeu[twoCount] = -1;
				twoCount++;
				oneCount=0;
				}
			
			if(oneCount == winCondition){
				oneCount = 0;
				int zeroCount = winCondition;
				int tempHeuristic = 0;
				
				for(int one: oneHeu){
					if(one!=0){
						int value = (int) Math.pow(expoOne,oneCount);
						tempHeuristic += value*one;
						oneCount++;
						zeroCount--;
					}
				}
				if(zeroCount!=0){
					heuristic += tempHeuristic;
				}
				oneCount = 0;
				oneHeu = new int[winCondition];
			}
			
			if(twoCount == winCondition){
				twoCount = 0;
				int zeroCount = winCondition;
				int tempHeuristic = 0;

				for(int two: twoHeu){
					if(two!=0){
					int value = (int) Math.pow(expoTwo,twoCount);
					heuristic += value*two;
					twoCount++;
					zeroCount--;
					}
				}
				if(zeroCount!=0){
					heuristic += tempHeuristic;
				}
				twoCount = 0;
				twoHeu = new int[winCondition];
			}
		}
		
		
	
		return heuristic;
	}
}