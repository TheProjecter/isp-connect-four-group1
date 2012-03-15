/**
 * Column is used to represent a column in a connect four board (the Board class).
 * A Column contains 0 or more Coins.
 */
public class Column implements Cloneable{
		Coin[] col = new Coin[0];
		int N=0; // N contains the index of first empty spot in the column.
		
				
		public Column(int y){
			this.col = new Coin[y];
			this.N = 0;
		}
		
		@Override
		public Object clone(){
			Column clone = new Column(this.col.length); 
			clone.N = this.N;
			for (int cointIt=0; cointIt < this.col.length;cointIt++){
				if(this.col[cointIt]==null){break;}
				clone.col[cointIt] = (Coin) this.col[cointIt].clone();
			}
			return clone;
		}
		
		@Override
		public String toString(){
			String colString="";
			for (Coin coin:col){
				colString+=coin.toString()+"\n";
			}
			return colString;
		}
		
		 /**
		  * Since N is the next empty spot in the column, it will be equal to the length of it when
		  * it is full.
		  * @return true if the column is full
		  */
		boolean isFull(){
			return N==col.length; 
		}
		
		/**
		 * Add coin to column if it is not empty. 
		 * @param PlayerID
		 */
		void add(int PlayerID){
			if(!this.isFull()){
				col[N]=new Coin(PlayerID);
				N++;				
			}
			
		}
		
		Coin get(int place){
			return col[place];
		}
				
	}

