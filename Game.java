/**
 * This is the game class
 * 
 * 
 * @author: Danielle Cai, uni: dc3347
 * @date: 10/28/18
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.Integer;

public class Game {
	
	private Player p;
	private Deck cards;  
	// you'll probably need some more here
	private ArrayList<Card> hand;
    private double payoff;
	
	public Game(String[] testHand){
		// This constructor is to help test your code.
		// use the contents of testHand to make a hand for the player
		// use the following encoding for cards
		// c = clubs
		// d = diamonds
		// h = hearts
		// s = spades
		// 1-13 correspond to ace-king
		// example: s1 = ace of spades
		// example: testhand = {s1, s13, s12, s11, s10} = royal flush
        p=new Player();
        cards = new Deck();
        cards.shuffle();
        argsHand(testHand);
        hand = new ArrayList<Card>(p.getHand());
        System.out.println("You have a " +checkHand(hand));
        System.exit(0); //to exit the game
	}
	
	public Game(){
		//This no-argument constructor is to actually play a normal game
		p=new Player();
        cards = new Deck();
        cards.shuffle();
        for(int i=1; i<=5; i++){ //make a hand of 5 cards
            //deals the card into the player's hand
            p.addCard(cards.deal());
        }
        hand = new ArrayList<Card>(p.getHand());    
	}
	
	public void play(){
		Boolean playAgain =true;
		while (playAgain){
            System.out.println("--------------------------------");
            System.out.println("Welcome to ultimate Poker!!");
            System.out.println("You have "+(int)p.getBankroll()+" tokens.");    
            Scanner scanner = new Scanner(System.in);

            System.out.println("How many tokens do you want to bet? (1-5)");
            int bet = scanner.nextInt();
            p.bets(bet); //assume intelligenet user input
            
            System.out.println("--------------------------------");
            System.out.println("Your hand is:");
                for(Card value:hand){
                    System.out.println(value);
                }
            System.out.println("Do you want to redraw (y/n)?");
            scanner.nextLine(); //to prevent a floating new line 
            String redraw = scanner.nextLine(); 
            if (redraw.equals("y") || redraw.equals("Y")){
               System.out.println("How many cards do you want to redraw?(1-5)");
                int input = scanner.nextInt();
                if (input>0 && input <6){
                    for(int i=0;i<input;i++){
                        System.out.println("Which card to redraw?(1-5)");
                        int selected=scanner.nextInt();
                        if(selected>0 && selected<6){
                            p.removeCard(hand.get(selected-1));
                            //use selected-1 for the index of hand arraylist
                            p.addCard(cards.deal());  
                        }
                    }
                }
                hand = new ArrayList<Card>(p.getHand());
                System.out.println("Your new hand is:");
                for(Card value:hand){
                    System.out.println(value);
                }
            }
            System.out.println("--------------------------------");
            System.out.println("You got a "+checkHand(hand));
            p.winnings(payoff);                    
            System.out.println("You now have: "+(int)p.getBankroll()+" tokens");
            System.out.println("Would you like to play again? (y/n)"); 
            String again =scanner.next();
            if(again.equals("y")||again.equals("Y")){
                p.reset(hand);
                for(int i=1; i<=5; i++){ //make a hand of 5 cards
                    p.addCard(cards.deal());//deals card into the player's hand
                }
                hand = new ArrayList<Card>(p.getHand()); 
            }
            else if(again.equals("n") || again.equals("N")){
                playAgain=false;
            }
        }
        System.out.println("--------------------------------");
        System.out.println("Bye! Thanks for playing!!"); 
        int tokens=(int)p.getBankroll(); //to keep code <80 lines
        System.out.println("Your final balance is: "+tokens+" tokens");
        System.out.println("--------------------------------");
    }
	
	public String checkHand(ArrayList<Card> hand){
		// this method should take an ArrayList of cards as 
		// input and then determine what it evaluates to and
		// return that as a String
		hand = sortedHand(hand);
        if (isRoyalFlush(hand)){
            payoff=250;
            return "Royal Flush";
        }
        else if (isStraightFlush(hand)){
            payoff=25;
            return "Straight Flush";
        }
        else if (isFourOfAKind(hand)){
            payoff=25;
            return "Four of a Kind";
        }
        else if (isFullHouse(hand)){
            payoff=6;
            return "Full House";
        }
        else if (isFlush(hand)){
            payoff=5;
            return "Flush";
        }
        else if (isStraight(hand)){
            payoff=4;
            return "Straight";
        }
        else if (isThreeOfAKind(hand)){
            payoff=3;
            return "Three of a Kind";
        }
        else if (isTwoPair(hand)){
            payoff=2;
            return "Two Pair";
        }
        else if (isOnePair(hand)==1){
            payoff=1;
            return "One Pair";
        }
        else{
            payoff=0;
            return "No Pair";
        }
	}
	
	// you will likely want many more methods here
	// per discussion in class
	
	//10,J,Q,K,A all of the same suit, payout 250
    private boolean isRoyalFlush(ArrayList<Card> hand){
        if (isStraightFlush(hand) 
           && hand.get(4).getRank()==13
           && hand.get(0).getRank()==1){ //true when there's a high ace
            return true;  
        }
        else{
            return false;
        }
    }
    
    //a straight and a flush
    //5 cards w consecutive values of same suit, payout 25
    private boolean isStraightFlush(ArrayList<Card> hand){
        if(isFlush(hand)
          && isStraight(hand)){
            return true;
        }
        else{
            return false;
        }
    }
    
    //four cards of same value, payout 25
    private boolean isFourOfAKind(ArrayList<Card> hand){
        if (isOnePair(hand)==2 && isThreeOfAKind(hand)){
            if((hand.get(0).getRank()==hand.get(3).getRank())
              ||(hand.get(1).getRank()==hand.get(4).getRank())){
                return true;
            }
        }
        return false;
    }
   
    //three of a kind and a pair, payout 6
    private boolean isFullHouse(ArrayList<Card> hand){
        if (isOnePair(hand)==2 && isThreeOfAKind(hand) && 
            !(isFourOfAKind(hand))){
            return true;
        }
        else{
            return false;
        }
    }
    
    //five cards of same suit, payout 5
    private boolean isFlush(ArrayList<Card> hand){
        if(isOnePair(hand)>0){
            //if there's at least one pair
            //they cannot be of same suit
            return false;
        }
        else{ //no pairs
            int i=1;
            for(Card value:hand){
                //if there's a suit that isn't the same
                if (value.getSuit()!=hand.get(i).getSuit()){
                    return false;
                } 
                if (i<hand.size()-1){
                    i++;
                }
            }
            return true;
        }
    }
    
    //5 cards of consecutive value, payout 4
    private boolean isStraight(ArrayList<Card> hand){
        Card card1= hand.get(0);
        Card card5= hand.get(4);
        Card card2=hand.get(1);
       
        if(isOnePair(hand)>0){
            return false;
        }
        else if(card5.getRank()-card1.getRank()==4){
            return true;
        }
        else if(card1.getRank()==1
              &&card2.getRank()==10
               &&card5.getRank()==13){
                return true;
            }
        else{
            return false;
        }
    }
    
    //3 cards of same value, payout 3
    private boolean isThreeOfAKind(ArrayList<Card> hand){
        if(isOnePair(hand)==0){
            return false;
        }
        //bc they're sorted, all 3 cards are same value
        else if((hand.get(0).getRank()==hand.get(2).getRank()) 
                ||(hand.get(1).getRank()==hand.get(3).getRank())
               || (hand.get(2).getRank()==hand.get(4).getRank())){
            return true;
        }
        else{
            return false;
        }
    }
    
     //two pairs, payout 2
    private boolean isTwoPair(ArrayList<Card> hand){
        if(isOnePair(hand)>=2){
            return true;
        }
        else {
            return false;
        }
    }
    
    //two cards of same value, payout 1
    private int isOnePair(ArrayList<Card> hand){
        int pairsCount=0;
        int i=0;
        while(i<hand.size()-1){
            if (hand.get(i).getRank()==hand.get(i+1).getRank()){//same rank
                i++;
                pairsCount++;
            }
            i++;
        }
        return pairsCount;
    }

    public ArrayList<Card> sortedHand(ArrayList<Card> hand){
        Collections.sort(hand);
        return hand;
    }
    
    //for the argument version(#2) of the game
    public void argsHand(String[] testHand){
        Card c;
        char suitChar;
        int suit;
        int rank;
        String valueString;
        for(int i=0;i<5;i++){
            //in args, suit is entered first
            suitChar=testHand[i].charAt(0);
            valueString=testHand[i].substring(1);
            
            rank=Integer.parseInt(valueString);
            
            if(suitChar=='c'){ //clubs
                suit=1;
            }
            else if(suitChar=='d'){ //diamonds
                suit=2;
            }
            else if(suitChar=='h'){ //hearts
                suit=3;
            }
            else{ //spades
                suit=4;
            }
            c=new Card(suit,rank);
            p.addCard(c);
        }  
    }
}