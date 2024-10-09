// Pranav Joseph 
//paj220001
import java.io.*;
import java.util.Scanner;


public class Main
{
    public static void main(String [] args) throws IOException
    {
        //create variable needed for operations
        String fileName;
        LinkedList list = new LinkedList(); 
        
        //create scanner to read from the user 
        Scanner input = new Scanner(System.in);

        //ask user for the file name and store it
        System.out.println("Please enter the routes file: ");
        fileName = input.nextLine();
        //if the files are not found
        try 
        {
        //create scanner to read from the file 
            Scanner fileReader = new Scanner(new File(fileName));

            //call the read file function
            readFile(fileReader, list);

            //call the toString method to print out the contents of the list 
            System.out.println("\n");
            list.toString();
            
            //ask user for the command file name and print it
            System.out.println("Please enter the command file: ");
            fileName = input.nextLine();

            //create scanner to read from the file 
            Scanner commands = new Scanner(new File(fileName));

            System.out.println("\n");

            //call the readcommandFile to read forom the file
            readCommandFile(commands, list);

            //close the files
            input.close();
            commands.close();
        }
        catch(FileNotFoundException e)//if the files are not found catch the exception
        {
            //print out message
            System.out.println(fileName + " not found.");
        }
        
    }

    //this function reads the driver and the points from the file and stores the driver and sends the rest to store area to get the area
    public static void readFile(Scanner scanner, LinkedList list)
    {
        //initialize variables
        String line;
        int count = 0;
        char ch;
        String driver;

        //read from the file as long as it has something in it 
        while(scanner.hasNext())
        {
            //try anything that sends exception
            try
            {  
                //store lines from the file in line
                line = scanner.nextLine();

                //if line is empty break 
                if(line.compareTo("") == 0)
                {
                    break;
                }

                int index = -1;
                for(int i = 0; i < line.length(); i++)
                {
                    //find the index of the first number and store it in index
                    if (Character.isDigit(line.charAt(i)) || line.charAt(i) == '-') 
                    {
                        index = i;

                        //if the character behind the number is not a space it is wrong formatting so throw exception
                        ch = line.charAt(index-1);
                        if(ch != ' ')
                        {
                            throw new Exception();
                        }
                        break;
                    }
                }

                //seperate everything before the number
                driver = line.substring(0, index-1);
                for(char c : driver.toCharArray())
                {
                    //if the name contains invalid elements throw and exception
                    if(!Character.isLetterOrDigit(c) && c != '-' && c != '\'' && c != ' ')
                    {
                        throw new Exception();
                    }

                    //if there is a in the name 
                    if(c == ' ')
                    {
                        //increment count
                        count += 1;

                        //if there is more than 1 space throw an exception
                        if(count > 1)
                        {
                            throw new Exception();
                        } 
                    }
                } 
                //reset count
                count = 0;

                //everything after the space is left in the line array 
                line = line.substring(index); 

                //with the remaining points find if there is set it to index
                index = line.indexOf('.');

                //if it does exist
                if(index > -1)
                {
                    // set pointAfter the point right after the .
                    char pointAfter = line.charAt(index + 1);

                    //if the char right after the point is not a number then trhow an exception
                    if(!Character.isDigit(pointAfter))
                    {
                        throw new Exception();
                    }
                }

                //send the values to find the area
                storeArea(driver, line, list);
            }
            catch(Exception e)//if there is anything wrong with the drivr line then skip it 
            {
                line = null;
            }
        }
    }

    //this function gets the remaining points string and creates the points to calculate the area
    public static void storeArea(String driverName, String line, LinkedList list) throws Exception
    {
        //initialize the variables
        int index; 
        String term;
        String firstPoint, lastPoint;
        double area = 0.0;
        double x, x2, y, y2;

        //find the index of the point seperation
        index = line.indexOf(' ');

        //find the first and last points
        firstPoint = line.substring(0, index);
        lastPoint = line.substring(line.lastIndexOf(' ') + 1, line.length());

        //if the first point is the same as the last point
        if(firstPoint.equals(lastPoint))
        {
            //keep going for the length of the line
            while(line.length() > 0 )
            {
                //find the point seperater
                index = line.indexOf(' ');

                //if the line is not empty
                if(index != -1)
                {
                    //store the point into term
                    term = line.substring(0, index);

                    //cut the point of the line
                    line = line.substring(index + 1);
                }
                else
                {
                    term = line;
                    line = "";
                }

                //seperate x and y
                index = term.indexOf(',');

                //store x and y values 
                x = Double.parseDouble(term.substring(0, index));
                y = Double.parseDouble(term.substring(index + 1));

                //find the point seperater
                index = line.indexOf(' ');

                //if the line is not empty
                if(index != -1)
                {
                    //store the point into term
                    term = line.substring(0, index);
                }
                else
                {
                    term = line;
                    line = "";
                }
                //seperate x and y
                index = term.indexOf(',');

                //store x2 and y2
                x2 = Double.parseDouble(term.substring(0, index));
                y2 = Double.parseDouble(term.substring(index + 1));

                //calculate first part of area
                area += (x2 + x) * (y2 - y);

            }

            //calculare second part of area 
            area = 0.5 * Math.abs(area);

            //store the driverInfo in a driver instance
            Driver driverInfo = new Driver(driverName, area);

            //add it to the linked list
            list.add(driverInfo);
        }
        else//if there is an error in the number this will catch it
        {
            throw new Exception("");
        }
    } 

    //this function reads the commands that should be formed and calls the correct method needed
    public static void readCommandFile(Scanner scanner, LinkedList list)
    {
        //initialize variables
        String command, result;
        int count = 0;
        
        //while the file is not empty
        while(scanner.hasNext())
        {
            //make sure the commands are valid
            try
            {
                //store command
                command = scanner.nextLine();
                
                //check the command to see if it is valid 
                for(char c : command.toCharArray())
                    {
                        //if the char is not valid throw an exception
                        if(!Character.isLetterOrDigit(c) && c != '-' && c != '\'' && c != ' ' && c != '.')
                        {
                            throw new Exception();
                        }

                        //if there is a space in the commnds
                        if(c == ' ')
                        {
                            //increment
                            count += 1;
                            
                            //if there is more than 1 space in the command throw an exception
                            if(count > 1)
                            {
                                throw new Exception();
                            } 
                        }
                    } 
                //reset count
                count = 0;

                //if sort area
                if(command.equals("sort area"))
                {
                    //set what is being compared
                    Driver.setComparison("area");

                    //sort nased on comparison
                    list.sort();

                    //print out the list
                    list.toString();
                    System.out.println("\n");
                }
                else if(command.equals("sort driver"))//if sort area
                {
                    //set what is being compared to driver
                    Driver.setComparison("driver");

                    //sort nased on comparison
                    list.sort();

                    //print out the list
                    list.toString();
                    System.out.println("\n");
                }
                else //if search
                {
                    //call the seach method and store the return in result and print
                    result = list.search(command);
                    System.out.println(result + '\n');

                }
            }
            catch(Exception e)//if the command is not valid skip it
            {
                command = null;
            }

        }   
    }


}

