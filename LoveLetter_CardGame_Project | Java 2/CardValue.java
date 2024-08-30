public enum CardValue {
    PRINCESS(9, "\u001B[33mPrincess\u001B[0m"), // Dark yellow for legendary
    COUNTESS(8, "\u001B[35mCountess\u001B[0m"), // Purple for epic
    KING(7, "\u001B[35mKing\u001B[0m"), // Purple for epic
    CHANCELLOR(6, "\u001B[34mChancellor\u001B[0m"), // Blue for rare
    PRINCE(5, "\u001B[34mPrince\u001B[0m"), // Blue for rare
    HANDMAID(4, "\u001B[34mHandmaid\u001B[0m"), // Blue for rare
    BARON(3, "\u001B[34mBaron\u001B[0m"), // Blue for rare
    PRIEST(2, "\u001B[34mPriest\u001B[0m"), // Blue for rare
    GUARD(1, "\u001B[32mGuard\u001B[0m"), // Dark green for common
    SPY(0, "\u001B[32mSpy\u001B[0m"); // Dark green for common
    
    private final int value;
    private final String name;
    
    private CardValue(int value, String name) {
        this.value = value;
        this.name = name;
    }
    
    public int getValue() {
        return value;
    }
    
    public String getName() {
        return name;
    }
}