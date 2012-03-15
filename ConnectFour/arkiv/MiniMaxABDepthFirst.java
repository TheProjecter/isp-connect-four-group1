public class MiniMaxABDepthFirst {
	int counter =0;
	int cutoff = 3000000;
	boolean wasCut = false;
	
	public MiniMaxABDepthFirst(){
	}

	int ABsearch(Board board){
			int v = MaxValue(board,Integer.MIN_VALUE,Integer.MAX_VALUE);
			int choice = ToolSet.Actions(board)[0];

			if(wasCut){
			System.out.println("Processing interrupted!");
			boolean crisis=true;
			for (int a: ToolSet.Actions(board)){
				int b = MinValue(ToolSet.Result(board,a,1),Integer.MIN_VALUE,Integer.MAX_VALUE);					
						if(!nextLoose(ToolSet.Result(board,a,1))){							
							int lastresort=a;
//							System.out.println("My opponent cannot instantwin if i play "+a);
							v=ToolSet.Max(v, b);
							if(b==v) {
									choice=a;
									crisis=false;
							}
							if(crisis){
								choice=lastresort;
								System.out.println("This move is a crisis move. I leave more space for my opponent, but avoids an instant win.");
							}
						}
					}
			}
			
			else{
			for (int a: ToolSet.Actions(board)){
				int b = MinValue(ToolSet.Result(board,a,1),Integer.MIN_VALUE,Integer.MAX_VALUE);
					if(b==v) {
					choice=a;
					}
			}
			switch (v){
			case 0:System.out.println("We tie for now, human.");
			break;
			case 1:System.out.println("Resistance is futile!");
			break;
			case -1:System.out.println("I cannot maintain!");
			}
			}
			
			System.out.println("After " + counter +" evaluations");
			System.out.println("I have chosen to play "+choice+".");
			counter=0;
			wasCut=false;

			
			return choice;
		}
	
	/**
	 * Checks if the opponent can win in the next move on a given board.
	 * @param board
	 * @return
	 */
	boolean nextLoose(Board board){
		boolean opWin=false;
		for (int a: ToolSet.Actions(board)){
			State s= new State(ToolSet.Result(board, a, -1));
			if(s.getUtility()==-1) opWin=true;
		}
		return opWin;
	}
	
	int MaxValue(Board board,int alpha,int beta){
		counter++;

		State test = new State(board);
		if(test.isTerminal())	return test.getUtility();	
		int v = Integer.MIN_VALUE;
		for (int a:ToolSet.Actions(board)){
			v=ToolSet.Max(v,MinValue((ToolSet.Result(board,a,1)),alpha,beta));
			if(v>=beta){return v;}			
			alpha=ToolSet.Max(alpha,v);
			if(counter>cutoff){wasCut=true;return v;}
		}
		
		return v;
	}
	
	int MinValue(Board board,int alpha,int beta){
		counter++;

		State test = new State(board);
		if(test.isTerminal()) return test.getUtility();		
		int v = Integer.MAX_VALUE;
		for (int a:ToolSet.Actions(board)){
			v=ToolSet.Min(v,MaxValue((ToolSet.Result(board,a,-1)),alpha,beta));
			if(v<=alpha){return v;}
			beta = ToolSet.Min(beta,v);
			if(counter>cutoff){wasCut=true;return v;}
		}
		
		return v;
	}

}
