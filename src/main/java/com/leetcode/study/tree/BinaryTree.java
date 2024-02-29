package com.leetcode.study.tree;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * @author dreamyao
 * @title 二叉树
 * @date 2024/2/26 17:16
 * @since 1.0.0
 */
@SuppressWarnings("all")
public class BinaryTree {

    private TreeNode pre = null;
    private int ans;
    private Set<Integer> set = new HashSet<>();

    /**
     * 二叉树
     */
    static class TreeNode {

        // 值
        int val;
        // 左孩子
        TreeNode left;
        // 右孩子
        TreeNode right;

        TreeNode() {

        }

        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = null;
        }
    }

    /**
     * 二叉树
     */
    static class Node {

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

    /**
     * 144. 二叉树的前序遍历
     * @param root 根节点
     * @return 遍历结果
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        traversal(root, ans);
        return ans;
    }

    private void traversal(TreeNode root, List<Integer> ans) {
        if (root == null) {
            return;
        }
        // 中
        ans.add(root.val);
        // 左
        traversal(root.left, ans);
        // 右
        traversal(root.right, ans);
    }

    /**
     * 前序遍历（迭代法）
     * @param root 根节点
     * @return 遍历结果
     */
    public List<Integer> preorderTraversalFront(TreeNode root) {
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
                // 添加左节点（空节点不入栈）
                if (node.left != null) st.push(node.left);
                // 添加中节点
                st.push(node);
                // 中节点访问过，但是还没有处理，加入空节点做为标记。
                st.push(null);

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
     * 后序遍历（迭代法）
     * @param root 根节点
     * @return 遍历结果
     */
    public List<Integer> preorderTraversalBack(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root != null) {
            stack.push(root);
        }
        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            // 将该节点弹出，避免重复操作，下面再将右中左节点添加到栈中 或 将空节点弹出
            stack.pop();
            if (node != null) {
                // 添加中节点
                stack.push(node);
                // 中节点访问过，但是还没有处理，加入空节点做为标记。
                stack.push(null);
                // 添加右节点
                if (node.right != null) {
                    stack.push(node.right);
                }
                // 添加左节点
                if (node.left != null) {
                    stack.push(node.left);
                }
            } else {
                // 只有遇到空节点的时候，才将下一个节点放进结果集
                // 重新取出栈中元素
                node = stack.pop();
                // 加入到结果集
                result.add(node.val);
            }
        }
        return result;
    }

    /**
     * 102. 二叉树的层序遍历
     * @param root 根节点
     * @return 遍历结果
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        // 存储最终结果
        List<List<Integer>> result = new ArrayList<>();
        // 定义队列
        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root != null) {
            // 根节点不为空就入队
            queue.offer(root);
        }
        // 遍历二叉树
        while (!queue.isEmpty()) {
            // 存放二叉树当前层的结果
            List<Integer> ans = new ArrayList<>();
            // 获取二叉树当前层对应的队列大小
            int size = queue.size();
            while (size-- > 0) {
                // 弹出队头元素
                TreeNode node = queue.poll();
                if (node != null) {
                    // 把遍历的值放入结果集中
                    ans.add(node.val);
                    if (node.left != null) {
                        // 如果左孩子不为空就把左孩子入队
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        // 如果右孩子不为空就把左孩子入队
                        queue.offer(node.right);
                    }
                }
            }
            result.add(ans);
        }
        return result;
    }

    /**
     * 107. 二叉树的层序遍历 II
     * @param root 根节点
     * @return 遍历结果
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        // 存储最终结果
        List<List<Integer>> result = new ArrayList<>();
        // 定义队列
        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root != null) {
            // 根节点不为空就入队
            queue.offer(root);
        }
        // 遍历二叉树
        while (!queue.isEmpty()) {
            // 存放二叉树当前层的结果
            List<Integer> ans = new ArrayList<>();
            // 获取二叉树当前层对应的队列大小
            int size = queue.size();
            while (size-- > 0) {
                // 弹出队头元素
                TreeNode node = queue.poll();
                if (node != null) {
                    // 把遍历的值放入结果集中
                    ans.add(node.val);
                    if (node.left != null) {
                        // 如果左孩子不为空就把左孩子入队
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        // 如果右孩子不为空就把左孩子入队
                        queue.offer(node.right);
                    }
                }
            }
            result.add(ans);
        }

        // 反转自顶向下层序遍历的结果，就得到了自底向上的层序遍历结果
        Collections.reverse(result);
        return result;
    }

    /**
     * 199. 二叉树的右视图
     * @param root 根节点
     * @return 遍历结果
     */
    public List<Integer> rightSideView(TreeNode root) {
        // 存放二叉树的结果
        List<Integer> ans = new ArrayList<>();
        // 定义队列
        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root != null) {
            // 根节点不为空就入队
            queue.offer(root);
        }
        // 遍历二叉树
        while (!queue.isEmpty()) {
            // 获取二叉树当前层对应的队列大小
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                // 弹出队头元素
                TreeNode node = queue.poll();
                if (node != null) {
                    if (i == size - 1) {
                        // size -1 就是队列的尾部，也就是二叉树最右边的孩子
                        // 把遍历的值放入结果集中
                        ans.add(node.val);
                    }
                    if (node.left != null) {
                        // 如果左孩子不为空就把左孩子入队
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        // 如果右孩子不为空就把左孩子入队
                        queue.offer(node.right);
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 637. 二叉树的层平均值
     * @param root 根节点
     * @return 遍历结果
     */
    public List<Double> averageOfLevels(TreeNode root) {
        // 存储结果
        List<Double> ans = new ArrayList<>();
        // 定义队列
        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root != null) {
            queue.offer(root);
        }
        // 遍历二叉树
        while (!queue.isEmpty()) {
            // 获取二叉树当前层对应队列的长度
            int size = queue.size();
            // 临时保持size大小，方便后面计算使用
            int cur_size = size;
            // 当前层的和
            BigDecimal sum = BigDecimal.ZERO;
            while (size-- > 0) {
                // 弹出队首元素
                TreeNode node = queue.poll();
                if (node != null) {
                    sum = sum.add(new BigDecimal(String.valueOf(node.val)));
                    if (node.left != null) {
                        // 如果左孩子不为空就把左孩子入队
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        // 如果右孩子不为空就把左孩子入队
                        queue.offer(node.right);
                    }
                }
            }
            ans.add(sum.divide(new BigDecimal(String.valueOf(cur_size)), 5,
                               RoundingMode.HALF_UP).doubleValue());
        }
        return ans;
    }

    /**
     * 515. 在每个树行中找最大值
     * @param root 根节点
     * @return 遍历结果
     */
    public List<Integer> largestValues(TreeNode root) {
        // 存储结果
        List<Integer> result = new ArrayList<>();
        // 定义队列
        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root != null) {
            // 根节点不为空则入队
            queue.offer(root);
        }
        // 遍历二叉树
        while (!queue.isEmpty()) {
            // 获取二叉树当前层对应的队列长度
            int size = queue.size();
            // 二叉树同层最大数
            int max = Integer.MIN_VALUE;
            while (size-- > 0) {
                // 弹出队首元素
                TreeNode node = queue.poll();
                if (node != null) {
                    // 计算最大值
                    max = Math.max(max, node.val);
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }
            }
            result.add(max);
        }
        return result;
    }

    /**
     * 116. 填充每个节点的下一个右侧节点指针
     * 1.从第一层开始（第一层只有一个 root 节点），每次循环：
     * 2.遍历当前层的链表节点，通过节点的 left 和 right 得到下一层的节点。
     * 3.把下一层的节点从左到右连接成一个链表。
     * 4.拿到下一层链表的头节点，进入下一轮循环。
     * @param root 根节点
     * @return 根节点
     */
    public Node connect(Node root) {
        Node dummy = new Node();
        Node cur = root;
        while (cur != null) {
            dummy.next = null;
            Node nxt = dummy; // 下一层的链表
            while (cur != null) { // 遍历当前层的链表
                if (cur.left != null) {
                    nxt.next = cur.left; // 下一层的相邻节点连起来
                    nxt = cur.left;
                }
                if (cur.right != null) {
                    nxt.next = cur.right; // 下一层的相邻节点连起来
                    nxt = cur.right;
                }
                cur = cur.next; // 当前层链表的下一个节点
            }
            cur = dummy.next; // 下一层链表的头节点
        }
        return root;
    }

    /**
     * 104. 二叉树的最大深度（后序遍历）
     * @param root 根节点
     * @return 最大深度
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * 104. 二叉树的最大深度（层序遍历）
     * @param root 根节点
     * @return 最大深度
     */
    public int maxDepthIter(TreeNode root) {
        // 定义最大高度
        int max_depth = 0;
        // 定义队列
        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root != null) {
            // 根节点不为空则入队
            queue.offer(root);
        }
        // 遍历二叉树
        while (!queue.isEmpty()) {
            // 长度
            int size = queue.size();
            max_depth++;
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (node != null) {
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }
            }
        }
        return max_depth;
    }

    /**
     * 111. 二叉树的最小深度
     * @param root 根节点
     * @return 最小深度
     */
    public int minDepth(TreeNode root) {
        // 1.递归道空节点，空节点道深度是0
        if (root == null) {
            return 0;
        }
        // 2.如果左孩子和由孩子其中一个为空，那么需要返回比较大的那个孩子的深度
        int m1 = minDepth(root.left);
        int m2 = minDepth(root.right);
        // 这里其中一个节点为空，说明m1和m2有一个必然为0，所以可以返回m1 + m2 + 1;
        if (root.left == null || root.right == null) {
            return m1 + m2 + 1;
        }
        // 3.最后一种情况，也就是左右孩子都不为空，返回最小深度+1即可
        return Math.min(m1, m2) + 1;
    }

    /**
     * 100. 相同的树（后序遍历）
     * @param p p树
     * @param q q树
     * @return 是否相同
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            // 当两棵树的当前节点都为 null 时返回 true
            return true;
        }
        if (p == null || q == null) {
            // 当其中一个为 null 另一个不为 null 时返回 false
            return false;
        }
        if (p.val != q.val) {
            // 当两个都不为空但是值不相等时，返回 false
            return false;
        }
        // 执行过程：当满足终止条件时进行返回，不满足时分别判断左子树和右子树是否相同，其中要注意代码中的短路效应
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /**
     * 572. 另一棵树的子树
     * @param root    root树
     * @param subRoot subRoot树
     * @return root树是否包含subRoot树
     */
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        // 定义队列
        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root != null) {
            queue.offer(root);
        }
        // 层序遍历root树
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (isSameTree(node, subRoot)) {
                    return true;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return false;
    }

    /**
     * 572. 另一棵树的子树
     * @param root    root树
     * @param subRoot subRoot树
     * @return root树是否包含subRoot树
     */
    public boolean isSubtreeII(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) {
            return true;
        }
        if (root == null || subRoot == null) {
            return false;
        }
        return isSameTree(root, subRoot) || isSubtreeII(root.left, subRoot) || isSubtreeII(root.right, subRoot);
    }

    /**
     * 226. 翻转二叉树（前序遍历）
     * @param root 根节点
     * @return 翻转后的二叉树
     */
    public TreeNode invertTree(TreeNode root) {

        // ----------------------- 深度优选搜索 -----------------------
        // 前序遍历
        /*if (root == null) {
            return null;
        }
        // 中
        // 交换左右子树
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        // 左
        invertTree(root.left);
        // 右
        invertTree(root.right);
        return root;*/

        // 中序遍历
        /*if (root == null) {
            return null;
        }
        // 左
        invertTree(root.left);
        // 中
        // 交换左右子树
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        // 右
        invertTree(root.left);
        return root;*/

        // 后序遍历
        /*if (root == null) {
            return null;
        }
        // 左
        invertTree(root.left);
        // 右
        invertTree(root.right);
        // 中
        // 交换左右子树
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        return root;*/

        // ----------------------- 广度优先搜索 -----------------------
        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root != null) {
            queue.offer(root);
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (node != null) {
                    // 交换左右子树
                    TreeNode temp = node.left;
                    node.left = node.right;
                    node.right = temp;
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }
            }
        }
        return root;
    }

    /**
     * 左右子树交换
     * @param node 节点
     */
    private void swap(TreeNode node) {
        // 交换左右子树
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
    }

    /**
     * 101. 对称二叉树
     * @param root 根节点
     * @return 是否对称
     */
    public boolean isSymmetric(TreeNode root) {
        return compare(root.left, root.right);
    }

    /**
     * 后序遍历
     * @param p
     * @param q
     * @return
     */
    private boolean compare(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            // 当两棵树的当前节点都为 null 时返回 true
            return true;
        }
        if (p == null || q == null) {
            // 当其中一个为 null 另一个不为 null 时返回 false
            return false;
        }
        if (p.val != q.val) {
            // 当两个都不为空但是值不相等时，返回 false
            return false;
        }
        // 执行过程：当满足终止条件时进行返回，不满足时分别判断左子树和右子树是否对称
        return compare(p.left, q.right) && compare(p.right, q.left);
    }

    /**
     * 222. 完全二叉树的节点个数（层序遍历）
     * @param root 根节点
     * @return 节点数
     */
    public int countNodes(TreeNode root) {
        // 统计节点数
        int count = 0;
        // 定义队列
        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root != null) {
            queue.offer(root);
        }
        // 遍历二叉树
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (node != null) {
                    count++;
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }
            }
        }
        return count;
    }

    /**
     * 222. 完全二叉树的节点个数（后序遍历）
     * @param node 二叉树
     * @return 节点数
     */
    private int getNodeNum(TreeNode node) {
        if (node == null) {
            return 0;
        }
        // 左
        int leftNodeNum = getNodeNum(node.left);
        // 右
        int rightNodeNum = getNodeNum(node.right);
        // 中
        return leftNodeNum + rightNodeNum + 1;
    }

    /**
     * 222. 完全二叉树的节点个数（利用完全二叉树的特点）
     * @param root 根节点
     * @return 节点数
     */
    public int countNodesII(TreeNode root) {
        if (root == null) {
            // 终止条件一：遍历道空节点就终止递归
            return 0;
        }
        // 定义左子树
        TreeNode left = root.left;
        // 定义右子树
        TreeNode right = root.right;
        // 记录左右子树的深度
        int left_depth = 0;
        int right_depth = 0;
        // 一直遍历左子树得到左子树的深度
        while (left != null) {
            left = left.left;
            left_depth++;
        }
        // 一直遍历右子树得到右子树的深度
        while (right != null) {
            right = right.right;
            right_depth++;
        }
        // 终止条件二：当左右子树深度相同时表明是子树是一个完全二叉树
        if (left_depth == right_depth) {
            // 注意(2<<1) 相当于2^2，所以leftDepth初始为0
            return (2 << left_depth) - 1;
        }

        // 单层递归的逻辑
        // 左
        int left_num = countNodesII(root.left);
        // 右
        int right_num = countNodesII(root.right);
        // 中
        return left_num + right_num + 1;
    }

    /**
     * 110. 平衡二叉树
     * @param root 根节点
     * @return 是否平衡二叉树
     */
    public boolean isBalanced(TreeNode root) {
        if (getHight(root) != -1) {
            return true;
        }
        return false;
    }

    /**
     * 计算平衡二叉树高度（后序遍历）
     * @param node 平衡二叉树
     * @return 高度
     */
    private int getHight(TreeNode node) {
        if (node == null) {
            // 递归终止条件一：遍历到空节点
            return 0;
        }
        // 左
        int leftHight = getHight(node.left);
        // 右
        int rightHight = getHight(node.right);
        // 中
        if (leftHight == -1) {
            // 递归终止条件二：左子树不是平衡二叉树
            return -1;
        }
        if (rightHight == -1) {
            // 递归终止条件三：右子树不是平衡二叉树
            return -1;
        }
        if (Math.abs(leftHight - rightHight) > 1) {
            // 递归终止条件四：左右子树高度差的绝对值超过1
            return -1;
        } else {
            return Math.max(leftHight, rightHight) + 1;
        }
    }

    /**
     * 257. 二叉树的所有路径
     * @param root 根节点
     * @return 所有路径
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<Integer> vals = new ArrayList<>();
        List<String> result = new ArrayList<>();
        binaryTreePaths(root, vals, result);
        return result;
    }

    private void binaryTreePaths(TreeNode root, List<Integer> vals, List<String> result) {

        // 中,需要放在此位置的原因是递归终止条件不在是node是空节点，而是node的子节点为空节点才终止
        // 如果放在终止条件之后就会漏掉最后一个节点
        vals.add(root.val);

        // 递归终止条件
        if (root.left == null && root.right == null) {
            // 输出
            StringBuilder sb = new StringBuilder();// StringBuilder用来拼接字符串，速度更快
            for (int i = 0; i < vals.size() - 1; i++) {
                sb.append(vals.get(i)).append("->");
            }
            sb.append(vals.get(vals.size() - 1));// 记录最后一个节点
            result.add(sb.toString());// 收集一个路径
            return;
        }

        // 左
        if (root.left != null) {
            binaryTreePaths(root.left, vals, result);
            // 回溯
            vals.removeLast();
        }

        // 右
        if (root.right != null) {
            binaryTreePaths(root.right, vals, result);
            // 回溯
            vals.removeLast();
        }
    }

    /**
     * 129. 求根节点到叶节点数字之和
     * @param root 根节点
     * @return 和
     */
    public int sumNumbers(TreeNode root) {
        List<Integer> vals = new ArrayList<>();
        List<String> result = new ArrayList<>();
        sumNumbers(root, vals, result);
        return result.stream().map(Integer::valueOf).reduce((v1, v2) -> v1 + v2).orElse(0);
    }

    private void sumNumbers(TreeNode root, List<Integer> vals, List<String> result) {
        // 中,需要放在此位置的原因是递归终止条件不在是node是空节点，而是node的子节点为空节点才终止
        // 如果放在终止条件之后就会漏掉最后一个节点
        vals.add(root.val);
        // 递归终止条件
        if (root.left == null && root.right == null) {
            // 输出
            StringBuilder sb = new StringBuilder();// StringBuilder用来拼接字符串，速度更快
            for (int i = 0; i < vals.size() - 1; i++) {
                sb.append(vals.get(i));
            }
            sb.append(vals.get(vals.size() - 1));// 记录最后一个节点
            result.add(sb.toString());// 收集一个路径
            return;
        }
        // 左
        if (root.left != null) {
            sumNumbers(root.left, vals, result);
            // 回溯
            vals.removeLast();
        }
        // 右
        if (root.right != null) {
            sumNumbers(root.right, vals, result);
            // 回溯
            vals.removeLast();
        }
    }

    /**
     * 113. 路径总和 II
     * @param root      根节点
     * @param targetSum 总和
     * @return 和等于总和的所有路径
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<Integer> vals = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        pathSum(root, vals, result, targetSum, 0);
        return result;
    }

    private void pathSum(TreeNode root, List<Integer> vals, List<List<Integer>> result, int targetSum, int sum) {
        // 中,需要放在此位置的原因是递归终止条件不在是node是空节点，而是node的子节点为空节点才终止
        // 如果放在终止条件之后就会漏掉最后一个节点
        vals.add(root.val);
        // 累加
        sum += root.val;

        // 递归终止条件
        if (root.left == null && root.right == null) {
            if (sum == targetSum) {
                result.add(List.copyOf(vals));
                sum = 0;
            }
            return;
        }

        // 左
        if (root.left != null) {
            pathSum(root.left, vals, result, targetSum, sum);
            // 回溯
            vals.removeLast();
        }

        // 右
        if (root.right != null) {
            pathSum(root.right, vals, result, targetSum, sum);
            // 回溯
            vals.removeLast();
        }
    }

    /**
     * 437. 路径总和 III （前序遍历 + 前缀和）
     * @param root      根节点
     * @param targetSum 总和
     * @return 总路径数
     */
    public int pathSumThree(TreeNode root, int targetSum) {
        Map<Long, Integer> prefix = new HashMap<Long, Integer>();
        prefix.put(0L, 1);
        return dfs(root, prefix, 0, targetSum);
    }

    public int dfs(TreeNode root, Map<Long, Integer> prefix, long curr, int targetSum) {
        // 递归终止条件
        if (root == null) {
            return 0;
        }

        // 中
        curr += root.val;
        int ret = prefix.getOrDefault(curr - targetSum, 0);
        prefix.put(curr, prefix.getOrDefault(curr, 0) + 1);
        // 左
        ret += dfs(root.left, prefix, curr, targetSum);
        // 右
        ret += dfs(root.right, prefix, curr, targetSum);
        // 回溯
        prefix.put(curr, prefix.getOrDefault(curr, 0) - 1);

        return ret;
    }

    /**
     * 404. 左叶子之和（后序遍历）
     * @param root 根节点
     * @return 左叶子之和
     */
    public int sumOfLeftLeaves(TreeNode root) {
        // 当前节点为空退出递归
        if (root == null) {
            return 0;
        }
        // 当前节点的左右子节点都为空说明已经遍历到叶子节点此时也退出递归
        if (root.left == null && root.right == null) {
            return 0;
        }
        // 左
        int leftSum = sumOfLeftLeaves(root.left);
        if (root.left != null && root.left.left == null && root.left.right == null) {
            // root处于左叶子节点的上一个节点
            leftSum = root.left.val;
        }
        // 右
        int rightSum = sumOfLeftLeaves(root.right);
        // 中
        return leftSum + rightSum;
    }

    /**
     * 404. 左叶子之和（层序遍历）
     * @param root 根节点
     * @return 左叶子之和
     */
    public int sumOfLeftLeavesII(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 采用层序遍历
        // 定义左子数之和
        int leftSum = 0;
        // 定义队列
        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root != null) {
            queue.offer(root);
        }
        // 遍历二叉树
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    if (node.left != null) {
                        queue.offer(node.left);
                        // 此判断表明是左叶子节点了
                        if (node.left.left == null && node.left.right == null) {
                            leftSum += node.left.val;
                        }
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }
            }
        }
        return leftSum;
    }

    /**
     * 513. 找树左下角的值（层序遍历）
     * @param root 根节点
     * @return 左下角的值
     */
    public int findBottomLeftValue(TreeNode root) {
        // 定义结果
        int result = 0;
        // 定义队列
        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root != null) {
            queue.offer(root);
        }
        // 遍历二叉树
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    if (i == 0) {
                        // 取每层的左边第一个节点值，下层覆盖上层的值，遍历到最后一层就是左边第一个叶子节点的值
                        result = node.val;
                    }
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }
            }
        }
        return result;
    }

    /**
     * 112. 路径总和
     * @param root      根节点
     * @param targetSum 目标值
     * @return 是否右总和等于目标值的路径
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        // 中,需要放在此位置的原因是递归终止条件不在是node是空节点，而是node的子节点为空节点才终止
        // 累加
        targetSum -= root.val;

        // 递归终止条件
        if (root.left == null && root.right == null) {
            return targetSum == 0;
        }

        // 左
        if (root.left != null) {
            boolean left = hasPathSum(root.left, targetSum);
            if (left) {
                return true;
            }
        }

        // 右
        if (root.right != null) {
            boolean right = hasPathSum(root.right, targetSum);
            if (right) {
                return true;
            }
        }
        return false;
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

    /**
     * 114. 二叉树展开为链表
     * @param root 根节点
     */
    public void flatten(TreeNode root) {
        // 需要按照先序遍历的相反顺序进行遍历，否则会导致当把左节点负值给右节点时原本的右节点丢失
        // 递归终止条件
        if (root == null) {
            return;
        }
        // 右
        if (root.right != null) {
            flatten(root.right);
        }
        // 左
        if (root.left != null) {
            flatten(root.left);
        }
        root.right = pre;
        root.left = null;
        pre = root;
    }

    /**
     * 543. 二叉树的直径
     * @param root 根节点
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {
        ans = 1;
        depth(root);
        return ans - 1;
    }

    public int depth(TreeNode node) {
        if (node == null) {
            // 访问到空节点了，返回0
            return 0;
        }
        // 左儿子为根的子树的深度
        int left = depth(node.left);
        // 右儿子为根的子树的深度
        int right = depth(node.right);
        // 计算d_node即L+R+1 并更新ans
        ans = Math.max(ans, left + right + 1);
        // 返回该节点为根的子树的深度
        return Math.max(left, right) + 1;
    }

    /**
     * 563. 二叉树的坡度
     * @param root 根节点
     * @return
     */
    public int findTilt(TreeNode root) {
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        // 左
        int sumLeft = dfs(node.left);
        // 右
        int sumRight = dfs(node.right);
        // 中
        ans += Math.abs(sumLeft - sumRight);
        return sumLeft + sumRight + node.val;
    }

    /**
     * 617. 合并二叉树
     * @param root1 二叉树
     * @param root2 二叉树
     * @return 合并后的二叉树
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return null;
        }
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        // 如果两个节点都不为空，那么合并值
        root1.val += root2.val;
        // 合并左子数
        root1.left = mergeTrees(root1.left, root2.left);
        // 合并右子数
        root1.right = mergeTrees(root1.right, root2.right);
        return root1;
    }

    /**
     * 2236. 判断根结点是否等于子结点之和
     * @param root 根节点
     * @return 根结点是否等于子结点之和
     */
    public boolean checkTree(TreeNode root) {
        if (root == null) {
            return false;
        }
        // 根节点值
        int rootVal = root.val;
        int sum = 0;
        if (root.left != null) {
            sum += root.left.val;
        }
        if (root.right != null) {
            sum += root.right.val;
        }
        return rootVal == sum;
    }

    /**
     * LCR 145. 判断对称二叉树
     * @param root 根节点
     * @return 是否对称二叉树
     */
    public boolean checkSymmetricTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        return checkSymmetricTree(root.left, root.right);
    }

    private boolean checkSymmetricTree(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            // 如果左右节点都为空则返回true
            return true;
        }
        if (left == null || right == null) {
            // 如果一个为空一个不为空则返回false
            return false;
        }
        // 左右节点都右值时判断值是否相等
        if (left.val != right.val) {
            // 如果值不相等返回false
            return false;
        }
        // 处理值相等的情况
        return checkSymmetricTree(left.left, right.right) && checkSymmetricTree(left.right, right.left);
    }

    /**
     * LCR 194. 二叉树的最近公共祖先（前序遍历）
     * <p>
     * 1. 若树里面存在p，也存在q，则返回他们的公共祖先。
     * 2. 若树里面只存在p，或只存在q，则返回存在的那一个。
     * 3. 若树里面既不存在p，也不存在q，则返回null。
     * @param root 根节点
     * @param p    p节点
     * @param q    q节点
     * @return 最近公共祖先节点
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 根节点都为空,那么root为根的树中必定没有p与q
        // 或者这么说,p与q均为null满足为null的子节点,也返回null
        if (root == null) {
            return null;
        }
        // 最近公共祖先的情况1:p或q直接为root,那么p或q就是所求
        // 这里具体又可以分为几种情况:(设定p == root, q == root同理)
        // 1.p与q均存在于root中,结论显然成立,返回p
        // 2.p存在但q不存在root中,根据helper()定义,返回存在的那个节点,返回p
        if (root == p) {
            return p;
        }
        // 同理
        if (root == q) {
            return q;
        }
        // 递归求出root左右子树中p与q的最近公共祖先
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // 最近公共祖先的情况2:p与q分居root异侧
        // 这种情况就是左右两边各有一个p或q,root必定为所求
        if (left != null && right != null) {
            return root;
        }
        // left == null && right != null时,左边没有 右边有 的情况
        // 最近公共祖先就是右边找到的那个(注意是已经递归出栈将祖先找出来了!)
        if (left == null) {
            return right;
        }
        // 同理
        if (right == null) {
            return left;
        }
        // left == null && right == null时
        // root两边都找不到,root本身也不是p或q,root又不为null
        return null;
    }

    /**
     * 面试题 04.02. 最小高度树
     * @param nums 数组
     * @return 二叉树
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return traversal(nums, 0, nums.length);
    }

    private TreeNode traversal(int[] nums, int left, int right) {
        if (left >= right) {
            // 递归终止条件
            return null;
        }
        // 从数组中间进行切割，找到数组中间节点下标
        int mid = left + (right - left) / 2;
        // 构造二叉树根节点
        TreeNode root = new TreeNode(nums[mid]);
        // 构造左子树
        root.left = traversal(nums, left, mid);
        // 构造右子树
        root.right = traversal(nums, mid + 1, right);
        return root;
    }

    /**
     * 965. 单值二叉树
     * @param root 根节点
     * @return 是否单值二叉树
     */
    public boolean isUnivalTree(TreeNode root) {
        if (root == null) {
            // 递归终止条件
            return true;
        }
        // 左
        if (root.left != null) {
            if (root.val != root.left.val || !isUnivalTree(root.left)) {
                return false;
            }
        }
        // 右
        if (root.right != null) {
            if (root.val != root.right.val || !isUnivalTree(root.right)) {
                return false;
            }
        }
        // 中
        return true;
    }

    /**
     * LCR 175. 计算二叉树的深度
     * @param root 根节点
     * @return 深度
     */
    public int calculateDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(calculateDepth(root.left), calculateDepth(root.right)) + 1;
    }

    /**
     * LCR 144. 翻转二叉树
     * @param root 根节点
     * @return 翻转后的二叉树
     */
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 左
        mirrorTree(root.left);
        // 右
        mirrorTree(root.right);
        // 中，交换左右节点
        swap(root);
        return root;
    }

    /**
     * 653. 两数之和 IV - 输入二叉搜索树（深度优先 + 哈希表）
     * @param root 根节点
     * @param k    和
     * @return 是否存在
     */
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        // 中
        if (set.contains(k - root.val)) {
            return true;
        } else {
            set.add(root.val);
        }
        // 左 右
        return findTarget(root.left, k) || findTarget(root.right, k);
    }
}
