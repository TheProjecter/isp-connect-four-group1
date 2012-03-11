

public class MatrixLogicMiniMaxAB {
	int counter =0;
	int cutoff = 10;
	boolean wasCut = false;
	int winCondition = 4;
	
	public MatrixLogicMiniMaxAB(){
	}

	int ABsearch(Board board){
			int v = MaxValue(board,Integer.MIN_VALUE,Integer.MAX_VALUE);
			if(wasCut) v=0;
			int choice = 0;
			
			for (int a: ToolSet.Actions(board)){
				int b = MinValue(ToolSet.Result(board,a,1),Integer.MIN_VALUE,Integer.MAX_VALUE);
				if(b==v) {
					choice=a;
					}
			}
			
			System.out.println("After "+counter+" evaluations.");
			counter=0;
			System.out.println("I have chosen to play: "+choice);
			System.out.println("For an expected outcome of: "+v);
			return choice;
		}
	
	int MaxValue(Board board,int alpha,int beta){
		counter++;
		
		State test = new State(board);
		if(test.isTerminal()) return test.getUtility();
	
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
