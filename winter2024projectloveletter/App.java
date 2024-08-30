import java.util.Random;
import java.util.Scanner;

public class App {
	public static void main (String[] args) {
		//CODE TESTING
		
		/* // Create Card objects for testing
		System.out.println("Testing cards");
        Card princessCard = new Card(CardValue.PRINCESS);
        Card countessCard = new Card(CardValue.COUNTESS);
		Card spyCard = new Card(CardValue.SPY);
        
		System.out.println(princessCard);
		System.out.println(countessCard);
		
		//Testing CardPile/DynamicArray
		System.out.println("Testing CardPile");
		CardPile cards = new CardPile();
		cards.add(princessCard);
		cards.add(countessCard);
		System.out.println("Length: " + cards.length());
		System.out.println(cards.contains(princessCard));
		System.out.println(cards.contains(spyCard));
		
		//Testing Deck class
		Deck deck = new Deck();
		deck.printDeck();
		deck.shuffleDeck();
		deck.printDeck();
		
		//Testing modified removeAtIndex() in Cardile and getTopCard in Deck()
		System.out.println("Test 1");
		Card topCard = deck.getTopCard();
		System.out.println("The top card: "+ topCard);
		deck.printDeck();
		Card topCard2 = deck.getTopCard();
		System.out.println("The second top card: "+ topCard2);
		deck.printDeck();
		
		//Testing drawCard() and printHand() in Player
		System.out.println("Test 2");
		Player player1 = new Player("Artem");
		System.out.println("New player: " + player1.getName());
		player1.drawCard(deck);
		player1.printHand();
		deck.printDeck();
		
		//Testing sorting & all deck other deck methods
		System.out.println("Ultimate deck test!");
		Deck deck = new Deck();
		System.out.println("Just CREATED the deck:");
		deck.printDeck();
		System.out.println("Just SORTED the deck:");
		deck.sortDeck();
		deck.printDeck();
		System.out.println("Just SHUFFLED the deck:");
		deck.shuffleDeck();
		deck.printDeck();
		
		//Testing Player
		System.out.println("Player class test!");
		Player p1 = new Player("Artem");
		System.out.println("New player: " + p1.getName());
		System.out.println(p1.getName() + " draws 2 card");
		p1.drawCard(deck);
		p1.drawCard(deck);
		p1.printHand();
		System.out.println(p1.getName() + " gets a token");
		p1.addFavorToken();
		System.out.println(p1.getName() + " tokens: " + p1.getFavorTokens());
		System.out.println("His target token number: " + p1.getTargetTokens());
		p1.printDiscardPile();
		System.out.println("Now Artem is going to discard the first card");
		p1.discardCard(1);
		p1.printHand();
		p1.printDiscardPile();
		System.out.println("Now Artem is going to discard the second card");
		p1.discardCard(1);
		p1.printHand();
		p1.printDiscardPile();
		
		//Testing methods in the main code (without a loop)
		Player[] players = new Player[4];
		players[0] = new Player("P1");
		players[1] = new Player("P2");
		players[2] = new Player("P3");
		players[3] = new Player("P4");
		Deck deck = new Deck();
		System.out.println("All players draw one card.");
		for (int i = 0; i < players.length; i++){ //Dealing one card to each player
			players[i].drawCard(deck);
		}
		System.out.println("Pass the controls to " + players[0].getName() + " and press 'Enter' to begin."); */
		
		//MAIN CODE
		//Part 1 - Initializing variables
		Scanner scan = new Scanner(System.in);
		int numOfPlayers = 0;
		int targetTokens = 7;
		boolean validInput = false;
		
		//Part 2 - Welcoming and initializing players
		System.out.println("Hi and welcome to Love Letter card game!\n");
		System.out.println("In Love Letter, 2-4 suitors compete to have\ntheir missives delivered to the kingdom's princess,\nwho seeks an ideal partner and confidant for when she assumes the throne.");
		printImage("Castle");
		System.out.println("Press 'Enter' to begin.");
		scan.nextLine();
		System.out.print("\033[H\033[2J");		
		
        while (!validInput) { //Player number input validation loop
			try {
				System.out.println("Enter the number of players (2-4): ");
				numOfPlayers = Integer.parseInt(scan.nextLine());
				if (numOfPlayers >= 2 && numOfPlayers <= 4){
					validInput = true;
					System.out.print("\033[H\033[2J");
				}
				else {
					throw new IllegalArgumentException();
				}
			}
			catch (Exception e){
				System.out.print("\033[H\033[2J");
				System.out.println("Invalid input. Please try again!");
			}
		}
		
		targetTokens = targetTokens - numOfPlayers;
		
		System.out.println("Now players, input your names one by one!"); //Creating player objects one by one
		Player[] players = new Player[numOfPlayers];
		for (int i = 0; i < numOfPlayers; i++){
			System.out.println("Player " + (i+1) + ", enter your name: ");
			String name = scan.nextLine();
			players[i] = new Player(name, targetTokens);
			System.out.print("\033[H\033[2J");
		}
		
		Random rng = new Random(); //Placing player objects in the array in random order (to decide the turn order)
		for (int i = 0; i < players.length; i++) { //Same algorithm as in the shuffle() in CardPile
			int randomIndex = rng.nextInt(players.length);
			Player randomPlayer = players[randomIndex];
			players[randomIndex] = players[i];
			players[i] = randomPlayer;
		}
		System.out.println("The turn order will be randomized! \nPlayers will go in this order: ");
		for (int i = 0; i < numOfPlayers; i++) { //Printing every player's name in the new order
            System.out.println((i + 1) + ") " + players[i].getName());
        }
		
		System.out.println("\nTo win the game, get " + targetTokens + " favor tokens!");
		System.out.println("Press 'Enter' to start the game.");
		scan.nextLine(); 
		System.out.print("\033[H\033[2J");
		
		//Part 3 - Main game loop
		boolean gameOn = true;
		boolean roundOn = true;
		int roundCount = 0;
		while (gameOn){
			roundCount++;
			System.out.println("Round " + roundCount + "\n");
			Deck deck = new Deck();
			deck.sortDeck();
			deck.shuffleDeck();
			System.out.println("Setting one card aside, facedown.");
			Card faceDown = deck.getTopCard();
			System.out.println("All players draw one card.\n");
			for (int i = 0; i < numOfPlayers; i++){ //Dealing one card to each player
				players[i].drawCard(deck);
			}
			System.out.println("Pass the controls to " + players[0].getName() + " and press 'Enter' to begin.");
			scan.nextLine();
			roundOn = true;
			while (roundOn){
				for (int i = 0; i < numOfPlayers; i++) {
					if (players[i].getPlayerStatus()){
						System.out.print("\033[H\033[2J");
						
						if (players[i].getHandmaidProtected()){
							System.out.println("[Warning!] You lost your Handmaid protection.\nOther players can now pick you as their target.\n");
							players[i].setHandmaidProtected(false);
						}
						
						players[i].drawCard(deck);
						Card drawnCard = players[i].getDrawnCard();
						players[i].sortHand();
						
						int optionPicked = 3;
						while (optionPicked == 3 || optionPicked == 4) {
							validInput = false;
							while (!validInput){
								try{
									System.out.println(players[i].getName() + "'s turn!\n");
									System.out.println("You draw: " + drawnCard + "\n");
									players[i].printHand();
									System.out.println("\nExtra options: " + "\n3) Take a look at the reference card" + "\n4) Look on the board");
									
									//checking for the countess & prince/king situation
									Card countessCard = new Card(CardValue.COUNTESS);
									Card kingCard = new Card(CardValue.KING);
									Card princeCard = new Card(CardValue.PRINCE);
									if (players[i].hasCard(countessCard) && ( players[i].hasCard(kingCard) || players[i].hasCard(princeCard))){
										System.out.println("[Warning!] When you have both, the Countess and a King/Prince, you MUST play the Countess.");
										optionPicked = Integer.parseInt(scan.nextLine());
										Card isCountess = players[i].getCardAtIndex(optionPicked);
										if (!(isCountess.getCardValue() == countessCard.getCardValue())){
											throw new IllegalArgumentException();
										}
										else{
											validInput = true;
										}
									}
									else{
										System.out.println("\nSelect one of the options from the above (must be a number): ");
										optionPicked = Integer.parseInt(scan.nextLine());
										if (optionPicked >= 1 && optionPicked <= 4) {
											validInput = true;
										}
										else {
											throw new IllegalArgumentException();
										}
									}
								}
								catch (Exception e) {
									System.out.print("\033[H\033[2J");
									System.out.println("[Invalid input. Please try again!]");
								}
							}
							if (optionPicked == 3) {
								printReferenceCard();
							}
							else if (optionPicked == 4) {
								printBoard(roundCount, deck, players, numOfPlayers);
							}
							else {
								playCard(players[i], optionPicked, players, numOfPlayers, deck, faceDown);
							}
						}
						
						roundOn = isRoundOn(players, deck, numOfPlayers); 
						if (!roundOn) {
							System.out.println("The round ends.\n\nThe facedown card was " + drawnCard + "\n");
							
							//Checking for a spy
							int playersWhoPlayedSpy = 0;
							Player playerWhoPlayedSpy = null;
							for (int j = 0; j < numOfPlayers; j++){
								if (players[j].getPlayedSpy() && players[j].getPlayerStatus()){
									playersWhoPlayedSpy++;
									playerWhoPlayedSpy = players[j];
								}
							}
							if (playersWhoPlayedSpy == 1){
								playerWhoPlayedSpy.addFavorToken();
								System.out.println(playerWhoPlayedSpy.getName() + " is the only player who played a Spy and hasn't been knocked out. Player gets an extra token!\n");
							}
							
							Player roundWinner = null;
							int highestValueCard = 0;
							Card bestCard = null;
							
							for (int j = 0; j < numOfPlayers; j++) {
								if (players[j].getPlayerStatus()) {
									if (players[j].getHighestCardValue() > highestValueCard){
										highestValueCard = players[j].getHighestCardValue();
										roundWinner = players[j];
										bestCard = players[j].getCardAtIndex(1);
									}
									else if (players[j].getHighestCardValue() == highestValueCard) {
										bestCard = players[j].getCardAtIndex(1);
										System.out.println("Draw between " + roundWinner.getName() + " and " + players[j].getName() + ".\nBoth have a " + bestCard + " and recieve a favor token.");
										printImage("Knight");
										roundWinner.addFavorToken();
										players[j].addFavorToken();
										roundWinner = null;
										System.out.println("Press 'Enter' to continue.");
										scan.nextLine();
									}
								}
							}
							if (roundWinner != null) {
								System.out.println(roundWinner.getName() + " has the highest value card, " + bestCard + ". Player wins the round and gets a favor token!");
								roundWinner.addFavorToken();
								printImage("Knight");
								System.out.println("Press 'Enter' to continue.");
								scan.nextLine();
							}
							for (int j = 0; j < numOfPlayers; j++) {
								players[j].reset();
							}
							System.out.print("\033[H\033[2J");
							break;
						}
						System.out.println("Pass the controls to the next player and press 'Enter'.");
						scan.nextLine();
						System.out.println("\033[H\033[2J");
					}
				}
			}
			gameOn = isGameOn(players, numOfPlayers);
			if (!gameOn) {
				Player gameWinner = null;
				for (int i = 0; i < numOfPlayers; i++) {
					if (players[i].hasPlayerWon()) {
						gameWinner = players[i];
					}
				}
				
				System.out.println("Game over! " + gameWinner.getName() + " gets " + gameWinner.getFavorTokens() + " tokens and wins the game!");
				printImage("Win");
				System.out.println("\nPress 'Enter' to exit");
				scan.nextLine();
				break;
			}
		}
	}
	
