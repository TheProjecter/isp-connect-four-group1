
public class MatrixLogicMiniMax {
	int counter =0;
	int cutoff=1000000;
	
	
	public MatrixLogicMiniMax(){
	}

	int MinimaxDecision(Board board){
		int v=Integer.MIN_VALUE;
		int choice=0;
		for (int a: ToolSet.Actions(board)){
			int b=ToolSet.Max(v,MinValue((ToolSet.Result(board,a,1))));
			if(b>v) {
				v=b;
				choice=a;
				}
		}
		System.out.println("After "+counter+" evaluations.");
		counter=0;
		System.out.println("I have chosen to play: "+choice);
		System.out.println("For an expected outcome of: "+v);
		return choice;
	}
		
	int MaxValue(Board board){
		counter++;

		State test = new State(board);
		if(test.isTerminal()) return test.getUtility();

		int v = Integer.MIN_VALUE;
		for (int a:ToolSet.Actions(board)){
			v=ToolSet.Max(v,MinValue((ToolSet.Result(board,a,1))));
			if(counter>cutoff){break;}
		}
		return v;
	}

	int MinValue(Board board){
		counter++;
		
		State test = new State(board);
		if(test.isTerminal()) return test.getUtility();

		int v = Integer.MAX_VALUE;
		for (int a:ToolSet.Actions(board)){
			v=ToolSet.Min(v,MaxValue((ToolSet.Result(board,a,-1))));
			if(counter>cutoff){break;}
		}
		return v;
	}
}
