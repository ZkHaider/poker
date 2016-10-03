package PJ4;
import java.util.*;

/*
 * Ref: http://en.wikipedia.org/wiki/Video_poker
 *      http://www.freeslots.com/poker.htm
 *
 * Short Description and Poker rules:
 *
 * Video poker is also known as draw poker. 
 * The dealer uses a 52-card deck, which is played fresh after each playerHand. 
 * The player is dealt one five-card poker playerHand. 
 * After the first draw, which is automatic, you may hold any of the cards and draw 
 * again to replace the cards that you haven't chosen to hold. 
 * Your cards are compared to a table of winning combinations. 
 * The object is to get the best possible combination so that you earn the highest 
 * payout on the bet you placed. 
 *
 * Winning Combinations
 *  
 * 1. Jacks or Better: a pair pays out only if the cards in the pair are Jacks, 
 * 	Queens, Kings, or Aces. Lower pairs do not pay out. 
 * 2. Two Pair: two sets of pairs of the same card denomination. 
 * 3. Three of a Kind: three cards of the same denomination. 
 * 4. Straight: five consecutive denomination cards of different suit. 
 * 5. Flush: five non-consecutive denomination cards of the same suit. 
 * 6. Full House: a set of three cards of the same denomination plus 
 * 	a set of two cards of the same denomination. 
 * 7. Four of a kind: four cards of the same denomination. 
 * 8. Straight Flush: five consecutive denomination cards of the same suit. 
 * 9. Royal Flush: five consecutive denomination cards of the same suit, 
 * 	starting from 10 and ending with an ace
 *
 */


/* This is the main poker game class.
 * It uses Decks and Card objects to implement poker game.
 * Please do not modify any data fields or defined methods
 * You may add new data fields and methods
 * Note: You must implement defined methods
 */


public class VideoPoker {

    // default constant values
    private static final int startingBalance=100;
    private static final int numberOfCards=5;

    // default constant payout value and playerHand types
    private static final int[] multipliers={1,2,3,5,6,9,25,50,250};
    private static final String[] goodHandTypes={ 
	  "Royal Pair" , "Two Pairs" , "Three of a Kind", "Straight", "Flush	", 
	  "Full House", "Four of a Kind", "Straight Flush", "Royal Flush" };

    // must use only one deck
    private static final Decks oneDeck = new Decks(1);

    // holding current poker 5-card hand, balance, bet    
    private List<Card> playerHand;
    private int playerBalance;
    private int playerBet;

    /** default constructor, set balance = startingBalance */
    public VideoPoker()
    {
	this(startingBalance);
    }

    /** constructor, set given balance */
    public VideoPoker(int balance)
    {
	this.playerBalance= balance;
    }

    /** This display the payout table based on multipliers and goodHandTypes arrays */
    private void showPayoutTable()
    { 
	System.out.println("\n\n");
	System.out.println("Payout Table   	      Multiplier   ");
	System.out.println("=======================================");
	int size = multipliers.length;
	for (int i=size-1; i >= 0; i--) {
		System.out.println(goodHandTypes[i]+"\t|\t"+multipliers[i]);
	}
	System.out.println("\n\n");
    }

    /** Check current playerHand using multipliers and goodHandTypes arrays
     *  Must print yourHandType (default is "Sorry, you lost") at the end of function.
     *  This can be checked by testCheckHands() and main() method.
     */
    private void checkHands()
    {
    	
	// implement this method!
    	int result = 0;
        String ranks;

        if (isRoyalPair() == true) {
            result = 1;
        }  if (isTwoPair() == true) {
        	result = 2;
        }
        if (isThreeOfAKind() == true) {
        	result = 3;
        }

        if (isStraight() == true) {
        	result = 4;
        }
        if (isFlush() == true) {
        	result = 5;
        }
        if (isFullHouse() == true) {
        	result = 6;
        }
        if (isFourOfAKind() == true) {
        	result = 7;
        }
        if (isStraightFlush() == true) {
        	result = 8;
        }
        if (isRoyalFlush() == true) {
        	result = 9;
        }


        result -= 1;
        if (result < 0) {
            ranks = "Sorry, you lost!";
        } else {
            ranks = goodHandTypes[result];
        }

        System.out.println("" + ranks);


        switch (ranks) {
            case "Royal Pair":
                this.playerBalance += (this.playerBet * multipliers[0]);
                break;
            case "Two Pairs":
                this.playerBalance += (this.playerBet * multipliers[1]);
                break;
            case "Three of a Kind":
                this.playerBalance += (this.playerBet * multipliers[2]);
                break;
            case "Straight":
                this.playerBalance += (this.playerBet * multipliers[3]);
                break;
            case "Flush":
                this.playerBalance += (this.playerBet * multipliers[4]);
                break;
            case "Full House":
                this.playerBalance += (this.playerBet * multipliers[5]);
                break;
            case "Four of a Kind":
                this.playerBalance += (this.playerBet * multipliers[6]);
                break;
            case "Straight Flush":
                this.playerBalance += (this.playerBet * multipliers[7]);
                break;
            case "Royal Flush":
                this.playerBalance += (this.playerBet * multipliers[8]);
                break;
            default:
                break;
        }

    
    }
    


