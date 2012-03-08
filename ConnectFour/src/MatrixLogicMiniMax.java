
public class MatrixLogicMiniMax {
	int counter =0;
	int cutoff=1000000;
	
	
	public MatrixLogicMiniMax(){
	}
	int MinimaxDecision(Board board){
		int v=Integer.MIN_VALUE;
		int choice=0;
		for (int a: stateEval.Actions(board)){
			int b=stateEval.Max(v,MinValue((stateEval.Result(board,a,1))));
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
		if(stateEval.TerminalTest(board)) {
			return stateEval.Utility(board);
		}
		int v = Integer.MIN_VALUE;
		for (int a:stateEval.Actions(board)){
			v=stateEval.Max(v,MinValue((stateEval.Result(board,a,1))));
			if(counter>cutoff){break;}
		}
		return v;
	}
	int MinValue(Board board){
		counter++;
		if(stateEval.TerminalTest(board)){		
			return stateEval.Utility(board);
		}
		int v = Integer.MAX_VALUE;
		for (int a:stateEval.Actions(board)){
			v=stateEval.Min(v,MaxValue((stateEval.Result(board,a,-1))));
			if(counter>cutoff){break;}
		}
		return v;
	}
	


}
