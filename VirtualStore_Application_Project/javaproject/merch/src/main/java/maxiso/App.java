package maxiso;

import java.io.IOException;
import java.io.Console;
import java.nio.file.*;
import java.util.*;
import maxiso.datalayer.merch.*;
import maxiso.displaylayer.*;
import maxiso.businesslayer.*;

/**
 * Bye world
 *
 */
public class App 
{
    //Main App logic
    public static void main( String[] args ) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true; //Loop for the main application
        boolean readyToProceed = false;


        //Welcome Screen
        clearTerminal();
        printASCIIArt(1);
        System.out.println("\nWelcome to MAXISO! Your one-stop shop for collectibles and electronics!\n\n" +
                           "Discover our wide range of figurines, plushies, and electronics crafted to bring joy and excitement to your life.\n" + 
                           "Whether you're looking to expand your collection or find the perfect gift, we've got you covered!\n\n" + //
                           "Let's make your shopping experience memorable. Start exploring now!");
        printASCIIArt(2);
        pause();
        clearTerminal();

        //Main App functionality
        while (isRunning) {

            //StoreManager Initialization

            //Since I will need the functionality of StoreManager to be able to login/register new users...
            //... we will create a new instance of StoreManager, and since constructor must take in values that we didn't create yet...
            //... we will assign default ones for the time being
            User guestUser = new UnregisteredUser("Guest-Default");
            ShoppingCart guestCart = new ShoppingCart(); //Reminder: shoppingCart constructor doesn't take any input
            List<Merch> availableProducts = new ArrayList<>(); //empty for now
            StoreManager storeManager = new StoreManager(guestUser, guestCart, availableProducts);

            //User Login|Registration
            
            //Printing the screen
            System.out.println("   Get Started at MAXISO");
            printASCIIArt(3);
            printASCIIArt(2);

            System.out.println("Why become a registered user?");
            System.out.println("- Purchase many of our products!");
            System.out.println("- Earn points for every purchase!");
            System.out.println("- Enjoy exclusive discounts and coupons!");

            printASCIIArt(2);

            //User options
            System.out.println("Please select an option:");
            System.out.println("1. Continue as Guest");
            System.out.println("2. Log in to an existing account");
            System.out.println("3. Register a new account");
            System.out.println("4. Exit the application");
            System.out.print("Enter your choice (1-4): ");
            String login_choice = scanner.nextLine();

            //Loop to check if the selected option is valid or not
            boolean validInput = false;
            while (!validInput) {
                try {
                    if (login_choice.equals("1")) {
                        //Handle "Continue as Guest"
                        continueAsGuest();
                        readyToProceed = true;
                        validInput = true;
                    } 
                    else if (login_choice.equals("2")) {
                        // Handle "Login"
                        if (logInUser(storeManager) != null) {
                            validInput = true; // Exit the loop only if login is successful
                            readyToProceed = true;
                        } 
                        else {
                           validInput = true;
                        }
                    }
                    else if (login_choice.equals("3")) {
                        //Handle "Register"
                        registerUser(storeManager);
                        validInput = true;
                    } 
                    else if (login_choice.equals("4")) {
                        //Exit the program
                        clearTerminal();
                        System.out.println("Thank you for visiting MAXISO! Goodbye!");
                        isRunning = false; //Exit the loop
                        validInput = true;
                    } 
                    else {
                        throw new IllegalArgumentException("Invalid choice. Please enter a number between 1 and 4.");
                    }
                } 
                catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    System.out.print("Please try again: ");
                    login_choice = scanner.nextLine(); // Re-prompt user
                }
            }
            //Check if a valid user exists to proceed with the rest of the application
            if (readyToProceed) { //&& storeManager.getUser() != null && (storeManager.getUser() instanceof RegisteredUser || storeManager.getUser() instanceof UnregisteredUser)) {
                // Proceed with the rest of the application
                clearTerminal();
                System.out.println("Welcome to the MAXISO store!");
                System.out.println("Enjoy browsing and shopping our amazing collection of figurines, plushies, and electronics!");
                printASCIIArt(2);
                pause();
                clearTerminal();
            } 
            else {
                // If no valid user, loop back to the login/register menu
                System.out.println("Returning to the main menu...");
                printASCIIArt(2);
                pause();
                clearTerminal();
            }