	//HELPER METHODS
	
	//PRINTERS
	public static void printBoard(int roundCount, Deck deck, Player[] players, int numOfPlayers){
		System.out.print("\033[H\033[2J");
		System.out.println("Round: " + roundCount);
		System.out.println("Tokens to win: " + players[0].getTargetTokens()); 
		System.out.println("Cards remaining in the deck: " + deck.getAmountOfCardsLeft() + "\n");
		System.out.println("------------------------------------------------------------\n");
		for (int i = 0; i < numOfPlayers; i++){
			System.out.println("Player " + (i+1) + ": " + players[i].getName());
			System.out.println("Favor tokens: " + players[i].getFavorTokens());
			if (players[i].getPlayerStatus()) {
				System.out.println("Status: Active");
			}
			else {
				System.out.println("Status: Out of the round");
			}
			if (players[i].getHandmaidProtected()){
				System.out.println("Protected by the Handmaid: yes");
			}
			else{
				System.out.println("Protected by the Handmaid: no");
			}
			System.out.println(players[i].getName() + "'s hand: ??");
			players[i].printDiscardPile();
		}
		System.out.println("------------------------------------------------------------");
		System.out.println("\nPress 'Enter' to go back.");
		Scanner scan = new Scanner(System.in);
		scan.nextLine();
		System.out.print("\033[H\033[2J");
	}
	
