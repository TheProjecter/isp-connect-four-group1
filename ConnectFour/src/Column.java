public class Column implements Cloneable{
		Coin[] col = new Coin[0];
		int N=0;
		
				
		public Column(int y){
			this.col = new Coin[y];
			this.N = 0;
		}
		
		@Override
		protected Object clone(){
			Column clone = new Column(this.col.length); 
			clone.N = this.N;
			for (int cointIt=0; cointIt < this.col.length;cointIt++){
				if(this.col[cointIt]==null){break;}
				clone.col[cointIt] = (Coin) this.col[cointIt].clone();
			}
			return clone;
		}
		
		public String toString(){
			String colString="";
			for (Coin coin:col){
				colString+=coin.toString()+"\n";
			}
			return colString;
		}
		
		boolean isFull(){
			return N==col.length;
		}
		
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

