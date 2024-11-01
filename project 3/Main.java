// Pranav Joseph paj220001
import java.io.*;
import java.util.*;
public class Main
{
    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String [] args)
    {
        String fileName;
        BinTree<Game> tree = new BinTree<>();
        Scanner scnr = new Scanner(System.in);

        System.out.print("Plese enter the name of the database: ");
        fileName = scnr.nextLine();

        try
        {
            Scanner dtbScnr = new Scanner(new File(fileName));
            readDatabase(dtbScnr, tree);
            System.out.print("Plese enter the name of the update File: ");
            fileName = scnr.nextLine();
            Scanner upScnr = new Scanner(new File(fileName));

            while(upScnr.hasNext())
            {
                String line = upScnr.nextLine();
                update(line, tree);
            }

            PrintWriter output = new PrintWriter("cidercade.dat");
            
            output.print(tree.breadthTraverse());
            
            scnr.close();
            dtbScnr.close();
            upScnr.close();
            output.close();

        }
        catch(IOException e)
        {
            System.out.println(fileName + " was not found.");
        }

        scnr.close();
    
    }

    public static void readDatabase(Scanner scnr, BinTree<Game> tree)
    {
        while(scnr.hasNext())
        {
            int index, highScore, plays;
            String name, initials, line;
            double revenue;

            line = scnr.nextLine();
            index = line.indexOf(",");
            name = line.substring(0, index);
            line = line.substring(index + 2);

            index = line.indexOf(',');
            highScore = Integer.parseInt(line.substring(0, index));
            line = line.substring(index + 2);

            index = line.indexOf(',');
            initials = line.substring(0, index);
            line = line.substring(index + 2);

            index = line.indexOf(',');
            plays = Integer.parseInt(line.substring(0, index));
            line = line.substring(index + 2);

            index = line.indexOf('$');
            revenue = Double.parseDouble(line.substring(index + 1));
            Game game = new Game(name, highScore, initials, plays, revenue);
            tree.insert(game);
        }
    }

    public static void update(String line, BinTree<Game> tree)
    {
        int instruction = Integer.parseInt(line.substring(0, 1));
        line = line.substring(2);
    

        switch(instruction)
        {
            case 1:
                line = line.substring(1);
                addNode(line, tree);
                break;
            case 2:
                 search(line, tree);
                 break;
            case 3:
                edit(line, tree);
                break;
            case 4: 
                Game game = new Game();
                game.setName(line);
                tree.delete(game);
                break;
            case 5:
                tree.Sort();   
                break;
        }
    }

    public static void search(String line, BinTree<Game> tree)
    {
        Game searchGame = new Game();
        searchGame.setName(line);
        searchGame = tree.search(searchGame);
        if(searchGame != null)
        {
            System.out.println(line + " FOUND");
            printGame(searchGame);
        }
        else
        {
            System.out.println(line + " NOT FOUND");
        }
    }

    public static void addNode(String line, BinTree<Game> tree)
    {
        int index, highScore, plays;
        String name, initials, rev;
        double revenue;

        System.out.println("RECORD ADDED");

        index = line.indexOf("\"");
        name = line.substring(0, index);
        System.out.println("Name: " + name);

        line = line.substring(index + 2);
        index = line.indexOf(' ');
        highScore = Integer.parseInt(line.substring(0, index));
        System.out.println("High Score: " + highScore);

        line = line.substring(index + 1);
        index = line.indexOf(' ');
        initials = line.substring(0, index);
        System.out.println("Initials: " + initials);
        
        line = line.substring(index + 1);
        index = line.indexOf(' ');
        plays = Integer.parseInt(line.substring(0, index));
        System.out.println("Plays: " + plays);
        
        line = line.substring(index + 1);
        index = line.indexOf('$');
        revenue = Double.parseDouble(line.substring(index + 1));
        rev = String.format("%.2f", revenue);
        System.out.println("Revenue: $" + rev + "\n");

        Game game = new Game(name, highScore, initials, plays, revenue);
        tree.insert(game);
    }

    public static void printGame(Game searchGame)
    {
        System.out.println("High Score: " + searchGame.getHighScore());
        System.out.println("Initials: " + searchGame.getInitals());
        System.out.println("Plays: " + searchGame.getPlays());
        System.out.println("Revenue: $" + searchGame.getRevenue() + "\n");
    }

    public static void edit(String line, BinTree<Game> tree)
    {
        String name;
        int index, instruction;
        index = line.indexOf("\"");
        line = line.substring(index + 1);

        index = line.indexOf("\"");
        name = line.substring(0, index);

        line = line.substring(index + 2);
        index = line.indexOf(" ");
        instruction = Integer.parseInt(line.substring(0, index));

        line = line.substring(index + 1);

        Game searchGame = new Game();
        searchGame.setName(name);

        searchGame = tree.search(searchGame);
        System.out.println(name + " UPDATED");
        switch(instruction)
        {
            case 1:
                System.out.println("UPDATE TO HIGHSCORE - " + line);
                int hightScore = Integer.parseInt(line);
                searchGame.setHighScore(hightScore);
                break;
            case 2:
                System.out.println("UPDATE TO INITIALS - " + line);
                searchGame.setInitals(line);
                break;
            case 3:
                System.out.println("UPDATE TO PLAYS - " + line);
                int plays = Integer.parseInt(line);
                searchGame.setPlays(plays);
                break;
        }
        printGame(searchGame);
        tree.insert(searchGame);
    }

}