	public static void printReferenceCard(){
		System.out.print("\033[H\033[2J");
		System.out.println("REFERENCE CARD\n\nTake a look at all the cards short description\nand how many of each are in the starting deck.\n");
		System.out.println("9-\u001B[33mPrincess\u001B[0m (x1): Out of the round if you play/discard.");
		System.out.println("8-\u001B[35mCountess\u001B[0m (x1): Must play if you have King or Prince.");
		System.out.println("7-\u001B[35mKing\u001B[0m (x1): Trade hands.");
		System.out.println("6-\u001B[34mChancellor\u001B[0m (x2): Draw & return 2 cards.");
		System.out.println("5-\u001B[34mPrince\u001B[0m (x2): Discard a hand & redraw.");
		System.out.println("4-\u001B[34mHandmaid\u001B[0m (x2): Immune to other cards until your next turn.");
		System.out.println("3-\u001B[34mBaron\u001B[0m (x2): Compare Hands.");
		System.out.println("2-\u001B[34mPriest\u001B[0m (x2): Look at a hand.");
		System.out.println("1-\u001B[32mGuard\u001B[0m (x6): Guess a hand.");
		System.out.println("0-\u001B[32mSpy\u001B[0m (x2): Gain favor if no one else plays/discards a Spy.");
		System.out.println("\nPress 'Enter' to go back.");
		Scanner scan = new Scanner(System.in);
		scan.nextLine();
		System.out.print("\033[H\033[2J");
	}
	
