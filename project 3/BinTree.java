import java.util.*;

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

    public g delete(g line)
    {
        Node<g> cur = root, parent = root;

        while(cur != null)
        {
            if(line.compareTo(cur.getData()) < 0)
            {
                parent = cur;
                cur = cur.getLeftPtr();
            }
            else if(line.compareTo(cur.getData()) > 0)
            {
                parent = cur;
                cur = cur.getRightPtr();
            }
            else
            {
                break;
            }
        }

        Node<g> returnValue = new Node<>();
        returnValue.setData(cur.getData());
        deleteRec(cur, parent);

        return returnValue.getData();
        
    }

    private Node<g> deleteRec(Node<g> cur, Node<g> parent)
    {

        if(cur != null)
        {
            if(cur.getLeftPtr() == null && cur.getRightPtr() == null)
            {
                if(parent == cur)
                {
                    root = null;
                }
                else if(parent.getLeftPtr() == cur)
                {
                    parent.setLeftPtr(null);
                }
                else
                {
                    parent.setRightPtr(null);
                }
            }
            else if(cur.getLeftPtr() == null)
            {
                if(parent == cur)
                {
                    root = cur.getRightPtr();
                    cur = null;
                }
                else if(parent.getLeftPtr() == cur)
                {
                    parent.setLeftPtr(cur.getRightPtr());
                }
                else
                {
                    parent.setRightPtr(cur.getRightPtr());
                }
            }
            else if(cur.getRightPtr() == null)
            {
                if(parent == cur)
                {
                    root = cur.getLeftPtr();
                    cur = null;
                }
                else if(parent.getRightPtr() == cur)
                {
                    parent.setRightPtr(cur.getLeftPtr());
                }
                else
                {
                    parent.setLeftPtr(cur.getLeftPtr());
                }
            }
            else
            {
                parent = cur;

                Node<g> successor = cur.getLeftPtr();

                while (successor.getRightPtr() != null) 
                {
                    parent = successor;
                    successor = successor.getRightPtr();
                }


                cur.setData(successor.getData());
                deleteRec(successor, parent);
            }
            
        }

        return cur;
    }

    public void Sort()
    {
        if(root == null)
        {
            return;
        }
        System.out.println("RECORDS SORTED");
        inorder(root);
    }

    private void inorder(Node<g> current)
    {
        if(current == null)
        {
            return;
        }

        inorder(current.getLeftPtr());

        System.out.print(current.getData());

        inorder(current.getRightPtr());
    }

    public int depth()
    {
        return depthRec(root);
    }

    private int depthRec(Node<g> cur)
    {
        if(cur == null)
        {
            return 0;
        }
        int leftDepth = depthRec(cur.getLeftPtr());
        int rightDepth = depthRec(cur.getRightPtr());

        return Math.max(leftDepth, rightDepth) + 1;
    }

    public String breadthTraverse()
    {
        Queue<Node<g>> queue = new LinkedList<>();
        queue.add(root);

        String line = "";

        if(root != null)
        {
            while(!queue.isEmpty())
            {
                Node<g> cur = queue.poll();
                line += cur.toString();

                if(cur.getLeftPtr() != null)
                {
                    queue.add(cur.getLeftPtr());
                }

                if(cur.getRightPtr() != null)
                {
                    queue.add(cur.getRightPtr());
                }
            }
        }

        return line;
    }
    
}