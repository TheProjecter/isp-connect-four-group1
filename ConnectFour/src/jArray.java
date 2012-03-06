
public class jArray {
	
	//J-array is a simple array with stack methods but it includes the sum of the numbers in the array
	//According to the following formula:
	// a[0]*a[1]+a[1]*a[2]+a[2]*a[3]+a[3]*.....
	
	int N=0;
	int[] a = new int[0];
	private int sum=0;
	
	//Constuctor
	public jArray(int height){
		a = new int[height];
	}
	
	//Methods
	private void calcSum(){
		sum=0;
		int max=0;
		for (int i=0;i<N;i++){
			if(sum>=0 && a[i]>0)
				sum+=1;
			else if(sum<0 && a[i]>0)
				sum=1;
			else if(sum>=0 && a[i]<0)
				sum=-1;
			else if(sum<0 && a[i]<0)
				sum-=1;
			if(sum>max) max = sum;
		}		
	}
	
	public int getSum(){
		return sum;
	}
	
	//Other methods are just a simple non-resizing stack implementation
	
	
	public Boolean isEmpty(){
		if (this.a.length==0)
			return true;
		return false;
	}
	
	public int size(){
		return N;
	}
	
	
	//Handels player 2 as -1 for working sums
	public void enqueue(int item){
		if (item==2) item = -1;
		this.a[N++]=item;
		calcSum();
		
	}
	
	public int dequeue(){
		int drawnItem = a[N];
		N--;
		calcSum();
		return drawnItem;
	}
	
	public int sample(){
		return a[N];
	}
	
	public String toString(){
		String s="";
		for (int i=0;i<N;i++){
			s+=a[i]+"\n";
		}
		return s;
	}


}
