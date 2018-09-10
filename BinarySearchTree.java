
/**
 *  Code for a binary search tree. 
 *  
 *  A binary search tree consists of nodes. Each node can have 0, 1 or 2 children nodes. The data value of the left child node is less than the parent's value. The data value of the right child is greater than the parent's value. 
 *  
 *  A binary tree has the benefits of both an ordered array and a linked list as search is as quick as in a sorted array and insertion or deletion operation are as fast as in linked list. 
 *  
 *  See for more: http://algorithms.tutorialhorizon.com/binary-search-tree-complete-implementation/
 *  
 */

public class BinarySearchTree
{
    Node root; //The root node of the BST
    
    //Constructor
    public BinarySearchTree()
    {
        root = null;
    }

    //Method to insert a value into the BST
    public void insert(int id)
    {
        Node temp = new Node(id); //Create a new node with the data value
        
        //If the root is null then this node becomes the root
        if(root == null)
        {
            root = temp;
        }
        else
        {
            Node current = root; //Stores the current node we are checking to see if it is null. Once current becomes null, the new node will be added as a child. current is initially the same object at the address of root
            Node parent; //Stores the node in which the new node will be added to as a child (i.e. the one before current)
            
            while(true)
            {
                parent = current; //parent equals the object at current's address
                
                if(id < current.data) //Go left
                {
                    current = current.left; //current points to the left child of the current node
                    
                    if(current == null)
                    {
                        parent.left = temp; //The left child of the node object at parent's address becomes temp 
                        return;
                    }
                    
                }
                else //Go right
                {
                    current = current.right;
                    if(current == null)
                    {
                        parent.right = temp;
                        return;
                    }
                }

            }
        }
    }
    
    //Method to return a node in the tree
    public Node find(int key)
    {
        Node current = root; //Start at the root
        
        //Once the current node does equal the key value, the loop will break and return the node
        //Otherwise if it reaches the end of the tree it will return null
        while(current.data != key)
        {
            if(key < current.data)
            {
                current = current.left;
            }
            else
            {
                current = current.right;
            }
            
            //If it is not in the tree
            if(current == null)
            {
                return null;
            }
        }
        
        return current;
    }
    
    
    //Method to delete a node in the tree
    public boolean delete(int id)
    {
        Node parent = root; //Stores the parent of the node we are currently checking
        Node current = root; //Stores the node we are currently checking
        boolean isLeftChild = false; //True if the node we are deleting is a left child of the parent, false if it is the right child of the parent
        
        //Find the node
        while(current.data != id)
        {
            parent = current; 
            
            if(id < current.data)
            {
                current = current.left;
                isLeftChild = true; //The node to be deleted is a left child of the parent node
            }
            else
            {
                current = current.right;
                isLeftChild = false; //The node to be deleted is a right child of the parent node
            }
            
            if(current == null)
            {
                return false; //A node with the value of id isn't in the tree
            }
        }
        
        //The node to be deleted has been found
        
        //Case 1: The node to be deleted has no children (i.e. it is a leaf node)
        //Just point the parent to null for that leaf
        if(current.left == null && current.right == null)
        {
            if(current == root)
            {
                root = null;
            }
            
            if(isLeftChild == true)
            {
                parent.left = null;
            }
            else if(isLeftChild == false)
            {
                parent.right = null; 
            }
        }
        
        
        //Case 2: Node to be deleted has only one child
        //Connect the node's child to the node's parent
        else if(current.right == null) //if the node's child is on the left
        {
            if(current == root)
            {
                root = current.left;
            }
            else if(isLeftChild) //If the current node is on the left of its parent
            {
                parent.left = current.left; //Connect the left of the parent to the left child of the current, thus eliminating the current node
            }
            else //If the current node is on the right of its parent
            {
                parent.right = current.left; //Connect the right of the parent to the left child of the current, thus eliminating the current node
            }
        }
        else if(current.left == null) //if the child of the node to be deleted is on the right
        {
            if(current == root)
            {
                root = current.right;
            }
            else if(isLeftChild) //If the current node is on the left of its parent
            {
                parent.left = current.right; //Connect the left of the parent to the right child of the current, thus eliminating the current node
            }
            else //If the current node is on the right of its parent
            {
                parent.right = current.right; //Connect the right of the parent to the right child of the current, thus eliminating the current node
            }
        }
        
        //Case 3: The node to be deleted has two children
        //Have to find the successor of the deleted node, i.e. we have to swap a node in for it
        //Successor is the smallest node in the right sub tree of the node to be deleted.
        
        else if(current.left != null && current.right != null) //i.e. current has two children
        {
            Node successor = getSuccessor(current); //Finds the minimum element in the right subtree of the node to be deleted. This will be swapped into its place 
            
            if(current == root)
            {
                root = successor;
            }
            else if(isLeftChild)
            {
                parent.left = successor;
            }
            else
            {
                parent.right = successor;
            }
            
            successor.left = current.left;
            
        }
        
        return true;
    }
    
    
    //This method is required for deleting a node that has two children
    //For this case, a successor must be found to replace the deleted node
    //This method returns the node that will replace the deleted node
    //Start with the node's right child
    //Keep going left until you can't. The last left node in this path is the node that will be swapped into the deleted node's place
    //If the right child has no left children, swap the right child into the deleted node's place
    
