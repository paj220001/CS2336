// Pranav Joseph 
//paj220001
public class LinkedList
{
    //initialize the private members
    private Node head;
    private Node tail;
    private int count;

    //default constructor 
    public LinkedList()
    {
        head = null;
        tail = null;
    }

    //overloaded constructor to store driver in list
    public void add(Driver driver)
    {
        //create new node instance with driver
        Node node = new Node(driver);

        //if list is empty make the driver the head and set tail to node to 
        if(head == null)
        {
            head = node;
            tail = node;
            node.setNext(null);
            count++;
        }
        else//ifthe list is not empty
        {
            //set next node 
            tail.setNext(node);
            tail = node;
            count++;
        }
        
    }

    //this method prints out the contents of the list by calling each drivers to string method
    public String toString()
    {
        //set current node to head
        Node currentNode = head;

        //while you don't reach the end of the list
        while(currentNode != null)
        {
            //store the area and driver line into line 
            String line = currentNode.toString();

            //print line
            System.out.println(line);
            
            //traverse list
            currentNode = currentNode.getNextDriver();
        }
        return "Done";
    }

    //this function traverses the list to find an area or driver in the list 
    public String search(String searchFor) throws Exception
    {
        //initialize variables
        Node currentNode = head;
        String name, subject = "";
        double area, searchforDouble;
        int count = 0;
        String returnString = searchFor + " not found\n";

        //try to see if searchFor is further valid
        try
        {
            //if the searchfor is a number change subject to area else throw exception
            Double.parseDouble(searchFor);
            subject = "area";
        }
        catch(Exception e)//if is not a number catch exception
        {
            //further look at the search for to see if its valid
            for(char c : searchFor.toCharArray())
            {
                //if word does not have valid character throw exception
                if(!Character.isLetterOrDigit(c) && c != '-' && c != '\'' && c != ' ')
                {
                    throw new Exception();
                }

                //if the word has a space
                if(c == ' ')
                {
                    //increment
                    count += 1;

                    //if it has more than 1 space throw exception
                    if(count > 1)
                    {
                        throw new Exception();
                    } 
                }
                
                //if word is valid set subject
                subject = "name";
            } 
            //set count
            count = 0;
        }

        //traverse the list 
        while(currentNode != null)
        {   
            //set area and name 
            area = currentNode.getArea();
            name = currentNode.getName();
            if(subject.equals("name") && name.equals(searchFor))//if we are looking for the name and name = searchfor 
            {
                //call the nodes toStrign and return
                returnString = currentNode.toString();
            }
            else if (subject.equals("area"))//if we are looking for area
            {
                //set searchForDouble
                searchforDouble = Double.parseDouble(searchFor);

                //format the searchfor and store it back into searchFordouble
                searchFor = String.format("%.2f", searchforDouble);
                searchforDouble = Double.parseDouble(searchFor);
                
                //if they are the same 
                if(area == searchforDouble)
                {
                    //call the nodes toStrign and return
                    returnString = currentNode.toString();
                }
            }
        //traverse the list 
        currentNode = currentNode.getNextDriver();

        }

        //return the string 
        return returnString;
    }

    //the sort method sorts the values of the list based on the static comparison variable in driver
    public void sort()
    {
        //initialize variables
        Node currentNode, nextNode, previousNode;
        int compareInt;
        int i = 0;
        boolean swap;
        
        //keep going for the amount of the list 
        while(i < count)
        {
            //set the nodes to traverse and swap to false
            currentNode = head;
            nextNode = head;
            previousNode = head;
            swap = false;
        
            //while you havent reached the end of the list
            while(currentNode.getNextDriver() != null)
            {
                //set next node what current is pointing to 
                nextNode = currentNode.getNextDriver();

                //call the driver compareTo method to compare next and current
                compareInt = currentNode.getDriver().compareTo(nextNode.getDriver());
                //if next is less than current
                if(compareInt > 0)
                {
                    //if current is the first node
                    if(currentNode == head)
                    {
                        //set head to point to the next
                        head = nextNode;

                        //current now points to next's next driver
                        currentNode.setNext(nextNode.getNextDriver());

                        //next now points to current 
                        nextNode.setNext(currentNode);

                        //set previous
                        previousNode = head;
                    }
                    else//if list is not empty
                    {
                        //node before current points to next node
                        previousNode.setNext(nextNode);

                        //current node points to node after nextNode
                        currentNode.setNext(nextNode.getNextDriver());

                        //next node points to current node 
                        nextNode.setNext(currentNode);
                        
                    }
                    //set swap to true
                    swap = true;
                }

                //move to next nodes
                previousNode = currentNode;
                currentNode = currentNode.getNextDriver();
                
                //if reached end of list break
                if(currentNode == null)
                {
                    break;
                }

            }
            
            //if no swaps ocurred break
            if(swap == false)
                break;

            //increment 
            i++;
        }
    }

    
}