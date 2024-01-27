package Trees.BinarySearchTree.Implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class binarySearchTree {
    // Node class
    public class Node {
        private int value;
        private int height;
        private Node right;
        private Node left;

        public Node(int value) {
            this.value = value;
        }

        public int getValue() { // because we have set the 'value' to private
            return value;
        }
    }

    // Pair Datatype
    public class Pair{
        private Node A;
        private int B;

        public Pair(Node A , int B ){
            this.A = A ;
            this.B = B ;
        }

        public Node getA(){
            return this.A;
        }
        
        public int getB(){
            return this.B;
        }
    }
    
    public Node root;

    public binarySearchTree() {

    }

    public int height(){
        return height(root);
    }
    public int height(Node node) { // this method returns the height of the tree as the node passed here will be// the root node and the height of the root node is the height is the height of// the tree
        if (node == null) { // if no tree exists, return -1
            return -1;
        }
        return node.height;
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

    // Insert in a tree
    public void VoidInsert(int value) { // takes a value, if the root node is null, it makes a node and assing the root// node to it, else it calls the VoidInsert helper function according to the// condition
        if (root == null) {
            Node newNode = new Node(value);
            root = newNode;
            root.height = 0; // made root node and assigned it's height ( which at the beginning will be 0 )
        } else {
            if (value < root.value) {
                VoidInsert(root.left, value, root, "left");
            } else {
                VoidInsert(root.right, value, root, "right");
            }
            root.height = Math.max(height(root.left), height(root.right)) + 1; // after inserting an element, we are// assigning the new height to the root// node
        }
    }
    private void VoidInsert(Node node, int value, Node prevNode, String way) { // In VoidInsert, when we reach the spot// where we want to add the new value, it// is a null spot.In function calls, we// are keeping track of the previous node// so when we reach that null spot, we// make the previousNode.'way' ( we are// keeping track of the way (left or// right) as well ) equals to the ( Node// which we create at that moment only )// and then return!! This is the Void way// of inserting .
        if (node == null) {
            if (way.equals("left")) {
                Node newNode = new Node(value);
                prevNode.left = newNode;
                return;
            } else {
                Node newNode = new Node(value);
                prevNode.right = newNode;
                return;
            }
        }

        if (value < node.value) {
            VoidInsert(node.left, value, node, "left");
        } else {
            VoidInsert(node.right, value, node, "right");
        }

        node.height = Math.max(height(node.left), height(node.right)) + 1; // while inserting value/node/elements, we// are also assigning the respective height// to the nodes on our way back! ( after// inserting an element, the height// increments for nodes , that's why a +1 )
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

        if (value < node.value) {
            node.left = ReturnInsert(node.left, value);
        } else {
            node.right = ReturnInsert(node.right, value);
        }

        node.height = Math.max(height(node.left), height(node.right)) + 1; // while inserting value/node/elements, we// are also assigning the respective height// to the nodes on our way back! ( after// inserting an element, the height// increments for nodes , that's why a +1 )

        return node;
    }

    public void remove(int value){
        root = remove(root, value);
    }
    private Node remove(Node node, int value) {
        if(node == null) return node;

        if(node.value == value){
            // 0 child
            if(node.left == null && node.right == null){
                return null;
            }

            // 1 child

            //left child
            if(node.left != null && node.right == null){
                return node.left;
            }

            //right child
            if(node.left == null && node.right != null){
                return node.right;
            }

            // 2 child
            if(node.left != null && node.right != null){
                int min = getMin(node.right).value;
                node.value = min;
                node.right = remove(node.right, min);
                return node;
            }
        }else if(node.value > value){
            node.left = remove(node.left, value);
        }else{
            node.right = remove(node.right,value);
        }
        return node;
    }
    private Node getMin(Node node) {
        if(node.left == null) return node;

        return getMin(node.left);
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

    // populate a BST
    public void populate(int[] nums) { // // using the ReturnInsert method internally for now
        for (int i = 0; i < nums.length; i++) {
            this.ReturnInsert(nums[i]);
        }
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

    // DFS -> preOrder, postOrder, inOrder
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

    // BFS
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

    // QUESTION --> Since we need to come up to find the ancestors ( parents/grandparents/etc ) WE will use DFS and since we need to find the answer asap ( lowest common ancestor) and near the root node so we will use POT ( pre order traversal )
    public Node LowestCommonAncestor(Node node1, Node node2){
        return LowestCommonAncestor(root,node1,node2);
    }
    private Node LowestCommonAncestor(Node node, Node node1, Node node2) {
        if(node == node1 || node == node2) return node;
        if(node == null) return null;

        Node left = LowestCommonAncestor(node.left, node1, node2);
        Node right = LowestCommonAncestor(node.right, node1, node2);

        if(left != null && right != null) return node;
        return left == null ? right : left;
    }

    // QUESTION --> Top View
    public ArrayList<Integer> TopView(){
        Queue<Pair> q = new LinkedList<Pair>();
        Map<Integer,Integer> map = new TreeMap<>();
        q.add(new Pair(root, 0));
        return TopView(root, q, map, 0);
    }
    public ArrayList<Integer> TopView(Node node, Queue<Pair> q, Map<Integer,Integer> map, int level) {
        ArrayList<Integer> list = new ArrayList<>();
        while(!q.isEmpty()){
            Pair current = q.poll();
            Node temp = current.getA();
            int x = current.getB();

            if(map.get(x) == null) map.put(x, temp.value);
            if(temp.left!= null) q.add(new Pair(temp.left, x-1));
            if(temp.right != null) q.add(new Pair(temp.right, x+1));
        }
        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            list.add(entry.getValue());
        }
        return list;
    }

    // QUESTION --> Bottom View
    public ArrayList<Integer> BottomView(){
        Queue<Pair> q = new LinkedList<Pair>();
        Map<Integer,Integer> map = new TreeMap<>();
        q.add(new Pair(root, 0));
        return BottomView(root, q, map, 0);
    }
    public ArrayList<Integer> BottomView(Node node, Queue<Pair> q, Map<Integer,Integer> map, int level) {
        ArrayList<Integer> list = new ArrayList<>();
        while(!q.isEmpty()){
            Pair current = q.poll();
            Node temp = current.getA();
            int x = current.getB();

            map.put(x, temp.value);
            if(temp.left!= null) q.add(new Pair(temp.left, x-1));
            if(temp.right != null) q.add(new Pair(temp.right, x+1));
        }
        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            list.add(entry.getValue());
        }
        return list;
    }
    
    //QUESTION --> LOT ( Level Order Traversal )
    public ArrayList<ArrayList<Integer>> LOT(){
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if(root == null) return result;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        return LOT(root, q, result);
    }
    private ArrayList<ArrayList<Integer>> LOT(Node node, Queue<Node> q, ArrayList<ArrayList<Integer>> result) {
        while(!q.isEmpty()){
            int levelSize = q.size();
            ArrayList<Integer> current = new ArrayList<>();
            for(int i =0; i< levelSize; i++){
                Node temp = q.poll();
                current.add(temp.value);
                if(temp.left != null) q.add(temp.left);
                if(temp.right != null) q.add(temp.right);
            }
            result.add(current);
        }

        return result;
    }

    //QUESTION --> Find height of a tree
    public int getHeight(){
        return getHeight(root);
    }
    private int getHeight(Node node) {
        if(node == null) return 0;

        return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    public static void main(String[] args) {
        binarySearchTree bst = new binarySearchTree();

        // for(int i = 0 ; i < 1000; i ++){
        //     bst.VoidInsert(i);
        // }

        // System.out.println(bst.height());
        int[] nums = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

        bst.populateSorted(nums); // using the ReturnInsert method internally for now

        // bst.ReturnInsert(10);
        // bst.ReturnInsert(15);
        // bst.ReturnInsert(5);
        // bst.ReturnInsert(20);
        // bst.ReturnInsert(7);
        // bst.ReturnInsert(4);
        // bst.ReturnInsert(9);
        // bst.ReturnInsert(12);
        // bst.ReturnInsert(13);
        // bst.ReturnInsert(2);
        // bst.ReturnInsert(1);

        // bst.VoidInsert(10);
        // bst.VoidInsert(15);
        // bst.VoidInsert(5);
        // bst.VoidInsert(20);
        // bst.VoidInsert(7);
        // bst.VoidInsert(4);
        // bst.VoidInsert(8);
        // bst.VoidInsert(6);
        // bst.VoidInsert(12);
        // bst.VoidInsert(3);
        // bst.VoidInsert(2);

        bst.display();
        bst.prettyDisplay();

        // System.out.println(bst.height(bst.root));
        // System.out.println(bst.height(bst.root.left));
        // System.out.println(bst.height(bst.root.right));

        // bst.preOrder();
        // System.out.println();
        // bst.InOrder();
        // System.out.println();
        // bst.postOrder();

        bst.BFS();
        System.out.println(bst.TopView());
        System.out.println(bst.BottomView());

        System.out.println(bst.LOT());

        bst.remove(5);

        bst.prettyDisplay();
        System.out.println();

        bst.remove(3);

        bst.prettyDisplay();
        System.out.println();

        bst.remove(6);
        
        bst.prettyDisplay();
        System.out.println();

        System.out.println(bst.getHeight());
    }
}