	public static void printImage(String image){
		if (image.equals("Castle")){
			System.out.println("\n				                     o                  ");
			System.out.println("                                                 _---|         _ _ _ _ _");
			System.out.println("                                              o   ---|     o   ]-I-I-I-[");
			System.out.println("                             _ _ _ _ _ _  _---|      | _---|    \\ ` ' /");
			System.out.println("                             ]-I-I-I-I-[   ---|      |  ---|    |.   |");
			System.out.println("                              \\ `   '_/       |     / \\    |    | /^\\|");
			System.out.println("                               [*]  __|       ^    / ^ \\   ^    | |*||");
			System.out.println("                               |__   ,|      / \\  /    `\\ / \\   | ===|");
			System.out.println("                            ___| ___ ,|__   /    /=_=_=_=\\   \\  |,  _|");
			System.out.println("                            I_I__I_I__I_I  (====(_________)___|_|____|____");
			System.out.println("                            \\-\\--|-|--/-/  |     I  [ ]__I I_I__|____I_I_|");
			System.out.println("                             |[]      '|   | []  |`__  . [  \\-\\--|-|--/-/");
			System.out.println("                             |.   | |' |___|_____I___|___I___|---------|");
			System.out.println("                            / \\| []   .|_|-|_|-|-|_|-|_|-|_|-| []   [] |");
			System.out.println("                           <===>  |   .|-=-=-=-=-=-=-=-=-=-=-|   |    / \\");
			System.out.println("                           ] []|`   [] ||.|.|.|.|.|.|.|.|.|.||-      <===>");
			System.out.println("                           ] []| ` |   |/////////\\\\\\\\\\\\\\.||__.  | |[] [");
			System.out.println("                           <===>     ' ||||| |   |   | ||||.||  []   <===>");
			System.out.println("                            \\T/  | |-- ||||| | O | O | ||||.|| . |'   \\T/");
			System.out.println("                             |      . _||||| |   |   | ||||.|| |     | |");
			System.out.println("                          ../|' v . | .|||||/____|____\\|||| /|. . | . ./");
			System.out.println("                           |//\\............/...........\\........../../\\\\\\");
		}
		else if (image.equals("Knight")){
			System.out.println("    .");
			System.out.println("   / \\");
			System.out.println("   | |");
			System.out.println("   |.|");
			System.out.println("   |.|");
			System.out.println("   |:|      __");
			System.out.println(" ,_|:|_,   /  )");
			System.out.println("   (Oo    / _I_");
			System.out.println("    +\\ \\ || __|");
			System.out.println("      \\ \\||___|");
			System.out.println("       \\ /.:.\\-\\");
			System.out.println("          |.:. /-----\\");
			System.out.println("          |___|::oOo::|");
			System.out.println("         /   |:<_T_>:|");
			System.out.println("        |_____\\ ::: /");
			System.out.println("         | |  \\ \\:/");
			System.out.println("         | |   | |");
			System.out.println("         \\ /   | \\___");
			System.out.println("         / |   \\_____\\ ");
			System.out.println("         `-'");
		}
		else if (image.equals("Win")){
			System.out.println("                                              (\\/)");
			System.out.println("                                               \\/");
			System.out.println("");
			System.out.println("                                        (,);");
			System.out.println("                                       ((  ^_.  ...");
			System.out.println("                                        ' / /_\\(()))");
			System.out.println("                                          L( '}{' ())");
			System.out.println("                                          ) (   )_ (()");
			System.out.println("                                        (_   \\ (   (_)");
			System.out.println("                                        | (__'__\\_) |");
			System.out.println("                                         \\___|_(}==/ \\");
			System.out.println("                                         |    |  |    |");
			System.out.println("                                         |_\\/\\_|  |    |");
			System.out.println("                                          |  |   |    |");
			System.out.println("                                           ) )\\  |    |");
			System.out.println("                                         _/| |/  |    \\");
			System.out.println("                                        ( ,\\ |_  '~~~~/7");
			System.out.println("                                         \\_(___)  _/Y");

		}
	}
	
