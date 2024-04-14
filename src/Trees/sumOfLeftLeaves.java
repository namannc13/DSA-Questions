package Trees;

public class sumOfLeftLeaves {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
   }
    // void
    public int sumOfLeftLeaves1(TreeNode root) {
        if(root == null) return 0;
        sum1(root, false);
        return sum;
    }
    int sum= 0;
    public void sum1(TreeNode root, boolean left){
        if(root == null) return;
        if(root.left == null && root.right == null && left == true){
            sum+= root.val;
            return;
        }
        sum1(root.left, true);
        sum1(root.right, false);
    }
    //return
    public int sumOfLeftLeaves2(TreeNode root) {
        if(root == null) return 0;
        return sum2(root, false);
    }
    public int sum2(TreeNode root, boolean left){
        if(root == null) return 0;
        if(root.left == null && root.right == null && left == true){
            return root.val;
        }
        return sum2(root.left, true) + sum2(root.right, false);
    }
    //without boolean left
    public int sumOfLeftLeaves3(TreeNode root) {
        if(root == null) return 0;
        return sum3(root);
    }
    public int sum3(TreeNode root){
        if(root == null) return 0;
        if(root.left != null && root.left.left == null && root.left.right == null){
            return root.left.val + sum3(root.right);
        }
        return sum3(root.left) + sum3(root.right);
    }
}
