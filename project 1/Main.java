// Pranav Joseph paj220001
import java.io.*;
import java.util.Scanner;


public class Main
{
    public static void main(String [] args) throws IOException
    {
        

        //create variable needed for operations
        String inputFileName, commandFileName;
        LinkedList list = new LinkedList(); 
        
        //create scanner to read from the user 
        Scanner input = new Scanner(System.in);

        //ask user for the file name and store it
        System.out.print("Please enter the filename of driver data: ");
        inputFileName = input.nextLine();

        //create scanner to read from the file 
        Scanner fileReader = new Scanner(new File(inputFileName));
        readFile(fileReader, list);
        list.toStringy();

        System.out.print("Please enter the command file name: ");
        commandFileName = input.nextLine();

        Scanner commands = new Scanner(new File(commandFileName));
        readCommandFile(commands, list);

        Driver.setComparison("driver");
        list.sort();
        list.toStringy();
        
        input.close();
        commands.close();
        
    }

    public static void readFile(Scanner scanner, LinkedList list)
    {
        String line;
        String driver;
        while(scanner.hasNext())
        {
            line = scanner.nextLine();

            if(line.compareTo("") == 0)
            {
                break;
            }

            int index = line.indexOf(' ');

            //seperate everything before the space into the drivers array
            driver = line.substring(0, index); 

            //everything after the space is left in the line array 
            line = line.substring(index + 1); 
            
            storeArea(driver, line, list);
        }
    }

    public static void storeArea(String driverName, String line, LinkedList list)
    {
        int index; 
        String term;
        double area = 0.0;
        double x, x2, y, y2;
        while(line.length() > 0 )
        {
            index = line.indexOf(' ');

            if(index != -1)
            {
                term = line.substring(0, index);

                line = line.substring(index + 1);
            }
            else
            {
                term = line;
                line = "";
            }

            index = term.indexOf(',');

            x = Double.parseDouble(term.substring(0, index));
            y = Double.parseDouble(term.substring(index + 1));

            index = line.indexOf(' ');

            if(index != -1)
            {
                term = line.substring(0, index);
            }
            else
            {
                term = line;
                line = "";
            }

            index = term.indexOf(',');

            x2 = Double.parseDouble(term.substring(0, index));
            y2 = Double.parseDouble(term.substring(index + 1));

            area += (x2 + x) * (y2 - y);

        }

        area = 0.5 * Math.abs(area);
        Driver driverInfo = new Driver(driverName, area);
        list.add(driverInfo);
    } 

    public static void readCommandFile(Scanner scanner, LinkedList list)
    {
        String command, result;
        while(scanner.hasNext())
        {
            command = scanner.nextLine();
            result = list.search(command);

            System.out.println(result);

        }
    }


}

