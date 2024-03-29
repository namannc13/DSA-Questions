package Trees.BinarySearchTree.Implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
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

    //QUESTION --> Right View
    public ArrayList<Integer> RightView(){
        ArrayList<Integer> list = new ArrayList<>();
        RightView(root, list, 0);
        return list;
    }
    private void RightView(Node node, ArrayList<Integer> list, int level) {
        if(node == null) return;

        if(level == list.size()){
            list.add(node.value);
        }

        RightView(node.right, list, level+1);
        RightView(node.left, list, level+1);
    }

    //QUESTION --> Left View
    public ArrayList<Integer> LeftView(){
        ArrayList<Integer> list = new ArrayList<>();
        LeftView(root, list, 0);
        return list;
    }
    private void LeftView(Node node, ArrayList<Integer> list, int level) {
        if(node == null) return;

        if(level == list.size()){
            list.add(node.value);
        }

        LeftView(node.left, list, level+1);
        LeftView(node.right, list, level+1);
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
    
    //QUESTION --> Average of Levels ( Level Order Traversal )
    public ArrayList<Integer> AverageOfLevels(){
        ArrayList<Integer> result = new ArrayList<>();
        if(root == null) return result;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        return AverageOfLevels(root, q, result);
    }
    private ArrayList<Integer> AverageOfLevels(Node node, Queue<Node> q, ArrayList<Integer> result) {
        while(!q.isEmpty()){
            int levelSize = q.size();
            int averageOfLevel = 0;
            for(int i =0; i< levelSize; i++){
                Node temp = q.poll();
                averageOfLevel += temp.value;
                if(temp.left != null) q.add(temp.left);
                if(temp.right != null) q.add(temp.right);
            }
            averageOfLevel = averageOfLevel/levelSize;
            result.add(averageOfLevel);
        }
        return result;
    }
    
    //QUESTION --> Sum of Levels ( Level Order Traversal )
    public ArrayList<Integer> SumOfLevels(){
        ArrayList<Integer> result = new ArrayList<>();
        if(root == null) return result;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        return SumOfLevels(root, q, result);
    }
    private ArrayList<Integer> SumOfLevels(Node node, Queue<Node> q, ArrayList<Integer> result) {
        while(!q.isEmpty()){
            int levelSize = q.size();
            int averageOfLevel = 0;
            for(int i =0; i< levelSize; i++){
                Node temp = q.poll();
                averageOfLevel += temp.value;
                if(temp.left != null) q.add(temp.left);
                if(temp.right != null) q.add(temp.right);
            }
            result.add(averageOfLevel);
        }
        return result;
    }
    
    
    //QUESTION --> Maximum Sum Level ( Level Order Traversal )
    public int MaxSumLevel(){
        if(root == null) return -1;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        return MaxSumLevel(root, q);
    }
    private int MaxSumLevel(Node node, Queue<Node> q) {
        int maxSum = Integer.MIN_VALUE;
        int ans = 0;
        int level = 1;
        while(!q.isEmpty()){
            int levelSize= q.size();
            int levelSum = 0;
            for(int i =0; i< levelSize; i++){
                Node temp = q.poll();
                levelSum += temp.value;
                if(temp.left != null) q.add(temp.left);
                if(temp.right != null) q.add(temp.right);
            }
            if(levelSum > maxSum){
                maxSum = levelSum;
                ans = level;
            }
            level++;
        }
        return ans;
    }

    //QUESTION --> Level Order Successor ( Level Order Traversal )
    public int LevelOrderSuccessor(int data){
        if(root == null) return -1;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        return LevelOrderSuccessor(root, q, data);
    }
    private int LevelOrderSuccessor(Node node, Queue<Node> q, int data) {
        while(!q.isEmpty()){
            int levelSize= q.size();
            for(int i =0; i< levelSize; i++){
                Node temp = q.poll();
                if(temp.left != null) q.add(temp.left);
                if(temp.right != null) q.add(temp.right);
                if(temp.value == data){
                    if(!q.isEmpty()){
                        Node ans = q.poll();
                        return ans.value;
                    }
                }
            }
        }
        return -1;
    }

    //QUESTION --> Zig Zag Traversal ( Level Order Traversal )
    public ArrayList<ArrayList<Integer>> ZigZagTraversal(){
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if(root == null) return result;
        Deque<Node> q = new LinkedList<>();
        q.add(root);
        boolean reverse = false;
        return ZigZagTraversal(root, q, result, reverse);
    }
    private ArrayList<ArrayList<Integer>> ZigZagTraversal(Node node, Deque<Node> q, ArrayList<ArrayList<Integer>> result, boolean reverse) {
        while(!q.isEmpty()){
            int levelSize = q.size();
            ArrayList<Integer> current = new ArrayList<>();
            for(int i =0; i< levelSize; i++){
                if(!reverse){
                    Node temp = q.pollFirst();
                    current.add(temp.value);
                    if(temp.left != null) q.addLast(temp.left);
                    if(temp.right != null) q.addLast(temp.right);
                    
                }else{
                    Node temp = q.pollLast();
                    current.add(temp.value);
                    if(temp.right != null) q.addFirst(temp.right);
                    if(temp.left != null) q.addFirst(temp.left);
                }
            }
            reverse = !reverse;
            result.add(current);
        }
        return result;
    }

    //QUESTION --> LOT Reverse ( Level Order Traversal )
    public ArrayList<ArrayList<Integer>> LOTReverse(){
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if(root == null) return result;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        return LOTReverse(root, q, result);
    }
    private ArrayList<ArrayList<Integer>> LOTReverse(Node node, Queue<Node> q, ArrayList<ArrayList<Integer>> result) {
        while(!q.isEmpty()){
            int levelSize = q.size();
            ArrayList<Integer> current = new ArrayList<>();
            for(int i =0; i< levelSize; i++){
                Node temp = q.poll();
                current.add(temp.value);
                if(temp.left != null) q.add(temp.left);
                if(temp.right != null) q.add(temp.right);
            }
            result.add(0,current);
        }
        
        return result;
    }

    //QUESTION --> Right View of Tree ( Using LOT purely ( the above right view solution uses different method ))
    public ArrayList<Integer> RightViewLOT(){
        ArrayList<Integer> result = new ArrayList<>();
        if(root == null) return result;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        return RightViewLOT(root, q, result);
    }
    private ArrayList<Integer> RightViewLOT(Node node, Queue<Node> q, ArrayList<Integer> result) {
        while(!q.isEmpty()){
            int levelSize = q.size();
            for(int i =0; i< levelSize; i++){
                Node temp = q.poll();
                if(i == levelSize-1){
                    result.add(temp.value);
                }
                if(temp.left != null) q.add(temp.left);
                if(temp.right != null) q.add(temp.right);
            }
        }
        return result;
    }

    //QUESTION --> Left View of Tree ( Using LOT purely ( the above right view solution uses different method ))
    public ArrayList<Integer> LeftViewLOT(){
        ArrayList<Integer> result = new ArrayList<>();
        if(root == null) return result;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        return LeftViewLOT(root, q, result);
    }
    private ArrayList<Integer> LeftViewLOT(Node node, Queue<Node> q, ArrayList<Integer> result) {
        while(!q.isEmpty()){
            int levelSize = q.size();
            for(int i =0; i< levelSize; i++){
                Node temp = q.poll();
                if(i == 0){
                    result.add(temp.value);
                }
                if(temp.left != null) q.add(temp.left);
                if(temp.right != null) q.add(temp.right);
            }
        }
        return result;
    }

    //QUESTION --> Is Cousins or not
    public boolean isCousins(int x , int y){
        return isCousins(root, x, y);
    }
    private boolean isCousins(Node root, int x, int y) {
        Node xx = findNode(root, x);
        Node yy = findNode(root, y);

        return (
            (level(root, xx, 0) == level(root, yy, 0)) && (!isSibling(root, xx, yy))
        );
    }
    private Node findNode(Node node, int x) {
        if (node == null) {
            return null;
        }
        if (node.value == x) {
            return node;
        }   
        Node n = findNode(node.left, x);
        if (n != null) {
            return n;
        }
        return findNode(node.right, x);
    }
    private boolean isSibling (Node node, Node x, Node y) {
        if (node == null) {
            return false;
        }   

        return (
        (node.left == x && node.right == y) || (node.left == y && node.right == x)
        || isSibling(node.left, x, y) || isSibling(node.right, x, y)
        );
    }
    private int level (Node node, Node x, int lev) {
        if(node == null) {
            return 0;
        }

        if(node == x) {
            return lev;
        }

        int l = level(node.left, x, lev+1);
        if (l != 0) {
            return l;
        }
        return level(node.right, x, lev+1);
    }

    //QUESTION --> Find height of a tree
    public int getHeight(){
        return getHeight(root);
    }
    private int getHeight(Node node) {
        if(node == null) return 0;

        return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }
    
    //QUESTION --> Diameter of a tree
    public int getDiameter(){
        ArrayList<Integer> list = new ArrayList<>();
        getDiameter(root,list);
        int max=Integer.MIN_VALUE;
        for(int i = 0; i < list.size(); i++){
            max = Math.max(max,list.get(i));
        }
        return max;
    }
    private void getDiameter(Node node, ArrayList<Integer> list) {
        if(node == null) return;

        int leftH = getHeight(node.left);
        int rightH = getHeight(node.right);

        list.add(leftH+rightH);

        getDiameter(node.left, list);
        getDiameter(node.right, list);

    }

    //QUESTION --> diameter of a binary search tree
    int diameter = 0;
    public int diameterOfBinaryTree(){
        return diameterOfBinaryTree(root);
    }
    public int diameterOfBinaryTree(Node root) {
        heightOfBinaryTree(root);
        return diameter-1; 
    }
    int heightOfBinaryTree(Node node) {
        if(node == null) {
            return 0;
        }

        int leftHeight = heightOfBinaryTree(node.left);
        int rightHeight = heightOfBinaryTree(node.right);

        int dia = leftHeight + rightHeight + 1;
        diameter = Math.max(diameter, dia);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    //QUESTION --> invert a tree
    public void invertTree(){
        invertTree(root);
    }
    private Node invertTree(Node node) {
        if (node == null) {
          return null;
        }
    
        Node left = invertTree(node.left);
        Node right = invertTree(node.right);
    
        node.left = right;
        node.right = left;
    
        return node;
    }

    //QUESTION --> Find the max Depth of a tree ( maximum nodes from root to the leaf node)
    public int maxDepth(){
        return maxDepth(root);
    }
    private int maxDepth(Node node){
        if(node == null) return 0;

        return Math.max(maxDepth(node.left), maxDepth(node.right)) + 1;
    }

    //QUESTION --> Convert a sorted array to BST
    public Node sortedArrayToBST(int[] nums) {
        addInBSTSorted(nums, 0, nums.length-1);
        return root;
    }
    public void addInBSTSorted(int[] nums, int start, int end){
        if(start > end){
            return;
        }
        int mid = (start + end + 1)/2;
        addInBST(nums[mid]);

        addInBSTSorted(nums, start, mid-1);
        addInBSTSorted(nums, mid+1, end);
    }
    public void addInBST(int value){
        root = addInBST(root, value);
    }
    public Node addInBST(Node node, int value){
        if(node == null){
            Node newNode = new Node(value);
            return newNode;
        }
        if(value < node.value) node.left = addInBST(node.left, value);
        if(value > node.value) node.right = addInBST(node.right, value);
        return node;
    }

    //QUESTION --> Flatten a tree
    @SuppressWarnings("unused")
    private void flatten(){
        flatten(root);
    }
    public void flatten(Node node) {
        Node current = node;
        while (current != null) {
          if (current.left != null) {
            Node temp = current.left;
            while(temp.right != null) {
              temp = temp.right;
            }
    
            temp.right = current.right;
            current.right = current.left;
            current.left= null;
          }
          current = current.right;
        }
    }

    //QUESTION --> is Valid tree or not
    public boolean isValidBST() {
        return helper(root, null, null);
    }
    public boolean helper(Node node, Integer low, Integer high) {
        if (node == null) {
            return true;
        }

        if (low != null && node.value <= low) {
            return false;
        }

        if(high != null && node.value >= high) {
            return false;
        }

        boolean leftTree = helper(node.left, low, node.value);
        boolean rightTree = helper(node.right, node.value, high);

        return leftTree && rightTree;
    }

    //QUESTION --> Lowest Common Ancestor
    public int lowestCommonAncestor(int x, int y){
        Node p = findNode(root, x);
        Node q =findNode(root, y);
        Node ans = lowestCommonAncestor(root, p, q);
        return ans.value;
    }
    public Node lowestCommonAncestor(Node root, Node p, Node q) {
        if (root == null) {
          return null;
        }
  
        if (root == p || root == q) {
          return root;
        }
  
        Node left = lowestCommonAncestor(root.left, p, q);
        Node right = lowestCommonAncestor(root.right, p, q);
  
        if (left != null && right != null) {
          return root;
        }
  
        return left == null ? right : left;
    }

    //QUESTION --> fint the kth smallest number in a tree
    int count = 0;
    public int kthSmallest(int k) {
        return helper(root, k).value;
    }
    public Node helper(Node root, int k) {
      if (root == null) {
        return null;
      }
  
      Node left = helper(root.left, k);

      if (left != null) {
        return left;
      }

      count++;

      if(count == k) {
        return root;
      }

      return helper(root.right, k);
    }

    //QUESTION --> build a tree when the preOrder and inOrder array is given 
    public Node buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
          return null;
        }
  
        int r = preorder[0];
        int index = 0;
  
        for(int i=0; i<inorder.length; i++) {
          if(inorder[i] == r) {
            index = i;
          }
        }
  
        Node node = new Node(r);
  
        node.left = buildTree(Arrays.copyOfRange(preorder, 1, index + 1), Arrays.copyOfRange(inorder, 0, index));
        node.right = buildTree(Arrays.copyOfRange(preorder, index + 1, preorder.length), Arrays.copyOfRange(inorder, index + 1, inorder.length));
  
        return node;
    }

    //QUESTION --> Serialize and Deserialize a tree Using ArrayList<String>
    public ArrayList<String> Serialize(){
        ArrayList<String> list = new ArrayList<>();
        SerializeHelper(root, list);
        return list;
    } 
    public void SerializeHelper(Node root, ArrayList<String> list){
        if(root == null){
            list.add("null");
            return;
        }

        list.add(String.valueOf(root.value));

        SerializeHelper(root.left, list);
        SerializeHelper(root.right, list);
    }

    public Node DeSerialize(ArrayList<String> list){
        Collections.reverse(list);
        Node root2 = DeSerializeHelper(list);
        return root2;
    }
    public Node DeSerializeHelper(ArrayList<String> list){
        String a = list.remove(list.size()-1);

        if(a.equals("null")){
            return null;
        }

        Node node = new Node(Integer.parseInt(a));

        node.left = DeSerializeHelper(list);
        node.right = DeSerializeHelper(list);

        return node;
    }

    //QUESTION --> Serialize and Deserialize a tree Using String
    public String SerializeString(){
        return SerializeStringHelper(root);
    } 
    public String SerializeStringHelper(Node root){
        if(root == null){
            return ",null";
        }

        String list = "," + String.valueOf(root.value);

        list += SerializeStringHelper(root.left);
        list += SerializeStringHelper(root.right);

        return list;
    }

    public Node DeSerializeString(String str){
        String[] s = str.split(",");
        return DeSerializeStringHelper(s);
    }
    public Node DeSerializeStringHelper(String[] str){
        ArrayList<String> list = new ArrayList<>();
        for(int i = 1; i< str.length; i++){
                list.add(str[i]);
        }
        Collections.reverse(list);
        Node root3 = DeSerializeStringHelper2(list);
        return root3;
    }
    public Node DeSerializeStringHelper2(ArrayList<String> list){
        String a = list.remove(list.size()-1);

        if(a.equals("null")){
            return null;
        }

        Node node = new Node(Integer.parseInt(a));

        node.left = DeSerializeStringHelper2(list);
        node.right = DeSerializeStringHelper2(list);

        return node;
    }

    //QUESTION --> Path Sum
    public boolean hasPathSum(int targetSum){
        return hasPathSum(root, targetSum);
    }
    public boolean hasPathSum(Node root, int targetSum) {
        if(root == null) return false;
        return hasPathSumHelper(root, targetSum);
    }
    public boolean hasPathSumHelper(Node root, int targetSum){
        if(root == null){
            return false;
        }
        if(root.value == targetSum && root.left == null && root.right == null) return true;
        boolean left = hasPathSum(root.left, targetSum-root.value);
        boolean right = hasPathSum(root.right, targetSum-root.value);
        return left || right;
    }
    
    //QUESTION --> Path Sum
    public boolean hasPathSum2(int targetSum){
        return hasPathSum2(root, targetSum);
    }
    public boolean hasPathSum2(Node root, int targetSum) {
        if(root == null) return false;
        return hasPathSum2Helper(root, targetSum);
    }
    public boolean hasPathSum2Helper(Node root, int targetSum){
        if(root == null){
            return false;
        }
        if(root.value == targetSum && root.left == null && root.right == null) return true;
        boolean left = hasPathSum2(root.left, targetSum-root.value) || hasPathSum2(root.left, targetSum);
        boolean right = hasPathSum2(root.right, targetSum-root.value)  || hasPathSum2(root.right, targetSum);
        return left || right;
    }
    
    public int sumNumbers() {
        return sumNumbers(root, "");
    }
    public int sumNumbers(Node root, String s){
        if(root == null) return 0;
        if(root.left == null && root.right == null){
            return Integer.parseInt(s + String.valueOf(root.value));
        }

        int left = sumNumbers(root.left, s + String.valueOf(root.value));
        int right = sumNumbers(root.right, s + String.valueOf(root.value));
        return left + right;
    }

    //QUESTION --> has path or not? (from root to leaf)
    public boolean hasPath(int[] arr){
        return hasPathHelper(root, arr, 0);
    }
    public boolean hasPathHelper(Node root, int[] arr, int index){
        if(root == null) return false;
        if(root.left == null && root.right == null && arr[index] == root.value) return true;
        if(index >= arr.length || arr[index] != root.value) return false;
        return hasPathHelper(root.left, arr, index+1) || hasPathHelper(root.right, arr ,index+1);
    }

    //QUESTION --> if has path Sum == targetSum, then return the path ( root to leaf only )
    public List<List<Integer>> pathSum(int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        pathSum(root, targetSum, result, "");
        return result;
    }
    public void pathSum(Node root, int targetSum, List<List<Integer>> result, String s){
        if(root == null) return;
        if(root.left == null && root.right == null){
            s += String.valueOf(root.value);
            String[] arr = s.split(",");
            
            int sum = 0;
            for(String i: arr){
                sum += Integer.parseInt(i);
            }

            List<Integer> list = new ArrayList<>();
            if(sum == targetSum){
                for(String i: arr){
                    list.add(Integer.parseInt(i));
                }
                result.add(list);
            }
        }

        pathSum(root.left, targetSum, result, s +  String.valueOf(root.value) + ",");
        pathSum(root.right, targetSum, result, s +  String.valueOf(root.value) + ",");
    }

    //QUESTION --> if has path Sum == targetSum, then return the path ( not root to leaf only )
    public List<List<Integer>> pathSum2(int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        pathSum2(root, targetSum, result, "");
        return result;
    }
    public void pathSum2(Node root, int targetSum, List<List<Integer>> result, String s){
        if(root == null) return;
        if(root.left == null && root.right == null){
            s += String.valueOf(root.value);
            String[] arr = s.split(",");
            
            int sum = 0;
            for(String i: arr){
                sum += Integer.parseInt(i);
            }

            List<Integer> list = new ArrayList<>();
            if(sum == targetSum){
                for(String i: arr){
                    list.add(Integer.parseInt(i));
                }
                result.add(list);
            }
        }

        pathSum2(root.left, targetSum, result, s +  String.valueOf(root.value) + ",");
        pathSum2(root.left, targetSum, result, s);
        pathSum2(root.right, targetSum, result, s +  String.valueOf(root.value) + ",");
        pathSum2(root.right, targetSum, result, s);
    }

    //QUESTION --> if has path Sum == targetSum, then return the count of those paths ( not root to leaf only ) ( backtracking --> better time complexity )
    int countPaths(Node node, int sum) {
        List<Integer> path = new ArrayList<>();
        return helper(node, sum, path);
    }
    int helper(Node node, int sum, List<Integer> path) {
        if(node == null) {
            return 0;
        }

        path.add(node.value);
        int count = 0;
        int s = 0;
        for (int i = path.size() - 1; i >= 0; i--) {
            s += path.get(i);
            if (s == sum) {
                count++;
            }
        }

        count += helper(node.left, s, path) + helper(node.right, sum, path);

        // backtrack
        path.remove(path.size() - 1);
        return count;
    }

    //QUESTION --> if has path Sum == targetSum, then return those paths ( not root to leaf only ) ( backtracking --> better time complexity )
    List<List<Integer>> countPaths2(int sum) {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> paths = new ArrayList<>();
        helper2(root,sum, path, paths);
        return paths;
    }
    void helper2(Node node, int sum, List<Integer> path, List<List<Integer>> paths) {
        if(node == null) {
            return;
        }

        path.add(node.value);
        int s = 0;
        // how many paths I can make
        List<Integer> list = new ArrayList<>();
        for (int i = path.size() - 1; i >= 0; i--) {
            s += path.get(i);
            list.add(path.get(i));
            if (s == sum) {
                Collections.reverse(list);
                paths.add(list);
                break;
            }
        }

        helper2(node.left, sum, path,paths);
        helper2(node.right, sum, path, paths);

        // backtrack
        path.remove(path.size() - 1);
        return;
    }

    //QUESTION --> is same tree or not?
    public boolean isSameTree(Node p, Node q) {
        if(p == null && q != null) return false;
        if(q == null && p != null) return false;
        if(p == null && q == null) return true;
        boolean ans = false;
        if(p.value == q.value) ans = true;
        return ans && isSameTree(p.left,q.left) && isSameTree(p.right, q.right);
    }

    //QUESTION --> is subRoot a subtree of root tree?
    public boolean isSubtree(Node root, Node subRoot) {
        if(root == null && subRoot == null) return true;
        if(root != null && subRoot == null) return true;
        if(root == null && subRoot != null) return false;
        if(root.value == subRoot.value){
            boolean ans = isSameTree(root, subRoot);
            return ans || isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
        }
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    // dfs using stack
    void dfsStack(){
        dfsStack(root);
    }
    void dfsStack(Node node) {
        if(node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(node);

        while(!stack.isEmpty()) {
            Node removed = stack.pop();
            System.out.print(removed.value + " ");
            if(removed.right != null) {
                stack.push(removed.right);
            }
            if(removed.left != null) {
                stack.push(removed.left);
            }
        }
    }

    //QUESTION --> count good Nodes of Binary tree ( if the node is bigger than the above nodes in it's path , then it's a good node)
    public int goodNodes() {
        if(root == null) return 0;
        return 1 + goodNodes(root.left, root.value) + goodNodes(root.right, root.value);
    }
    public int goodNodes(Node node, int maxval){
        if(node == null) return 0;
        int newmaxval = Math.max(node.value, maxval);
        int num=0;
        if(newmaxval == node.value){
            num = 1;
        }else{
            num = 0;
        }
        return num + goodNodes(node.left, newmaxval) + goodNodes(node.right, newmaxval);
    }
    
    public static void main(String[] args) {
        binarySearchTree bst = new binarySearchTree();

        // for(int i = 0 ; i < 1000; i ++){
        //     bst.VoidInsert(i);
        // }

        // System.out.println(bst.height());
        int[] nums = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

        bst.sortedArrayToBST(nums);
        // bst.populateSorted(nums); // using the ReturnInsert method internally for now

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
        System.out.println(bst.RightView());
        System.out.println(bst.LeftView());

        // System.out.println(bst.LOT());

        // bst.remove(5);

        // bst.prettyDisplay();
        // System.out.println();

        // bst.remove(3);

        // bst.prettyDisplay();
        // System.out.println();

        // bst.remove(6);
        
        // bst.prettyDisplay();
        // System.out.println();

        System.out.println(bst.getHeight());
        System.out.println(bst.getDiameter());

        System.out.println();
        System.out.println(bst.AverageOfLevels());
        System.out.println();
        System.out.println(bst.SumOfLevels());
        System.out.println();
        System.out.println(bst.MaxSumLevel());
        System.out.println();
        System.out.println(bst.LevelOrderSuccessor(2));
        System.out.println();
        System.out.println(bst.ZigZagTraversal());
        System.out.println();
        System.out.println(bst.LOT());
        System.out.println();
        System.out.println(bst.LOTReverse());
        System.out.println();
        System.out.println(bst.RightViewLOT());
        System.out.println();
        System.out.println(bst.LeftViewLOT());
        System.out.println();
        System.out.println(bst.isCousins(7, 4));
        System.out.println();
        System.out.println();
        System.out.println(bst.getDiameter());
        System.out.println();
        System.out.println(bst.diameterOfBinaryTree());
        System.out.println();
        // bst.invertTree();
        bst.prettyDisplay();
        System.out.println();
        System.out.println(bst.maxDepth());

        // bst.flatten();
        // bst.prettyDisplay();

        System.out.println();
        System.out.println(bst.isValidBST());
        System.out.println();
        System.out.println(bst.lowestCommonAncestor(5, 1));
        System.out.println();
        System.out.println(bst.kthSmallest(7));
        System.out.println();

        int[] preOrder = {3, 8, 9, 20, 15, 7};
        int[] inOrder = {8, 9, 3, 15, 20,7};
        Node rootOfNewTree = bst.buildTree(preOrder, inOrder);
        bst.prettyDisplay(rootOfNewTree, 0);

        System.out.println();
        System.out.println(bst.Serialize());
        System.out.println();
        ArrayList<String> SerializedStringUsingArrayList = bst.Serialize();
        Node n = bst.DeSerialize(SerializedStringUsingArrayList);
        bst.prettyDisplay(n, 0);

        System.out.println();
        System.out.println(bst.SerializeString());

        Node n2 = bst.DeSerializeString(bst.SerializeString());
        bst.prettyDisplay(n2, 0);

        System.out.println();
        System.out.println("pathSum exists?->" + bst.hasPathSum(18));

        System.out.println();
        System.out.println(bst.hasPathSum2(12));

        System.out.println();
        System.out.println(bst.sumNumbers());

        int[] arr ={ 6 ,9, 8, 7};
        System.out.println();
        System.out.println(bst.hasPath(arr));

        System.out.println();
        System.out.println("pathSum list->" + bst.pathSum(12));

        System.out.println();
        System.out.println("pathSum2 list->" + bst.pathSum2(12));

        System.out.println();
        System.out.println(bst.countPaths2(12));

        System.out.println();
        bst.dfsStack();

        System.out.println();
        System.out.println(bst.goodNodes());

    }
}
