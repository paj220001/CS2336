// Pranav Joseph paj220001
import java.io.*;
import java.util.Scanner;


public class Main
{
    static final int MAX_DRIVERS = 20;
    static final int MAX_COORDINATES = 16;
    static final int THIRD_DIMENSION = 2;
    public static void main(String [] args) throws IOException
    {
        

        //create variable needed for operations
        String inputFileName;
        String [] drivers = new String[MAX_DRIVERS];
        double [][][] coordinateArray = new double[MAX_DRIVERS][MAX_COORDINATES][THIRD_DIMENSION];
        double [] areaOFP = new double[MAX_DRIVERS];
        int counter = 0;
        int [] points = new int[MAX_DRIVERS];
        
        //create scanner to read from the user 
        Scanner input = new Scanner(System.in);

        //ask user for the file name and store it
        System.out.print("Please enter the filename of driver data: ");
        inputFileName = input.nextLine();

        //create scanner to read from the file 
        Scanner fileReader = new Scanner(new File(inputFileName));

        if(fileReader.hasNext())//if there is a next element 
        {
            //call the readFile function to read from the file and get count and return it into counter
           counter = readFile(drivers, coordinateArray, counter, points, fileReader);
            
            for(int x = 0; x < counter; x++)//this goes on for how many drivers there are
            {
                //call the area function to calculate area and store it into the array
                areaOFP[x] = area(coordinateArray, x, points);

                // print out the array with 2 decimal points
                System.out.printf("%s\t%.2f%n", drivers[x], areaOFP[x]);
            }

        }

        //close the scanners
        fileReader.close();
        input.close();
    }


    //this function gets the lines with just the points and pareses each point to store it into its respective 
    //x and y coordinate array value
    public static void getCoordinates(int counter, double [][][] coordinateArray, String [] line, int [] point)
    {
        int index;
        String term;
        
        for(int count = 0; count < counter; count++)// will keep going till each point for each driver is parsed
        {
            //initialize variables
            int i = 0;
            point[count] = 0;

            while(line[count].length() > 0)// makes sure that there are still points left to parse
            {
                //find the location of the next space
                index = line[count].indexOf(' ');

                if(index != -1)// if there is no more spaces
                {
                    //parses 1 point into term
                    term = line[count].substring(0, index);
                    
                    //the rest to th points remain in line
                    line[count] = line[count].substring(index + 1);
                }
                else 
                {
                    //last remaing ppount
                    term = line[count];
                    line[count] = "";

                }

                //find the index of the comma seperating the x and y 
                index = term.indexOf(',');

                //store the string turned into double x value before the comma
                coordinateArray[count][i][0] = Double.parseDouble(term.substring(0, index));

                //store the string turned into double y value after the comma
                coordinateArray[count][i][1] = Double.parseDouble(term.substring(index + 1));

                //increment
                i++;
                point[count]++;
            }
            
        }
    }

    //this function reads each line from the routes file and stores it into a line array. Then it parses the string into 2 arrays
    // one is the array with just the name of the drivers and the other with the points of the drivers. It then sends those arrays
    // to get coordinates to be further parsed 
    public static int readFile(String [] drivers, double [][][] coordinateArray, int counter, int [] points, Scanner scanner)
    {
        //initialize varibles
        String [] line = new String[MAX_DRIVERS]; 

        //counter is the amount of drivers
        counter = 0;

        while(scanner.hasNext())//while there is a next element in the file
        {
            //scanner reads the whole line and stores it into the line array 
            line[counter] = scanner.nextLine();

            if(line[counter].compareTo("") == 0)//if the line is empty it will break 
            {
                break;
            }

            //find the space so you know where to parse
            int index = line[counter].indexOf(' ');

            //seperate everything before the space into the drivers array
            drivers[counter] = line[counter].substring(0, index); 

            //everything after the space is left in the line array 
            line[counter] = line[counter].substring(index + 1);

            //increment counter
            counter++;
        
        }

        //send the line array so it can be parsed and stored in in coordineareArray
        getCoordinates(counter, coordinateArray, line, points);

        //return the amount of drivers
        return counter;
    }

    //this function calculates the area of the drivers route using all the points
    public static double area (double [][][] coordinateArray, int counter, int [] points)
    {
        //initalize sum
        double sum = 0.0;

        //calculate the first instance of sum
        sum = (coordinateArray[counter][1][0] + coordinateArray[counter][0][0]) * (coordinateArray[counter][1][1] - coordinateArray[counter][0][1]);

        for(int i = 1; i < points[counter] - 1; i++)//this will calculate the sum for the rest of the points
        {
            sum += (coordinateArray[counter][i + 1][0] + coordinateArray[counter][i][0]) * (coordinateArray[counter][i + 1][1] - coordinateArray[counter][i][1]);
        }

        //return the absolute value of the sum and divide it in half
        return 0.5 * Math.abs(sum);
    }

}