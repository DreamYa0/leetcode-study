package com.leetcode.study.tree.multi;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author dreamyao
 * @title N叉树
 * @date 2024/2/26 17:22
 * @since 1.0.0
 */
@SuppressWarnings("all")
public class MultiTree {

    /**
     * N 叉树
     */
    static class ManyNode {

        // 值
        public int val;
        // 子树
        public List<ManyNode> children;

        public ManyNode() {
        }

        public ManyNode(int _val) {
            val = _val;
        }

        public ManyNode(int _val, List<ManyNode> _children) {
            val = _val;
            children = _children;
        }
    }

    /**
     * 429. N 叉树的层序遍历
     * @param root 根节点
     * @return 遍历结果
     */
    public List<List<Integer>> levelOrder(ManyNode root) {
        // 存储结果
        List<List<Integer>> result = new ArrayList<>();
        // 定义队列
        Queue<ManyNode> queue = new ArrayDeque<>();
        if (root != null) {
            queue.offer(root);
        }
        // 遍历N叉树
        while (!queue.isEmpty()) {
            // 获取N叉树当前层对应的队列长度
            int size = queue.size();
            // 存放当前层结果
            List<Integer> ans = new ArrayList<>();
            while (size-- > 0) {
                ManyNode node = queue.poll();
                if (node != null) {
                    ans.add(node.val);
                    if (!node.children.isEmpty()) {
                        // 如果子孩子不为空，就从左到右遍历入队列
                        for (ManyNode child : node.children) {
                            queue.offer(child);
                        }
                    }
                }
            }
            result.add(ans);
        }
        return result;
    }

    /**
     * 559. N 叉树的最大深度
     * @param root 根节点
     * @return 最大深度
     */
    public int maxDepth(ManyNode root) {
        // 递归终止条件
        if (root == null) {
            // 遍历道叶子节点时递归退出
            return 0;
        }
        // 定义最大深度
        int max_depth = 0;
        for (ManyNode child : root.children) {
            max_depth = Math.max(max_depth, maxDepth(child));
        }
        return max_depth + 1;
    }

    /**
     * 589. N 叉树的前序遍历
     * @param root 根节点
     * @return 前序遍历结果
     */
    public List<Integer> preorder(ManyNode root) {
        List<Integer> result = new ArrayList<>();
        preorder(root, result);
        return result;
    }

    private void preorder(ManyNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        // 中
        result.add(root.val);
        // 左到右
        for (ManyNode child : root.children) {
            preorder(child, result);
        }
    }

    /**
     * 590. N 叉树的后序遍历
     * @param root 根节点
     * @return 后序遍历结果
     */
    public List<Integer> postorder(ManyNode root) {
        List<Integer> result = new ArrayList<>();
        postorder(root, result);
        return result;
    }

    private void postorder(ManyNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        // 左到右
        for (ManyNode child : root.children) {
            postorder(child, result);
        }
        // 中
        result.add(root.val);
    }
}
