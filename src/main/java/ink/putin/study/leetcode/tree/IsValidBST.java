package ink.putin.study.leetcode.tree;

/**
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 */
public class IsValidBST {

    public static void main(String[] args) {
        TreeNode tree = new TreeNode(new TreeNode(1),new TreeNode(3),2);
        System.out.println(isValidBST(tree));

        TreeNode tree5 = new TreeNode(new TreeNode(1),null,5);
        TreeNode tree4 = new TreeNode(new TreeNode(3),new TreeNode(6),4);
        tree5.right = tree4;
        System.out.println(isValidBST(tree5));
    }

    public static boolean isValidBST(TreeNode root) {
        return validTreeNode(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }

    private static boolean validTreeNode(TreeNode node,int lower,int upper) {
        if (node == null) {
            return true;
        }
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        return validTreeNode(node.left, lower, node.val) && validTreeNode(node.right, node.val, upper);
    }
}
