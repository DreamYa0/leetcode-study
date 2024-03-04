package com.leetcode.study.tree.binary;

import com.leetcode.study.tree.binary.node.TreeNode;

import java.util.*;

/**
 * @author dreamyao
 * @title 二叉树后序遍历
 * @date 2024/3/3 23:00
 * @since 1.0.0
 */
@SuppressWarnings("all")
public class PostOrder {

    private int ans;

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
     * 2331. 计算布尔二叉树的值
     * 给你一棵 完整二叉树 的根，这棵树有以下特征：
     * <p>
     * 叶子节点 要么值为 0 要么值为 1 ，其中 0 表示 False ，1 表示 True 。
     * 非叶子节点 要么值为 2 要么值为 3 ，其中 2 表示逻辑或 OR ，3 表示逻辑与 AND 。
     * 计算 一个节点的值方式如下：
     * <p>
     * 如果节点是个叶子节点，那么节点的 值 为它本身，即 True 或者 False 。
     * 否则，计算 两个孩子的节点值，然后将该节点的运算符对两个孩子值进行 运算 。
     * 返回根节点 root 的布尔运算值。
     * <p>
     * 完整二叉树 是每个节点有 0 个或者 2 个孩子的二叉树。
     * <p>
     * 叶子节点 是没有孩子的节点。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * <img src="https://assets.leetcode.com/uploads/2022/05/16/example1drawio1.png"/>
     * <p>
     * <p>
     * 输入：root = [2,1,3,null,null,0,1]
     * 输出：true
     * 解释：上图展示了计算过程。
     * AND 与运算节点的值为 False AND True = False 。
     * OR 运算节点的值为 True OR False = True 。
     * 根节点的值为 True ，所以我们返回 true 。
     * 示例 2：
     * <p>
     * 输入：root = [0]
     * 输出：false
     * 解释：根节点是叶子节点，且值为 false，所以我们返回 false 。
     * <p>
     * <p>
     * 提示：
     * <p>
     * 树中节点数目在 [1, 1000] 之间。
     * 0 <= Node.val <= 3
     * 每个节点的孩子数为 0 或 2 。
     * 叶子节点的值为 0 或 1 。
     * 非叶子节点的值为 2 或 3 。
     * @param root
     * @return
     */
    public boolean evaluateTree(TreeNode root) {
        if (root.left == null) {
            return root.val == 1;
        }
        // 左
        boolean left = evaluateTree(root.left);
        // 右
        boolean right = evaluateTree(root.right);
        // 中
        return root.val == 2 ? left || right : left && right;
    }
}
