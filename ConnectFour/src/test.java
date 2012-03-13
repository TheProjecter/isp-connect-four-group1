import java.util.Hashtable;


public class test {

	public static Hashtable<Board, State> Ht = new Hashtable<Board, State>();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Board b = new Board(4,4);
//		b.add(3, 2);
//		b.add(3, 2);
//		b.add(3, 2);
//		b.add(3, 1);
//		
//		b.add(4, 2);
//		b.add(4, 2);
//		b.add(4, 1);
//		
//		b.add(5, 2);
//		b.add(5, 1);
//		
//		b.add(6, 1);
//		
//		System.out.println("Diagonal count is: "+b.diagonalCount());
//		for(int r = 0; r< b.diagonalCount();r++){
//			Coin[] coinArr = b.getRightDiagonal(r);
//			String s = "";
//			for(Coin coin:coinArr){
//				System.out.println(coin);
////				s.concat(coin+" ");
//			}
//			System.out.println(s);
//		}
//		
//		Board b = new Board(7,6);
//		b.add(3, 2);
//		b.add(3, 2);
//		b.add(3, 2);
//		b.add(3, 1);
//		
//		b.add(4, 2);
//		b.add(4, 2);
//		b.add(4, 1);
//		
//		b.add(5, 2);
//		b.add(5, 1);
//		
//		b.add(6, 1);
//		
//		System.out.println("Diagonal count is: "+b.diagonalCount());
//		for(int l = 0; l< b.diagonalCount();l++){
//			Coin[] coinArr = b.getLeftDiagonal(l);
//			String s = "";
//			for(Coin coin:coinArr){
//				System.out.println(coin);
////				s.concat(coin+" ");
//			}
//			System.out.println(s);
//		}
//		
//		
//		System.out.println(0.123456789f);
//		
//		
		
		   for (int x = 0; x< b.colCount(); x++) {
	    	   for (int y = 0;  y < b.rowCount(); y++) {
	    		   b.add(x, (int)(Math.random() * 2));
	    		   State s = new State(b);
	    		   System.out.println("Utility for board is:"+s.getUtility());
	    		   System.out.println("Open slots in board is:"+b.openSlotsLeft());
	    		   System.out.println(b);
	    	   }
	       }   
	}
}