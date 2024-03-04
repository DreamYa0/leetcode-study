package com.leetcode.study.tree.binary.node;

/**
 * @author dreamyao
 * @title 二叉树
 * @date 2024/3/4 12:39
 * @since 1.0.0
 */
public class TreeNode {

    // 值
    public int val;
    // 左孩子
    public TreeNode left;
    // 右孩子
    public TreeNode right;

    public TreeNode() {

    }

    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = null;
    }
}
