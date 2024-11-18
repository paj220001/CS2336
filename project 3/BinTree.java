import java.util.*;

public class BinTree<g extends Comparable<g>>
{
    // Private node to hold the root of the binary tree
    private Node<g> root;

    // Default constructor initializes the tree with root set to null
    public BinTree()
    {
        root = null;
    }

    // Function to insert a new data object into the tree
    public g insert(g data)
    {
        if(root == null) // Check if the tree is empty
        {
            // Create a new node for the root and set its data
            root = new Node<>();
            root.setData(data);
            return root.getData(); // Return inserted data
        }
        // If the tree is not empty, insert the data recursively
        Node<g> insertNode = insertRec(root, data);
        return insertNode.getData(); // Return inserted data from inserted node
    }

    // Recursive helper function to insert a new node in the correct position
    private Node<g> insertRec(Node<g> current, g data)
    {
        if(current == null) // Base case: create a new node if current is null
        {
            Node<g> newNode = new Node<>();
            newNode.setData(data);
            return newNode;
        }
        
        // Compare data with current node to decide where to insert
        if(data.compareTo(current.getData()) < 0) // Insert in left subtree
        {
            current.setLeftPtr(insertRec(current.getLeftPtr(), data));
        }
        else if(data.compareTo(current.getData()) > 0) // Insert in right subtree
        {
            current.setRightPtr(insertRec(current.getRightPtr(), data));
        }
        else // If data is equal, update the node data
        {
            current.setData(data);
        }
        
        return current; // Return current node after insertion
    }

    // Function to search for a node containing specific data in the tree
    public g search(g line)
    {
        try
        {
            // Call recursive search helper and store result
            Node<g> search = searchRec(root, line);
            if(line.compareTo(search.getData()) != 0)
            {
                return null; // Return null if data not found
            }
            return search.getData(); // Return found data
        }
        catch(NullPointerException e)
        {
            return null; // Return null if node doesn't exist or error occurs
        }
    }

    // Recursive helper function to search for a specific data value
    private Node<g> searchRec(Node<g> current, g line)
    {
        if(line.compareTo(current.getData()) == 0) // Base case: data found
        {
            return current;
        }
        else if(line.compareTo(current.getData()) < 0) // Search in left subtree
        {
            current = searchRec(current.getLeftPtr(), line);
        }
        else // Search in right subtree
        {
            current = searchRec(current.getRightPtr(), line);
        }

        return current; // Return the current node if data matches
    }

    // Function to delete a node containing specific data from the tree
    public g delete(g line)
    {
        Node<g> cur = root, parent = root;

        // Find the node to delete and keep track of its parent node
        while(cur != null)
        {
            if(line.compareTo(cur.getData()) < 0) // Traverse left
            {
                parent = cur;
                cur = cur.getLeftPtr();
            }
            else if(line.compareTo(cur.getData()) > 0) // Traverse right
            {
                parent = cur;
                cur = cur.getRightPtr();
            }
            else // Node to delete is found
            {
                break;
            }
        }

        // Store the data of the node to be deleted
        Node<g> returnValue = new Node<>();
        returnValue.setData(cur.getData());
        
        // Call recursive helper to delete the found node
        deleteRec(cur, parent);
        return returnValue.getData(); // Return deleted data
    }

    // Recursive helper function to handle deletion of node
    private Node<g> deleteRec(Node<g> cur, Node<g> parent)
    {
        if(cur != null)
        {
            if(cur.getLeftPtr() == null && cur.getRightPtr() == null) // Case: no children
            {
                // Handle deletion if the node is the root or not
                if(parent == cur)
                {
                    root = null; // Tree becomes empty
                }
                else if(parent.getLeftPtr() == cur)
                {
                    parent.setLeftPtr(null); // Disconnect from parent
                }
                else
                {
                    parent.setRightPtr(null); // Disconnect from parent
                }
            }
            else if(cur.getLeftPtr() == null) // Case: only right child exists
            {
                if(parent == cur)
                {
                    root = cur.getRightPtr(); // Update root if deleting root
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
            else if(cur.getRightPtr() == null) // Case: only left child exists
            {
                if(parent == cur)
                {
                    root = cur.getLeftPtr(); // Update root if deleting root
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
            else // Case: two children exist
            {
                // Find in-order predecessor to replace current node
                parent = cur;
                Node<g> successor = cur.getLeftPtr();

                while (successor.getRightPtr() != null) 
                {
                    parent = successor;
                    successor = successor.getRightPtr();
                }

                // Replace current node data with successor data and delete successor
                cur.setData(successor.getData());
                deleteRec(successor, parent); // Recursively delete successor
            }
        }
        return cur; // Return the current node after deletion
    }

    // Function to sort the tree by performing in-order traversal
    public void Sort()
    {
        if(root == null) // Check if the tree is empty
        {
            return;
        }
        System.out.println("RECORDS SORTED");
        inorder(root); // Call in-order traversal
    }

    // In-order traversal helper function to sort and print node data
    private void inorder(Node<g> current)
    {
        if(current == null) // Base case: null node
        {
            return;
        }

        inorder(current.getLeftPtr()); // Traverse left subtree
        System.out.print(current.getData()); // Visit current node
        inorder(current.getRightPtr()); // Traverse right subtree
    }

    // Function to calculate the depth (height) of the tree
    public int depth()
    {
        return depthRec(root); // Call recursive depth helper
    }

    // Recursive helper to calculate the depth of a node
    private int depthRec(Node<g> cur)
    {
        if(cur == null) // Base case: null node contributes no depth
        {
            return 0;
        }
        
        // Calculate depth of left and right subtrees
        int leftDepth = depthRec(cur.getLeftPtr());
        int rightDepth = depthRec(cur.getRightPtr());

        // Return maximum depth of subtrees + 1 for current node
        return Math.max(leftDepth, rightDepth) + 1;
    }

    // Function to perform breadth-first traversal of the tree
    public String breadthTraverse()
    {
        Queue<Node<g>> queue = new LinkedList<>(); // Queue to manage levels
        queue.add(root);

        String line = ""; // To accumulate traversal results

        if(root != null)
        {
            while(!queue.isEmpty())
            {
                // Process each node in the queue
                Node<g> cur = queue.poll();
                line += cur.toString(); // Append node data

                // Enqueue left child if exists
                if(cur.getLeftPtr() != null)
                {
                    queue.add(cur.getLeftPtr());
                }

                // Enqueue right child if exists
                if(cur.getRightPtr() != null)
                {
                    queue.add(cur.getRightPtr());
                }
            }
        }
        return line; // Return the result of breadth-first traversal
    }
}