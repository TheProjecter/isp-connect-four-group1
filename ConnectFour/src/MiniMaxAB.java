public class MiniMaxAB {
	int counter =0;
	int cutoff = 7;
	int evals = 0;
	int depth = 0;
	
	public MiniMaxAB(){
	}

	int ABsearch(Board board){
//			cutoff=(int) ((float) (189/board.openSlotsLeft()+2.5));

//			int v = MaxValue(board,Integer.MIN_VALUE,Integer.MAX_VALUE);
			int choice = ToolSet.Actions(board)[0];
			int[] vAd = new int[2];
			vAd[0] = Integer.MIN_VALUE;
			vAd[1] = 0;
			
			for (int a: ToolSet.Actions(board)){
				counter++;
//				int b = MinValue(ToolSet.Result(board,a,1),Integer.MIN_VALUE,Integer.MAX_VALUE);
				int[] bAd = MinValue((ToolSet.Result(board,a,1)), Integer.MIN_VALUE, Integer.MAX_VALUE);
//				if(b==v) choice=a;
				if(bAd[0] > vAd[0]) {
					choice = a;
					vAd = bAd;
				}
				else if(bAd[0] == vAd[0]){
						if(bAd[0] >= 0) {
							if(bAd[1] < vAd[1]){
								vAd = bAd;
								choice = a;
							}
						}
						else if(bAd[1] > vAd[1]) {
							vAd = bAd;
							choice = a;
						}
					}
				counter--;
			}

			System.out.println("At depth "+vAd[1]+", after " + evals+" evaluations, and "+board.openSlotsLeft()+" open slots left in the board.");
			counter=0;
			System.out.println("I have chosen to play: "+choice);
			System.out.println("For an expected outcome of: "+vAd[0]+", at depth:"+vAd[1]);
			return choice;
		}
	
	int[] MaxValue(Board board,int alpha,int beta){
		int[] ret = new int[2];
		ret[1] = counter;
		StateEvolved test = new StateEvolved(board);
		if(counter>cutoff){
			ret[0] = test.getUtility();
			return ret;
			}
		evals++;
		if(test.isTerminal()){
			ret[0] = test.getUtility();
			return ret;	
		}
		int v[] = new int[2];
		v[0] = Integer.MIN_VALUE;
		for (int a:ToolSet.Actions(board)){
			counter++;
			int[] minV = MinValue((ToolSet.Result(board,a,1)),alpha,beta);
			counter--;
			if(v[0] < minV[0])v = minV;
			
			if(v[0] >= beta) return v;

			
			alpha=ToolSet.Max(alpha,v[0]);
		}
		return v;
	}
	
	int[] MinValue(Board board,int alpha,int beta){
		int[] ret = new int[2];
		ret[1] = counter;
		StateEvolved test = new StateEvolved(board);
		if(counter>cutoff){
			depth=counter;
			ret[0] = test.getUtility();
			return ret;	
		}
		evals++;
		if(test.isTerminal()){
			if(counter>depth) depth=counter;
			ret[0] = test.getUtility();
			return ret;		
		}
		int v[] = new int[2];
		v[0] = Integer.MAX_VALUE;
		for (int a:ToolSet.Actions(board)){
			counter++;
			int[] maxV = MaxValue((ToolSet.Result(board,a,-1)),alpha,beta);
			counter--;
			if(v[0] > maxV[0])v = maxV;			
			if(v[0] <= alpha) return v;

			beta = ToolSet.Min(beta,v[0]);
		}

		return v;
	}

}
