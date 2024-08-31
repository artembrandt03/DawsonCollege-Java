import java.util.Scanner;
import java.util.Random;

public class Hangman {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] words = {
            "apple", "banana", "orange", "grape", "peach", "melon", "kiwi", "strawberry", "raspberry", "blueberry",
            "watermelon", "pineapple", "mango", "papaya", "apricot", "plum", "cherry", "pear", "lemon", "lime",
            "carrot", "tomato", "cucumber", "broccoli", "cauliflower", "spinach", "lettuce", "cabbage", "pumpkin", "zucchini",
            "onion", "garlic", "potato", "sweet", "corn", "pepper", "celery", "asparagus", "eggplant", "radish", "beet",
            "meat", "chicken", "beef", "pork", "lamb", "turkey", "fish", "salmon", "tuna", "trout",
            "bread", "butter", "cheese", "yogurt", "cream", "milk", "egg", "sugar", "salt", "pepper",
            "cake", "cookie", "pie", "brownie", "donut", "pancake", "waffle", "croissant", "bagel", "muffin",
            "coffee", "tea", "water", "juice", "soda", "wine", "beer", "whiskey", "rum", "vodka",
            "car", "bike", "bus", "train", "plane", "boat", "ship", "motorcycle", "truck", "scooter",
            "house", "apartment", "condo", "cottage", "mansion", "villa", "hut", "shack", "cabin", "tent",
            "city", "town", "village", "country", "continent", "ocean", "river", "mountain", "desert", "forest",
            "summer", "winter", "spring", "autumn", "rain", "snow", "sun", "cloud", "wind", "storm",
            "dog", "cat", "mouse", "rat", "rabbit", "deer", "bear", "lion", "tiger", "elephant",
            "bird", "fish", "frog", "snake", "lizard", "turtle", "shark", "whale", "dolphin", "octopus",
            "rose", "lily", "tulip", "daisy", "sunflower", "orchid", "cactus", "fern", "bamboo", "ivy",
            "book", "pen", "paper", "pencil", "notebook", "desk", "chair", "table", "lamp", "computer",
            "shirt", "pants", "shorts", "skirt", "dress", "coat", "jacket", "hat", "scarf", "gloves",
            "shoe", "boot", "sandal", "sneaker", "slipper", "sock", "tie", "belt", "watch", "ring",
            "phone", "tablet", "laptop", "monitor", "keyboard", "mouse", "speaker", "camera", "headphone", "charger",
            "football", "soccer", "basketball", "tennis", "golf", "baseball", "hockey", "cricket", "rugby", "volleyball",
            "piano", "guitar", "violin", "drum", "flute", "trumpet", "saxophone", "clarinet", "harp", "cello",
            "red", "blue", "green", "yellow", "purple", "pink", "orange", "black", "white", "gray",
            "circle", "square", "triangle", "rectangle", "pentagon", "hexagon", "octagon", "star", "heart", "diamond",
            "happy", "sad", "angry", "excited", "bored", "tired", "scared", "nervous", "surprised", "confused",
            "walk", "run", "jump", "swim", "fly", "dance", "sing", "talk", "listen", "write",
            "eat", "drink", "sleep", "wake", "read", "draw", "play", "work", "drive", "travel",
            "morning", "afternoon", "evening", "night", "midnight", "sunrise", "sunset", "noon", "dawn", "dusk",
            "teacher", "doctor", "nurse", "lawyer", "engineer", "scientist", "artist", "musician", "actor", "writer",
            "king", "queen", "prince", "princess", "knight", "wizard", "witch", "dragon", "giant", "elf",
            "apple", "banana", "cherry", "grape", "peach", "pear", "lemon", "lime", "plum", "melon",
            "mango", "apricot", "pineapple", "raspberry", "strawberry", "blueberry", "blackberry", "kiwi", "papaya", "coconut",
            "carrot", "tomato", "potato", "onion", "garlic", "pepper", "cucumber", "lettuce", "spinach", "broccoli",
            "cauliflower", "zucchini", "eggplant", "radish", "beet", "asparagus", "celery", "pumpkin", "corn", "bean",
            "meat", "chicken", "fish", "beef", "pork", "lamb", "turkey", "duck", "bacon", "sausage",
            "egg", "cheese", "butter", "milk", "yogurt", "cream", "sugar", "salt", "pepper", "spice",
            "bread", "cake", "pie", "cookie", "donut", "pancake", "waffle", "croissant", "muffin", "bagel",
            "coffee", "tea", "juice", "water", "soda", "wine", "beer", "whiskey", "rum", "vodka",
            "car", "bus", "bike", "train", "plane", "boat", "ship", "truck", "scooter", "motorcycle",
            "house", "apartment", "cabin", "tent", "villa", "mansion", "shack", "hut", "cottage", "castle",
            "city", "town", "village", "country", "continent", "island", "ocean", "river", "mountain", "valley",
            "summer", "winter", "spring", "autumn", "rain", "snow", "storm", "wind", "sun", "cloud",
            "dog", "cat", "mouse", "rabbit", "deer", "bear", "lion", "tiger", "elephant", "giraffe",
            "bird", "fish", "frog", "snake", "lizard", "turtle", "shark", "whale", "dolphin", "seal",
            "rose", "lily", "tulip", "daisy", "sunflower", "orchid", "cactus", "fern", "bamboo", "ivy",
            "book", "pen", "pencil", "notebook", "paper", "desk", "chair", "lamp", "computer", "phone",
            "shirt", "pants", "dress", "skirt", "shorts", "coat", "jacket", "hat", "scarf", "glove",
            "shoe", "boot", "sandal", "sneaker", "sock", "tie", "belt", "watch", "ring", "bracelet",
            "coffee", "tea", "juice", "milk", "water", "soda", "wine", "beer", "whiskey", "vodka",
            "football", "soccer", "basketball", "tennis", "golf", "baseball", "hockey", "cricket", "rugby", "volleyball",
            "piano", "guitar", "violin", "drum", "flute", "trumpet", "saxophone", "clarinet", "harp", "cello",
            "red", "blue", "green", "yellow", "purple", "pink", "orange", "black", "white", "gray",
            "circle", "square", "triangle", "rectangle", "pentagon", "hexagon", "octagon", "star", "heart", "diamond",
            "happy", "sad", "angry", "excited", "bored", "tired", "scared", "nervous", "surprised", "confused",
            "walk", "run", "jump", "swim", "fly", "dance", "sing", "talk", "listen", "write",
            "eat", "drink", "sleep", "wake", "read", "draw", "play", "work", "drive", "travel",
        };
        
        Random random = new Random();
        String wordToGuess = words[random.nextInt(words.length)];
        int attemptsLeft = 7;
        char[] guessedWord = new char[wordToGuess.length()];
        char[] wrongGuesses = new char[26];
        int wrongGuessCount = 0;

        // Initialize guessedWord with underscores
        initializeGuessedWord(guessedWord);

        while (attemptsLeft > 0) {
            printHangman(attemptsLeft);
            System.out.println("Word to guess: " + String.valueOf(guessedWord));
            System.out.print("Wrong guesses: ");
            printWrongGuesses(wrongGuesses, wrongGuessCount);
            System.out.println("Attempts left: " + attemptsLeft);
            System.out.print("Guess a letter: ");
            char guess = scanner.next().toLowerCase().charAt(0);

            if (!Character.isLetter(guess)) {
                System.out.println("Please enter a valid letter.");
                continue;
            }

            if (letterAlreadyGuessed(guess, guessedWord, wrongGuesses, wrongGuessCount)) {
                System.out.println("You've already guessed that letter. Try again.");
                continue;
            }

            if (!processGuess(wordToGuess, guessedWord, guess)) {
                wrongGuesses[wrongGuessCount++] = guess;
                attemptsLeft--;
                System.out.println("Wrong guess!");
            }

            if (String.valueOf(guessedWord).equals(wordToGuess)) {
                System.out.println("Congratulations! You've guessed the word: " + wordToGuess);
                break;
            }
        }

        if (attemptsLeft == 0) {
            printHangman(0);
            System.out.println("Sorry, you've been hanged! The word was: " + wordToGuess);
        }

        scanner.close();
    }

    public static void initializeGuessedWord(char[] guessedWord) {
        for (int i = 0; i < guessedWord.length; i++) {
            guessedWord[i] = '_';
        }
    }

    public static void printHangman(int attemptsLeft) {
        if (attemptsLeft == 7) {
            System.out.println("""
                  +---+
                      |
                      |
                      |
                      |
                      |
                =========""");
        } 
        else if (attemptsLeft == 6) {
            System.out.println("""
                  +---+
                  O   |
                      |
                      |
                      |
                      |
                =========""");
        } 
        else if (attemptsLeft == 5) {
            System.out.println("""
                  +---+
                  O   |
                  |   |
                      |
                      |
                      |
                =========""");
        } 
        else if (attemptsLeft == 4) {
            System.out.println("""
                  +---+
                  O   |
                 /|   |
                      |
                      |
                      |
                =========""");
        } 
        else if (attemptsLeft == 3) {
            System.out.println("""
                  +---+
                  O   |
                 /|\\  |
                      |
                      |
                      |
                =========""");
        } 
        else if (attemptsLeft == 2) {
            System.out.println("""
                  +---+
                  O   |
                 /|\\  |
                 /    |
                      |
                      |
                =========""");
        } 
        else if (attemptsLeft == 1) {
            System.out.println("""
                  +---+
                  O   |
                 /|\\  |
                 / \\  |
                      |
                      |
                =========""");
        } 
        else if (attemptsLeft == 0) {
            System.out.println("""
                  +---+
                 [O]  |
                 /|\\  |
                 / \\  |
                      |
                      |
                =========""");
        }
    }

    public static void printWrongGuesses(char[] wrongGuesses, int wrongGuessCount) {
        for (int i = 0; i < wrongGuessCount; i++) {
            System.out.print(wrongGuesses[i] + " ");
        }
        System.out.println();
    }

    public static boolean letterAlreadyGuessed(char guess, char[] guessedWord, char[] wrongGuesses, int wrongGuessCount) {
        for (char c : guessedWord) {
            if (c == guess) {
                return true;
            }
        }
        for (int i = 0; i < wrongGuessCount; i++) {
            if (wrongGuesses[i] == guess) {
                return true;
            }
        }
        return false;
    }

    public static boolean processGuess(String wordToGuess, char[] guessedWord, char guess) {
        boolean correctGuess = false;
        for (int i = 0; i < wordToGuess.length(); i++) {
            if (wordToGuess.charAt(i) == guess) {
                guessedWord[i] = guess;
                correctGuess = true;
            }
        }
        return correctGuess;
    }
}