    /*************************************************
     *   add other private methods here ....
     *
     *************************************************/
    	  private int[] countRank() {
    			int[] frequency = new int[14];
    			ListIterator playerCards = playerHand.listIterator();
    			while (playerCards.hasNext()) {
    				frequency[((Card) playerCards.next()).getRank()]++;
    			} 
    			return frequency;
    		}
    
    	  private int[] countSuit() {
    			int[] frequency = new int[5];
    			ListIterator playerCards = playerHand.listIterator();
    			while (playerCards.hasNext()) {
    				frequency[((Card) playerCards.next()).getSuit()]++;
    			}
    			return frequency;
    		}
   
    private boolean isStraight() {
    	int[] checkrank = countRank();
		
		for (int i = 1; i < 10; i++) {
			if (checkrank[i] == 1 && checkrank[i + 1] == 1 && checkrank[i + 2] == 1
					&& checkrank[i + 3] == 1 && checkrank[i + 4] == 1) {
				return true;
			} 
		} 
		return false;
	    
    }
    
    private boolean isFlush() {
    	int[] checksuit = countSuit();
		
		for (int i = 1; i <= 4; i++) {
			if (checksuit[i] == 5) {
				return true;
			} 
		} 
		return false;
	}
      
    
    private boolean isStraightFlush() {
        if (isStraight() == true && isFlush() == true) {
            return true;
        }
        return false;
    }

    private boolean isRoyalFlush() {
    	int[] check = countRank();
		
		if (isFlush()) {
			if (check[1] == 1 && check[10] == 1 && check[11] == 1
					&& check[12] == 1 && check[13] == 1) {
				return true;
			} 
		} 
		return false;
	}

