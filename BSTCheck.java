import java.util.*;

/**
 *  Code to check if a given tree is a BST
 */
public class BSTCheck
{
    public static boolean isBST(Node root)
    {
        //Perform an inOrder travesal (LNR)
        //This searches a BST in from lowest value to hightest
        //Add each node to a linked list
        //check this list to make sure that it is in order
        
        if(root == null)
        {
            return true;
        }
        
        LinkedList<Node> list = new LinkedList<Node>();
        
        list = inOrderTraversal(root, list);
        
        //check if the linked list is in order
        boolean check = inOrderLL(list);
        
        return check;
    }
    
    //LNR
    //returns a linked list containing the nodes of a binary tree in increasing order of their value
    public static LinkedList<Node> inOrderTraversal(Node root, LinkedList<Node> list)
    {
        if(root != null)
        {
            inOrderTraversal(root.left, list);
            list.add(root);
            inOrderTraversal(root.right, list);
            return list;
        }
        
        return list;
    }
    
    //Checks if the values of a linked list are in order
    public static boolean inOrderLL(LinkedList<Node> list)
    {   
        Iterator<Node> iter = list.iterator(); //create iterator for linked list made of nodes
        
        Node prev = iter.next(); //get the first element of the linked list
        
        //while there are more nodes left in the list
        while(iter.hasNext()) 
        {
            Node temp = iter.next(); //get the next element
            
            //if prev is not smaller than temp
            if(!(prev.data < temp.data)) 
            {
                return false;
            }
            
            prev = temp; //move prev along
        }
        
        return true;
    }
    
    
    //test code
    public static void main()
    {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(3);
        bst.insert(2);
        bst.insert(1);
        bst.insert(4);
        bst.insert(5);
        
        System.out.println(isBST(bst.root));
    }
}
