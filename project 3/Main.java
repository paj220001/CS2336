// Pranav Joseph paj220001
import java.io.*;
import java.util.Scanner;
public class Main
{
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
            System.out.print("Plese enter the name of the update File: ");
            fileName = scnr.nextLine();
            Scanner upScnr = new Scanner(new File(fileName));

            while(upScnr.hasNext())
            {
                String line = upScnr.nextLine();
                update(line, tree);
            }

        }
        catch(FileNotFoundException e)
        {
            System.out.println(fileName + " was not found.");
        }

    
    }

    public static void update(String line, BinTree<Game> tree)
    {
        int instruction = Integer.parseInt(line.substring(0, 1));
        //System.out.println(instruction);
        line = line.substring(2);
        //System.out.println(line);

        switch(instruction)
        {
            case 1:
                line = line.substring(1);
                Game game = addNode(line);
                tree.insert(game);
                break;
            case 2:
                System.out.println(line);
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
        }
    }

    public static Game addNode(String line)
    {
        int index, highScore, plays;
        String name, initials;
        double revenue;

        index = line.indexOf("\"");
        name = line.substring(0, index);

        line = line.substring(index + 2);
        index = line.indexOf(' ');

        highScore = Integer.parseInt(line.substring(0, index));

        line = line.substring(index + 1);
        index = line.indexOf(' ');
        initials = line.substring(0, index);
        
        line = line.substring(index + 1);
        index = line.indexOf(' ');
        plays = Integer.parseInt(line.substring(0, index));
        
        line = line.substring(index + 1);
        index = line.indexOf('$');
        revenue = Double.parseDouble(line.substring(index + 1));

        Game game = new Game(name, highScore, initials, plays, revenue);
        return game;
    }
}