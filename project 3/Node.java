public class Node<g extends Comparable<g>> implements Comparable<Node<g>>
{
    // Pointer to the right child node
    private Node<g> rightPtr;
    
    // Pointer to the left child node
    private Node<g> leftPtr;
    
    // Data stored in the node, of generic type g
    private g data;

    // Default constructor initializes left and right pointers to null
    public Node()
    {
        leftPtr = null;
        rightPtr = null;
    }

    // Constructor to initialize left and right pointers with given nodes
    public Node(Node<g> leftPtr, Node<g> rightPtr) {
        this.leftPtr = leftPtr;
        this.rightPtr = rightPtr;
    }

    // Override of toString() to return string representation of data
    @Override 
    public String toString()
    {
        return data.toString();
    }

    // Implementation of compareTo() to compare nodes based on their data
    @Override 
    public int compareTo(Node<g> compValue) {
        return data.compareTo(compValue.getData());
    }

    // Getter method for the left child pointer
    public Node<g> getLeftPtr() {
        return leftPtr;
    }

    // Getter method for the right child pointer
    public Node<g> getRightPtr() {
        return rightPtr;
    }

    // Setter method to set the left child pointer to a new node
    public void setLeftPtr(Node<g> newPtr)
    {
        this.leftPtr = newPtr;
    }

    // Setter method to set the right child pointer to a new node
    public void setRightPtr(Node<g> newPtr)
    {
        this.rightPtr = newPtr;
    }

    // Getter method for retrieving the data stored in this node
    public g getData() {
        return data;
    }

    // Setter method to set the data of this node
    public void setData(g data) {
        this.data = data;
    }
}