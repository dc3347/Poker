/**
 * This is the player class
 * 
 * 
 * @author: Danielle Cai, uni: dc3347
 * @date: 10/28/18
 */

import java.util.ArrayList;
public class Player {
	
	private ArrayList<Card> hand; // the player's cards
	private double bankroll=10;
    private double bet;

	// you may choose to use more instance variables
	private double payoff;
    private Player p;
	private Deck cards; 
		
	public Player(){		
	    // create a player here
	    hand= new ArrayList<Card>();
	}

    public ArrayList<Card> getHand(){
        return hand;
        
    }
	public void addCard(Card c){
	    // add the card c to the player's hand
	    hand.add(c);
	}

	public void removeCard(Card c){
	    // remove the card c from the player's hand
        hand.remove(c);
    }
    
    public void bets(double amt){
        // player makes a bet
        bet=amt;
        bankroll=bankroll-bet;
    }

    public void winnings(double odds){
        //adjust bankroll if player wins
        payoff=odds;
        bankroll=bankroll+(bet*payoff);
    }

    public double getBankroll(){
        // return current balance of bankroll
        return bankroll;
    }

    // you may wish to use more methods here
    public void reset(ArrayList<Card> h){
        p=new Player();
        hand=h;
        hand.clear(); //clears the arraylist of card objects
        cards = new Deck();
        cards.shuffle();
    }   
}



