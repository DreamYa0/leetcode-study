package com.leetcode.study.tree.binary;

import com.leetcode.study.tree.binary.node.Node;
import com.leetcode.study.tree.binary.node.TreeNode;

import java.util.*;

/**
 * @author dreamyao
 * @title 二叉树中序遍历
 * @date 2024/3/3 23:00
 * @since 1.0.0
 */
@SuppressWarnings("all")
public class MidOrder {

    private TreeNode pre = null;
    // 最下差值
    private int minDiff = Integer.MAX_VALUE;

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
     * 98. 验证二叉搜索树
     * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
     * <p>
     * 有效 二叉搜索树定义如下：
     * <p>
     * 节点的左子树只包含 小于 当前节点的数。
     * 节点的右子树只包含 大于 当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * <img src="https://assets.leetcode.com/uploads/2020/12/01/tree1.jpg" />
     * <p>
     * 输入：root = [2,1,3]
     * 输出：true
     * 示例 2：
     * <p>
     * <img src="https://assets.leetcode.com/uploads/2020/12/01/tree2.jpg" />
     * <p>
     * 输入：root = [5,1,4,null,null,3,6]
     * 输出：false
     * 解释：根节点的值是 5 ，但是右子节点的值是 4 。
     * <p>
     * <p>
     * 提示：
     * <p>
     * 树中节点数目范围在[1, 104] 内
     * -231 <= Node.val <= 231 - 1
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        // 左
        boolean left = isValidBST(root.left);

        // 中
        if (pre != null && root.val <= pre.val) {
            // 如果前一个节点大于后一个节点了
            return false;
        }
        pre = root;

        // 右
        boolean right = isValidBST(root.right);

