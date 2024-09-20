class Driver
{
    private String name;
    private double area;
    private static String comparison;

    public Driver()
    {
        name = "noName!";
        area = -1;
    }

    public Driver(String name, double area)
    {
        this.name = name;
        this.area = area;
    }

    public String getName()
    {
        return name;
    }

    public double getArea()
    {
        return area;
    }

    public static String getComparison()
    {
        return comparison;
    }

    public void changeName(String name)
    {
        this.name = name;
    }

    public void changeArea(double area)
    {
        this.area = area;
    }

    public static void setComparison(String string)
    {
        comparison = string;
    }

    public String toString()
    {
        String areaString = String.format("%.2f", area);
        String line = name + "\t" + areaString;
        return line;
    }

    public int compareTo(Driver driver)
    {
        double compareInt = 0;
        if(comparison.equals("area"))
        {
            compareInt = area - driver.getArea();
        }
        else if(comparison.equals("driver"))
        {
            compareInt = name.compareTo(driver.getName());
        }

        return (int)compareInt;
    }
}