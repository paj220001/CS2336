public class Node<g extends Comparable<g>> implements Comparable<Node<g>>
{
    private Node<g> rightPtr;
    private Node<g> leftPtr;
    private g data;

    public Node()
    {
        leftPtr = null;
        rightPtr = null;
    }

    public Node(Node<g> leftPtr, Node<g> rightPtr) {
        this.leftPtr = leftPtr;
        this.rightPtr = rightPtr;
    }

    @Override 
    public String toString()
    {
        return data.toString();
    }

    @Override 
    public int compareTo(Node<g> compValue) {
        return data.compareTo(compValue.getData());
    }

    public Node<g> getLeftPtr() {
        return leftPtr;
    }

    public Node<g> getRightPtr() {
        return rightPtr;
    }

    public void setLeftPtr(Node<g> newPtr)
    {
        this.leftPtr = newPtr;
    }

    public void setRightPtr(Node<g> newPtr)
    {
        this.rightPtr = newPtr;
    }

    public g getData() {
        return data;
    }

    public void setData(g data) {
        this.data = data;
    }
}