            //PRODUCT VIEWING AND THE REST OF THE APP GOES HERE
            if (readyToProceed){
                Scanner reader = new Scanner(System.in);
                String figurinesFile = "javaproject/merch/csv/Figurines.csv";
                String plushiesFile = "javaproject/merch/csv/Plushies.csv";
                String electronicsFile = "javaproject/merch/csv/Electronics.csv";
                ShoppingCart userCart = new ShoppingCart();
                IDisplay displayer = new AllDisplay();
                FileImporter products = new FileImporter(figurinesFile, plushiesFile, electronicsFile);
                
                chooseMerch(products, reader, userCart, displayer, storeManager);
            }
            //Set everything back to false to allow another itteration of loop
            clearTerminal();
            validInput = false;
            readyToProceed = false;
            guestUser = null;
        }
        

    }

    //Helper Methods

    //1
    /**
     * Helper method to print ASCII art based on input
     *
     * @param artType The option of ASCII art to print.
     */
    public static void printASCIIArt(int artOption) {
        //This will be printed for store logo
        if (artOption == 1) {
            System.out.println("                             _        ,");
            System.out.println("                            (_\\______/________");
            System.out.println("                               \\-|-|/|-|-|-|-|/");
            System.out.println("                                \\==/-|-|-|-|-/");
            System.out.println("                                 \\/|-|-|-|,-'");
            System.out.println("                                  \\--|-'''");
            System.out.println("                                   \\_j________");
            System.out.println("                                   (_)     (_)");
        }
        //Just a simple line for better and more clear seperatation when printing to terminal
        else if (artOption == 2) {
            System.out.println("\n-----------------------------------------------------");
        }
        else if (artOption == 3) {
            System.out.println("    (._.)          (._.)");
            System.out.println("    <) )/          \\( (>");
            System.out.println("     / \\            / \\");
        } 
    }

    //2
    /**
     * Utility method to clear the terminal.
     * It uses ANSI escape codes to clear the screen and move the cursor to the top.
     */
    public static void clearTerminal() {
        System.out.print("\033[H\033[2J"); //escape codes to clear the screen
        System.out.flush(); //Ensures the output is flushed to the console immediately
    }

    //3
    /**
     * Utility method.
     * Pauses the application and waits for the user to press Enter.
     * Allows to seperate different proccesses throughout our app.
     */
    public static void pause() {
        System.out.println("Press 'Enter' to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine(); // Waits for the user to press Enter
    }

    //4
    /**
     * Handles the "Continue as Guest" functionality.
     * Creates an UnregisteredUser with a unique guest ID based on current time (useful to not hardcode it)
     *
     * @return UnregisteredUser instance representing the guest user.
     */
    public static UnregisteredUser continueAsGuest() {
        //Generates a unique guest ID 
        String guestID = "Guest-" + System.currentTimeMillis(); //https://www.geeksforgeeks.org/java-lang-system-currenttimemillis-method-with-examples/

        //Creates a new UnregisteredUser instance
        UnregisteredUser guestUser = new UnregisteredUser(guestID);

        //Prints
        clearTerminal();
        System.out.println("\nYou are now continuing as a guest.");
        System.out.println("Your Guest ID is: " + guestID);
        pause();

        //Return the guest user for further use
        return guestUser;
    }

    //5
    /**
     * Handles the user login functionality.
     * Prompts the user to enter their username and password, allowing retries for both.
     * If the username does not exist, the user is given the option to re-enter or go back to the main menu.
     * Once a valid username is found, the user can attempt to log in by entering the correct password.
     * If login is successful, the RegisteredUser object is returned and set in the StoreManager.
     * 
     * @param storeManager The StoreManager instance used to manage the logged-in user.
     * @return The RegisteredUser object representing the logged-in user, or null if the user chooses to go back.
     */
    public static RegisteredUser logInUser(StoreManager storeManager) {

        //Preliminaries
        Scanner scanner = new Scanner(System.in);
        UserDataHandler userDataHandler = new UserDataHandler();
        List<RegisteredUser> registeredUsers;
        String username = null;

        try {
            //Loading all registered users from the file
            registeredUsers = userDataHandler.loadUsers();
        } 
        catch (Exception e) {
            System.err.println("Error loading user data: " + e.getMessage());
            return null;
        }

        //Finding username
        boolean validInput = false;
        while (!validInput) {
            try {
                clearTerminal();
                System.out.println("\nLogin page.");
                printASCIIArt(2);
                System.out.println("1. Enter your username");
                System.out.println("2. Go back to the main menu");
                System.out.print("Your choice: ");
                String choice = scanner.nextLine();

                if (choice.equals("2")) {
                    clearTerminal();
                    validInput = true;
                    return null;
                }
                else if (choice.equals("1")) {
                    clearTerminal();
                    System.out.print("Enter your username: ");
                    username = scanner.nextLine();
        
                    //Checks if the username exists in the CSV
                    boolean usernameExists = storeManager.findUser(registeredUsers, username);
        
                    if (!usernameExists) {
                        System.out.println("No such username found.");
                        pause();
                    }
                    else {
                        validInput = true; 
                    }
                }
                else {
                    throw new IllegalArgumentException("Invalid choice. Please enter a valid option (must be a number.)");
                }
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                pause();
            }    
        }

        // if (choice.equals("2")) {
        //     return null;
        // }
        
        //Password matching
        boolean validPassword = false;
        int attempts = 3;

        while (attempts > 0 && !validPassword) {
            clearTerminal();
            System.out.println("User found: " + username);
            System.out.println("You have " + attempts + " attempt(s) remaining.");

            //Using Console to securely read password
            Console console = System.console();
            String password;
            if (console != null) {
                char[] passwordArray = console.readPassword("Enter password: ");
                password = new String(passwordArray);
            } 
            else {
                //added plan b code if initializing console fails
                System.out.print("Enter password: ");
                password = scanner.nextLine();
            }

            for (RegisteredUser user : registeredUsers) {
                if (user.getUsername().equals(username)) {
                    if (user.getAccess(username, password)) {
                        System.out.println("\nSuccessfully logged in! Welcome back, " + user.getUsername() + "!");
                        pause();
                        storeManager.setUser(user); // Set the logged-in user in the StoreManager
                        validPassword = true;
                        return user; // Return the RegisteredUser instance
                    } else {
                        clearTerminal();
                        System.out.println("Incorrect password.");
                        pause();
                    }
                }
            }

            //Decrements attempts and notify the user if too many failed
            attempts--;
            if (attempts == 0) {
                clearTerminal();
                System.out.println("Too many failed attempts.");
                return null;
            }
        }
        return null;
    }

    //6
    /**
     * Handles the user registration process.
     * Prompts the user to enter a username and password, and attempts to register the user.
     *
     * @param storeManager The StoreManager instance to handle user registration.
     * @return true if the user is successfully registered, false otherwise.
     */
    public static RegisteredUser registerUser(StoreManager storeManager) {
        Scanner scanner = new Scanner(System.in);
        UserDataHandler userDataHandler = new UserDataHandler();
        List<RegisteredUser> registeredUsers;
    
        // Load all registered users
        try {
            registeredUsers = userDataHandler.loadUsers();
        } 
        catch (IOException e) {
            System.err.println("Error loading user data: " + e.getMessage());
            return null;
        }
    
        String username = null;
        boolean usernameValid = false;
    
        //Prompt for a unique username that doesn't exist in the CSV
        while (!usernameValid) {
            clearTerminal();
            System.out.println("Let's register you to get you ready to shop!");
            printASCIIArt(2);
            System.out.print("Enter a username: ");
            username = scanner.nextLine();
    
            if (storeManager.findUser(registeredUsers, username)) {
                clearTerminal();
                System.out.println("Username already exists. Please try another username.");
                pause();
            } 
            else {
                usernameValid = true; //Proceeds only if the username is unique
            }
        }
    
        //Using Console to securely read password (resources: https://docs.oracle.com/en/java/javase/20/docs/api/java.base/java/io/Console.html)
        Console console = System.console();
        String password = null;
        char[] passwordArray = console.readPassword("Enter a password: ");
        password = new String(passwordArray); // Convert char[] to String
    
        // Register the user using StoreManager
        storeManager.register(username, password);
        clearTerminal();
        System.out.println("Registration successful! " + username + ", please login now.");
        pause();
        clearTerminal();
        return (RegisteredUser) storeManager.getUser(); // Return the RegisteredUser object
    }

    //7
    /**
     * Displays a menu for users to choose a type of merch to view, such as figurines, plushies, electronics, or all items together.
     * Once a selection is made, it calls `displayCollections` to handle the display of items in collections.
     *
     * @param products   The FileImporter instance used to load merchandise data.
     * @param reader     The Scanner instance for user input.
     * @param userCart   The ShoppingCart instance where users can add items.
     * @param displayer  The IDisplay instance used for displaying merchandise details.
     */
    public static void chooseMerch(FileImporter products, Scanner reader, ShoppingCart userCart, IDisplay displayer, StoreManager storeManager) {
        while (true) {
            clearTerminal();
            List<Merch> productList = null;
            System.out.println("Which merch would you like to see?");
            System.out.println("1. Figurines");
            System.out.println("2. Plushies");
            System.out.println("3. Electronics");
            System.out.println("4. All");
            System.out.println("5. Logout");
            
            int chosenMerch = getValidatedInput(reader, 1, 5);

            if (chosenMerch == 5) {
                clearTerminal();
                System.out.println("Proceed to log out? (yes/no)");
                String confirmation = reader.next().trim().toLowerCase();
                if (confirmation.equals("yes")) {
                    clearTerminal();
                    System.out.println("You have been logged out. Returning to the main menu.");
                    printASCIIArt(2);
                    pause();
                    return;
                } 
                else {
                    clearTerminal();
                    System.out.println("Logout canceled. Returning to the merchandise menu.");
                    printASCIIArt(2);
                    pause();
                    continue;
                }
            }
            switch (chosenMerch) {
                case 1:
                    productList = products.loadFigurines();
                    break;
                case 2:
                    productList = products.loadPlushies();
                    break;
                case 3:
                    productList = products.loadElectronics();
                    break;
                case 4:
                    productList = products.loadAllMerch();
                    break;
                default:
                    System.out.println("Invalid option, please select a number 1-4");
            }
            displayCollections(displayer, productList, reader, userCart, storeManager);
        }
    }

    //8
    /**
     * Displays collections of a selected merch category.
     * Users can choose a specific collection or view all items in the category.
     * Once a selection is made, it calls `filterMerch` to further process the items.
     *
     * @param displayer        The IDisplay instance used for displaying collection details.
     * @param productChosen    The list of merch items chosen from the previous menu.
     * @param reader           The Scanner instance for user input.
     * @param userCart         The ShoppingCart instance where users can add items.
     */
    public static void displayCollections(IDisplay displayer, List<Merch> productChosen, Scanner reader, ShoppingCart userCart, StoreManager storeManager) {
        while (true) {
            clearTerminal();
            HashSet<String> collections = displayer.getCollections(productChosen);
            List<String> collectionList = new ArrayList<>(collections);

            int option = 1;
            System.out.println("Choose a collection:");

            for (String collection : collectionList) {
                System.out.println(option + ". " + collection);
                option++;
            }

            System.out.println(option + ". All");
            System.out.println(option+1 + ". Go back");
            int choice = getValidatedInput(reader, 1, option + 1) - 1;
            reader.nextLine();
            if (choice == option)
                return;

            filterMerch(productChosen, collectionList, reader, displayer, choice, userCart, storeManager);
        }
        
    }

    //9
    /**
     * Filters the merch based on the selected collection and displays the filtered items appropriately.
     * If we select "All", all items in the store are displayed. Otherwise items are filtered by the chosen collection.
     *
     * @param productChosen    The list of merchandise items chosen from the previous menu.
     * @param collectionList   The list of available collections for the selected merchandise type.
     * @param reader           The Scanner instance for user input.
     * @param displayer        The IDisplay instance used for displaying merchandise details.
     * @param chosen           The index of the chosen collection in the collection list.
     * @param userCart         The ShoppingCart instance where users can add items.
     */
    public static void filterMerch(List<Merch> productChosen, List<String> collectionList, Scanner reader, IDisplay displayer, int chosen, ShoppingCart userCart, StoreManager storeManager) {
        if(collectionList.size() == chosen){
            displayAndNavigateProducts(productChosen, displayer, new ShoppingCart(), reader, storeManager);
        } else {
            IFilter collectionFilter = new CollectionFilter(collectionList.get(chosen));
            List<Merch> merch = filterMerch(productChosen, collectionFilter);
            displayAndNavigateProducts(merch, displayer, userCart, reader, storeManager);
        }
    }

    //10
    /**
     * Displays products to the user and allows clear navigation, filtering, and sorting of products.
     * Users can view details of individual products, add products to their shopping cart, and modify filters or sorters.
     *
     * @param originalProducts The original list of products before any filters or sorters are applied.
     * @param displayer        The IDisplay instance used for displaying product details.
     * @param userCart         The ShoppingCart instance where users can add items.
     * @param reader           The Scanner instance for user input.
     */
    public static void displayAndNavigateProducts(List<Merch> originalProducts, IDisplay displayer, ShoppingCart userCart, Scanner reader, StoreManager storeManager) {
        List<Merch> newProducts = new ArrayList<>(originalProducts);  
        int currentProductIndex = 0;
        int pageSize = 1; 
        int totalPages = (newProducts.size() + pageSize - 1) / pageSize;
    
        while (true) {
            clearTerminal();
            if (newProducts.isEmpty()) {
                System.out.println("No products available with the current filters.");
                System.out.println("\nChoose an option:");
                System.out.println("1. Remove ALL filters");
                System.out.println("2. Go back");
                
                int choice = getValidatedInput(reader, 1, 2);
    
                if (choice == 1) {
                    newProducts = new ArrayList<>(originalProducts);
                    currentProductIndex = 0; 
                    totalPages = (newProducts.size() + pageSize - 1) / pageSize; 
                    continue; 
                } else if (choice == 2) {
                    return; 
                }
            }
    
            int currentPage = currentProductIndex / pageSize + 1;
    
            System.out.println(displayer.allDisplay(newProducts.get(currentProductIndex)));
    
            System.out.println("\n(" + currentPage + "/" + totalPages + ")");
            System.out.println("\nChoose an option:");
            System.out.println("1. Next Product");
            System.out.println("2. Previous Product");
            System.out.println("3. Add product to cart");
            System.out.println("4. Add a filter/sorter");
            System.out.println("5. Remove ALL filters/sorters");
            System.out.println("6. View cart");
            System.out.println("7. Go back");
    
            int userChoice = getValidatedInput(reader, 1, 7);
    
            if (userChoice == 7) {
                return; 
            }
    
            switch (userChoice) {
                case 1:
                    currentProductIndex = (currentProductIndex + 1) % newProducts.size();
                    break;

                case 2:
                    currentProductIndex = (currentProductIndex - 1 + newProducts.size()) % newProducts.size();
                    break;
                case 3:
                    userCart.addItem(newProducts.get(currentProductIndex));
                    System.out.println("Added to cart! Press Enter to continue.");
                    reader.nextLine();
                    reader.nextLine();
                    break;
    
                case 4:
                    newProducts = addFilterOrSorter(newProducts, reader, originalProducts);
                    currentProductIndex = 0; 
                    totalPages = (newProducts.size() + pageSize - 1) / pageSize; 
                    break;
    
                case 5:
                    newProducts = new ArrayList<>(originalProducts); 
                    currentProductIndex = 0; 
                    totalPages = (newProducts.size() + pageSize - 1) / pageSize; 
                    break;
    
                case 6:
                    browseCart(userCart, displayer, reader, storeManager);
                    break;
    
                default:
                    System.out.println("Invalid option. Please choose again.");
                    break;
            }
    
            if (!newProducts.isEmpty() && currentProductIndex >= newProducts.size()) {
                currentProductIndex = newProducts.size() - 1;  
            }
        }
    }

    //11
    /**
     * Allows users to apply filters or sorters to a list of products and returns the modified list.
     *
     * @param products The list of products to filter or sort.
     * @param reader   The Scanner instance for user input.
     * @return The modified list of products after applying the selected filter or sorter.
     */
    public static List<Merch> addFilterOrSorter(List<Merch> products, Scanner reader, List<Merch> originalProducts) {
        while (true) {
            System.out.println("What would you like to do?");
            System.out.println("1. Apply a Filter");
            System.out.println("2. Apply a Sorter");
            System.out.println("3. Go back");
        
            int userChoice = getValidatedInput(reader, 1, 3);
            switch (userChoice) {
                case 1:
                    products = new ArrayList<>(originalProducts);
                    return applyFilter(products, reader);
        
                case 2:
                    return applySorter(products, reader);
        
                case 3:
                    break;
            }
            return products;    
        }
        
    }
    //12
    /**
     * Applies a specific filter to the list of products based on user input.
     * Options include our custom filters, such as filtering by price range, minimum price, maximum price, or collection.
     *
     * @param products The list of products to filter.
     * @param reader   The Scanner instance for user input.
     * @return The filtered list of products.
     */
    private static List<Merch> applyFilter(List<Merch> products, Scanner reader) {
        IFilter filter = null;
        List<Merch> filteredProducts = new ArrayList<>(products);
    
        System.out.println("Which filter would you like to apply?");
        System.out.println("1. Price Range");
        System.out.println("2. Minimum Price");
        System.out.println("3. Maximum Price");
        System.out.println("4. Collection");
        System.out.println("5. Go back");
    
        int userChoice = getValidatedInput(reader, 1, 5);
        switch (userChoice) {
            case 1:
                System.out.println("Enter the minimum price:");
                double minPrice = reader.nextDouble();
                System.out.println("Enter the maximum price:");
                double maxPrice = reader.nextDouble();
                filter = new PriceRangeFilter(minPrice, maxPrice);
                break;
    
            case 2:
                System.out.println("Enter the minimum price:");
                int targetPrice = reader.nextInt();
                filter = new PriceMinimum(targetPrice);
                break;
            
            case 3:
                System.out.println("Enter the maximum price:");
                int target = reader.nextInt();
                filter = new PriceMaximum(target);
                break;
    
            case 4:
                HashSet<String> collections = new HashSet<>();
                for (Merch merch : products) {
                    collections.add(merch.getCollection());
                }
    
                List<String> collectionList = new ArrayList<>(collections);
                System.out.println("Available collections:");
                for (int i = 0; i < collectionList.size(); i++) {
                    System.out.println((i + 1) + ". " + collectionList.get(i));
                }
                System.out.println((collectionList.size() + 1) + ". Go back");
    
                int collectionChoice = getValidatedInput(reader, 1, collectionList.size() + 1);
    
                if (collectionChoice == collectionList.size() + 1) {
                    return filteredProducts;
                }
    
                String targetCollection = collectionList.get(collectionChoice - 1);
                filter = new CollectionFilter(targetCollection);
                break;
    
            case 5:
                return filteredProducts;
        }
    
        if (filter != null) {
            filteredProducts = filterMerch(products, filter);
        }
        return filteredProducts;
    }

    //13
    /**
     * Applies a specific sorting order to the list of products based on user input.
     *
     * @param products The list of products to sort.
     * @param reader   The Scanner instance for user input.
     * @return The sorted list of products.
     */
    private static List<Merch> applySorter(List<Merch> products, Scanner reader) {
        System.out.println("Which sorting option would you like to apply?");
        System.out.println("1. Price Ascending");
        System.out.println("2. Price Descending");
        System.out.println("3. Go back");
    
        int userChoice = getValidatedInput(reader, 1, 3);
        switch (userChoice) {
            case 1:
                products.sort(new PriceAscending());
                break;
    
            case 2:
                products.sort(new PriceDescending());
                break;
    
            case 3:
                return products;
        }
        return products;
    }

    //14
    /**
     * Filters a list of products based on a specified IFilter implementation.
     *
     * @param input  The list of products to filter.
     * @param filter The IFilter implementation to apply.
     * @return The filtered list of products.
     */
    public static List<Merch> filterMerch(List<Merch> input, IFilter filter) {
        List<Merch> filterList = new ArrayList<>();
        for (Merch merch : input) {
            if (filter.filter(merch)) {
                filterList.add(merch);
            }
        }
        return filterList;
    }

    //15
    /**
     * Prompts the user to input a number within a specified range and validates the input.
     * Ensures the user provides a valid integer within the range before returning it.
     *
     * @param reader     The Scanner instance for user input.
     * @param lowerBound The lower bound of the acceptable range (inclusive).
     * @param upperBound The upper bound of the acceptable range (inclusive).
     * @return The validated integer input from the user.
     */
    public static int getValidatedInput(Scanner reader, int lowerBound, int upperBound) {
        int userInput;
        while (true) {
            try {
                System.out.print("Enter a number between " + lowerBound + " and " + upperBound + ": ");
                userInput = reader.nextInt();
                if (userInput >= lowerBound && userInput <= upperBound) {
                    return userInput;
                } else {
                    System.out.println("Invalid input. Please enter a number within the range " + lowerBound + " to " + upperBound + ".");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                reader.nextLine();
            }
        }
    }

    //16
    /**
     * Displays the items in the user's shopping cart and allows navigation through the items.
     * Users can remove items, proceed to checkout, or navigate through the cart.
     *
     * @param userCart  The ShoppingCart instance containing the user's selected items.
     * @param displayer The IDisplay instance used to format and display item details.
     * @param reader    The Scanner instance for user input.
     */
    public static void browseCart(ShoppingCart userCart, IDisplay displayer, Scanner reader, StoreManager storeManager) {
        List<Merch> items = userCart.getItems();  
        int currentItemIndex = 0;  
        int pageSize = 1;
        int totalPages = (items.size() + pageSize - 1) / pageSize;
    
        while (true) {
            clearTerminal();
    
            if (items.isEmpty()) {
                System.out.println("Your cart is empty.");
                System.out.println("\nChoose an option:");
                System.out.println("1. Go back");
                
                int choice = getValidatedInput(reader, 1, 1);
                if (choice == 1) {
                    return; 
                }
            }

            int currentPage = currentItemIndex / pageSize + 1;
            System.out.println(displayer.categoryDisplay(items.get(currentItemIndex)));
            double totalPrice = userCart.getPriceBeforeTax();
            System.out.println("Total before tax: " + "$" + totalPrice);
    
            System.out.println("\n(" + currentPage + "/" + totalPages + ")");
            System.out.println("\nChoose an option:");
            System.out.println("1. Next Item");
            System.out.println("2. Previous Item");
            System.out.println("3. Remove item from cart");
            System.out.println("4. Checkout");
            System.out.println("5. Go back");
    
            int userChoice = getValidatedInput(reader, 1, 5);
            if (userChoice == 5) {
                return; 
            }
            switch (userChoice) {
                case 1:
                    currentItemIndex = (currentItemIndex + 1) % items.size();
                    break;
    
                case 2:
                    currentItemIndex = (currentItemIndex - 1 + items.size()) % items.size();
                    break;
    
                case 3: 
                    items.remove(currentItemIndex);
                    userCart.removeItem(items.get(currentItemIndex));
                    System.out.println("Item succesfully removed!");
                    totalPages = (items.size() + pageSize - 1) / pageSize;
                    pause();
                    break;
                case 4:
                    System.out.println("Proceeding to checkout...");
                    checkout(userCart, reader, (RegisteredUser) storeManager.getUser());
                    return; 
    
                default:
                    System.out.println("Invalid option. Please choose again.");
                    break;
            }
        }
    }

    //17
    /**
     * Handles the checkout process for the user's shopping cart.
     * Allows users to apply a coupon or proceed without one.
     *
     * @param userCart The ShoppingCart instance containing the items to be checked out.
     * @param reader   The Scanner instance for user input.
     */
    public static void checkout(ShoppingCart userCart, Scanner reader, RegisteredUser user) {
        clearTerminal();
        System.out.println("Would you like to:");
        System.out.println("1. Use a coupon");
        System.out.println("2. Use loyalty points");
        System.out.println("3. Proceed without discounts");
        int discountChoice = getValidatedInput(reader, 1, 3);
    
        switch (discountChoice) {
            case 1:
                checkCoupon(userCart, reader);
                break;
            case 2:
                useLoyaltyPoints(userCart, reader, user);
                break;
            case 3:
                finalizeOrder(userCart);
                break;
            default:
                System.out.println("Invalid option. Returning to checkout.");
                checkout(userCart, reader, user);
        }
    }

    //18
    /**
     * Prompts the user to input a coupon code and applies it to the cart if valid.
     * If the coupon is invalid, the user can choose to retry or proceed without applying a coupon.
     *
     * @param userCart The ShoppingCart instance where the coupon will be applied.
     * @param reader   The Scanner instance for user input.
     */
    public static void checkCoupon(ShoppingCart userCart, Scanner reader) {
        String couponFile = "javaproject/merch/csv/Coupons.csv";
    
        List<Coupon> coupons = loadCoupons(couponFile);
        CouponManager checkCoupons = new CouponManager(coupons);
    
        while (true) {
            System.out.println("Please enter a coupon name:");
            if (reader.hasNextLine()) {
                reader.nextLine();
            }
    
            String couponName = reader.nextLine();
    
            boolean isValidCoupon = checkCoupons.isValidCoupon(couponName);
            if (isValidCoupon) {
                Coupon toApply = checkCoupons.getCoupon(couponName);
                userCart.setCoupon(toApply);
                userCart.applyCoupon();
                System.out.println("Coupon applied successfully!");
                System.out.println("Total price after tax: $" + userCart.getPriceBeforeTax());
                System.out.println("Your order has been recorded! You will get payment inquiries soon from from MAXISO@ma.ca ;)");
                pause();
                System.exit(0);
                break;
            } else {
                System.out.println("Coupon does not exist. Do you wish to try again?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                
                int tryAgain = getValidatedInput(reader, 1, 2);
                
                if (tryAgain == 2) {
                    System.out.println("No coupon applied to your order.");
                    System.out.println("Total price after tax: $" + userCart.getPriceBeforeTax());
                    System.out.println("Your order has been recorded! You will get payment inquiries soon from from MAXISO@ma.ca ;)");
                    pause();
                    System.exit(0);
                    return;
                }
            }
        }
    }
    
    //19
    /**
     * Loads the list of coupons from a specified file and returns it as a list.
     * Handles errors during file reading and returns an empty list if an error occurs.
     *
     * @param couponFile The file path to the coupon CSV file.
     * @return A list of Coupon objects loaded from the file.
     */
    public static List<Coupon> loadCoupons(String couponFile) {
        try {
            CouponDataHandler couponList = new CouponDataHandler(couponFile);
            return couponList.loadCoupons();
        } catch (IOException e) {
            System.err.println("Error loading coupons from file: " + couponFile);
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    //20
    /**
     * Applies loyalty points as a discount to the user's cart.
     * Deducts the equivalent points from the user's account.
     *
     * @param userCart The ShoppingCart instance to apply the discount to.
     * @param reader   The Scanner instance for user input.
     * @param user     The RegisteredUser instance to deduct points from.
     */
    public static void useLoyaltyPoints(ShoppingCart userCart, Scanner reader, RegisteredUser user) {
        clearTerminal();
        int userPoints = user.getPoints();
        System.out.println("You have " + userPoints + " loyalty points.");
        System.out.println("Each 100 points equals $1 discount.");
        System.out.println("How many points would you like to redeem?");
        System.out.println("Enter a value up to " + userPoints + " (or enter 0 to cancel):");

        int pointsToRedeem = getValidatedInput(reader, 0, userPoints);
        if (pointsToRedeem > 0) {
            double discount = pointsToRedeem / 100.0;
            double cartTotal = userCart.getPriceBeforeTax();

            if (discount > cartTotal) {
                System.out.println("The discount exceeds the total price. Adjusting to maximum discount.");
                discount = cartTotal;
                pointsToRedeem = (int) (cartTotal * 100);
            }

            userCart.applyDiscount(discount); // Assuming this method applies a flat discount
            user.addPoints(-pointsToRedeem); // Deduct the redeemed points
            System.out.println("Successfully applied a $" + discount + " discount using " + pointsToRedeem + " points.");
            finalizeOrder(userCart);
        } 
        else {
            System.out.println("No points redeemed. Returning to checkout.");
            checkout(userCart, reader, user);
        }
    }

    //21
    /**
     * Finalizes the order by displaying the final price and confirming the purchase.
     *
     * @param userCart The ShoppingCart instance containing the items to be checked out.
     */
    public static void finalizeOrder(ShoppingCart userCart) {
        clearTerminal();
        System.out.println("Final total price after tax: $" + userCart.getPriceAfterTax());
        System.out.println("Your order has been recorded! You will receive a confirmation email shortly.");
        pause();
        System.exit(0); //Exit the application after finalizing the order
    }  
}
