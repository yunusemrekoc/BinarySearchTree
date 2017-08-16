import java.io.*;
import java.util.*;               // for Stack class
////////////////////////////////////////////////////////////////
class Node
   {
   public int iData;              // data item (key)
   public Node leftChild;         // this node's left child
   public Node rightChild;        // this node's right child
   
   Node(int id){
       iData = id;
   }
   
   public void displayNode()      // display ourself
      {
      System.out.print('{');
      System.out.print(iData);
      System.out.print("} ");
      }
   }  // end class Node
////////////////////////////////////////////////////////////////
class Tree
   {
   private Node root;             // first node of tree

// -------------------------------------------------------------
   public Tree()                  // constructor
      { root = null; }            // no nodes in tree yet

// -------------------------------------------------------------
   public void insert(int id)       //without using recursion
      {
      Node newNode = new Node(id);    // make new node
      
      if(root==null)                // no node in root
         root = newNode;
      else                          // root occupied
         {
         Node current = root;       // start at root
         Node parent;
         while(true)                // (exits internally)
            {
            parent = current;
            if(id < current.iData)  // go left?
               {
               current = current.leftChild;
               if(current == null)  // if end of the tree,
                  {                 // insert on left
                  parent.leftChild = newNode;
                  return;
                  }
               }  // end if go left
            else                    // or go right?
               {
               current = current.rightChild;
               if(current == null)  // if end of the tree
                  {                 // insert on right
                  parent.rightChild = newNode;
                  return;
                  }
               }  // end else go right
            }  // end while
         }  // end else not root
      }  // end insert()
   
// -------------------------------------------------------------
   public void insert2(int id){                 // recursive insert function
       if(root == null)
           root = new Node(id);
       else{
           insertRecursive(root,id);
       }
   }
 
		
		

   
   public void insertRecursive(Node current,int id){
       if(id<current.iData){
           if(current.leftChild==null){
               current.leftChild = new Node(id);
           }
           else{
               insertRecursive(current.leftChild,id);
           }
       }
       else{
           if(current.rightChild==null){
               current.rightChild=new Node(id);
           }
           else{
               insertRecursive(current.rightChild,id);
           }
       }
   }
   public void delete(int data){
	  deleteNode(root, data);
   }
   public boolean deleteNode(Node node,int data){
	    
		if(node==null){
			return false;
		}
		if(node.iData==data){
		if(node.leftChild==null&&node.rightChild==null){
			node=null;
			return true;
		}
		if(node.leftChild!=null&&node.rightChild!=null){
			
			findMinimumAndReturnWithDelete(node.rightChild);
			return true;
		}
		if (node.leftChild != null) {
           root=node.leftChild;
           node = null;
           return true;
       }

       if (node.rightChild != null) {
           root=node.rightChild;
           node = null;
           return true;
       }
	}
		root=node;
		if (node.iData > data) {
           return deleteNode(node.leftChild, data);
       } else {
           return deleteNode(node.rightChild, data);
       }
   }
   
	public int findMinimumAndReturnWithDelete(Node node) {
		
       if (node.leftChild == null) {
           int x = node.iData;
           node = null;
           return x;
       }
       return findMinimumAndReturnWithDelete(node.leftChild);
   }

// -------------------------------------------------------------
   public void traverse(int traverseType)
      {
      switch(traverseType)
         {
         case 1: System.out.print("\nPreorder traversal: ");
                 preOrder(root);
                 break;
         case 2: System.out.print("\nInorder traversal:  ");
                 inOrder(root);
                 break;
         case 3: System.out.print("\nPostorder traversal: ");
                 postOrder(root);
                 break;
         }
      System.out.println();
      }
// -------------------------------------------------------------
   private void preOrder(Node localRoot)
      {
      if(localRoot != null)
         {
         System.out.print(localRoot.iData + " ");
         preOrder(localRoot.leftChild);
         preOrder(localRoot.rightChild);
         }
      }
// -------------------------------------------------------------
   private void inOrder(Node localRoot)
      {
      if(localRoot != null)
         {
         inOrder(localRoot.leftChild);
         System.out.print(localRoot.iData + " ");
         inOrder(localRoot.rightChild);
         }
      }
// -------------------------------------------------------------
   private void postOrder(Node localRoot)
      {
      if(localRoot != null)
         {
         postOrder(localRoot.leftChild);
         postOrder(localRoot.rightChild);
         System.out.print(localRoot.iData + " ");
         }
      }
// -------------------------------------------------------------
   public void displayTree()
      {
      Stack globalStack = new Stack();
      globalStack.push(root);
      int nBlanks = 32;
      boolean isRowEmpty = false;
      System.out.println(
      "......................................................");
      while(isRowEmpty==false)
         {
         Stack localStack = new Stack();
         isRowEmpty = true;

         for(int j=0; j<nBlanks; j++)
            System.out.print(' ');

         while(globalStack.isEmpty()==false)
            {
            Node temp = (Node)globalStack.pop();
            if(temp != null)
               {
               System.out.print(temp.iData);
               localStack.push(temp.leftChild);
               localStack.push(temp.rightChild);

               if(temp.leftChild != null ||
                                   temp.rightChild != null)
                  isRowEmpty = false;
               }
            else
               {
               System.out.print("--");
               localStack.push(null);
               localStack.push(null);
               }
            for(int j=0; j<nBlanks*2-2; j++)
               System.out.print(' ');
            }  // end while globalStack not empty
         System.out.println();
         nBlanks /= 2;
         while(localStack.isEmpty()==false)
            globalStack.push( localStack.pop() );
         }  // end while isRowEmpty is false
      System.out.println(
      "......................................................");
      }  // end displayTree()
// -------------------------------------------------------------
   }  // end class Tree
////////////////////////////////////////////////////////////////
class TreeApp
   {
   public static void main(String[] args) throws IOException
      {
      
      Tree theTree = new Tree();
      
      theTree.insert(50);
      theTree.insert(25);
      theTree.insert(75);
      theTree.insert(12);
      theTree.insert(37);
      theTree.insert2(43);
      theTree.insert2(30);
      theTree.insert2(33);
      theTree.insert2(87);
      theTree.insert2(93);
      theTree.insert2(97);
      theTree.delete(12);
   
      
      
      theTree.displayTree();
      
 
      theTree.traverse(1);
      theTree.traverse(2);
      theTree.traverse(3);
      
      
      }
}
