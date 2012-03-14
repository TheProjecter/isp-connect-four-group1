public class MMABWeakDepthLtm {
	int counter =0;
	int cutoff = 5;
	int evals = 0;
	int depth = 0;
	
	public MMABWeakDepthLtm(){
	}

	int ABsearch(Board board){
//			cutoff=210/board.openSlotsLeft()+2;

//			int v = MaxValue(board,Integer.MIN_VALUE,Integer.MAX_VALUE);
			int choice = ToolSet.Actions(board)[0];
			int v = Integer.MIN_VALUE;

			for (int a: ToolSet.Actions(board)){
				counter++;
//				int b = MinValue(ToolSet.Result(board,a,1),Integer.MIN_VALUE,Integer.MAX_VALUE);
				int b=ToolSet.Max(v,MinValue((ToolSet.Result(board,a,1)), Integer.MIN_VALUE, Integer.MAX_VALUE));
//				if(b==v) choice=a;
				if(b > v) {
					choice = a;
					v = b;
				}
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
