package Trees.AVLTree.Implementation;

import java.util.Arrays;

import javax.swing.tree.TreeNode;

public class AVLTree {
    public class Node {
        private int value;
        private int height;
        private Node right;
        private Node left;
    
        public Node(int value) {
            this.value = value;
        }
    
        public int getValue() {
            return value;
        }
    }
    
    private Node root;
    
    public AVLTree() {
    
    }

    public int height() {
        return height(root);
    }
    
    public int height(Node node) { // this method returns the height of the tree as the node passed here will be// the root node and the height of the root node is the height is the height of// the tree
        if (node == null) { // if no tree exists, return -1
            return -1;
        }
        return node.height;
    }

    public int getHeight(){
        return getHeight(root);
    }
    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        } else {
            // Calculate the height of the left and right subtrees
            int leftHeight = getHeight(node.left);
            int rightHeight = getHeight(node.right);

            // Return the maximum of the left and right subtree heights, plus 1 (for the current level)
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }
    
    public boolean isEmpty() {
        return root == null;
    }
    
    public void display() {
        display(root, "Root Node : ");
    }
    
    private void display(Node node, String details) {
        if (node == null) {
            return;
        }
    
        System.out.println(details + node.getValue());
    
        display(node.left, "Left child of " + node.value + " : ");
        display(node.right, "Right child of " + node.value + " : ");
    
    }
    
    public void ReturnInsert(int value) {
        root = ReturnInsert(root, value);
    }
    private Node ReturnInsert(Node node, int value) {
        if (node == null) {
            Node newNode = new Node(value);
            return newNode;
        }
    
        if (value < node.value) {
            node.left = ReturnInsert(node.left, value);
        } else {
            node.right = ReturnInsert(node.right, value);
        }
    
        node.height = Math.max(height(node.left), height(node.right)) + 1; // while inserting value/node/elements, we// are also assigning the respective height// to the nodes on our way back! ( after// inserting an element, the height// increments for nodes , that's why a +1 )
    
        return ReturnRotate(node); // before returning the node, we need to check whether this node is balanced or unbalanced ( AVL Trees ) // Like earlier, we are still returning this node only ( it's just in an extra function )
    }

    private Node ReturnRotate(Node node) {
        // these 2 conditions below will only run for one node as only one node will be unbalanced!!
        if(height(node.left) - height(node.right) > 1){ // if the difference in the heights here is 1, it doesn't matter and it is balanced ( we are checking if the node.left and node.right are balanced or not ( unlike in the nested loop under this))
            //left heavy case
            if(height(node.left.left) - height(node.left.right) > 0){ // Here, we only want to know which side is greater so that we can apply our ways/rules ( this part is already balanced as we used bottom to up approach(So, it has already been checked))
                //left left case
                return ReturnRightRotate(node);
            }
            if(height(node.left.left) - height(node.left.right) < 0){ // Here, we only want to know which side is greater so that we can apply our ways/rules ( this part is already balanced as we used bottom to up approach(So, it has already been checked))
                //left right case
                node.left = ReturnLeftRotate(node.left);
                return ReturnRightRotate(node);
            }
        }

        if(height(node.left) - height(node.right) < -1){ // if the difference in the heights here is 1, it doesn't matter and it is balanced ( we are checking if the node.left and node.right are balanced or not ( unlike in the nested loop under this))
            //right heavy case
            if(height(node.right.left) - height(node.right.right) < 0){ // Here, we only want to know which side is greater so that we can apply our ways/rules ( this part is already balanced as we used bottom to up approach(So, it has already been checked))
                //right right case
                return ReturnLeftRotate(node);
            }
            if(height(node.right.left) - height(node.right.right) > 0){ // Here, we only want to know which side is greater so that we can apply our ways/rules ( this part is already balanced as we used bottom to up approach(So, it has already been checked))
                //right left case
                node.right = ReturnRightRotate(node.right);
                return ReturnLeftRotate(node);
            }
        }
        
        return node;
    }

    private Node ReturnLeftRotate(Node c) { // refer to notes for clearity
        Node p = c.right;
        Node t = p.left;

        p.left = c;
        c.right = t;

        //VERY IMPORTANT -> update the c(child) height first and then the p(parent) height so as not get a wrong answer
        c.height = Math.max(height(c.left),height(c.right)) + 1; // upddating the height for child node 
        p.height = Math.max(height(p.left),height(p.right)) + 1; // upddating the height for parent node

        return p;
    }

    private Node ReturnRightRotate(Node p) { // the only nodes which are going to get changed are the parent and the child node!!
        Node c = p.left;
        Node t = c.right;

        c.right = p;
        p.left = t;

        //VERY IMPORTANT -> update the c(child) height first and then the p(parent) height so as not get a wrong answer
        c.height = Math.max(height(c.left),height(c.right)) + 1; // upddating the height for child node
        p.height = Math.max(height(p.left),height(p.right)) + 1; // upddating the height for parent node
        

        return c;
    }

    public void prettyDisplay() {
        prettyDisplay(root, 0);
    }
    
    private void prettyDisplay(Node node, int level) { // display2
        if (node == null) {
            return;
        }
    
        prettyDisplay(node.right, level + 1); // traverse to the rightmost node first
    
        if (level != 0) { // if the level is not 0, it means we are in the right and not at the root node
            for (int i = 0; i < level - 1; i++) {
                System.out.print("\t"); // print tabs the amount of levels
            }
            System.out.println("|----->" + node.value); // then print the value
        } else { // means we are at the root node
            System.out.println(node.value);
        }
    
        prettyDisplay(node.left, level + 1);
    }
    
    // This is the Main function
    public boolean balanced() {
        return balanced(root);
    }
    
    // This is the helper function
    private boolean balanced(Node node) {
        if (node == null) {
            return true;
        }
    
        return Math.abs(height(node.left) - height(node.right)) <= 1 && balanced(node.left) && balanced(node.right); // Math.abs// method// returns// the// absolute// value// of// a// number,// making// it// non-negative.// If// the// argument// is// already// non-negative,// it// returns// the// argument// itself.
    }
    
    public void populate(int[] nums) { // // using the ReturnInsert method internally for now
        for (int i = 0; i < nums.length; i++) {
            this.ReturnInsert(nums[i]);
        }
    }
    
    public void populateSorted(int[] nums) { // if an array is sorted and we try to populate it using the default// method, then it will make the tree a Skewed Tree which we don't want so// we are using this method where we find the mid element and then insert// it, thereafter we insert the left part and the right part using// recursion
        populateSorted(nums, nums.length);
    }
    
    private void populateSorted(int[] nums, int size) {
        if (size == 0) {
            return;
        }
    
        int mid = (size / 2);
        this.ReturnInsert(nums[mid]); // this -> bst ( the object )
    
        populateSorted(Arrays.copyOfRange(nums, 0, mid), mid);
        populateSorted(Arrays.copyOfRange(nums, mid + 1, size), size - mid - 1); // don't do mid -1 otherwise it will// give index 0 out of bounds for// length 0 as mid will be 0 in this// loop at one point
    }
    
    public void preOrder() {
        preOrder(root);
    }
    
    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
    
        System.out.print(node.value + " ");
        preOrder(node.left);
        preOrder(node.right);
    }
    
    public void InOrder() {
        InOrder(root);
    }
    
    private void InOrder(Node node) {
        if (node == null) {
            return;
        }
    
        InOrder(node.left);
        System.out.print(node.value + " ");
        InOrder(node.right);
    }
    
    public void postOrder() {
        postOrder(root);
    }
    
    private void postOrder(Node node) {
        if (node == null) {
            return;
        }
    
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.value + " ");
    
    }
    
    public static void main(String[] args) {
        AVLTree avl1 = new AVLTree();
    
        avl1.ReturnInsert(30);
        avl1.ReturnInsert(20);
        avl1.ReturnInsert(40);
        avl1.ReturnInsert(10);
        avl1.ReturnInsert(50);
        
        avl1.preOrder();
    }
}

