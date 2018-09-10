
/**
 *  This class defines a Node object for a binary search tree. 
 */
public class Node
{
    int data;
    Node left;
    Node right;
    
    //Default constructor
    public Node()
    {
        
    }
    
    //Overwritten constructor
    public Node(int data)
    {
        this.data = data;
        left = null;
        right = null;
    }
    
    public void print()
    {
        System.out.print("Value of the node is: " + data);
    }
}
