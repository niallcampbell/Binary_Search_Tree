
/**
 *  Code for finding the lowest common ancestor of a binary search tree. 
 */
public class LCA
{
    public static Node lca(Node root, int v1, int v2)
    {
        if(root == null)
        {
            return null;
        }
        
        //If the first value is less than the root and the second value is greater than the root then the lca is the root
        if(v1 < root.data && v2 > root.data)
        {
            return root;
        }
        else if(v1 < root.data && v2 < root.data)
        {
            //if the two values are both less than the root then their lca is in the left subtree
            //put this subtree back into the method recursively to find the lca
            return lca(root.left, v1, v2);
        }
        else if(v1 > root.data && v2 > root.data)
        {
            //if the two values are both greater than the root then their lca is in the right subtree
            //put this subtree back into the method recursively to find the lca
            return lca(root.right, v1, v2);
        }
        
        return root;
    }
    
    
    //test the method
    public static void main()
    {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(3);
        bst.insert(2);
        bst.insert(1);
        bst.insert(4);
        bst.insert(5);
        
        System.out.println(lca(bst.root,2,5).data);
    }
}
