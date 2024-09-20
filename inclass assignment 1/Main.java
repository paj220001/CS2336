//Pranav Joseph paj220001
//Ryan Ho RXH200021

import java.util.Scanner;

public class Main
{
    public static void main(String [] args)
    {
        int a,b,c, index;
        double root1, root2;
        String line;

        Scanner scnr = new Scanner(System.in);

        System.out.print("Please enter the equation: ");
        line = scnr.nextLine();

        index = line.indexOf('x');
        a = Integer.parseInt(line.substring(0,index));
        line = line.substring(index + 3);

        index = line.indexOf('x');
        b = Integer.parseInt(line.substring(0, index));
        line = line.substring(index + 1);

        c = Integer.parseInt(line);

        QuadraticEquation equation = new QuadraticEquation(a, b, c);

        root1 = equation.getRoot1();
        root2 = equation.getRoot2();

        System.out.println("Root 1: " + root1 + " Root 2: " + root2);

        scnr.close();
        
    }
}
