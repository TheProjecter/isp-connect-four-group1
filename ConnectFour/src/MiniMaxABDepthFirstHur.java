public class MiniMaxABDepthFirstHur {
	int counter =0;
	int cutoff = 1000000;
	boolean wasCut = false;
	
	public MiniMaxABDepthFirstHur(){
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
							System.out.println("My opponent cannot instantwin if i play "+a);
							State jHureval = new State(ToolSet.Result(board,a,1));
							System.out.println("This play has jHur"+ jHureval.getjHur());
							v=ToolSet.Max(v, b);
							if(b==v) {
									choice=a;
									crisis=false;
							}
							if(crisis){
								choice=lastresort;
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
			if(counter>cutoff){
				wasCut=true;
				State jHur = new State(ToolSet.Result(board,a,1)); 
				return ToolSet.Max(v,jHur.getjHur());
			}
			v=ToolSet.Max(v,MinValue((ToolSet.Result(board,a,1)),alpha,beta));
			if(v>=beta){return v;}			
			alpha=ToolSet.Max(alpha,v);
		}
		
		return v;
	}
	
	int MinValue(Board board,int alpha,int beta){
		counter++;

		State test = new State(board);
		if(test.isTerminal()) return test.getUtility();		
		int v = Integer.MAX_VALUE;
		for (int a:ToolSet.Actions(board)){
			if(counter>cutoff){
				wasCut=true;
				State jHur = new State(ToolSet.Result(board,a,-1)); 
				return ToolSet.Min(v,jHur.getjHur());
			}
			v=ToolSet.Min(v,MaxValue((ToolSet.Result(board,a,-1)),alpha,beta));
			if(v<=alpha){return v;}
			beta = ToolSet.Min(beta,v);
		}
		
		return v;
	}

}
