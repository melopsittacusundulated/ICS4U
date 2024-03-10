import java.util.*; //import java.util package

public class CardGame {
/*
 * Author: Dorothy Lin
 * Class: ICS4U
 * Date: 2024.3.6.
 */
	public static void main(String[] args) {

        //this main method instantiates 2d arrays and arraylists and passes them into
        //various methods to simulate a card game, where 2 players are dealt cards
        //and the one with the highest hand value wins.

		//create card deck with 4 rows, 13 columns
        String[][] deck = new String[4][13];

        //pass 2d array to card-filling method
		createDeck(deck); 

        //Create and load 2d array deck to arraylist
        ArrayList<String> cards = new ArrayList<String>(); //instantiate arraylist
        loadList(cards, deck); //load deck into cards list from 2d array

        //shuffle cards
        shuffle(cards);

        //instantiate arraylists for each players' hand
        ArrayList<String> playerOne = new ArrayList<String>(); //player one's hand
        ArrayList<String> playerTwo = new ArrayList<String>(); //player two's hand

        //deal cards to both players
        deal(cards, playerOne); //deal cards to player one
        deal(cards, playerTwo); //deal cards to player two

        //display decks
        System.out.println("\nPlayer One's Deck\n" + playerOne);
        System.out.println("Player Two's Deck\n" + playerTwo);

        //calculate points for each player, create variable for points received
        int p1 = calcPoints(playerOne);
        int p2 = calcPoints(playerTwo);

        //compare points and announce winner
        if (p1 > p2) {
            System.out.println("\nPlayer One has WON! They had " + p1 + " points."); 
        } else if (p2 > p1) {
            System.out.println("\nPlayer Two has WON! They had " + p2 + " points.");
        } else {
            System.out.println("\nTIE! Player One and Two had " + p1 + " points.");
        }

	}
	
	public static void createDeck(String[][] deck) {
		
		// This method fills a 4 x 13 2D array that replicates a standard deck of cards.
		
		//nested loop that iterates through each element in the deck and assigns it a
		// suit and rank value.
		for (int r = 0; r < deck.length; r++) {
			for (int c = 0; c < deck[r].length; c++) {
				
				//assign suits
				String suit; //declare suit variable
				if (r == 0) { //if the card is in the first row, it is a diamond.
					suit = "D"; 
				} else if (r == 1) { //if it is in the second row, it is a club.
					suit = "C";
				} else if (r == 2) { //the third row contains hearts.
					suit = "H";
				} else { //the last row must be spades.
					suit = "S";
				}
				
				//assign ranks
				String rank; //declare rank variable
				if (c == 0) { //if the card is in the first column, it must be an ACE
					rank = "A";
				} else if (c == 10) { //if the card is in the 11th column, it is a JACK
					rank = "J";
				} else if (c == 11) { //if the card is in the 12th column, it is a QUEEN
					rank = "Q";
				} else if (c == 12) { //if the card is in the 13th column, it is a KING
					rank = "K";
				} else {
					 // Otherwise, assign the card a number rank based on its column number (2-10)
					//add 1 to index, but convert the int to String so that it can be assigned to String rank.
					rank = String.valueOf(c + 1); //use String.valueOf to convert to from int to String.
				}
				
				//Concatenate the rank and suit string to create a card in the format (suit + rank)
				//ex. king of hearts == "HK", two of spades == "S2"
				deck[r][c] = suit + rank; 
			}
		}
	}
	
	public static void shuffle(ArrayList<String> deck) {
		
		//this method takes an ArrayList card deck and shuffles it.
		
		Random randGen = new Random(); //instantiate new random generator class
		
		//iterate through each element, shuffle for size of array amount of times
		for (int i = 0; i < deck.size(); i++) { 
			int newIndex = randGen.nextInt(52); //generate an index number from 0 to 51
            //put card at random index in temporary variable, swap indexes
			String temp = deck.get(newIndex); 
			deck.set(newIndex, deck.get(i)); 
            deck.set(i, temp);
		}
	}

    public static void loadList(ArrayList<String> cards, String[][] deck) {

        //this method uses a nested loop to access elements from
        //the 2d deck array, to add cards to an arraylist.

        for (int r = 0; r < deck.length; r++) { //iterates through each row
            for (int c = 0; c < deck[r].length; c++) { //iterate through each column for every row
                cards.add(deck[r][c]); //add each card to the arraylist
            }
        }
    }
	
    public static void deal(ArrayList<String> cards, ArrayList<String> playerHand) {

        //this method deals half (26) of a shuffled deck to each player, 
        //assuming there are only 2 players.

        //this loop deals half of the deck to a player
        //decrement deck to avoid index out of bounds error after removing cards.
        for (int i = 25; i >= 0; i--) {  
            playerHand.add(cards.get(i)); //add the card at the respective index to the player's hand
            cards.remove(i); //remove the drawn card from the card array list
        }
    }

    public static int calcPoints(ArrayList<String> playerHand) {

        //this function-type method calculates the number of points a player has received from their hand.

        int points = 0; //instantiate points accumulator 
        for (int i = 0; i < playerHand.size(); i++) { //iterates through every card in hand

            // get rank using substring() method- in other words, the number/letter starting from the String's 2nd character (this includes number 10) index.
            String rank = playerHand.get(i).substring(1);

            //if the rank is a face card (K, Q, J), then add 10 points
            if (rank.equals("K") || rank.equals("Q") || rank.equals("J")) {
                points += 10;
            } else if (rank.equals("A")) { 
                //if it is an ace, add 1
                points += 1;
            } else {
                //otherwise, change the String to an int value and add its respective value to points.
                int number = Integer.valueOf(rank); //utilise Object.valueOf() class once again to convert.
                points += number; //add to points
            }

        }
        return points; //return points to main method
    }
}