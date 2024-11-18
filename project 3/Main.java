// Pranav Joseph paj220001
// Import necessary classes for input/output operations and utilities
import java.io.*;
import java.util.*;

// Define the main class
public class Main
{
    public static void main(String [] args)
    {
        String fileName; // Variable to store filenames
        BinTree<Game> tree = new BinTree<>(); // Initialize a binary search tree to store Game objects
        Scanner scnr = new Scanner(System.in); // Scanner object to read user input from the console

        // Prompt the user to enter the name of the database file
        System.out.print("Please enter the name of the database: ");
        fileName = scnr.nextLine(); // Read the database filename entered by the user

        try
        {
            // Initialize a Scanner to read from the specified database file
            Scanner dtbScnr = new Scanner(new File(fileName));
            readDatabase(dtbScnr, tree); // Read and parse the database file, inserting records into the tree

            // Prompt the user to enter the name of the update file
            System.out.print("Please enter the name of the update File: ");
            fileName = scnr.nextLine(); // Read the update filename entered by the user
            Scanner upScnr = new Scanner(new File(fileName)); // Initialize a Scanner for the update file

            // Process each line in the update file
            while(upScnr.hasNext())
            {
                String line = upScnr.nextLine(); // Read the next line from the update file
                update(line, tree); // Apply the update based on the instruction in the line
            }

            // Specify the output filename
            fileName = "cidercade.dat";
            PrintWriter output = new PrintWriter(fileName); // Initialize a PrintWriter to write to the output file

            // Write the breadth-first traversal of the tree to the output file
            output.print(tree.breadthTraverse());

            // Close all Scanner and PrintWriter objects to free resources
            scnr.close();
            dtbScnr.close();
            upScnr.close();
            output.close();
        }
        catch(Exception e) // Catch any exceptions that occur during file operations
        {
            System.out.println(fileName + " was not found."); // Inform the user if the file was not found
        }

        scnr.close(); // Close the main Scanner (redundant as it's already closed above)
    }

    /**
     * Reads the database file and inserts Game records into the binary search tree.
     */
    public static void readDatabase(Scanner scnr, BinTree<Game> tree)
    {
        // Loop through each line in the database file
        while(scnr.hasNext())
        {
            int index, highScore, plays; // Variables to store parsed integers
            String name, initials, line; // Variables to store parsed strings
            double revenue; // Variable to store parsed double

            line = scnr.nextLine(); // Read the next line from the database file

            // Parse the name field
            index = line.indexOf(","); // Find the position of the first comma
            name = line.substring(0, index); // Extract the substring before the comma as the name
            line = line.substring(index + 2); // Remove the parsed part from the line (assumes ", " as delimiter)

            // Parse the highScore field
            index = line.indexOf(','); // Find the next comma
            highScore = Integer.parseInt(line.substring(0, index)); // Parse the high score as an integer
            line = line.substring(index + 2); // Remove the parsed part from the line

            // Parse the initials field
            index = line.indexOf(','); // Find the next comma
            initials = line.substring(0, index); // Extract the initials
            line = line.substring(index + 2); // Remove the parsed part from the line

            // Parse the plays field
            index = line.indexOf(','); // Find the next comma
            plays = Integer.parseInt(line.substring(0, index)); // Parse the number of plays as an integer
            line = line.substring(index + 2); // Remove the parsed part from the line

            // Parse the revenue field
            index = line.indexOf('$'); // Find the dollar sign indicating the start of revenue
            revenue = Double.parseDouble(line.substring(index + 1)); // Parse the revenue as a double
            // Alternatively, if the revenue might have commas or other formatting, additional parsing may be needed

            // Create a new Game object with the parsed data
            Game game = new Game(name, highScore, initials, plays, revenue);
            tree.insert(game); // Insert the Game object into the binary search tree
        }
    }

    /**
     * Processes an update instruction and applies it to the binary search tree.
     */
    public static void update(String line, BinTree<Game> tree)
    {
        // The first character indicates the type of instruction
        int instruction = Integer.parseInt(line.substring(0, 1)); // Parse the instruction code
        line = line.substring(2); // Remove the instruction code and the following space

        // Determine which action to perform based on the instruction code
        switch(instruction)
        {
            case 1:
                line = line.substring(1); // Remove the initial character (e.g., a quotation mark)
                addNode(line, tree); // Add a new Game record to the tree
                break;
            case 2:
                search(line, tree); // Search for a Game record in the tree
                break;
            case 3:
                edit(line, tree); // Edit an existing Game record in the tree
                break;
            case 4: 
                // Delete a Game record from the tree
                Game game = new Game(); // Create a temporary Game object
                game.setName(line); // Set its name to the target name for deletion
                game = tree.delete(game); // Delete the Game from the tree and retrieve the deleted Game object
                System.out.println("RECORD DELETED"); // Inform the user about the deletion
                System.out.println("Name: " + game.getName()); // Print the name of the deleted Game
                printGame(game); // Print the details of the deleted Game
                break;
            case 5:
                tree.Sort(); // Sort the tree (assuming Sort() is defined in BinTree)
                break;
            // You can add more cases here for additional instructions if needed
        }
    }