	public static boolean isGameOn(Player[] players, int numOfPlayers){
		boolean gameOn = true; 
		for (int i = 0; i < numOfPlayers; i++){
			if (players[i].hasPlayerWon()){
				gameOn = false;
			}
		}
		return gameOn;
	}
	
	public static boolean isRoundOn(Player[] players, Deck deck, int numOfPlayers){
		boolean roundOn = true;
		int activePlayers = 0;
		for (int i = 0; i < numOfPlayers; i++){
			if (players[i].getPlayerStatus()){
				activePlayers++;
			}
		}
		
		if (deck.getAmountOfCardsLeft() == 0){
			System.out.print("\033[H\033[2J");
			System.out.println("The deck is out of cards!");
			roundOn = false;
		}
		else if (activePlayers == 1) {
			System.out.print("\033[H\033[2J");
			System.out.println("Only one player remains!");
			roundOn = false;
		}
		
		return roundOn;
	}
	
	public static void playCard(Player currentPlayer, int cardIndex, Player[] players, int numOfPlayers, Deck deck, Card faceDown){
		System.out.print("\033[H\033[2J");
		Card pickedCard = currentPlayer.getCardAtIndex(cardIndex);
		System.out.println("Picked card: " + pickedCard + "\n");
		
		//checking if before any card is played, any player has a Princess in the hand
		Card princessCard = new Card(CardValue.PRINCESS);
		for (int i = 0; i < numOfPlayers; i++){
			if (players[i].getPlayerStatus() && players[i].hasCard(princessCard)){
				players[i].setHasPrincess(true);
			}
		}
		//
		
		if (pickedCard.getCardValue() == CardValue.SPY.getValue()){
			playSpy(currentPlayer);
			currentPlayer.discardCard(cardIndex);
		}
		else if (pickedCard.getCardValue() == CardValue.GUARD.getValue()){
			playGuard(currentPlayer, players, numOfPlayers); 
			currentPlayer.discardCard(cardIndex);
		}
		else if (pickedCard.getCardValue() == CardValue.PRIEST.getValue()){
			playPriest(currentPlayer, players, numOfPlayers);
			currentPlayer.discardCard(cardIndex);
		}
		else if (pickedCard.getCardValue() == CardValue.BARON.getValue()){
			playBaron(currentPlayer, players, numOfPlayers);
			currentPlayer.discardCard(cardIndex);
		}
		else if (pickedCard.getCardValue() == CardValue.HANDMAID.getValue()){
			playHandmaid(currentPlayer, players, numOfPlayers);
			currentPlayer.discardCard(cardIndex);
		}
		else if (pickedCard.getCardValue() == CardValue.PRINCE.getValue()){
			playPrince(currentPlayer, players, numOfPlayers, deck, faceDown);
			currentPlayer.discardCard(cardIndex);
		}
		else if (pickedCard.getCardValue() == CardValue.CHANCELLOR.getValue()){
			playChancellor(currentPlayer, deck, cardIndex);
		}
		else if (pickedCard.getCardValue() == CardValue.KING.getValue()){
			playKing(currentPlayer, players, numOfPlayers, cardIndex);
		}
		else if (pickedCard.getCardValue() == CardValue.COUNTESS.getValue()){
			playCountess();
			currentPlayer.discardCard(cardIndex);
		}
		else if (pickedCard.getCardValue() == CardValue.PRINCESS.getValue()){
			playPrincess();
			currentPlayer.discardCard(cardIndex);
			currentPlayer.setPlayerStatus(false);
		}
		
		//checking if the player that had princess discarded her due to any other card effects, if yes - they're out of round
		for (int i = 0; i < numOfPlayers; i++){
			if (players[i].getHasPrincess()){
				if ( !(players[i].hasCard(princessCard)) ){
					System.out.println(players[i].getName() + " discarded princess and is out of the round!");
					players[i].setHasPrincess(false);
					players[i].setPlayerStatus(false);
				}
			}
		}
	}
	
