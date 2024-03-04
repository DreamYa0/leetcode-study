package com.leetcode.study.tree.binary;

import com.leetcode.study.tree.binary.node.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author dreamyao
 * @title 二叉树中序遍历
 * @date 2024/3/3 23:00
 * @since 1.0.0
 */
@SuppressWarnings("all")
public class MidOrder {

    /**
     * 中序遍历（迭代法）
     * @param root 根节点
     * @return 遍历结果
     */
    public List<Integer> preorderTraversalMiddle(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        Stack<TreeNode> st = new Stack<>();
        if (root != null) st.push(root);
        while (!st.empty()) {
            TreeNode node = st.peek();
            // 将该节点弹出，避免重复操作，下面再将右中左节点添加到栈中 或 将空节点弹出
            st.pop();
            if (node != null) {
                // 添加右节点（空节点不入栈）
                if (node.right != null) st.push(node.right);
                // 添加中节点
                st.push(node);
                // 中节点访问过，但是还没有处理，加入空节点做为标记。
                st.push(null);
                // 添加左节点（空节点不入栈）
                if (node.left != null) st.push(node.left);
            } else {
                // 只有遇到空节点的时候，才将下一个节点放进结果集
                // 重新取出栈中元素
                node = st.pop();
                // 加入到结果集
                result.add(node.val);
            }
        }
        return result;
    }

    /**
     * 106. 从中序与后序遍历序列构造二叉树
     * @param inorder   中序数组
     * @param postorder 后序数组
     * @return 二叉树
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (postorder.length == 0 || inorder.length == 0) {
            return null;
        }
        return buildHelper(inorder, 0, inorder.length, postorder, 0,
                           postorder.length);
    }

    private TreeNode buildHelper(int[] inorder,
                                 int inorderStart,
                                 int inorderEnd,
                                 int[] postorder,
                                 int postorderStart,
                                 int postorderEnd) {

        if (postorderStart == postorderEnd) {
            return null;
        }
        // 后序数组的最后一个元素就是根节点元素
        int rootVal = postorder[postorderEnd - 1];
        // 构造根节点
        TreeNode root = new TreeNode(rootVal);
        // 根节点在中序数组中的位置
        int middleIndex;
        for (middleIndex = inorderStart; middleIndex < inorderEnd; middleIndex++) {
            // 找到根节点在中序数组中的位置
            if (inorder[middleIndex] == rootVal)
                break;
        }
        // 中序数组左区间开始位置
        int leftInorderStart = inorderStart;
        // 中序数组左区间结束位置
        int leftInorderEnd = middleIndex;
        // 中序数组右区间开始位置
        int rightInorderStart = middleIndex + 1;
        // 中序数组右区间结束位置
        int rightInorderEnd = inorderEnd;
        // 后序数组左区间开始位置
        int leftPostorderStart = postorderStart;
        // 后序数组左区间结束位置
        int leftPostorderEnd = postorderStart + (middleIndex - inorderStart);
        // 后序数组右区间开始位置
        int rightPostorderStart = leftPostorderEnd;
        // 后序数组右区间结束位置
        int rightPostorderEnd = postorderEnd - 1;
        // 构造左子数
        root.left = buildHelper(inorder, leftInorderStart, leftInorderEnd, postorder, leftPostorderStart, leftPostorderEnd);
        // 构造右指数
        root.right = buildHelper(inorder, rightInorderStart, rightInorderEnd, postorder, rightPostorderStart, rightPostorderEnd);
        // 返回根节点
        return root;
    }
}