    private boolean isFourOfAKind() {
    	for(int i = 0; i < 13; i++){
    	int counter = 0;	
    	for(int y = i + 1; y < numberOfCards; y++){
    		if (playerHand.get(i).getRank() == playerHand.get(y).getRank())	
    		{
    			counter++;
 	    
    		}
    	}
    		if(counter == 3)
    		{
    			return true;
    		}
   
    	}
    	
    		
     return false;
    } 		
     
    		
   private boolean isFullHouse() {
        if (isThreeOfAKind() == true && isOnePair() == true) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isThreeOfAKind() {
    	for(int i = 0; i < 14; i++)
    		{
    		int counter =0;
          for(int y = 0; y < numberOfCards; y++){
    		if (playerHand.get(y).getRank() == i)
    		 {
    		counter++;
    		 }
          }                       
    		if(counter == 3)
    		{
    	 return true;
    	   }
    		 }
    		
    return false;
    }

    private boolean isTwoPair() {
        
        if (isFourOfAKind() == true) {
            return false;
        }
        int pair = 0;
        int sum = 0;

        for(int j = 0; j < 5; j++) {
            for(int k = 0; k < 5; k++) {
                if(playerHand.get(j).getRank()
                        == playerHand.get(k).getRank()) {
                    pair++;
                }
            }
            if(pair > 1) {
                sum++;
            }
            pair = 0;
        }
        if(sum == 4) 
        {
        	 return true;
        	}
        	
        	return false;
        }
    

    private boolean isOnePair() {
    	 
        
    	 for (int i = 1; i < 14; i++) {
             int Count = 0;
             for (int y = 0; y < 5; y++) {
                 if (playerHand.get(y).getRank() == i) {
                     Count++;
                 }
             }
             if (Count == 2) {
                 return true;
             }
         }
         return false;
    }

    private boolean isRoyalPair() {
    	for (int i = 0; i < 14; i++){
    		int count =0;
    		for(int j = 0; j < 5; j++) {
                if((playerHand.get(j).getRank() == 1 || playerHand.get(j).getRank() > 10) && playerHand.get(j).getRank() ==i) {
                	count++;
                }
            }
            if(count == 2) {
                return true;
            }
        }
    	    return false;
    	  }
    

    public void play() 
    {
    /** The main algorithm for single player poker game 
     *
     * Steps:
     * 		showPayoutTable()
     *
     * 		++	
     * 		show balance, get bet 
     *		verify bet value, update balance
     *		reset deck, shuffle deck, 
     *		deal cards and display cards
     *		ask for positions of cards to keep  
     *          get positions in one input line
     *		update cards
     *		check hands, display proper messages
     *		update balance if there is a payout
     *		if balance = O:
     *			end of program 
     *		else
     *			ask if the player wants to play a new game
     *			if the answer is "no" : end of program
     *			else : showPayoutTable() if user wants to see it
     *			goto ++
     */


        // implement this method!
    	showPayoutTable();
    	boolean game = true;
    	while(game){
    	
    	Scanner keepnumbers = new Scanner(System.in);
    	Scanner position = new Scanner(System.in);

    	//String answer;
    	String cardstokeep;
    	
    	int currentposition = 0;
    	int[] keepArray = new int[5];

    	System.out.println("Balance: $" + playerBalance);
    	System.out.print("Enter Bet: ");
    	Scanner user = new Scanner(System.in);
    	playerBet = user.nextInt();
    	while(playerBet > playerBalance)
    	 {
    	 System.out.println("insufficient funds!");
    	System.out.println("Enter bet");
    	playerBet = user.nextInt();
    	 }
    	 				
    	 			
    	 playerBalance -= playerBet;
    	 oneDeck.reset();
    	 oneDeck.shuffle();
    	try {
    	            	playerHand = oneDeck.deal(numberOfCards);
    	            } catch (PlayingCardException e) {
    	                System.out.println("Not enough cards to deal");
    	            }
    	System.out.println("Hand: " + playerHand);
    	System.out.print("Enter positions of cards to keep (e.g. 1 4 5 ):");
    	user = new Scanner(System.in);
    	cardstokeep=user.nextLine();
    	 Scanner numbers = new Scanner(cardstokeep);
    	while (numbers.hasNextInt()) {
    		currentposition = numbers.nextInt();
    	                if ((currentposition > 5 || currentposition < 0)) {
    	                    System.out.print("Invalid position, Enter correct positions of cards to keep: ");
    	                    user = new Scanner(System.in);
    	                    cardstokeep = user.nextLine();
    	                    numbers = new Scanner(cardstokeep);
    	                    keepArray = new int[5];
    	                }
    	                for (int i = 0; i < 5; i++) {
    	                    if (currentposition - 1 == i) {
    	                    	keepArray[i] = 1;
    	                    }
    	                }
    	            }
    	for (int i = 0; i < 5; i++) {
    	                if (keepArray[i] == 0) {
   	                    try {
   	                        List<Card> tempCard = oneDeck.deal(1);
   	                        playerHand.set(i, tempCard.get(0));
    	                    } catch (PlayingCardException PCE) {
    	                        System.out.println(PCE.getMessage());
   	                    }
  	                }
  	            }
    	System.out.println("Hand: " + playerHand);
    	            checkHands();
    	            System.out.println();

    	            if (playerBalance == 0) {
    	                System.out.println("Your balance is 0");
    	                System.out.println("Bye!");
    	                game = false;
    	                break;
    	            }
    	            
    	System.out.println();
    	System.out.print("Your balance:$" + this.playerBalance + ", one more game (y or n)?");
    	
                    	 Scanner input = new Scanner(System.in);
    					
    					char wantToPlay = input.next().charAt(0);
    					
    					if(wantToPlay == 'y'){
    						System.out.print("Want to see payout table (y or n)");
    						 char answer = input.next().charAt(0);
    						
    						if(answer == 'y') {
    							showPayoutTable();
    						}
    						game = true;
    						
    					} else if(wantToPlay == 'n') {
    						System.out.println("Thanks for playing!");
    						game = false;
    					}
    					
    			} 
 		
 		   }
        
 			


    /*************************************************
     *   Do not modify methods below
    /*************************************************


    /** testCheckHands() is used to test checkHands() method 
     *  checkHands() should print your current hand type
     */ 

    private void testCheckHands()
    {
      	try {
    		playerHand = new ArrayList<Card>();

		// set Royal Flush
		playerHand.add(new Card(4,1));
		playerHand.add(new Card(4,10));
		playerHand.add(new Card(4,12));
		playerHand.add(new Card(4,11));
		playerHand.add(new Card(4,13));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Straight Flush
		playerHand.set(0,new Card(4,9));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Straight
		playerHand.set(4, new Card(2,8));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Flush 
		playerHand.set(4, new Card(4,5));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// "Royal Pair" , "Two Pairs" , "Three of a Kind", "Straight", "Flush	", 
	 	// "Full House", "Four of a Kind", "Straight Flush", "Royal Flush" };

		// set Four of a Kind
		playerHand.clear();
		playerHand.add(new Card(4,8));
		playerHand.add(new Card(1,8));
		playerHand.add(new Card(4,12));
		playerHand.add(new Card(2,8));
		playerHand.add(new Card(3,8));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Three of a Kind
		playerHand.set(4, new Card(4,11));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Full House
		playerHand.set(2, new Card(2,11));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Two Pairs
		playerHand.set(1, new Card(2,9));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Royal Pair
		playerHand.set(0, new Card(2,3));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// non Royal Pair
		playerHand.set(2, new Card(4,3));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");
      	}
      	catch (Exception e)
      	{
		System.out.println(e.getMessage());
      	}
    }


    /* Run testCheckHands() */
    public static void main(String args[]) 
    {
	VideoPoker pokergame = new VideoPoker();
	pokergame.testCheckHands();
    }
}

