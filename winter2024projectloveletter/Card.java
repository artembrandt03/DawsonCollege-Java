public class Card {
	private CardValue value;

    public Card(CardValue value) {
        this.value = value;
    }

    public int getCardValue() {
       return value.getValue(); 
    }

    public String toString() {
        return value.getName() + "(" + value.getValue() + ") ";
    }
	
}