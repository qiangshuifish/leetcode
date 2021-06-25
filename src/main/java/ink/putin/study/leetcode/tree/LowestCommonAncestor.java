package ink.putin.study.leetcode.tree;

/**
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。

 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LowestCommonAncestor {

    public static void main(String[] args) {
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(treeNode3, treeNode5,4);
        TreeNode treeNode8 = new TreeNode(new TreeNode(7),new TreeNode(9),8);
        TreeNode treeNode0 = new TreeNode(0);
        TreeNode treeNode2 = new TreeNode(treeNode0, treeNode4,2);
        TreeNode treeNode6 = new TreeNode(treeNode2,treeNode8,6);

        System.out.println(lowestCommonAncestor(treeNode6,treeNode2,treeNode8).val);
        System.out.println(lowestCommonAncestor(treeNode6,treeNode0,treeNode5).val);
        System.out.println(lowestCommonAncestor(treeNode6,treeNode0,treeNode5).val);
        System.out.println(lowestCommonAncestor(treeNode6,treeNode3,treeNode5).val);
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q){
            return root;
        }
        /*
         * 如果一个节点的左子树找到了P,右子树找到了q ,那他肯定就是最近的祖先节点
         *
         * 以 p = 2, q = 8 为例子
         *      2是6的左子树上的节点，8是6右子树上的节点，所以 8 是 2和6 的最近的祖先节点
         *
         * 以 p = 0, q = 5 为例子
         *      0是2的左子树上的节点，5是2右子树上的节点，所以 2 是 0和5 的最近的祖先节点
         *
         * 以 p = 3, q = 5 为例子
         *      0是4的左子树上的节点，5是4右子树上的节点，所以 4 是 3和5 的最近的祖先节点
         */
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left != null && right != null){
            return root;
        }
        if(left != null){
            return left;
        }
        return right;
    }
}
