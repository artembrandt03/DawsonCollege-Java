package maxiso.datalayer.merch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import maxiso.businesslayer.RegisteredUser;

/**
 * UserDataHandler class is responsible for managing user data stored in a CSV file (Users.csv)
 * It implements ability to register new users and load existing users from the file.
 */
public class UserDataHandler {
    private final String userFile = "javaproject/merch/csv/Users.csv"; //this will be final as it's a fixed file path and cannot be changed

    /**
     * Constructor for UserDataHandler.
     * Always uses the predefined file path for storing user data.
     */
    public UserDataHandler() {
    }

    /**
     * 
     * Registers a new user by appending their data to the CSV file for users.
     *
     * @param user The RegisteredUser object representing the user to be saved in the file.
     * @throws IOException If an error occurs while writing to the file.
     */
    public void registerUser(RegisteredUser user) throws IOException {
        Path path = Paths.get(userFile);
        
        System.out.println("All Registered Users:");

        //Prepares the user data as a CSV string
        String userData = user.getUsername() + "," + user.getPassword() + "," + user.getPoints() + "," + user.getDateOfCreation();

        System.out.println("All Registered Users:");
        //Appends the user data to an existing file
        List<String> existingUsers = Files.readAllLines(path);
        
        System.out.println("All Registered User2:");
        existingUsers.add(userData);
        
        System.out.println("All Registered Users:");
        Files.write(path, existingUsers); //Overwrites with updated content

    };

    /**
     * Loads all registered users from the CSV file into a list.
     * Each line in the file is parsed into a RegisteredUser object.
     *
     * @return A list of RegisteredUser objects representing all registered users in that file.
     * @throws IOException If an error occurs while reading from the file.
     */
    public List<RegisteredUser> loadUsers() throws IOException {
        Path path = Paths.get(userFile);
        List<RegisteredUser> users = new ArrayList<>();

        //Reading all lines from the file
        List<String> lines = Files.readAllLines(path);
        for (String line : lines) {
            String[] parts = line.split(","); 
            if (parts.length == 4) { //Ensures that the line has exactly 4 components (username, password, points, dateOfCreation) as it is in the RegisteredUser class
                String username = parts[0];
                String password = parts[1];
                int points = Integer.parseInt(parts[2]);
                Date dateOfCreation = new Date(parts[3]);
                users.add(new RegisteredUser(username, password, points));
            }
        }
        return users;
    }
};