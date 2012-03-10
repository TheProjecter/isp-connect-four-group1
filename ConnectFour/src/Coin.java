
public class Coin implements Cloneable{
boolean playerOne; //If true, coin is belonging to player one, if false coin belongs to player two. 

public Coin(int choosePlayer){
	playerOne = (choosePlayer==1);
}

public boolean isPlayerOne(){
	return playerOne;
}

//No need for this function, purely an æsthetic choice. The compiler doesn't mind though.
public boolean isPlayerTwo(){
	return !(playerOne);
}

public String toString(){
	if (playerOne) return "1";
	else return "2";
}

protected Object clone(){
	if (playerOne) return new Coin(1);
	else return new Coin(2);
}


}
