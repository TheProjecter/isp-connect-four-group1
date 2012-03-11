
public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Board b = new Board(6,7);
		
		   for (int x = 0; x< b.colCount(); x++) {
			   
	    	   for (int y = 0;  y < b.rowCount(); y++) {
	    		   b.add(x, (int)(Math.random() * 2));
	    		   System.out.println(b.doubleHashCode());
	    		   System.out.println(b);
	    		   
	    	   }
	       }
		   
		

	}

}
