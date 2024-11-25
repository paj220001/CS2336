import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scnr = new Scanner(System.in);
        String graphFileName, routeFileName;

        String[] array = new String[10];

        System.out.print("Enter the name of the graph data file: ");
        graphFileName = scnr.next();


        try
        {
            Scanner graphScnr = new Scanner(new File(graphFileName));
            readGraph(graphScnr);

            System.out.print("Enter the name of the route data file: ");
            routeFileName = scnr.next();
            
            Scanner routeScnr = new Scanner(new File(routeFileName)); 
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }
    }

    public static void readGraph(Scanner scnr)
    {
        while(scnr.hasNext())
        {
            
        }
    }

}