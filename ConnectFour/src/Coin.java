/**
 * The Coin class represent a players coin. 
 * A Column can contain a number of Coin objects.
 * 
 */
public class Coin implements Cloneable{
boolean playerOne; //If true, coin is belonging to player one, if false coin belongs to player two. 

public Coin(int choosePlayer){
	playerOne = (choosePlayer==1);
}

public boolean isPlayerOne(){
	return playerOne;
}

//No need for this function, purely an æsthetic choice.
public boolean isPlayerTwo(){
	return !(playerOne);
}

public String toString(){
	if (playerOne) return "1";
	else return "2";
}

/**
 * Simple clone method overriding Objects clone method
 */
@Override
protected Object clone(){
	if (playerOne) return new Coin(1);
	else return new Coin(2);
}

/**
 * Simple equals method overriding Objects equals. 
 */
@Override
public boolean equals(Object object) {
    if (object == null) return(false); //Null reference pointer, return false
    if (object == this) return(true); //Same object, return true
    if (!(object instanceof Coin)) return(false); //Not same class, return false

    Coin C = (Coin) object; //Cast to Board
    
    return (C.isPlayerOne()==this.playerOne);
}


}
