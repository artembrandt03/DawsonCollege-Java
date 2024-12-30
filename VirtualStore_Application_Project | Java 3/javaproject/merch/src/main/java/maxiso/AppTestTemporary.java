package maxiso;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import maxiso.businesslayer.*;
import maxiso.displaylayer.*;
import maxiso.datalayer.merch.*;

public class AppTestTemporary {

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        String figurinesFile = "javaproject/merch/csv/Figurines.csv";
        String plushiesFile = "javaproject/merch/csv/Plushies.csv";
        String electronicsFile = "javaproject/merch/csv/Electronics.csv";
        ShoppingCart userCart = new ShoppingCart();
        IDisplay displayer = new AllDisplay();
        FileImporter products = new FileImporter(figurinesFile, plushiesFile, electronicsFile);
        
        chooseMerch(products, reader, userCart, displayer);
    }

    public static void chooseMerch(FileImporter products, Scanner reader, ShoppingCart userCart, IDisplay displayer) {
        while (true) {
            clearScreen();
            List<Merch> productList = null;
            System.out.println("Which merch would you like to see?");
            System.out.println("1. Figurines");
            System.out.println("2. Plushies");
            System.out.println("3. Electronics");
            System.out.println("4. All");
            System.out.println("5. Go back");
            
            int chosenMerch = getValidatedInput(reader, 1, 5);

            if (chosenMerch == 0)
                return;
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
            displayCollections(displayer, productList, reader, userCart);
        }
    }

    public static void displayCollections(IDisplay displayer, List<Merch> productChosen, Scanner reader, ShoppingCart userCart) {
        while (true) {
            clearScreen();
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

            filterMerch(productChosen, collectionList, reader, displayer, choice, userCart);
        }
        
    }

    public static void filterMerch(List<Merch> productChosen, List<String> collectionList, Scanner reader, IDisplay displayer, int chosen, ShoppingCart userCart) {
        if(collectionList.size() == chosen){
            displayAndNavigateProducts(productChosen, displayer, new ShoppingCart(), reader);
        } else {
            IFilter collectionFilter = new CollectionFilter(collectionList.get(chosen));
            List<Merch> merch = filterMerch(productChosen, collectionFilter);
            displayAndNavigateProducts(merch, displayer, userCart, reader);
        }
    }

    public static void displayAndNavigateProducts(List<Merch> originalProducts, IDisplay displayer, ShoppingCart userCart, Scanner reader) {
        List<Merch> newProducts = new ArrayList<>(originalProducts);  
        int currentProductIndex = 0;
        int pageSize = 1; 
        int totalPages = (newProducts.size() + pageSize - 1) / pageSize;
    
        while (true) {
            clearScreen();
    
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
                    System.out.println("Not implemented");
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
    
    public static List<Merch> filterMerch(List<Merch> input, IFilter filter) {
        List<Merch> filterList = new ArrayList<>();
        for (Merch merch : input) {
            if (filter.filter(merch)) {
                filterList.add(merch);
            }
        }
        return filterList;
    }

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
}
