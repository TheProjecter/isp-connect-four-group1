public class MiniMaxAB {
	int counter =0;
	int cutoff = 7;
	int evals = 0;
	int depth = 0;
	
	public MiniMaxAB(){
	}

	int ABsearch(Board board){
			if(board.openSlotsLeft()<25)cutoff=8;
			if(board.openSlotsLeft()<20)cutoff=10;
			int v = MaxValue(board,Integer.MIN_VALUE,Integer.MAX_VALUE);
			int choice = ToolSet.Actions(board)[0];
			
			for (int a: ToolSet.Actions(board)){
				counter++;
				int b = MinValue(ToolSet.Result(board,a,1),Integer.MIN_VALUE,Integer.MAX_VALUE);
				if(b==v)choice=a;
				counter--;
			}

			System.out.println("At depth "+depth+", after " + evals+" evaluations, and "+board.openSlotsLeft()+" open slots left in the board.");
			counter=0;
			System.out.println("I have chosen to play: "+choice);
			System.out.println("For an expected outcome of: "+v);
			return choice;
		}
	
	int MaxValue(Board board,int alpha,int beta){
		StateEvolved test = new StateEvolved(board);
		if(counter>cutoff){depth=counter;return test.getUtility();}
		evals++;
		if(test.isTerminal())	return test.getUtility();	
		int v = Integer.MIN_VALUE;
		for (int a:ToolSet.Actions(board)){
			counter++;
			v=ToolSet.Max(v,MinValue((ToolSet.Result(board,a,1)),alpha,beta));
			counter--;
			if(v>=beta){
				return v;
			}
			
			alpha=ToolSet.Max(alpha,v);
		}
		
		return v;
	}
	
	int MinValue(Board board,int alpha,int beta){
		StateEvolved test = new StateEvolved(board);
		if(counter>cutoff){depth=counter;return test.getUtility();}
		evals++;
		if(test.isTerminal()) return test.getUtility();		
		int v = Integer.MAX_VALUE;
		for (int a:ToolSet.Actions(board)){
			counter++;
			v=ToolSet.Min(v,MaxValue((ToolSet.Result(board,a,-1)),alpha,beta));
			counter--;
			if(v<=alpha){return v;}
			beta = ToolSet.Min(beta,v);
		}
		
		return v;
	}

}
