
public class Heuristic {
	int winCondition;
	int heuristic;
	
	public Heuristic(Board b){
		winCondition = b.getWinCondition();
		this.calcBoardHeuristic(b);
	}
	
	public int getHeuristic(){
		return heuristic;
	}
	/**
	 * Calculates the heuristic of an entire board, using the calcHeuristics method for 
	 * specific rows, columns diagonals.
	 * @param heuristic 
	 * @return
	 */
	private void calcBoardHeuristic(Board b){
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
		
    	heuristic = bHeuristic;
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
				for(int one: oneHeu){
					if(one!=0){
						int value = (int) Math.pow(10,oneCount);
						heuristic += value*one;
						oneCount++;
					}
				}
				oneCount = 0;
				oneHeu = new int[winCondition];
			}
			
			if(twoCount == winCondition){
				twoCount = 0;
				for(int two: twoHeu){
					int value = (int) Math.pow(10,twoCount);
					heuristic += value*two;
					twoCount++;
				}
				twoCount = 0;
				twoHeu = new int[winCondition];
			}
		}
		
		
	
		return heuristic;
	}
}