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

    /**
     * 872. 叶子相似的树
     * 请考虑一棵二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列 。
     * <p>
     * <img src="https://s3-lc-upload.s3.amazonaws.com/uploads/2018/07/16/tree.png" />
     * <p>
     * <p>
     * 举个例子，如上图所示，给定一棵叶值序列为 (6, 7, 4, 9, 8) 的树。
     * <p>
     * 如果有两棵二叉树的叶值序列是相同，那么我们就认为它们是 叶相似 的。
     * <p>
     * 如果给定的两个根结点分别为 root1 和 root2 的树是叶相似的，则返回 true；否则返回 false 。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * <img src="https://assets.leetcode.com/uploads/2020/09/03/leaf-similar-1.jpg" />
     * <p>
     * <p>
     * 输入：root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
     * 输出：true
     * 示例 2：
     * <p>
     * <img src="https://assets.leetcode.com/uploads/2020/09/03/leaf-similar-2.jpg" />
     * <p>
     * <p>
     * 输入：root1 = [1,2,3], root2 = [1,3,2]
     * 输出：false
     * <p>
     * <p>
     * 提示：
     * <p>
     * 给定的两棵树结点数在 [1, 200] 范围内
     * 给定的两棵树上的值在 [0, 200] 范围内
     * @param root1
     * @param root2
     * @return
     */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> node1 = new ArrayList<>();
        leafSimilar(root1, node1);
        List<Integer> node2 = new ArrayList<>();
        leafSimilar(root2, node2);
        if (node1.size() != node2.size()) {
            return false;
        }
        for (int i = 0; i < node1.size(); i++) {
            int v1 = node1.get(i);
            int v2 = node2.get(i);
            if (v1 != v2) {
                return false;
            }
        }
        return true;
    }

    public void leafSimilar(TreeNode node, List<Integer> ans) {
        if (node == null) {
            return;
        }
        // 左
        leafSimilar(node.left, ans);
        // 右
        leafSimilar(node.right, ans);
        if (node.left == null && node.right == null) {
            // 中 只需要把叶子节点加入到 ans中
            ans.add(node.val);
        }
    }

    /**
     * 236. 二叉树的最近公共祖先
     * 中等
     * 相关标签
     * 相关企业
     * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     * <p>
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * <img src="https://assets.leetcode.com/uploads/2018/12/14/binarytree.png" />
     * <p>
     * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
     * 输出：3
     * 解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
     * 示例 2：
     * <p>
     * <img src="https://assets.leetcode.com/uploads/2018/12/14/binarytree.png" />
     * <p>
     * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
     * 输出：5
     * 解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
     * 示例 3：
     * <p>
     * 输入：root = [1,2], p = 1, q = 2
     * 输出：1
     * <p>
     * <p>
     * 提示：
     * <p>
     * 树中节点数目在范围 [2, 105] 内。
     * -109 <= Node.val <= 109
     * 所有 Node.val 互不相同 。
     * p != q
     * p 和 q 均存在于给定的二叉树中。
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }
        // 左
        TreeNode l = lowestCommonAncestor(root.left, p, q);
        // 右
        TreeNode r = lowestCommonAncestor(root.right, p, q);
        // 中
        if (l == null && r == null) {
            // 没找到
            return null;
        } else if (l != null && r == null) {
            // 说明p,q的公共祖先再左子树
            return l;
        } else if (l == null && r != null) {
            // 说明p,q的公共祖先再右子树
            return r;
        } else {
            // p,q公共祖先
            return root;
        }
    }

    /**
     * 337. 打家劫舍 III
     * 中等
     * 相关标签
     * 相关企业
     * 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。
     *
     * 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
     *
     * 给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。
     *
     *
     *
     * 示例 1:
     *
     * <img src="https://assets.leetcode.com/uploads/2021/03/10/rob1-tree.jpg" />
     *
     * 输入: root = [3,2,3,null,3,null,1]
     * 输出: 7
     * 解释: 小偷一晚能够盗取的最高金额 3 + 3 + 1 = 7
     * 示例 2:
     *
     * <img src="https://assets.leetcode.com/uploads/2021/03/10/rob2-tree.jpg" />
     *
     * 输入: root = [3,4,5,1,3,null,1]
     * 输出: 9
     * 解释: 小偷一晚能够盗取的最高金额 4 + 5 = 9
     *
     *
     * 提示：
     *
     * 树的节点数在 [1, 104] 范围内
     * 0 <= Node.val <= 104
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int money = root.val;
        // 左
        if (root.left != null) {
            money += (rob(root.left.left) + rob(root.left.right));
        }
        if (root.right != null) {
            money += (rob(root.right.left) + rob(root.right.right));
        }
        return Math.max(money, rob(root.left) + rob(root.right));
    }
}
