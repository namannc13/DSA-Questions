package Trees.BinarySearchTree.Ques;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


public class populatingNextRightPointersInEachNode {
    public class Node{
        int data;
        Node left;
        Node right;
        Node next;
        int height;

        public Node(int data) {
            this.data = data;
        }
    }

    Node root;

    public int height(){
        return height(root);
    }
    public int height(Node node) { // this method returns the height of the tree as the node passed here will be// the root node and the height of the root node is the height is the height of// the tree
        if (node == null) { // if no tree exists, return -1
            return -1;
        }
        return node.height;
    }

    // Insert in a tree
    public void ReturnInsert(int value) {
        root = ReturnInsert(root, value);
    }
    private Node ReturnInsert(Node node, int value) {
        if (node == null) {
            Node newNode = new Node(value);
            return newNode;
        }

        if (value < node.data) {
            node.left = ReturnInsert(node.left, value);
        } else {
            node.right = ReturnInsert(node.right, value);
        }

        node.height = Math.max(height(node.left), height(node.right)) + 1; // while inserting value/node/elements, we// are also assigning the respective height// to the nodes on our way back! ( after// inserting an element, the height// increments for nodes , that's why a +1 )

        return node;
    }

    // populate a BST sorted
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

    //QUESTION --> Populating Next Right Pointers in Each Node ( Level Order Traversal ) (Not using queue)( Only for Perfect Binary Search tree)
    public void populatingNextRightPointers(){
        if(root == null) return;
        if(root.left == null) return;
        root.left.next = root.right;
        populatingNextRightPointers(root);
    }
    public void populatingNextRightPointers(Node node){
        Node leftMost = node.left;
        while(leftMost!=null){
            Node currentNode = leftMost;
            while(currentNode != null && currentNode.left != null){
                currentNode.left.next = currentNode.right;
                if(currentNode.next != null){
                    currentNode.right.next = currentNode.next.left;
                }
                currentNode = currentNode.next;
            }
            leftMost = leftMost.left;
        }
    }

    public void LOT(){
        if(root == null) return;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        LOT(root, q);
    }
    private void LOT(Node node, Queue<Node> q) {
        while(!q.isEmpty()){
            int levelSize = q.size();
            for(int i =0; i< levelSize; i++){
                Node temp = q.poll();
                System.out.println(temp.data + " " + temp +  " " +  temp.next + "     ");
                if(temp.left != null) q.add(temp.left);
                if(temp.right != null) q.add(temp.right);
            }
            System.out.println();
        }
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
            System.out.println("|----->" + node.data); // then print the value
        } else { // means we are at the root node
            System.out.println(node.data);
        }

        prettyDisplay(node.left, level + 1);
    }
    
    public static void main(String[] args) {
        
        populatingNextRightPointersInEachNode bst = new populatingNextRightPointersInEachNode();
        int[] nums = { 1, 2, 3, 4, 5, 6, 7 };

        bst.populateSorted(nums); // using the ReturnInsert method internally for now

        bst.populatingNextRightPointers();
        bst.prettyDisplay();
        System.out.println();
        bst.LOT();
    }
}
