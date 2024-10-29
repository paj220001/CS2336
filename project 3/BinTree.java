public class BinTree<g extends Comparable<g>>
{
    private Node<g> root;

    public BinTree()
    {
        root = null;
    }

    public g insert(g data)
    {
        if(root == null)
        {
            root = new Node<>();
            root.setData(data);
            //System.out.println(root);
            return root.getData();
        }
        Node<g> insertNode = insertRec(root, data);
        return insertNode.getData();
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
        else if(data.compareTo(current.getData()) == 0)
        {
            current.setData(data);
        }

        return current;
    }

    public g search(g line)
    {
        try
        {
            Node<g> search = searchRec(root, line);
            if(line.compareTo(search.getData()) != 0)
            {
                return null;
            }
            
            return search.getData();
        }
        catch(NullPointerException e)
        {
            return null;
        }
    }

    private Node<g> searchRec(Node<g> current, g line)
    {
        if(line.compareTo(current.getData()) == 0)
        {
            return current;
        }
        else if(line.compareTo(current.getData()) < 0)
        {
            current = searchRec(current.getLeftPtr(), line);
        }
        else
        {
            current = searchRec(current.getRightPtr(), line);
        }

        
        return current;
    }

    public void Sort()
    {
        if(root == null)
        {
            return;
        }
        System.out.println("RECORDS SORTED ASCENDING");
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