import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scnr = new Scanner(System.in);
        String graphFileName, routeFileName;
        Graph graph = new Graph();

        System.out.print("Enter the name of the graph data file: ");
        graphFileName = scnr.next();


        try
        {
            Scanner graphScnr = new Scanner(new File(graphFileName));
            readGraph(graphScnr, graph);

            System.out.print("Enter the name of the route data file: ");
            routeFileName = scnr.next();
            
            Scanner routeScnr = new Scanner(new File(routeFileName)); 
            readRoute(routeScnr, graph);

            //graph.print();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }
    }

    public static void readGraph(Scanner scnr, Graph graph)
    {
        while(scnr.hasNext())
        {
            String node1, node2, line;
            int index, weight;

            line = scnr.nextLine();
            index = line.indexOf(" ");
            node1 = line.substring(0, index);
            graph.addNode(node1);
            line = line.substring(index + 1);
            
            while(!line.isEmpty())
            {
                index = line.indexOf(",");
                node2 = line.substring(0, index);
                graph.addNode(node2);
                weight = Integer.parseInt(line.substring(index + 1, index + 2));
                graph.addEdge(node1, node2, weight);
                

                try
                {
                    line = line.substring(index + 3);
                }
                catch(Exception e)
                {
                    break;
                }
            }
        }
    }

    public static void readRoute(Scanner scnr, Graph graph)
    {
        while(scnr.hasNext())
        {
            int index;
            String name, node1, node2, line = scnr.nextLine();

            index = line.indexOf(" ");

            name = line.substring(0, index);

            line = line.substring(index + 1);

            
                while(!line.isEmpty())
                {
                    try
                    {
                        index = line.indexOf(" ");

                        node1 = line.substring(0, index);

                        line = line.substring(index + 1);

                        index = line.indexOf(" ");

                        if(index != -1)
                        {
                            node2 = line.substring(0, index);
                        }
                        else
                        {
                            node2 = line;
                        }
                        
                        
                    }
                    catch(Exception e)
                    {
                        break;
                    }
                }



        }
    }

}