	public static void playSpy(Player currentPlayer){
		System.out.println("A Spy has no effect when played or discarded.");
		System.out.println("At the end of the round, if you are\nthe only player still in the round who played or\ndiscarded a Spy, you gain one favor token.\n");
		currentPlayer.setPlayedSpy();
	}
	
	public static void playGuard(Player currentPlayer, Player[] players, int numOfPlayers){
		Scanner scan = new Scanner(System.in);
		System.out.println("Choose another player and name a character other than Guard.\nIf the chosen player has that card in their hand, they are out of the round.\n");
		
		Player targetPlayer = selectATarget(currentPlayer, players, numOfPlayers);
		if (targetPlayer != null){
			Card[] cardOptions = {
			new Card(CardValue.SPY),
			new Card(CardValue.PRIEST),
			new Card(CardValue.BARON),
			new Card(CardValue.HANDMAID),
			new Card(CardValue.PRINCE),
			new Card(CardValue.CHANCELLOR),
			new Card(CardValue.KING),
			new Card(CardValue.COUNTESS),
			new Card(CardValue.PRINCESS)
			};
			
			System.out.println("Now, select a card to guess: ");
			for (int i = 0; i < cardOptions.length; i++) {
				System.out.println((i + 1) + ") " + cardOptions[i]);
			}
			
			int cardIndex = 0;
			boolean validInput = false;
			while (!validInput){
				try{
					System.out.println("(Enter a number between 1 and 9)");
					cardIndex = Integer.parseInt(scan.nextLine()) - 1;
					if (cardIndex >= 0 && cardIndex < 10){
						validInput = true;
					}
					else {
						throw new IllegalArgumentException();
					}
				}
				catch (Exception e) {
					System.out.println("Invalid input. Please try again!");
				}
			}
			
			System.out.print("\033[H\033[2J");
			Card cardToGuess = cardOptions[cardIndex];
			if (targetPlayer.hasCard(cardToGuess)){
				System.out.println("Guessed correctly! " + targetPlayer.getName() + " has a " + cardToGuess + "\nPlayer is out of the round.\n");
				boolean newStatus = false;
				targetPlayer.setPlayerStatus(newStatus);
			}
			else{
				System.out.println("Wrong guess! " + targetPlayer.getName() + " does not have a " + cardToGuess);
			}
		}
		else{
			System.out.println("Guard is played without any effect.\n");
		}
	}
	
	public static void playBaron(Player currentPlayer, Player[] players, int numOfPlayers){
		Scanner scan = new Scanner(System.in);
		System.out.println("Choose another player.\nYou and that player secretly compare your hands.\nWhoever has the lower-value card is out of the round.\nIf there is a tie, neither player is out of the round.\n");
		
		Player targetPlayer = selectATarget(currentPlayer, players, numOfPlayers);
		if (targetPlayer != null){
			int currentPlayerHighestValue = currentPlayer.getHighestCardValue();
			int targetPlayerHighestValue = targetPlayer.getHighestCardValue();
			
			System.out.println("Let's compare the cards!");
			System.out.println(currentPlayer.getName() + ": " + currentPlayer.getHighestCard());
			System.out.println(targetPlayer.getName() + ": " + targetPlayer.getHighestCard() + "\n");
			
			if (currentPlayerHighestValue > targetPlayerHighestValue){
				System.out.println(currentPlayer.getName() + " wins! " + targetPlayer.getName() + " is out of round.");
				targetPlayer.setPlayerStatus(false);
			}
			else if (currentPlayerHighestValue < targetPlayerHighestValue){
				System.out.println(targetPlayer.getName() + " wins! " + currentPlayer.getName() + " is out of round.");
				currentPlayer.setPlayerStatus(false);
			}
			else{
				System.out.println("It's a tie! Nobody is out of the round.");
			}
		}
		else{
			System.out.println("Baron is played without any effect.\n");
		}
	}
	
	public static void playPriest(Player currentPlayer, Player[] players, int numOfPlayers){
		Scanner scan = new Scanner(System.in);
		System.out.println("Choose another player and secretly look at their hand\n(without revealing it to anyone else).\n");
		Player targetPlayer = selectATarget(currentPlayer, players, numOfPlayers);
		if (targetPlayer != null){
			targetPlayer.printHand();
		}
		else{
			System.out.println("Priest is played without its effect.\n");
		}
	}
	
