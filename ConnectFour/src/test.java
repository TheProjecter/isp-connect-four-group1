import java.util.Hashtable;


public class test {

	public static Hashtable<Board, State> Ht = new Hashtable<Board, State>();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Board b = new Board(6,7);
		Board c = new Board(6,7);
		b.add(0, 1);
		c.add(0, 1);
		
		Coin a = new Coin(1);
		Coin d = new Coin(1);
		System.out.println(a.equals(d));
		
		
		System.out.println(b.equals(c));
		
		   for (int x = 0; x< b.colCount(); x++) {
			   
	    	   for (int y = 0;  y < b.rowCount(); y++) {
	    		   b.add(x, (int)(Math.random() * 2));
	    		   State s = new State(b);
	    		   Ht.put(b,s);
	    		   //System.out.println(b.hashCode());
//	    		  System.out.println(s.getUtility()==Ht.get(b).getUtility());	   
	    	   }
	    	   
	    	  
	       }
		   
		   
		   
		

		   
	}
	
	
	

}