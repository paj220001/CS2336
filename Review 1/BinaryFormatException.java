public class BinaryFormatException extends Exception
{
    private String num;

    public BinaryFormatException(String number)
    {
        num = number;
    }

    public String getNum()
    {
        return num;
    }

    public String getMessage()
    {
        return num;
    }   
}