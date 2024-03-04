package com.leetcode.study.tree.binary.node;

/**
 * @author dreamyao
 * @title
 * @date 2024/3/4 12:40
 * @since 1.0.0
 */
public class Node {

    // 值
    public int val;
    // 左孩子
    public Node left;
    // 右孩子
    public Node right;
    // next指针
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}