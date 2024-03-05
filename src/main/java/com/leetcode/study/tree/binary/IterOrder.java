package com.leetcode.study.tree.binary;

import com.leetcode.study.tree.binary.node.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author dreamyao
 * @title 迭代法
 * @date 2024/3/5 13:20
 * @since 1.0.0
 */
@SuppressWarnings("all")
public class IterOrder {

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
     * 700. 二叉搜索树中的搜索
     * 给定二叉搜索树（BST）的根节点 root 和一个整数值 val。
     * <p>
     * 你需要在 BST 中找到节点值等于 val 的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 null 。
     * <p>
     * <p>
     * <p>
     * 示例 1:
     * <p>
     * <img src="https://assets.leetcode.com/uploads/2021/01/12/tree1.jpg" />
     * <p>
     * 输入：root = [4,2,7,1,3], val = 2
     * 输出：[2,1,3]
     * 示例 2:
     * <p>
     * <img src="https://assets.leetcode.com/uploads/2021/01/12/tree2.jpg" />
     * <p>
     * 输入：root = [4,2,7,1,3], val = 5
     * 输出：[]
     * <p>
     * <p>
     * 提示：
     * <p>
     * 树中节点数在 [1, 5000] 范围内
     * 1 <= Node.val <= 107
     * root 是二叉搜索树
     * 1 <= val <= 107
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST(TreeNode root, int val) {
        while (root != null) {
            if (root.val > val) {
                root = root.left;
            } else if (root.val < val) {
                root = root.right;
            } else {
                return root;
            }
        }
        return null;
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
        // 定义栈
        Stack<TreeNode> stack = new Stack<>();
        if (root != null) {
            stack.push(root);
        }
        TreeNode pre = null;
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (cur != null) {

                // 右
                if (cur.right != null) {
                    stack.push(cur.right);
                }

                // 中
                stack.push(cur);
                stack.push(null);

                // 左
                if (cur.left != null) {
                    stack.push(cur.left);
                }
            } else {
                TreeNode temp = stack.pop();
                if (pre != null && pre.val >= temp.val) {
                    return false;
                }
                pre = temp;
            }
        }
        return true;
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
        // 定义栈
        Stack<TreeNode> stack = new Stack<>();
        // 定义前驱节点
        TreeNode pre = null;
        // 定义最小差值
        int minDiff = Integer.MAX_VALUE;
        if (root != null) {
            stack.push(root);
        }
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (cur != null) {
                // 右
                if (cur.right != null) {
                    stack.push(cur.right);
                }
                // 中
                stack.push(cur);
                stack.push(null);
                // 左
                if (cur.left != null) {
                    stack.push(cur.left);
                }
            } else {
                TreeNode temp = stack.pop();
                if (pre != null) {
                    minDiff = Math.min(minDiff, temp.val - pre.val);
                }
                pre = temp;
            }
        }
        return minDiff;
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
        List<Integer> res = new ArrayList<>();
        int count = 0;
        int maxCount = 0;
        // 定义前驱节点
        TreeNode pre = null;
        // 定义栈
        Stack<TreeNode> stack = new Stack<>();
        // 定义最小差值
        int minDiff = Integer.MAX_VALUE;
        if (root != null) {
            stack.push(root);
        }
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (cur != null) {
                // 右
                if (cur.right != null) {
                    stack.push(cur.right);
                }
                // 中
                stack.push(cur);
                stack.push(null);
                // 左
                if (cur.left != null) {
                    stack.push(cur.left);
                }
            } else {
                TreeNode temp = stack.pop();
                if (pre == null || pre.val != temp.val) {
                    count = 1;
                } else {
                    count++;
                }
                if (count > maxCount) {
                    maxCount = count;
                    res.clear();
                    res.add(temp.val);
                } else if (count == maxCount) {
                    res.add(temp.val);
                }
                pre = temp;
            }
        }
        int[] nums = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            nums[i] = res.get(i);
        }
        return nums;
    }

    /**
     * 235. 二叉搜索树的最近公共祖先
     * 中等
     * 相关标签
     * 相关企业
     * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
     * <p>
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     * <p>
     * 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
     * <p>
     *     <img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/binarysearchtree_improved.png" />
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
        while (root != null) {
            if (root.val > p.val && root.val > q.val) {
                return lowestCommonAncestor(root.left, p, q);
            }
            if (root.val < p.val && root.val < q.val) {
                return lowestCommonAncestor(root.right, p, q);
            }
        }
        return root;
    }
}
