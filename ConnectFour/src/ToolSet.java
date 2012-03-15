/**
 * 
 * Toolset is a helper class that contains helper methods.
 *
 */
public class ToolSet {

	
	/**
	 * Return the maximum of ints a and b, if equal return a.
	 * @param a
	 * @param b
	 * @return
	 */
	public static int Max(int a, int b){
		if(a<b){
			return b;
		}
		return a;
	}
	/**
	 * Return minimum of ints a and b, if equal return a.
	 * @param a
	 * @param b
	 * @return
	 */
	public static int Min(int a, int b){
		if(a>b){
			return b;
		}
		return a;
	}
	
	/**
	 * The resulting board of playerID adding to a column a in a board s
	 */
	public static Board Result(Board org,int action,int playerID){
		Board board = (Board) org.clone();
		board.add(action, playerID);
		return board;
	}
	
	
	/**
	 * Actions returns the number of the playable columns
	 * @param board
	 * @return
	 */
	public static int[] Actions(Board board){
		return board.openColums();
	}
	

}
