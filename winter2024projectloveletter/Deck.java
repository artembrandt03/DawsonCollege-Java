public class Deck {
	private CardPile cards;

    public Deck() {
		cards = new CardPile();

		cards.add(new Card(CardValue.PRINCESS));
		cards.add(new Card(CardValue.COUNTESS));
		cards.add(new Card(CardValue.KING));

		for (int i = 0; i < 2; i++) {
			cards.add(new Card(CardValue.CHANCELLOR));
			cards.add(new Card(CardValue.PRINCE));
			cards.add(new Card(CardValue.HANDMAID));
			cards.add(new Card(CardValue.BARON));
			cards.add(new Card(CardValue.PRIEST));
			cards.add(new Card(CardValue.SPY));
		}
		for (int i = 0; i < 6; i++) {
			cards.add(new Card(CardValue.GUARD));
		}
	}
	
	public void printDeck(){
		System.out.println(this.cards);
	}
	
	public int getAmountOfCardsLeft(){
		return this.cards.length();
	}
	
	public void shuffleDeck() {
		this.cards.shuffle();
	}
	
	public void sortDeck() {
		this.cards.sort();
	}
	
	public Card getTopCard() {
        Card topCard = cards.removeAtIndex(cards.length() - 1);
        return topCard;
    }
	
	public void addCardBack(Card returnedCard){
		this.cards.add(returnedCard);
	}
}