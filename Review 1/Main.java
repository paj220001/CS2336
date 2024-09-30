
public class Main
{
    public static void main(String [] args)
    {
        String value = "101011";
        value.indexOf
        try
        {
            int dec = bin2Dec(value);
            System.out.println(dec);
        }
        catch(BinaryFormatException e)
        {
            System.out.println(e.getMessage());
        }

        /*ArrayList<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("Bye");
        list.add("pranav");
        list.add("joseph");

        GenericList<String> listG = new GenericList<>(list);
        listG.InsertionSort();
        list = listG.getList();
        for(int i = 0; i < list.size(); i++)
        {
            System.out.println(list.get(i));
        }*/
    }

    public static int bin2Dec(String line) throws BinaryFormatException
    {
        int dec = 0;
        int index = line.length();
        for(int i = 0; i < line.length(); i++)
        {
            
            int point = Integer.parseInt(line.substring((index - 1), index));
            if(point == 1)
            {
                dec += Math.pow(2, i);
                
            }
            else if(point != 0)
            {
                throw new BinaryFormatException(line);
            }

            index = index - 1;
        }

        return dec;
    }
}