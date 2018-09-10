import java.util.*;
/**
 *  Depth First Search uses a Stack.
 *  
 *  DFS explores a path as far as it can go and then backtracks when it meets a dead end.
 */

public class DepthFirstSearch
{
    //Checks if a node is in a given tree
    public static boolean depthFirstSearch(Node root, int val)
    {
        Stack<Node> stack = new Stack<Node>();
        
        if(root == null)
        {
            return false;
        }
        
        stack.add(root);
		
        while(!stack.isEmpty())
        {
			Node node = stack.pop(); //Removes the element at the top of the queue and saves it in node
			
			if(node.data == val)
			{
			    return true;
			}
			else
			{
			    if(node.right!=null)
    			{
    			    stack.add(node.right);
    			}
    			
    			if(node.left!=null)
    			{
    			    stack.add(node.left);		
    			}
			}
		}
		
		return false; //couldn't find it
    }
    
    //Print nodes of a BST in depth first search manner using a stack
    //Result will be the same of pre-order traversal (NLR)
    public static void depthFirstPrint(Node root)
    {
        Stack<Node> stack = new Stack<Node>();
        
        if(root == null)
        {
            return;
        }
        
        stack.add(root);
		
        while(!stack.isEmpty())
        {
			Node node = stack.pop(); //Removes the element at the top of the queue and saves it in node
			
			System.out.print(node.data + " ");
			
			if(node.right!=null)
			{
			    stack.add(node.right);
			}
			
			if(node.left!=null)
			{
			    stack.add(node.left);		
			}
			
		}
    }
    
    /*
     *          6
              /  \
            4      8
           / \    / \
          3   5  7   9
          
          Prints: 6 4 3 5 8 7 9
    */
    public static void main()
    {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(6);
        bst.insert(4);
        bst.insert(3);
        bst.insert(5);
        bst.insert(8);
        bst.insert(7);
        bst.insert(9);
        
        depthFirstPrint(bst.root);
    }
}
