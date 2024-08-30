public class CardPile {
	private Card[] cards;
	private int next;
	
	//Constructor
	public CardPile() {
        this.cards = new Card[100];
        this.next = 0;
    }
	
	//Getters
	public int length() {
		return this.next;
	}
	
	public Card seeTopCard(){
		return this.cards[next-1];
	}
	
	//toString
	public String toString(){
		String output="";
		for (int i = 0; i < this.next; i++){
			output += (i + 1) + ") " + this.cards[i].toString()+"\n";
		}
		//output += "\nExtra options: " + "\n3) Take a look at the reference card" + "\n4) Look on the board";
		return output;
	}
	
	////Additional Methods
	
	//add: add a card object to the array
	public void add(Card card) {
		this.cards[next] = card;
		this.next++;
	}
	
	//removeatIndex: removes the card at the specific index
	public Card removeAtIndex(int index) {
        Card removedCard = cards[index];
        cards[index] = null;
        for (int i = index; i < this.next - 1; i++) {
            cards[i] = cards[i + 1];
        }
        cards[this.next - 1] = null;
        next--;
        return removedCard;
    }
	
	public Card getHighestCard(){
		if (next == 0) {
			return null;
		}
		else {
			Card highestValueCard = cards[0];
			
			for (int i = 1; i < next; i++){
				if (cards[i].getCardValue() > highestValueCard.getCardValue()){
					highestValueCard = cards[i];
				}
			}
			
			return highestValueCard;
		}
	}
	
	//contains: check if an array contains a certain card object
	public boolean contains(Card card) {
		for (int i = 0; i < next; i++ ) {
			if (cards[i].getCardValue() == card.getCardValue()) {
				return true;
			}
		}
		return false;
	}
	
	//shuffle: shuffles all the cards in the array
	public void shuffle(){
		java.util.Random rng = new java.util.Random();
		for (int i = 0; i < this.next; i++) {
			int randomIndex = rng.nextInt(this.next);
			Card tempCard = this.cards[randomIndex];
			this.cards[randomIndex] = this.cards[i];
			this.cards[i] = tempCard;
		}
	}
	
	//sort(): sorts all the cards in the array based on their value (from lowest, 0, to highest, 9)
	public void sort(){ //logic: we create a temporary array to put cards in order, then we copy the values from it back to this.cards array.
		Card[] sortedCards = new Card[this.next];
		for (int i = 0; i < this.next; i++) { //the first loop populates sortedCards so then we can re-arrange it later
			sortedCards[i] = this.cards[i];
		}

		for (int i = 0; i < this.next - 1; i++) { //this loop is the sorting algorithm itself
			int minIndex = i; //outer loop itterates trough each card except the last one
				for (int j = i + 1; j < this.next; j++) { //the inner loop compares the card at index j with the lowest card found so far (using minIndex)
					if (sortedCards[j].getCardValue() < sortedCards[minIndex].getCardValue()) { //if the card is lower, we update the lowest card (using minIndex)
						minIndex = j;
					}
				}
			if (minIndex != i) { //logic here: we check if minIndex is different from i, and if it is we swap the cards in the sortedCards array
				Card temp = sortedCards[i];
				sortedCards[i] = sortedCards[minIndex];
				sortedCards[minIndex] = temp;
			}
		}

		for (int i = 0; i < this.next; i++) { //here we just copy all the cards in sortedCards to this.cards
			this.cards[i] = sortedCards[i];
		}
	}
	
	public Card getCardAtIndex(int index){
		Card cardToReturn = this.cards[index-1];
		return cardToReturn;
	}
	
	public Card princeDiscard(){
		Card discardedCard = null;
		for (int i = 0; i < next; i++) {
            if (cards[i].getCardValue() != CardValue.PRINCE.getValue()) {
				discardedCard = cards[i];
                removeAtIndex(i);
                break;
            }
        }
		
		return discardedCard;
	}
}
