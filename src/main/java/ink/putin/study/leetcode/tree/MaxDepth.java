package ink.putin.study.leetcode.tree;

import java.util.LinkedList;

/**
 * 树的深度优先算法
 */
public class MaxDepth {
    public static void main(String[] args) {
        TreeNode treeNode = TreeOperation.getTreeNode();
        System.out.println(maxDepth(treeNode));
        System.out.println(maxDepth1(treeNode));
    }

    /**
     * 深度有限算法
     *
     * @param treeNode
     * @return
     */
    public static int maxDepth(TreeNode treeNode) {
        if (treeNode == null) return 0;
        return Math.max(maxDepth(treeNode.left), maxDepth(treeNode.right)) + 1;
    }

    /**
     * 广度有限算法
     *
     * @param treeNode
     * @return
     */
    public static int maxDepth1(TreeNode treeNode) {
        if(treeNode == null) return 0;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(treeNode);

        LinkedList<TreeNode> list = null;
        int count = 0;

        while (!queue.isEmpty()) {
            list = new LinkedList<>();
            for (TreeNode poll : queue) {
                if (poll.left != null) {
                    list.add(poll.getLeft());
                }
                if (poll.right != null) {
                    list.add(poll.getRight());
                }
            }
            queue = list;
            count++;
        }
        return count;
    }
}
