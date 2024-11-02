import java.util.*;

public class BinTree<g extends Comparable<g>>
{
    // Private field to hold the root node of the binary tree
    private Node<g> root;

    // Default constructor initializes the binary tree with root set to null
    public BinTree()
    {
        root = null;
    }

    // Method to insert a new data object into the binary tree
    public g insert(g data)
    {
        if(root == null) // Check if the tree is empty
        {
            // Create a new node for the root and set its data
            root = new Node<>();
            root.setData(data);
            return root.getData(); // Return the inserted data
        }
        // If the tree is not empty, insert the data recursively
        Node<g> insertNode = insertRec(root, data);
        return insertNode.getData(); // Return the inserted data from the node
    }

    // Recursive helper method to insert a new node in the correct position
    private Node<g> insertRec(Node<g> current, g data)
    {
        if(current == null) // Base case: create a new node if current is null
        {
            Node<g> newNode = new Node<>();
            newNode.setData(data);
            return newNode;
        }
        
        // Compare data with the current node to decide where to insert
        if(data.compareTo(current.getData()) < 0) // Insert in the left subtree
        {
            current.setLeftPtr(insertRec(current.getLeftPtr(), data));
        }
        else if(data.compareTo(current.getData()) > 0) // Insert in the right subtree
        {
            current.setRightPtr(insertRec(current.getRightPtr(), data));
        }
        else // If data is equal, update the node’s data
        {
            current.setData(data);
        }
        
        return current; // Return the current node after insertion
    }

    // Method to search for a node containing specific data in the binary tree
    public g search(g line)
    {
        try
        {
            // Call the recursive search helper and store the result
            Node<g> search = searchRec(root, line);
            if(line.compareTo(search.getData()) != 0)
            {
                return null; // Return null if data not found
            }
            return search.getData(); // Return the found data
        }
        catch(NullPointerException e)
        {
            return null; // Return null if node doesn't exist or error occurs
        }
    }

    // Recursive helper method to search for a specific data value in the tree
    private Node<g> searchRec(Node<g> current, g line)
    {
        if(line.compareTo(current.getData()) == 0) // Base case: data is found
        {
            return current;
        }
        else if(line.compareTo(current.getData()) < 0) // Search in the left subtree
        {
            current = searchRec(current.getLeftPtr(), line);
        }
        else // Search in the right subtree
        {
            current = searchRec(current.getRightPtr(), line);
        }

        return current; // Return the current node if data matches
    }

    // Method to delete a node containing specific data from the binary tree
    public g delete(g line)
    {
        Node<g> cur = root, parent = root;

        // Traverse the tree to find the node to delete, tracking its parent
        while(cur != null)
        {
            if(line.compareTo(cur.getData()) < 0) // Traverse left subtree
            {
                parent = cur;
                cur = cur.getLeftPtr();
            }
            else if(line.compareTo(cur.getData()) > 0) // Traverse right subtree
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
        
        // Call recursive helper to delete the located node
        deleteRec(cur, parent);
        return returnValue.getData(); // Return the deleted data
    }

    // Recursive helper method to handle the deletion of a node
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
                // Find in-order predecessor to replace current node’s data
                parent = cur;
                Node<g> successor = cur.getLeftPtr();

                while (successor.getRightPtr() != null) 
                {
                    parent = successor;
                    successor = successor.getRightPtr();
                }

                // Replace current node data with successor data and delete successor
                cur.setData(successor.getData());
                deleteRec(successor, parent); // Recursively delete the successor
            }
        }
        return cur; // Return the current node after deletion
    }

    // Method to sort the binary tree by performing in-order traversal
    public void Sort()
    {
        if(root == null) // Check if the tree is empty
        {
            return;
        }
        System.out.println("RECORDS SORTED");
        inorder(root); // Call in-order traversal to sort
    }

    // In-order traversal helper method to print and sort node data
    private void inorder(Node<g> current)
    {
        if(current == null) // Base case: null node
        {
            return;
        }

        inorder(current.getLeftPtr()); // Traverse the left subtree
        System.out.print(current.getData()); // Visit the current node
        inorder(current.getRightPtr()); // Traverse the right subtree
    }

    // Method to calculate the depth (height) of the binary tree
    public int depth()
    {
        return depthRec(root); // Call the recursive depth helper
    }

    // Recursive helper method to calculate the depth of a node
    private int depthRec(Node<g> cur)
    {
        if(cur == null) // Base case: null node contributes no depth
        {
            return 0;
        }
        
        // Calculate depth of left and right subtrees
        int leftDepth = depthRec(cur.getLeftPtr());
        int rightDepth = depthRec(cur.getRightPtr());

        // Return the maximum depth of the subtrees + 1 for the current node
        return Math.max(leftDepth, rightDepth) + 1;
    }

    // Method to perform a breadth-first traversal of the binary tree
    public String breadthTraverse()
    {
        Queue<Node<g>> queue = new LinkedList<>(); // Queue to manage levels
        queue.add(root); // Add root to start traversal

        String line = ""; // To accumulate the traversal results

        if(root != null)
        {
            while(!queue.isEmpty())
            {
                // Process each node in the queue
                Node<g> cur = queue.poll();
                line += cur.toString(); // Append the current node data

                // Enqueue the left child if it exists
                if(cur.getLeftPtr() != null)
                {
                    queue.add(cur.getLeftPtr());
                }

                // Enqueue the right child if it exists
                if(cur.getRightPtr() != null)
                {
                    queue.add(cur.getRightPtr());
                }
            }
        }
        return line; // Return the result of the breadth-first traversal
    }
}