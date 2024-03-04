package Trees.BinaryTree.Implementation;

import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

public class binaryTree {
    private class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    private Node root;

    // insert elements // Scanner can be passed an an argument too. Nice! 
    // This is the Main Function
    public void populate (Scanner scanner){ // this will traverse through the tree ask us at every step whether we want to insert an element on the right or the left 
        System.out.println("Enter the root node: ");
        int value = scanner.nextInt();
        root = new Node(value);
        populate(scanner, root);
    }
    
    // This is the Helper Function
    private void populate(Scanner scanner, Node node) {
        System.out.println("Do you want to enter left of: " + node.value);
        boolean left = scanner.nextBoolean();
        if(left){
            System.out.println("Enter the value of the left of: " + node.value);
            int value = scanner.nextInt();
            node.left = new Node(value);
            populate(scanner, node.left);
        }

        System.out.println("Do you want to enter right of: " + node.value);
        boolean right = scanner.nextBoolean();
        if(right){
            System.out.println("Enter the value of the right of: " + node.value);
            int value = scanner.nextInt();
            node.right = new Node(value);
            populate(scanner, node.right);
        }
    }

    //This is the Main function
    public void display(){
        display(root, "");
    }

    //This is the helper function
    private void display(Node node, String indent) { // display1
        if(node == null){
            return;
        }

        System.out.println(indent + node.value);
        display(node.left, indent + "\t");
        display(node.right, indent + "\t");
    }

    public void prettyDisplay(){
        prettyDisplay(root, 0);
    }

    private void prettyDisplay(Node node, int level) { //display2
        if(node == null){
            return;
        }

        prettyDisplay(node.right, level+1); // traverse to the rightmost node first

        if(level != 0){ // if the level is not 0, it means we are in the right and not at the root node
            for(int i = 0 ; i < level-1; i ++){
                System.out.print("\t"); // print tabs the amount of levels
            }
            System.out.println("|----->" + node.value); // then print the value
        }else{ // means we are at the root node
            System.out.println(node.value);
        }

        prettyDisplay(node.left, level + 1);
    }

    public void BFS(){
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        BFS(root, q);
    }


    private void BFS(Node node, Queue<Node> q) {
        while(!q.isEmpty()){
            Node current = q.poll();

            System.out.println( current.value );

            if(current.left != null) q.add(current.left);
            if(current.right != null) q.add(current.right);
        }
    }

    //QUESTION --> is Symmetric or not ( is mirror or not )
    public boolean isSymmetric(){
        return isSymmetric(root);
    }
    public boolean isSymmetric(Node node) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);

        while(!queue.isEmpty()) {
            Node left = queue.poll();
            Node right = queue.poll();

            if(left == null && right == null) {
                continue;
            }
            
            if(left == null || right == null) {
                return false;
            }

            if (left.value != right.value) {
                return false;
            }

            queue.add(left.left);
            queue.add(right.right);
            queue.add(left.right);
            queue.add(right.left);
        
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        binaryTree bt = new binaryTree();
        bt.populate(scanner);
        bt.display();
        System.out.println();
        bt.prettyDisplay();
        bt.BFS();
        System.out.println();
        System.out.println(bt.isSymmetric());
    }
}