    /**
     * Searches for a Game in the binary search tree and prints the result.
     */
    public static void search(String line, BinTree<Game> tree)
    {
        Game searchGame = new Game(); // Create a temporary Game object for searching
        searchGame.setName(line); // Set its name to the target name for searching
        searchGame = tree.search(searchGame); // Search for the Game in the tree

        // Check if the Game was found
        if(searchGame != null)
        {
            System.out.println(line + " FOUND"); // Inform the user that the Game was found
            printGame(searchGame); // Print the details of the found Game
        }
        else
        {
            System.out.println(line + " NOT FOUND"); // Inform the user that the Game was not found
        }
    }

    /**
     * Adds a new Game node to the binary search tree based on the provided line.
     */
    public static void addNode(String line, BinTree<Game> tree)
    {
        int index, highScore, plays; // Variables to store parsed integers
        String name, initials, rev; // Variables to store parsed strings
        double revenue; // Variable to store parsed double

        System.out.println("RECORD ADDED"); // Inform the user that a new record is being added

        // Parse the name field
        index = line.indexOf("\""); // Find the position of the first quotation mark
        name = line.substring(0, index); // Extract the name (assuming it ends with a quotation mark)
        System.out.println("Name: " + name); // Print the name

        line = line.substring(index + 2); // Remove the parsed part from the line (assuming '", ')

        // Parse the highScore field
        index = line.indexOf(' '); // Find the next space character
        highScore = Integer.parseInt(line.substring(0, index)); // Parse the high score as an integer
        System.out.println("High Score: " + highScore); // Print the high score

        line = line.substring(index + 1); // Remove the parsed part from the line

        // Parse the initials field
        index = line.indexOf(' '); // Find the next space character
        initials = line.substring(0, index); // Extract the initials
        System.out.println("Initials: " + initials); // Print the initials

        line = line.substring(index + 1); // Remove the parsed part from the line

        // Parse the plays field
        index = line.indexOf(' '); // Find the next space character
        plays = Integer.parseInt(line.substring(0, index)); // Parse the number of plays as an integer
        System.out.println("Plays: " + plays); // Print the number of plays

        line = line.substring(index + 1); // Remove the parsed part from the line

        // Parse the revenue field
        index = line.indexOf('$'); // Find the dollar sign indicating the start of revenue
        revenue = Double.parseDouble(line.substring(index + 1)); // Parse the revenue as a double
        rev = String.format("%.2f", revenue); // Format the revenue to two decimal places
        System.out.println("Revenue: $" + rev + "\n"); // Print the formatted revenue

        // Create a new Game object with the parsed data
        Game game = new Game(name, highScore, initials, plays, revenue);
        tree.insert(game); // Insert the Game object into the binary search tree
    }


    /**
     * Edits an existing Game in the binary search tree based on the provided line.
     */
    public static void edit(String line, BinTree<Game> tree)
    {
        String name; // Variable to store the name of the Game to edit
        int index, instruction; // Variables to store parsed integers
        index = line.indexOf("\""); // Find the first quotation mark
        line = line.substring(index + 1); // Remove up to and including the first quotation mark

        index = line.indexOf("\""); // Find the next quotation mark
        name = line.substring(0, index); // Extract the name of the Game to edit

        line = line.substring(index + 2); // Remove the parsed part from the line (assuming '", ')

        index = line.indexOf(" "); // Find the next space character
        instruction = Integer.parseInt(line.substring(0, index)); // Parse the instruction code for editing

        line = line.substring(index + 1); // Remove the parsed part from the line

        // Create a temporary Game object with the target name for searching
        Game searchGame = new Game();
        searchGame.setName(name);

        searchGame = tree.search(searchGame); // Search for the Game in the tree

        System.out.println(name + " UPDATED"); // Inform the user that the Game is being updated

        // Determine which attribute to update based on the instruction code
        switch(instruction)
        {
            case 1:
                // Update the high score
                System.out.println("UPDATE TO high score - VALUE " + line);
                int highScore = Integer.parseInt(line); // Parse the new high score
                searchGame.setHighScore(highScore); // Set the new high score
                break;
            case 2:
                // Update the initials
                System.out.println("UPDATE TO initials - VALUE " + line);
                searchGame.setInitals(line); // Set the new initials
                break;
            case 3:
                // Update the number of plays
                System.out.println("UPDATE TO plays - VALUE " + line);
                int plays = Integer.parseInt(line); // Parse the new number of plays
                searchGame.setPlays(plays); // Set the new number of plays
                break;
        }

        printGame(searchGame); // Print the updated Game details
        tree.insert(searchGame); // Re-insert the updated Game into the tree (assuming this replaces the old record)
    }

    //prints attributes of the game 
    public static void printGame(Game searchGame)
    {
        // Print each attribute of the Game
        System.out.println("High Score: " + searchGame.getHighScore());
        System.out.println("Initials: " + searchGame.getInitals());
        System.out.println("Plays: " + searchGame.getPlays());
        System.out.println("Revenue: $" + searchGame.getRevenue() + "\n");
    }
}
