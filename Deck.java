/**
 * This is the deck class
 * 
 * 
 * @author: Danielle Cai, uni: dc3347
 * @date: 10/28/18
 */

public class Deck {
	
	private Card[] cards;
	private int top; // the index of the top of the deck
 
	// add more instance variables if needed
    //constructor
	public Deck(){
        //create object of class Card
        Card c;
        
		// make a 52 card deck here
        cards=new Card[52];
        
        //instance variable
        int index=0;
        
        //initializing the deck of cards
        //add card to deck
        for (int i=1; i<=4;i++){ //13 values
            for (int j=1; j<=13;j++){ // 4 suits
                c = new Card(i,j);
                cards[index]= c; //put card in position i
                index++;
            }
        }
        top=0;
	}
	
	public void shuffle(){
		// shuffle the deck here

        Card temp;     
        //swaps the first with a random card 1000 times
        for (int i=0; i<cards.length;i++){ 
            //random num between 0 and 51
            int randomPos=(int)(Math.random()*(cards.length));
            temp=cards[i];
            cards[i]=cards[randomPos];
            cards[randomPos]=temp;
        }
	}
	
	public Card deal(){
		// deal the top card in the deck  
       if (top>=52){
           top=0;
           return new Card(0,0);
       }
        else{
            top++;
            return cards[top-1];
        }
	}
}
