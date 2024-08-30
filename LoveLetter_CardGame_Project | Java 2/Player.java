public class Player {
	private String name;
	private CardPile hand;
	private CardPile discardPile;
	private int favorTokens;
	private int targetTokens; 
	private boolean playerStatus; //true = player active in the round, false = player has been knocked out of the round
	private boolean playedSpy; //true = player played a spy card during the round, false = hasn't
	private boolean handmaidProtected;
	private boolean hasPrincess;
	
	//Constructor
	public Player(String name, int newTargetTokens){
		this.name = name;
		this.hand = new CardPile();
		this.discardPile = new CardPile();
		this.favorTokens = 0;
		this.targetTokens = newTargetTokens;
		this.playerStatus = true;
		this.playedSpy = false;
		this.handmaidProtected = false;
		this.hasPrincess = false;
	}
	
	//Getters
	public String getName(){
		return this.name;
	}
	
	public CardPile getHand(){
		return this.hand;
	}
	
	public CardPile getDiscardPile(){ //not tested
		return this.discardPile;
	}
	
	public int getFavorTokens(){
		return this.favorTokens;
	}
	
	public int getTargetTokens(){
		return this.targetTokens;
	}
	
	public boolean getPlayerStatus(){
		return this.playerStatus;
	}
	
	public boolean getPlayedSpy(){
		return this.playedSpy;
	}
	
	public boolean getHandmaidProtected(){
		return this.handmaidProtected;
	}
	
	public boolean getHasPrincess(){
		return this.hasPrincess;
	}

	public Card getDrawnCard(){
		Card drawnCard = this.hand.seeTopCard();
		return drawnCard;
	}
	
	public Card getCardAtIndex(int index){
		Card card = this.hand.getCardAtIndex(index);
		return card;
	}
	
	//Setters
	public void setPlayerStatus(boolean newStatus){
		this.playerStatus = newStatus;
	}
	
	public void setPlayedSpy(){
		this.playedSpy = true;
	}
	
	public void setHandmaidProtected(boolean newProtectionStatus){
		this.handmaidProtected = newProtectionStatus;
	}
	
	public void setHasPrincess(boolean newPrincessStatus){
		this.hasPrincess = newPrincessStatus;
	}
	
	//Other Methods
	public void drawCard(Deck deck){
		Card card = deck.getTopCard();
		this.hand.add(card);
	}
	
	public void discardCard(int index){
		Card discardedCard = this.hand.removeAtIndex(index-1);
		this.discardPile.add(discardedCard);
	}
	
	public void addFavorToken() {
		this.favorTokens++;
	}
	
	public boolean hasPlayerWon(){
		boolean hasPlayerWon = false;
		if (this.favorTokens >= this.targetTokens){
			hasPlayerWon = true;
		}
		return hasPlayerWon;
	}
	
	public void printHand(){
		System.out.println(this.name + "'s hand:\n" + hand);
	}
	
	public void printDiscardPile(){ //not tested
		System.out.println(this.name + "'s discarded cards:\n" + discardPile);
	}
	
	public void sortHand(){
		this.hand.sort();
	}
	
	public void reset(){
		this.hand = new CardPile();
		this.discardPile = new CardPile();
		this.playerStatus = true;
		this.playedSpy = false;
		this.handmaidProtected = false;
		this.hasPrincess = false;
	}
	
	public int getHighestCardValue(){
		Card highestCard = this.hand.getHighestCard();
		return highestCard.getCardValue();
	}
	
	public Card getHighestCard(){
		return this.hand.getHighestCard();
	}
	
	public boolean hasCard(Card card){
		boolean hasCard = this.hand.contains(card);
		return hasCard;
		
	}
	
	public Card specialDiscardPrince(){
		Card discardedCard = this.hand.princeDiscard();
		return discardedCard;
	}
	
	public void addCard(Card card){
		this.hand.add(card);
	}
	
	public Card removeButNotDiscard(int index){
		Card removedCard = this.hand.removeAtIndex(index-1);
		return removedCard;
	}
	
}