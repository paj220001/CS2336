// Pranav Joseph 
//paj220001
public class Node
{
    //initialize the private members
    private Driver driver;
    private Node nextPtr = null;

    //default constructor
    public Node()
    {
       driver = new Driver();
    }

    //overloaded conatructor
    public Node(Driver driver)
    {
        this.driver = driver;
    }

    //returns the driver instance
    public Driver getDriver()
    {
        return driver;
    }

    //returns the instance of the nextptr
    public Node getNextDriver()
    {
        return nextPtr;
    }

    //calls the drivers getlname method to get and return name
    public String getName()
    {
        return driver.getName();
    }

    //this function gets the area of the driver for the getArea method and turns it into a doubke and returns it
    public double getArea()
    {
        double area = Double.parseDouble(String.format("%.2f", driver.getArea()));
        return area;
    }

    //this function sets the driver method to the user given driver instance
    public void setDriver(Driver driver)
    {
        this.driver = driver;
    }

    //this function sets the nextdriver method to the user given driver instance
    public void setNext(Node nextDriver)
    {
        nextPtr = nextDriver;
    }

    //calls the drivers to string method to return the area and name
    public String toString()
    {
        return driver.toString();
    }
}