import java.util.*;
/**
 *  Breadth first is a queue, depth first is a stack.
 *  For breadth first, add all children to the queue, then pull the head and do a breadth first search on it, using the same queue.
 *  For depth first, add all children to the stack, then pop and do a depth first on that node, using the same stack.
 * 
 *  BFS visits all possibilities at the same depth before going any deeper.
 *  Basically goes row by row. 
 *  
 */
public class BreadthFirstSearch
{
    
    //Checks if a node is in a given BST using breadth first search
    //returns true if it is, false if it isn't
    public static boolean breadthFirstSearch(Node root, int val)
    {
        if(root == null)
        {
            return false;
        }
        
        Queue<Node> myQ = new LinkedList<Node>();
        
        myQ.clear();
        
        myQ.add(root);
        
        while(!myQ.isEmpty())
        {
            Node temp = myQ.remove();
            
            if(temp.data == val)
            {
                return true;
            }
            else
            {
                if(temp.left != null)
                {
                    myQ.add(temp.left);
                }
                
                if(temp.right != null)
                {
                    myQ.add(temp.right);
                }
            }
        }
        
        return false;
    }
    
    //Prints the elements of a BST in breadth first search manner
    public static void breadthFirstPrint(Node root)
    {
        //Queue is an abstract class.
        //Abstract classes are classes that contain one or more abstract methods.
        //An abstract method is a method that is declared, but contains no implementation.
        //Abstract classes may not be instantiated, and require subclasses to provide implementations for the abstract methods.
        //Here we implement a queue using a linked list
        //http://www.javacoffeebreak.com/faq/faq0084.html
        Queue<Node> queue = new LinkedList<Node>();
        
        if(root == null)
        {
            return;
        }
        
        queue.clear(); //Removes all of the elements from the queue
        
        queue.add(root); //Inserts the root into the queue 
        
        while(!queue.isEmpty())
        {
            Node node = queue.remove(); //Retrieves and removes the head of this queue (i.e. the front of the queue)
            
            System.out.print(node.data + " ");
            
            if(node.left != null) 
            {
                queue.add(node.left); //Adds the left child
            }
            
            if(node.right != null)
            {
                queue.add(node.right); //Adds the right child
            }
        }
    }
    
    
    /*
     *          6
              /  \
            4      8
           / \    / \
          3   5  7   9
          
          Prints: 6 4 8 3 5 7 9
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
        
        breadthFirstPrint(bst.root);
        System.out.println();
        System.out.println(breadthFirstSearch(bst.root, 5));
    }
    
    
}
