public class BinTree<g extends Comparable<g>>
{
    private Node<g> root;

    public BinTree()
    {
        root = null;
    }

    public Node<g> insert(g data)
    {
        if(root == null)
        {
            root = new Node<>();
            root.setData(data);
            //System.out.println(root);
            return root;
        }
        return insertRec(root, data);
    }

    private Node<g> insertRec(Node<g> current, g data)
    {
        if(current == null)
        {
            Node<g> newNode = new Node<>();
            newNode.setData(data);
            //System.out.println(newNode);
            return newNode;
        }
        if(data.compareTo(current.getData()) < 0)
        {
            current.setLeftPtr(insertRec(current.getLeftPtr(), data));
        }
        else if(data.compareTo(current.getData()) > 0)
        {
            current.setRightPtr(insertRec(current.getRightPtr(), data));
        }

        return current;
    }

    public Node<g> search(g line)
    {
        return searchRec(root, line);
    }

    private Node<g> searchRec(Node<g> current, g line)
    {
        
        if(line.compareTo(current.getData()) == 0)
        {
            return current;
        }
        else if(line.compareTo(current.getData()) < 0)
        {
            current.setLeftPtr(searchRec(current.getLeftPtr(), line));
        }
        else
        {
            current.setRightPtr(insertRec(current.getRightPtr(), line));
        }

        return current;
    }

    public void Sort()
    {
        if(root == null)
        {
            return;
        }

        inorder(root);
    }

    private void inorder(Node<g> current)
    {
        if(current == null)
        {
            return;
        }

        inorder(current.getLeftPtr());

        System.out.println(current.getData());

        inorder(current.getRightPtr());
    }

}