	public static void playHandmaid(Player currentPlayer, Player[] players, int numOfPlayers){
		int eligiblePlayersToPick = 0; //testing how many players already have handmaid protection
		for (int i = 0; i < numOfPlayers; i++){ //if only one player (who's turn it is currently) is unprotected, we reset all the protections
			if ( !(players[i].getHandmaidProtected() && players[i].getPlayerStatus()) ){
				eligiblePlayersToPick++;
			}
		}
		
		if (eligiblePlayersToPick == 1){
			System.out.println("All other players are currenlty already protected by the Handmaid.\nYour Handmaid removes their protection and gives one to you!\n");
			for (int i = 0; i < numOfPlayers; i++){
				players[i].setHandmaidProtected(false);
			}
		}
		
		System.out.println("Until the start of your next turn, other players cannot choose you for their card effects.");
		currentPlayer.setHandmaidProtected(true);
	}
	
	public static void playPrince(Player currentPlayer, Player[] players, int numOfPlayers, Deck deck, Card faceDown){
		Scanner scan = new Scanner(System.in);
		boolean validInput = false;
		int targetChoice = 0;
		Card discardedCard = null;
		
		System.out.println("Choose any player (including yourself).\nThat player discards their hand (without resolving its effect) and draws a new hand.\nIf the deck is empty, the chosen player draws the facedown set-aside card.\n");
		
		while(!validInput){
			try{
				System.out.println("Would you like to play the Prince on:\n1) Yourself\n2) Another player\n");
				targetChoice = Integer.parseInt(scan.nextLine());
				
				if (targetChoice == 1 || targetChoice == 2){
					validInput = true;
				}
				else{
					throw new IllegalArgumentException();
				}
			}
			catch (Exception e){
				System.out.print("\033[H\033[2J");
				System.out.println("Invalid input. Please try again!\n(Must be a number: 1 or 2.)");
			}
		}
		
		if (targetChoice == 1){
			discardedCard = currentPlayer.specialDiscardPrince();
			if (deck.getAmountOfCardsLeft() == 0){
				currentPlayer.addCard(faceDown);
			}
			else{
				currentPlayer.drawCard(deck);
			}
			System.out.print("\033[H\033[2J");
			System.out.println("You discard " + discardedCard + "and draw " + currentPlayer.getDrawnCard());
		}
		else if (targetChoice == 2){
			Player targetPlayer = selectATarget(currentPlayer, players, numOfPlayers);
			if (targetPlayer != null){
				discardedCard = targetPlayer.specialDiscardPrince();
				if (deck.getAmountOfCardsLeft() == 0){
					targetPlayer.addCard(faceDown);
				}
				else{
					targetPlayer.drawCard(deck);
				}
				System.out.println(targetPlayer.getName() + " discards " + discardedCard + "and draws a new card.");
			}
			else{
				System.out.println("Prince is played without any effect.\n");
			}
		}
		
	}
	
	public static void playChancellor(Player currentPlayer, Deck deck, int cardIndex){
		Scanner scan = new Scanner(System.in);
		int optionPicked = 0;
		currentPlayer.discardCard(cardIndex);
		boolean validInput = false;
		
		System.out.println("Draw two cards from the deck into your hand.\nChoose and keep one of the three cards now in your hand, \nand place the other two facedown on the bottom of the deck in any order.\n");
		System.out.println("\nIf there is only one card in the deck, draw it and return one card.\nIf there are no cards left, this card is played with no effect.\n");
		
		if (deck.getAmountOfCardsLeft() <= 1){
			System.out.println("There are not enough cards left in the deck.\nChancellor is played with no effect.\n");
		}
		else {
			Card card1 = deck.getTopCard();
			Card card2 = deck.getTopCard();
			System.out.println("You draw " + card1 + "and " + card2 + "\n\nChoose which one of three cards you want to keep: ");
			System.out.println("1) " + currentPlayer.getCardAtIndex(1) + "\n2) " + card1 + "\n3) " + card2);
			
			while(!validInput){
				try{
					optionPicked = Integer.parseInt(scan.nextLine());
					
					if (optionPicked > 0 && optionPicked < 4){
						validInput = true;
					}
					else{
						throw new IllegalArgumentException();
					}
				}
				catch (Exception e){
					System.out.println("Invalid input. Please try again!\n(Must be a number between 1 and 3)");
				}
			}
			
			System.out.print("\033[H\033[2J");
			if (optionPicked == 1){
				System.out.println("You chose to keep your " + currentPlayer.getCardAtIndex(1));
				System.out.println(card1 + "and " + card2 + "are shuffled back into the deck.\n");
				deck.addCardBack(card1);
				deck.addCardBack(card2);
				deck.shuffleDeck();
			}
			else if (optionPicked == 2){
				System.out.println("You chose to keep " + card1);
				System.out.println(currentPlayer.getCardAtIndex(1) + "and " + card2 + "are shuffled back into the deck.\n");
				
				Card toPutBack = currentPlayer.removeButNotDiscard(1);
				currentPlayer.addCard(card1);
				deck.addCardBack(toPutBack);
				deck.addCardBack(card2);
				deck.shuffleDeck();
			}
			else if (optionPicked == 3){
				System.out.println("You chose to keep " + card2);
				System.out.println(currentPlayer.getCardAtIndex(1) + "and " + card1 + "are shuffled back into the deck.\n");
				
				Card toPutBack = currentPlayer.removeButNotDiscard(1);
				currentPlayer.addCard(card2);
				deck.addCardBack(toPutBack);
				deck.addCardBack(card1);
				deck.shuffleDeck();
			}
		}
	}
	
