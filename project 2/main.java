// Pranav Joseph paj220001
import java.io.*;
import java.util.Scanner;

public class Main
{
    public static void main(String [] args) throws IOException
    {
        //create varible for file name 
        String fileName;

        //create scanner for user input
        Scanner scnr = new Scanner(System.in);

        //ask for the filename and store it into the file name 
        System.out.print("Please enter the filename: ");
        fileName = scnr.nextLine();

        try
        {
            //create a scanner to read the file
            Scanner fileReader = new Scanner(new File(fileName));
            //call the method to read the file
            readFile(fileReader);

            //close file scanner
            fileReader.close();
        }
        catch(FileNotFoundException e)//if the file is not found 
        {
            //print error message 
            System.out.println(fileName + " was not found.");
        }

        //close the scanners
        scnr.close();
    }

    public static void readFile(Scanner fileReader)
    {
        //create variables
        String line, part1, part2, symbol, line2;
        int index;

        while(fileReader.hasNext())//while the the file is not empty
        {
            try
            {
                //read the line 
                line = fileReader.nextLine();

                if(line.equals(""))//if line is empty break
                {
                    break;
                }

                //make a copy of the line
                line2 = line;

                //get the first part of the equation
                index = line.indexOf(" ");
                part1 = line.substring(0, index);
                line = line.substring(index + 1);

                //get the symbol of the equation
                index = line.indexOf(" ");
                symbol = line.substring(0, index);
                line = line.substring(index + 1);

                //the last value in the eqaution 
                part2 = line;

                //call the set variable function
                setVariables(part1, symbol, part2, line2);
            }
            catch (Exception e)//if anything causes an expception skip the line 
            {

            }
        }
    }

    public static void setVariables(String part1, String symbol, String part2, String line) throws Exception
    {
        //create the number variables 
        Number num1 = null;
        Complex complex1 = null;

        //find the i in the first part
        int index = part1.indexOf('i');

        if(!part1.contains("i"))//if the number is a real number 
        {
            //initialize a real number 
            num1 = new Number(Double.parseDouble(part1));
            
        }
        else
        {
            if(part1.indexOf('+') == -1 && part1.indexOf('-') == -1)//if the complex number doesnt have a real number 
            {
                //find the number part and store it in the complex variables
                part1 = part1.substring(0,index);
                complex1 = new Complex(0, Double.parseDouble(part1));
            }
            else//the number hs a+bi
            {
                //initialize variables
                double real, imaginary;
                //find the symbol between a and b
                int index2 = part1.indexOf('-');
                int index3 = part1.indexOf('+');

                //if the real number is negative 
                if(index2 == 0)
                {
                    //find if there is another - symbol
                    index2 = part2.indexOf('-', index2 + 1);
                }

                if(index3 != -1)//if the symbol is +
                {
                    //find the i
                    index2 = part1.indexOf('i');

                    //cut real number off
                    real = Double.parseDouble(part1.substring(0, index3));

                    //cut the imaginary number off
                    imaginary = Double.parseDouble(part1.substring(index3, index2));

                    //store into complex
                    complex1 = new Complex(real, imaginary);
                }
                else//if its -
                {
                    //find the i
                    index3 = part1.indexOf('i');

                    //parse the real
                    real = Double.parseDouble(part1.substring(0, index2));

                    //parse the imaginary
                    imaginary = Double.parseDouble(part1.substring(index2, index3));

                    //store it into complex
                    complex1 = new Complex(real, imaginary);
                }
            }
        }
       
        //DO THE SAME AS YOU DID FOR NUM1 FOR NUM2

        Number num2 = null;
        Complex complex2 = null;
        index = part2.indexOf('i');

        if(!part2.contains("i"))
        {
            num2 = new Number(Double.parseDouble(part2));
            
        }
        else
        {
            if(part2.indexOf('+') == -1 && part2.indexOf('-') == -1)
            {
                part2 = part2.substring(0,index);
                complex2 = new Complex(0, Double.parseDouble(part2));
            }
            else
            {
                int index2 = part2.indexOf('-');
                double real, imaginary;
                int index3 = part2.indexOf('+');
                
                if(index2 == 0)
                {
                    index2 = part2.indexOf('-', index2 + 1);
                }

                if(index3 != -1)
                {
                    index2 = part2.indexOf('i');
                    real = Double.parseDouble(part2.substring(0, index3));
                    imaginary = Double.parseDouble(part2.substring(index3, index2));
                    complex2 = new Complex(real, imaginary);
                }
                else
                {
                    index3 = part2.indexOf('i');
                    real = Double.parseDouble(part2.substring(0, index2));
                    imaginary = Double.parseDouble(part2.substring(index2, index3));
                    complex2 = new Complex(real, imaginary);
                }
            }
        }

        if(num1 != null)//if the first number is a real number
        {
            if(num2 != null)//if the second number is a real number
            {
                //call the operation function
                operation(num1, symbol, num2, line);
            }
            else if(complex2 != null)//if the second number is complex
            {
                //call the operation
                operation(num1, symbol, complex2, line);
            }
        }
        else if(complex1 != null)//if complex for num1
        {
            if(num2 != null)//if second number is a real 
            {
                //call operation
                operation(complex1, symbol, num2, line);
            }
            else if(complex2 != null)//is the second number is complex
            {
                //call operation
                operation(complex1, symbol, complex2, line);
            }   
        }
        
    }


    public static void operation( Number num1, String symbol, Number num2, String line) throws Exception
    {
        //store the original line 
        String answer = line;
        //compare number
        int num = 0;
        switch(symbol)
        {
            case "+"://if the symbol is a +
                //call the add method
                answer = num1.add(num2);
                break;
            case "-"://if symbol is -
                //call subtraction method
                answer = num1.sub(num2);
                break;
            case "*"://if symbol is *
                //call the multiplication method 
                answer = num1.mult(num2);
                break;
            case "/"://if the symbol is /
                //call the functiond division method
                answer = num1.div(num2);
                break;
            case "<"://if the symbol is <
                //call the overridden compareTo and store the value
                num = num1.compareTo(num2);
                if(num < 0)//if the compare value is less than 0
                {
                    //means true
                    answer = " true";
                }
                else//if not
                {
                    //its false
                    answer = " false";
                }
                break;
            case ">"://if the symbol is >
                //call the overridden compareto and store the value 
                num = num1.compareTo(num2);
                if(num > 0)//if the compare value is greater than 0
                {
                    //true
                    answer += " true";
                }
                else//if not
                {
                    //fasle
                    answer += " false";
                }
                break;
            case "="://if the symbol is =
                //call the overridden equals function and store the boolean value
                boolean value = num1.equals(num2);
                if(value)//if true
                {
                    //answer = true
                    answer += " true";
                }
                else
                {   
                    //its false
                    answer += " false";
                } 
                break;
            default://if there is another symbol it is irrelevant and ignored
                throw new Exception();
        }

        //print result 
        System.out.println(line + "\t" + answer);
    }
}