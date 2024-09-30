import java.util.ArrayList;

public class GenericList <G extends Comparable<G>>
{
    private ArrayList<G> list;

    public GenericList()
    {
        list = new ArrayList<>();
    }

    public GenericList(ArrayList<G> userList)
    {
        list = new ArrayList<>(userList);
    }

    public void setList(G add)
    {
        list.add(add);
    }

    public ArrayList<G> getList()
    {
        return list;
    }

    public void InsertionSort()
    {
        for(int i = 1; i < list.size(); i++)
        {
            int index = i - 1;
            G value = list.get(i);

            while(index >= 0 && list.get(index).compareTo(value) > 0)
            {
                list.set(index + 1, list.get(index));
                index = index-1;
            }
            list.set(index + 1, value);
        }
    }

    public boolean BinarySearch(G search)
    {
        boolean found = false;
        for(int i = 0; i < list.size(); i++)
        {
            if(list.get(i).equals(search))
            {
                found = true;
            }
        }

        return found;
    }


}