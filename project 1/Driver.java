// Pranav Joseph 
//paj220001
public class Driver
{
    //initialize the private member
    private String name;
    private double area;
    private static String comparison;

    //constructor
    public Driver()
    {
        name = "noName!";
        area = -1;
    }

    //overloaded constructor sets the private members to the driver and area values
    public Driver(String name, double area)
    {
        this.name = name;
        this.area = area;
    }

    //this function returns the name of the driver
    public String getName()
    {
        return name;
    }

    //this function returns the name of the driver
    public double getArea()
    {
        return area;
    }

    //this function gets the comparison member
    public static String getComparison()
    {
        return comparison;
    }

    //this function changes to name of the driver to the given name
    public void changeName(String name)
    {
        this.name = name;
    }

    //this function changes to area of the driver to the given area
    public void changeArea(double area)
    {
        this.area = area;
    }

    //this function sets the comparison string to the comparison given by the user
    public static void setComparison(String string)
    {
        comparison = string;
    }

    //this method returns the drivers name plus the formated area to 2 digits
    public String toString()
    {
        String areaString = String.format("%.2f", area);
        String line = name + "\t" + areaString;
        return line;
    }
    
    //this function compares the driver based on the comparison word to the driver given by the user 
    public int compareTo(Driver driver)
    {
        //initialize variables 
        double compareInt = 0;

        //if the comparison word is area
        if(comparison.equals("area"))
        {
            //set compare int to the original area - the given area
            compareInt = area - driver.getArea();
        }
        else if(comparison.equals("driver"))//if the word is driver
        {
            //set compare int to the original name - the given name
            compareInt = name.compareTo(driver.getName());
        }

        //return the compare int
        return (int)compareInt;
    }
}