    public Node getSuccessor(Node deleteNode)
    {
        Node successsor = null; //Will store the successor node
        Node successsorParent = null; //Will store the parent of the successor node
        Node current = deleteNode.right; //Start with the right child of the node to be deleted
        
        //Keep going left until you can't.
        while(current != null)
        {
            successsorParent = successsor;
            successsor = current;
            current = current.left;
        }
        
        //if the successor is not the right child of the node to be deleted, the right child of the right child of the deleted node must be dealt with
        if(successsor != deleteNode.right)
        {
            successsorParent.left = successsor.right;
            successsor.right = deleteNode.right;
        }
        
        return successsor;
    }
    
    /*
     * Binary Search Tree
     * 
     *         6
              /  \
            4      8
           / \    / \
          3   5  7   9
     * 
     * 
     * InOrder: 3 4 5 6 7 8 9
     * PreOrder: 6 4 3 5 8 7 9
     * PostOrder: 3 5 4 7 9 8 6
     * 
    */
   
   
    //LNR
    //Displays the contents of a binary search tree in ascending order (aka in order traversal)
    public void displayInOrder(Node root)
    {
        if(root != null)
        {
            displayInOrder(root.left);
            System.out.print(" " + root.data);
            displayInOrder(root.right);
        }
    }
    
    //NLR = node left right
    //Basically traverses the subtrees of the tree.
    //Check if the current node is empty / null.
    //Display the data part of the root (or current node).
    //Traverse the left subtree by recursively calling the pre-order function.
    //Traverse the right subtree by recursively calling the pre-order function.
    public void displayPreOrder(Node root)
    {
        if(root != null)
        {
            System.out.print(" " + root.data);
            displayPreOrder(root.left);
            displayPreOrder(root.right);
        }
    }
    
    //LRN
    //Postorder traversal is used to delete the tree.
    public void displayPostOrder(Node root)
    {
        if(root != null)
        {
            displayPostOrder(root.left);
            displayPostOrder(root.right);
            System.out.print(" " + root.data);
        }
    }
    
    
    //Returns the height of the binary search tree which in this case is the max amount of edges between the root node and the deepest node
    public int height(Node root)
    {
        if(root == null)
        {
            return -1;
        }
        else
        {
            int leftHeight = height(root.left); //recursively find the depth of the left subtree
            int rightHeight = height(root.right); //recursively find the depth of the right subtree
            
            //Choose the longer one and add one to it for the current node
            if (leftHeight > rightHeight)
            { 
                return (leftHeight + 1);
            }
            else
            {
                return (rightHeight + 1);
            }        
        }
    }
    
}
