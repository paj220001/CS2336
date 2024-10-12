// Pranav Joseph paj220001
import java.io.*;
import java.util.Scanner;

public class Main
{
    public static void main(String [] args) throws IOException
    {
        String fileName;
        Scanner scnr = new Scanner(System.in);

        System.out.print("Please enter the filename: ");
        fileName = scnr.nextLine();

        try
        {
            Scanner fileReader = new Scanner(new File(fileName));
            readFile(fileReader);
        }
        catch(FileNotFoundException e)
        {
            System.out.println(fileName + " was not found.");
        }

        scnr.close();
    }

    public static void readFile(Scanner fileReader)
    {
        String line, part1, part2, symbol;
        int index;
        while(fileReader.hasNext())
        {
            line = fileReader.nextLine();

            if(line.equals(""))
            {
                break;
            }

            System.out.print(line + "\t");

            index = line.indexOf(" ");
            part1 = line.substring(0, index);
            line = line.substring(index + 1);

            index = line.indexOf(" ");
            symbol = line.substring(0, index);
            line = line.substring(index + 1);

            part2 = line;

            //System.out.println(part2);
            setVariables(part1, symbol, part2);
        }
    }

    public static void setVariables(String part1, String symbol, String part2)
    {
        Number num1 = null;
        Complex complex1 = null;
        int index = part1.indexOf('i');

        if(index == -1)
        {
            num1 = new Number(Double.parseDouble(part1));
            
        }
        else
        {
            if(part1.indexOf('+') == -1 && part1.indexOf('-') == -1)
            {
                part1 = part1.substring(0,index);
                complex1 = new Complex(0, Double.parseDouble(part1));
            }
            else
            {
                int index2 = part1.indexOf('-');
                double real, imaginary;
                int index3 = part1.indexOf('+');


                if(index3 != -1)
                {
                    index2 = part1.indexOf('i');
                    real = Double.parseDouble(part1.substring(0, index3));
                    imaginary = Double.parseDouble(part1.substring(index3, index2));
                    complex1 = new Complex(real, imaginary);
                }
                else
                {
                    index3 = part1.indexOf('i');
                    real = Double.parseDouble(part1.substring(0, index2));
                    imaginary = Double.parseDouble(part1.substring(index2, index3));
                    complex1 = new Complex(real, imaginary);
                }
            }
        }
       

        Number num2 = null;
        Complex complex2 = null;
        index = part2.indexOf('i');

        if(index == -1)
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
        if(num1 != null)
        {
            if(num2 != null)
            {
                operation(num1, symbol, num2);
            }
            else if(complex2 != null)
            {
                operation(num1, symbol, complex2);
            }
        }
        else if(complex1 != null)
        {
            if(num2 != null)
            {
                operation(complex1, symbol, num2);
            }
            else if(complex2 != null)
            {
                operation(complex1, symbol, complex2);
            }   
        }
        
    }


    public static void operation( Number num1, String symbol, Number num2)
    {
        String answer = "";
        int num = 0;
        switch(symbol)
        {
            case "+":
                answer = num1.add(num2);
                break;
            case "-":
                answer = num1.sub(num2);
                break;
            case "*":
                answer = num1.mult(num2);
                break;
            case "/":
                answer = num1.div(num2);
                break;
            case "<":
                num = num1.compareTo(num2);
                if(num < 0)
                {
                    answer = "True";
                }
                else
                {
                    answer = "False";
                }
                break;
            case ">":
                num = num1.compareTo(num2);
                if(num > 0)
                {
                    answer = "True";
                }
                else 
                {
                    answer = "False";
                }
                break;
            case "=":
                boolean value = num1.equals(num2);
                if(value)
                {
                    answer = "True";
                }
                else
                {
                    answer = "False";
                } 
                break;
        }

        System.out.println(answer);
    }
}