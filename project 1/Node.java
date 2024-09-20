public class Node
{
    private Driver driver;
    private Node nextPtr = null;

    public Node()
    {
       driver = new Driver();
    }

    public Node(Driver driver)
    {
        this.driver = driver;
    }

    public Driver getDriver()
    {
        return driver;
    }

    public Node getNextDriver()
    {
        return nextPtr;
    }

    public String getName()
    {
        return driver.getName();
    }

    public String getArea()
    {
        String areaString = String.format("%.2f", driver.getArea());
        return areaString;
    }

    public void setDriver(Driver driver)
    {
        this.driver = driver;
    }

    public void setNext(Node nextDriver)
    {
        nextPtr = nextDriver;
    }

    public String toString()
    {
        return driver.toString();
    }
}