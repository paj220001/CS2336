public class Graph {
    private int size;
    private String[] driverNames;
    private int[][] graphArray;

    public Graph()
    {
        driverNames = new String[10];
        graphArray = new int[10][10];
    }

    public int getSize() {
        return size;
    }

    public void addNode(String edge)
    {
        for(int i = 0; i < driverNames.length; i++)
        {
            if(driverNames[i] == null)
            {
                driverNames[i] = edge;
            }
        }
    }

    public void addEdge(String edge1, String edge2, int weight)
    {
        int index1 = 0, index2 = 0;
        for(int i = 0; i < driverNames.length; i++)
        {
            if(driverNames[i].equals(edge1))
            {
                index1 = i;
            }
            else if(driverNames[i].equals(edge2))
            {
                index2 = i;
            }
        }

        graphArray[index1][index2] = weight;
    }

}
