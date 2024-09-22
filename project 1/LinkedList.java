public class LinkedList
{
    private Node head;
    private Node tail;
    private int count;

    public LinkedList()
    {
        head = null;
        tail = null;
    }

    public void add(Driver driver)
    {
        Node node = new Node(driver);
        if(head == null)
        {
            head = node;
            tail = node;
            node.setNext(null);
            count++;
        }
        else
        {
            tail.setNext(node);
            tail = node;
            count++;
        }
        
    }

    public String toString()
    {
        Node currentNode = head;
        while(currentNode != null)
        {
            String line = currentNode.toString();
            System.out.println(line);
            currentNode = currentNode.getNextDriver();
        }
        return "Done";
    }

    public String search(String searchFor)
    {
        Node currentNode = head;
        String area, name;
        String returnString = searchFor + " not found\n";
        while(currentNode != null)
        {
            area = currentNode.getArea();
            name = currentNode.getName();
            if(area.equals(searchFor) || name.equals(searchFor))
            {
                returnString = currentNode.toString();
            }

            currentNode = currentNode.getNextDriver();

        }

        return returnString;
    }

    public void sort()
    {
        Node currentNode, nextNode, previousNode;
        int compareInt;
        int i = 0;
        boolean swap;
        
        while(i < count)
        {
            currentNode = head;
            nextNode = head;
            previousNode = head;
            swap = false;
        
            while(currentNode.getNextDriver() != null)
            {
                nextNode = currentNode.getNextDriver();
                compareInt = currentNode.getDriver().compareTo(nextNode.getDriver());
                if(compareInt < 0)
                {
                    if(currentNode == head)
                    {
                        head = nextNode;
                        currentNode.setNext(nextNode.getNextDriver());
                        nextNode.setNext(currentNode);
                        previousNode = head;
                    }
                    else
                    {
                        previousNode.setNext(nextNode);
                        currentNode.setNext(nextNode.getNextDriver());
                        nextNode.setNext(currentNode);
                        
                    }
                    swap = true;
                }
                previousNode = currentNode;
                currentNode = currentNode.getNextDriver();
                
                if(currentNode == null)
                {
                    break;
                }

            }
            
            if(swap == false)
                break;

            i++;
        }
    }

    
}