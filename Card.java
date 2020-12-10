/**
 * This is the card class
 * 
 * 
 * @author: Danielle Cai, uni: dc3347
 * @date: 10/28/18
 */

public class Card implements Comparable<Card>{
	
	private int suit; // use integers 1-4 to encode the suit 
    //(club, diamond, heart, spades)
	private int rank; // use integers 1-13 to encode the rank
    
	public Card(int s, int r){
		//make a card with suit s and value r
		suit=s;
        rank=r;
	}
	
	public int compareTo(Card c){
		// use this method to compare cards so they may be easily sorted
		// compares ranks, but use suit to break any ties
		
        //when the cards have the same number
        if (this.getRank()==c.getRank()){ 
            //look at the suits to break tie
            if (this.getSuit()>c.getSuit()){
                return 1;
            }
            else{
                return -1;
            }
        }
        else if (this.getRank()>c.getRank()){
            return 1;
        }
        else{
            return -1;
        }
	}
	
	public String toString(){
		// use this method to easily print a Card object
		String card;
		String stringRank="-1";
        String stringSuit="-1";
        
        if (rank>1 && rank<11){
            //stringRank= Integer.toString(rank);
            stringRank=""+rank;
        }
		else if (rank==11){
            stringRank= "Jack";
        }
        else if (rank==12){
            stringRank="Queen";
        }
        else if (rank ==13){
            stringRank="King";
        }
        else if (rank==1){
            stringRank="Ace";
        }
        
        //convert numbers to suit names
        if (suit==1){
            stringSuit="Clubs";
        }
        else if (suit==2){
            stringSuit="Diamonds";
        }
        else if (suit==3){
            stringSuit="Hearts";
        }
        else{ //suit==4
            stringSuit="Spades";
        }
        card= stringRank+ " of " +stringSuit;
        return card;
	}
	// add some more methods here if needed
	public int getRank(){ //1-13
        return rank;
    }
    public int getSuit(){ //1-4
        return suit;
    }
}