        return left && right;
    }

    /**
     * 530. 二叉搜索树的最小绝对差
     * 简单
     * 相关标签
     * 相关企业
     * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
     * <p>
     * 差值是一个正数，其数值等于两值之差的绝对值。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * <img src="https://assets.leetcode.com/uploads/2021/02/05/bst1.jpg" />
     * <p>
     * 输入：root = [4,2,6,1,3]
     * 输出：1
     * 示例 2：
     * <p>
     * <img src="https://assets.leetcode.com/uploads/2021/02/05/bst2.jpg" />
     * <p>
     * 输入：root = [1,0,48,null,null,12,49]
     * 输出：1
     * <p>
     * <p>
     * 提示：
     * <p>
     * 树中节点的数目范围是 [2, 104]
     * 0 <= Node.val <= 105
     * <p>
     * <p>
     * 注意：本题与 783 https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/ 相同
     * @param root
     * @return
     */
    public int getMinimumDifference(TreeNode root) {
        minDif(root);
        return minDiff;
    }

    private void minDif(TreeNode node) {
        if (node == null) {
            return;
        }
        // 左
        getMinimumDifference(node.left);
        // 中
        if (pre != null) {
            minDiff = Math.min(minDiff, node.val - pre.val);
        }
        pre = node;
        // 右
        getMinimumDifference(node.right);
    }

    /**
     * 501. 二叉搜索树中的众数
     * 简单
     * 相关标签
     * 相关企业
     * 给你一个含重复值的二叉搜索树（BST）的根节点 root ，找出并返回 BST 中的所有 众数（即，出现频率最高的元素）。
     * <p>
     * 如果树中有不止一个众数，可以按 任意顺序 返回。
     * <p>
     * 假定 BST 满足如下定义：
     * <p>
     * 结点左子树中所含节点的值 小于等于 当前节点的值
     * 结点右子树中所含节点的值 大于等于 当前节点的值
     * 左子树和右子树都是二叉搜索树
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * <img src="https://assets.leetcode.com/uploads/2021/03/11/mode-tree.jpg" />
     * <p>
     * 输入：root = [1,null,2,2]
     * 输出：[2]
     * 示例 2：
     * <p>
     * 输入：root = [0]
     * 输出：[0]
     * <p>
     * <p>
     * 提示：
     * <p>
     * 树中节点的数目在范围 [1, 104] 内
     * -105 <= Node.val <= 105
     * <p>
     * <p>
     * 进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
     * @param root
     * @return
     */
    public int[] findMode(TreeNode root) {
        doFindMode(root);
        int[] nums = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            nums[i] = res.get(i);
        }
        return nums;
    }

    private List<Integer> res = new ArrayList<>();
    private int count = 0;
    private int maxCount = 0;

    private void doFindMode(TreeNode cur) {
        if (cur == null) {
            return;
        }
        // 左
        doFindMode(cur.left);

        if (pre == null) {
            // pre 为空表示是第一个节点
            count = 1;
        } else if (pre.val == cur.val) {
            // 如果出现重复元素，count就增加一
            count++;
        } else {
            // 如果不重复了，count重置为一
            count = 1;
        }
        if (count == maxCount) {
            res.add(cur.val);
        }
        if (count > maxCount) {
            maxCount = count;
            // 清空之前的结果
            res.clear();
            res.add(cur.val);
        }
        pre = cur;

        // 右
        doFindMode(cur.right);
    }

    /**
     * 235. 二叉搜索树的最近公共祖先
     * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
     * <p>
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     * <p>
     * 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
     * <p>
     * <img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/binarysearchtree_improved.png" />
     * <p>
     * <p>
     * <p>
     * <p>
     * 示例 1:
     * <p>
     * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
     * 输出: 6
     * 解释: 节点 2 和节点 8 的最近公共祖先是 6。
     * 示例 2:
     * <p>
     * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
     * 输出: 2
     * 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
     * <p>
     * <p>
     * 说明:
     * <p>
     * 所有节点的值都是唯一的。
     * p、q 为不同节点且均存在于给定的二叉搜索树中。
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 因为p、q 为不同节点且均存在于给定的二叉搜索树中 所有p，q肯定会找到所有不用写终止条件
        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        return root;
    }

    /**
     * 701. 二叉搜索树中的插入操作
     * 给定二叉搜索树（BST）的根节点 root 和要插入树中的值 value ，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 输入数据 保证 ，新值和原始二叉搜索树中的任意节点值都不同。
     * <p>
     * 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回 任意有效的结果 。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * <img src="https://assets.leetcode.com/uploads/2020/10/05/insertbst.jpg" />
     * <p>
     * 输入：root = [4,2,7,1,3], val = 5
     * 输出：[4,2,7,1,3,5]
     * 解释：另一个满足题目要求可以通过的树是：
     * <p>
     * <img src="https://assets.leetcode.com/uploads/2020/10/05/bst.jpg" />
     * <p>
     * 示例 2：
     * <p>
     * 输入：root = [40,20,60,10,30,50,70], val = 25
     * 输出：[40,20,60,10,30,50,70,null,null,25]
     * 示例 3：
     * <p>
     * 输入：root = [4,2,7,1,3,null,null,null,null,null,null], val = 5
     * 输出：[4,2,7,1,3,5]
     * <p>
     * <p>
     * 提示：
     * <p>
     * 树中的节点数将在 [0, 104]的范围内。
     * -108 <= Node.val <= 108
     * 所有值 Node.val 是 独一无二 的。
     * -108 <= val <= 108
     * 保证 val 在原始BST中不存在。
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) // 如果当前节点为空，也就意味着val找到了合适的位置，此时创建节点直接返回。
            return new TreeNode(val);

        if (root.val < val) {
            root.right = insertIntoBST(root.right, val); // 递归创建右子树
        } else if (root.val > val) {
            root.left = insertIntoBST(root.left, val); // 递归创建左子树
        }
        return root;
    }

    /**
     * 538. 把二叉搜索树转换为累加树
     * 中等
     * 相关标签
     * 相关企业
     * 给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
     * <p>
     * 提醒一下，二叉搜索树满足下列约束条件：
     * <p>
     * 节点的左子树仅包含键 小于 节点键的节点。
     * 节点的右子树仅包含键 大于 节点键的节点。
     * 左右子树也必须是二叉搜索树。
     * 注意：本题和 1038: https://leetcode-cn.com/problems/binary-search-tree-to-greater-sum-tree/ 相同
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     *     <img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/05/03/tree.png" />
     * <p>
     * <p>
     * 输入：[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
     * 输出：[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
     * 示例 2：
     * <p>
     * 输入：root = [0,null,1]
     * 输出：[1,null,1]
     * 示例 3：
     * <p>
     * 输入：root = [1,0,2]
     * 输出：[3,3,2]
     * 示例 4：
     * <p>
     * 输入：root = [3,2,4,1]
     * 输出：[7,9,4,10]
     * <p>
     * <p>
     * 提示：
     * <p>
     * 树中的节点数介于 0 和 104 之间。
     * 每个节点的值介于 -104 和 104 之间。
     * 树中的所有值 互不相同 。
     * 给定的树为二叉搜索树。
     * @param root
     * @return
     */
    public TreeNode convertBST(TreeNode root) {
        convert(root);
        return root;
    }

    private void convert(TreeNode node) {
        // 反向中序遍历
        if (node == null) {
            return;
        }
        // 右
        convert(node.right);
        // 中
        if (pre != null) {
            node.val = pre.val + node.val;
        }
        pre = node;
        // 左
        convert(node.left);
    }

    /**
     * LCR 174. 寻找二叉搜索树中的目标节点
     *
     * 某公司组织架构以二叉搜索树形式记录，节点值为处于该职位的员工编号。请返回第 cnt 大的员工编号。
     *
     *
     *
     * 示例 1：
     *
     * <img src="https://pic.leetcode.cn/1695101634-kzHKZW-image.png" />
     *
     * 输入：root = [7, 3, 9, 1, 5], cnt = 2
     *        7
     *       / \
     *      3   9
     *     / \
     *    1   5
     * 输出：7
     * 示例 2：
     *
     * <img src="https://pic.leetcode.cn/1695101636-ESZtLa-image.png" />
     *
     * 输入: root = [10, 5, 15, 2, 7, null, 20, 1, null, 6, 8], cnt = 4
     *        10
     *       / \
     *      5   15
     *     / \    \
     *    2   7    20
     *   /   / \
     *  1   6   8
     * 输出: 8
     *
     *
     * 提示：
     *
     * 1 ≤ cnt ≤ 二叉搜索树元素个数
     * @param root
     * @param cnt
     * @return
     */
    public int findTargetNode(TreeNode root, int cnt) {
        this.cnt = cnt;
        findTargetNode(root);
        return result;
    }

    private int cnt = 0;
    private int result = 0;

    private void findTargetNode(TreeNode root) {
        if (root == null) {
            return;
        }
        // 右
        findTargetNode(root.right);
        cnt--;
        // 中
        if (cnt == 0) {
            result = root.val;
        }
        // 左
        findTargetNode(root.left);
    }

    /**
     * 938. 二叉搜索树的范围和
     * 简单
     * 相关标签
     * 相关企业
     * 给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。
     *
     *
     *
     * 示例 1：
     *
     * <img src="https://assets.leetcode.com/uploads/2020/11/05/bst1.jpg" />
     *
     * 输入：root = [10,5,15,3,7,null,18], low = 7, high = 15
     * 输出：32
     * 示例 2：
     *
     * <img src="https://assets.leetcode.com/uploads/2020/11/05/bst2.jpg" />
     *
     * 输入：root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
     * 输出：23
     *
     *
     * 提示：
     *
     * 树中节点数目在范围 [1, 2 * 104] 内
     * 1 <= Node.val <= 105
     * 1 <= low <= high <= 105
     * 所有 Node.val 互不相同
     * @param root
     * @param low
     * @param high
     * @return
     */
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }
        if (root.val < low) {
            return rangeSumBST(root.right, low, high);
        }
        if (root.val > high) {
            return rangeSumBST(root.left, low, high);
        }
        return root.val + rangeSumBST(root.left, low, high) +
                rangeSumBST(root.right, low, high);
    }

    private double minAbs = Integer.MAX_VALUE;
    private int cns = 0;

    /**
     * 270. 最接近的二叉搜索树值
     * 简单
     * 相关标签
     * 相关企业
     * 给你二叉搜索树的根节点 root 和一个目标值 target ，请在该二叉搜索树中找到最接近目标值 target 的数值。如果有多个答案，返回最小的那个。
     *
     *
     * 示例 1：
     *
     * <img src="https://assets.leetcode.com/uploads/2021/03/12/closest1-1-tree.jpg" />
     *
     * 输入：root = [4,2,5,1,3], target = 3.714286
     * 输出：4
     * 示例 2：
     *
     * 输入：root = [1], target = 4.428571
     * 输出：1
     *
     *
     * 提示：
     *
     * 树中节点的数目在范围 [1, 104] 内
     * 0 <= Node.val <= 109
     * -109 <= target <= 109
     *
     * @param root
     * @param target
     * @return
     */
    public int closestValue(TreeNode root, double target) {
        closest(root, target);
        return cns;
    }

    private void closest(TreeNode cur, double target) {
        if (cur == null) {
            return;
        }
        // 左
        closest(cur.left, target);

        // 中
        if (minAbs > Math.abs(cur.val - target)) {
            minAbs = Math.abs(cur.val - target);
            cns = cur.val;
        } else if (minAbs == Math.abs(cur.val - target)) {
            cns = Math.min(cns, cur.val);
        }

        // 右
        closest(cur.right, target);
    }

    /**
     * 783. 二叉搜索树节点最小距离
     * 简单
     * 相关标签
     * 相关企业
     * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
     *
     * 差值是一个正数，其数值等于两值之差的绝对值。
     *
     *
     *
     * 示例 1：
     *
     *
     * 输入：root = [4,2,6,1,3]
     * 输出：1
     * 示例 2：
     *
     *
     * 输入：root = [1,0,48,null,null,12,49]
     * 输出：1
     *
     *
     * 提示：
     *
     * 树中节点的数目范围是 [2, 100]
     * 0 <= Node.val <= 105
     *
     *
     * 注意：本题与 530：https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/ 相同
     *
     * @param root
     * @return
     */
    public int minDiffInBST(TreeNode root) {
        minDiffIn(root);
        return minDiff;
    }

    private void minDiffIn(TreeNode root) {
        if (root == null) {
            return;
        }
        // 左
        minDiffIn(root.left);
        // 中
        if (pre != null) {
            minDiff = Math.min(minDiff, Math.abs(root.val - pre.val));
        }
        pre = root;
        // 右
        minDiffIn(root.right);
    }

    /**
     * 897. 递增顺序搜索树
     *
     * 给你一棵二叉搜索树的 root ，请你 按中序遍历 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。
     *
     *
     *
     * 示例 1：
     *
     *
     * 输入：root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
     * 输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
     * 示例 2：
     *
     *
     * 输入：root = [5,1,7]
     * 输出：[1,null,5,null,7]
     *
     *
     * 提示：
     *
     * 树中节点数的取值范围是 [1, 100]
     * 0 <= Node.val <= 1000
     *
     * @param root
     * @return
     */
    public TreeNode increasingBST(TreeNode root) {
        TreeNode dummyHead = new TreeNode(0);
        dummyHead.right = root;
        pre = dummyHead;
        increasing(root);
        return dummyHead.right;
    }

    private void increasing(TreeNode root) {
        if (root == null) {
            return;
        }
        // 左
        increasing(root.left);
        // 中
        if (pre != null) {
            root.left = null;
            pre.right = root;
        }
        pre = root;
        // 右
        increasing(root.right);
    }

    /**
     * 96. 不同的二叉搜索树
     *
     * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
     *
     *
     *
     * 示例 1：
     *
     * <img src="https://assets.leetcode.com/uploads/2021/01/18/uniquebstn3.jpg" />
     *
     * 输入：n = 3
     * 输出：5
     * 示例 2：
     *
     * 输入：n = 1
     * 输出：1
     *
     *
     * 提示：
     *
     * 1 <= n <= 19
     *
     * 解题思路:
     *
     * 解题思路
     * 标签：动态规划
     * 假设 n 个节点存在二叉排序树的个数是 G (n)，令 f(i) 为以 i 为根的二叉搜索树的个数，则
     * G(n)=f(1)+f(2)+f(3)+f(4)+...+f(n)
     *
     * 当 i 为根节点时，其左子树节点个数为 i-1 个，右子树节点为 n-i，则
     * f(i)=G(i−1)∗G(n−i)
     *
     * 综合两个公式可以得到 卡特兰数 公式
     * G(n)=G(0)∗G(n−1)+G(1)∗(n−2)+...+G(n−1)∗G(0)
     *
     * @param n
     * @return
     */
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i < n + 1; i++)
            for (int j = 1; j < i + 1; j++)
                dp[i] += dp[j - 1] * dp[i - j];

        return dp[n];
    }

    /**
     * LCR 155. 将二叉搜索树转化为排序的双向链表
     *
     * 将一个 二叉搜索树 就地转化为一个 已排序的双向循环链表 。
     *
     * 对于双向循环列表，你可以将左右孩子指针作为双向循环链表的前驱和后继指针，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。
     *
     * 特别地，我们希望可以 就地 完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中最小元素的指针。
     *
     *
     *
     * 示例 1：
     *
     * 输入：root = [4,2,5,1,3]
     *
     * <img src="https://assets.leetcode.com/uploads/2018/10/12/bstdllreturndll.png" />
     *
     * 输出：[1,2,3,4,5]
     *
     * 解释：下图显示了转化后的二叉搜索树，实线表示后继关系，虚线表示前驱关系。
     *
     * <img src="https://assets.leetcode.com/uploads/2018/10/12/bstdllreturnbst.png" />
     *
     * 示例 2：
     *
     * 输入：root = [2,1,3]
     * 输出：[1,2,3]
     * 示例 3：
     *
     * 输入：root = []
     * 输出：[]
     * 解释：输入是空树，所以输出也是空链表。
     * 示例 4：
     *
     * 输入：root = [1]
     * 输出：[1]
     *
     *
     * 提示：
     *
     * -1000 <= Node.val <= 1000
     * Node.left.val < Node.val < Node.right.val
     * Node.val 的所有值都是独一无二的
     * 0 <= Number of Nodes <= 2000
     * 注意：本题与主站 426 题相同：https://leetcode-cn.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
     *
     * 解题思路：https://leetcode.cn/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/solutions/186518/mian-shi-ti-36-er-cha-sou-suo-shu-yu-shuang-xian-5/
     *
     * @param root
     * @return
     */
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        treeToDoubly(root);
        head.left = prev;
        prev.right = head;
        return head;
    }

    private Node prev = null;
    private Node head = null;

    private void treeToDoubly(Node cur) {
        if (cur == null) {
            return;
        }
        // 左
        treeToDoubly(cur.left);

        // 中
        if (prev != null) {
            prev.right = cur;
            cur.left = prev;
        } else {
            // 当pre为空的时候说明再处理的节点为链表的头节点，所有需要记录一下
            head = cur;
        }
        // 记录前驱节点
        prev = cur;

        // 右
        treeToDoubly(cur.right);
    }

    /**
     * LCR 152. 验证二叉搜索树的后序遍历序列
     *
     * 请实现一个函数来判断整数数组 postorder 是否为二叉搜索树的后序遍历结果。
     *
     *
     *
     * 示例 1：
     *
     * <img src="https://pic.leetcode.cn/1706665328-rfvWhs-%E6%88%AA%E5%B1%8F2024-01-31%2009.41.48.png" />
     *
     *
     * 输入: postorder = [4,9,6,5,8]
     * 输出: false
     * 解释：从上图可以看出这不是一颗二叉搜索树
     * 示例 2：
     *
     * <img src="https://pic.leetcode.cn/1694762510-vVpTic-%E5%89%91%E6%8C%8733.png" />
     *
     * 输入: postorder = [4,6,5,9,8]
     * 输出: true
     * 解释：可构建的二叉搜索树如上图
     *
     *
     * 提示：
     *
     * 数组长度 <= 1000
     * postorder 中无重复数字
     *
     * 解题思路：https://leetcode.cn/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/solutions/150225/mian-shi-ti-33-er-cha-sou-suo-shu-de-hou-xu-bian-6/
     *
     * @param postorder
     * @return
     */
    public boolean verifyTreeOrder(int[] postorder) {
        Stack<Integer> stack = new Stack<>();
        int root = Integer.MAX_VALUE;
        for (int i = postorder.length - 1; i >= 0; i--) {
            if (postorder[i] > root) return false;
            while (!stack.isEmpty() && stack.peek() > postorder[i])
                root = stack.pop();
            stack.add(postorder[i]);
        }
        return true;
    }
}
