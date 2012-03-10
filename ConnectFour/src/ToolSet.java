
public class ToolSet {
	static int winCondition=4;

	
	
	public static int Max(int a, int b){
		if(a<b){
			return b;
		}
		return a;
	}
	public static int Min(int a, int b){
		if(a>b){
			return b;
		}
		return a;
	}
	
	//The resulting board of playerID adding to a column a in a board s
	public static Board Result(Board org,int action,int playerID){
		Board board = (Board) org.clone();
		board.add(action, playerID);
		return board;
	}
	
	
	//Actions returns the number of the playable columns
	public static int[] Actions(Board board){
		return board.openColums();
	}
	

}
