import java.util.Hashtable;

public class MiniMaxABTT {
	int counter =0;
	int cutoff = 200000;
	boolean wasCut = false;
	int winCondition = 4;
	public static Hashtable<Board, State> TransTableMax = new Hashtable<Board, State>();
	public static Hashtable<Board, State> TransTableMin = new Hashtable<Board, State>();
	
	public MiniMaxABTT(){
	}

	int ABsearch(Board board){
			int v = MaxValue(board,Integer.MIN_VALUE,Integer.MAX_VALUE);
			System.out.println("MaxVal: "+ v);
			int choice = 0;
			
			for (int a: ToolSet.Actions(board)){
				int b = MinValue(ToolSet.Result(board,a,1),Integer.MIN_VALUE,Integer.MAX_VALUE);
					if(wasCut){
						//Hvis der var et cutoff kan vi ikke regne med at hverken min eller max val er rigtige.
						//Vi bliver n�dt til at spille efter den maksimale af de udregnede minvals.
						//Hele pointen med cut-off er at vi ikke kan tjekke alle de f�lgende muligheder. Men vi kan da tjekke
						//Det n�ste niveau og sikre os at man ikke spiller et spil der giver modstanderen sejr i n�ste tr�k.
						if(!nextLoose(ToolSet.Result(board,a,1))){
							v=ToolSet.Max(v, b);
							if(b==v) {
									choice=a;
							}
						}
					}
					else if(b==v) {
					choice=a;
					}
			}
			
			System.out.println("After "+counter+" evaluations.");
			counter=0;
			wasCut=false;
			System.out.println("I have chosen to play: "+choice);
			System.out.println("For an expected outcome of: "+v);
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
		
		if(TransTableMax.containsKey(board)){
			return TransTableMax.get(board).getUtility();
		}
		
		State test = new State(board);
		if(test.isTerminal()){
			TransTableMax.put(board, test);
			return test.getUtility();
		}
	
		int v = Integer.MIN_VALUE;
		for (int a:ToolSet.Actions(board)){
			v=ToolSet.Max(v,MinValue((ToolSet.Result(board,a,1)),alpha,beta));
			
			if(v>=beta){
				TransTableMax.put(board, new State(v));
				return v;
			}
			
			alpha=ToolSet.Max(alpha,v);
			
			if(counter>cutoff){
				wasCut=true;
				return v;
				}
		}
		
		TransTableMax.put(board, new State(v));
		return v;
	}
	
	int MinValue(Board board,int alpha,int beta){
		counter++;
		
		if(TransTableMin.containsKey(board)){
			return TransTableMin.get(board).getUtility();
		}
		
		State test = new State(board);
		if(test.isTerminal()) {
			TransTableMin.put(board, test);
			return test.getUtility();
		}
		
		int v = Integer.MAX_VALUE;
		for (int a:ToolSet.Actions(board)){
			v=ToolSet.Min(v,MaxValue((ToolSet.Result(board,a,-1)),alpha,beta));
			if(v<=alpha){
				TransTableMin.put(board, new State(v));
				return v;
				}
			beta = ToolSet.Min(beta,v);
			if(counter>cutoff){wasCut=true;return v;}
		}
		TransTableMin.put(board, new State(v));
		return v;
	}

}
