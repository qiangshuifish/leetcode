package ink.putin.study.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树前序遍历
 * 顶点->左节点->右节点
 */
public class PrintTreeNode {

    public static void main(String[] args) {
        TreeNode treeNode = TreeOperation.getTreeNode();
        List<Integer> list = new ArrayList<>();

        printTreeNLR(treeNode,list);
        System.out.println(list);
        list.clear();

        printTreeLNR(treeNode,list);
        System.out.println(list);
        list.clear();

        printTreeLRN(treeNode,list);
        System.out.println(list);
    }

    public static void printTreeNLR(TreeNode node,List<Integer> list){
        if(node == null){
            return;
        }
        list.add(node.val);
        printTreeNLR(node.left,list);
        printTreeNLR(node.right,list);
    }

    public static void printTreeLNR(TreeNode node,List<Integer> list){
        if(node == null){
            return;
        }
        printTreeLNR(node.left,list);
        list.add(node.val);
        printTreeLNR(node.right,list);
    }

    public static void printTreeLRN(TreeNode node,List<Integer> list){
        if(node == null){
            return;
        }
        printTreeLRN(node.left,list);
        printTreeLRN(node.right,list);
        list.add(node.val);
    }
}
