
/**
 * 
 *  Code to find if a binary tree has a path that all of the values sum up to a given value.
 *  
 *  Question:
 *  
 *  Given a binary tree, find if there is a path that the sum of the nodes in the path equals to a given number target.
 *  A valid path is from root node to any of the leaf nodes (leaf node = node with no children). 
 *  
 *  Example:
 *  
 *      Given the below binary tree and sum = 22,
 *      
 *            5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
 *  
 *      return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 * 
 *  Algorithm:
 *  
 *  Subtract the values of the node from the target until you reach the end of the path.
 *  If the sum minus the values equals 0 then the path exists.
 *  Otherwise go back up the tree and try the other paths.
 * 
 *  Algorithm is a form of Depth First Search. It will go as far as it can with each path starting with the leftmost. 
 *  
 *  Once a path is found, ans will become true. The algorithm will keep going through the rest of ther paths though.
 *  However due to the OR in the statement, once ans has a true value it will keep that value. 
 * 
 *  Time Complexity: O(n)
 *  
 */
public class FindPathSum
{     
    public static boolean hasPathSum(Node node, int sum) 
    {
        //If the tree is empty
        if (node == null)
        {
            return (sum == 0);
        }
        else
        {
            boolean ans = false; //Stores whether there is a path or not
            int subsum = sum - node.data; //Stores the target minus the data values of the nodes in the path
            
            //Reached the end of the path and the values sum up to the target
            if (subsum == 0 && node.left == null && node.right == null)
            {
                return true;
            }
                
            if (node.left != null)
            {
                //If answer = true at some point, then the OR clause will ensure that ans will continues to equal true while it checks the rest of the tree as (TRUE || anything = TRUE)
                ans = (ans || hasPathSum(node.left, subsum));
            }
            
            if (node.right != null)
            {
                ans = (ans || hasPathSum(node.right, subsum));
            }
            
            return ans;
        }
    }
    
    
    //Alternate Method
    public static boolean hasPathSum1 (Node node, int sum)
    { 
      if(node == null)
      {                               
          return false;
      }
      
      if (node.left == null && node.right == null)
      {
          return node.data == sum;  
      }   
      
      return hasPathSum1(node.left, sum - node.data) || hasPathSum1(node.right, sum - node.data);
    } 
    
    
    public static void main() 
    {
        int target = 15;
        
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(6);
        bst.insert(4);
        bst.insert(3);
        bst.insert(5);
        bst.insert(9);
        bst.insert(8);
        bst.insert(7);
  
        if (hasPathSum(bst.root, target))
        {
            System.out.println("There exists a root to leaf path with sum " + target);
        }
        else
        {
            System.out.println("There is no root to leaf path with sum " + target);
        }
    }
}