	public static void playKing(Player currentPlayer, Player[] players, int numOfPlayers, int cardIndex){
		System.out.println("Choose another player and trade hands with that player.\n");
		
		Player targetPlayer = selectATarget(currentPlayer, players, numOfPlayers);
		currentPlayer.discardCard(cardIndex);
		
		if (targetPlayer != null){
			System.out.print("\033[H\033[2J");
			
			Card currentPlayerCard = currentPlayer.getCardAtIndex(1);
			Card targetPlayerCard = targetPlayer.getCardAtIndex(1);
			
			System.out.println(currentPlayer.getName() + " and " + targetPlayer.getName() + " switch hands!");
			System.out.println("What you gave: " + currentPlayerCard + "\nWhat you recieved: " + targetPlayerCard + "\n");
			
			Card temp = currentPlayer.removeButNotDiscard(1);
			temp = targetPlayer.removeButNotDiscard(1);
			currentPlayer.addCard(targetPlayerCard);
			targetPlayer.addCard(currentPlayerCard);
		}
		else{
			System.out.println("King is played without any effect.\n");
		}
	}
	
	public static void playCountess(){
		System.out.println("The Countess has no effect when played or discarded.");
		System.out.println("You MUST play the Countess as the card for your turn\nif either the King or a Prince is the other card in your hand.");
	}
	
	public static void playPrincess(){
		System.out.println("If you either play or discard the Princess for any reason,\nyou are immediately out of the round.");
	}
	
	public static Player selectATarget(Player currentPlayer, Player[] players, int numOfPlayers){
		Scanner scan = new Scanner(System.in);
		int numOfValidTargets = 0;
		Player targetPlayer = null;
		boolean validInput = false;
		int targetIndex = 0;
		
		//Checking how many players are eligible to be a target
		for (int i = 0; i < numOfPlayers; i++){
			if ( !players[i].getName().equals(currentPlayer.getName()) && players[i].getPlayerStatus() && !(players[i].getHandmaidProtected()) ){
				numOfValidTargets++;
			}
		}
		
		if (numOfValidTargets == 0){
			System.out.println("You cannot pick any other players!\n");
		}
		else{
			//Populating the targetOptions array
			Player[] validTargetOptions = new Player[numOfValidTargets];
			int index = 0;
			for (int i = 0; i < numOfPlayers; i++){
				if (!players[i].getName().equals(currentPlayer.getName()) && players[i].getPlayerStatus() && !(players[i].getHandmaidProtected())){
					validTargetOptions[index] = players[i];
					index++;
				}
			}
			
			//Printing the possible targets to the console
			for (int i = 0; i < numOfValidTargets; i++) {
				System.out.println((i + 1) + ") " + validTargetOptions[i].getName());
			}
			
			while (!validInput){
				try{
					System.out.println("Choose a player (must be a number): ");
					targetIndex = Integer.parseInt(scan.nextLine()) - 1;
					if (targetIndex >= 0 && targetIndex < numOfValidTargets){
						validInput = true;
					}
					else {
						throw new IllegalArgumentException();
					}
				}
				catch (Exception e) {
					System.out.println("Invalid input. Please try again!");
				}
			}
			
			targetPlayer = validTargetOptions[targetIndex];
			System.out.print("\033[H\033[2J");
		}
		
		return targetPlayer;
	}
	
}