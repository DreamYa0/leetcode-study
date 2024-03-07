package com.leetcode.study.tree.binary;

import com.beust.ah.A;
import com.leetcode.study.list.SingleLinkedList;
import com.leetcode.study.tree.binary.node.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author dreamyao
 * @title 二叉树前序遍历
 * @date 2024/3/3 22:58
 * @since 1.0.0
 */
@SuppressWarnings("all")
public class PreOrder {

    private final Set<Integer> set = new HashSet<>();
    private TreeNode pre = null;

    /**
     * 144. 二叉树的前序遍历
     * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * 输入：root = [1,null,2,3]
     * 输出：[1,2,3]
     * 示例 2：
     * <p>
     * 输入：root = []
     * 输出：[]
     * 示例 3：
     * <p>
     * 输入：root = [1]
     * 输出：[1]
     * 示例 4：
     * <p>
     * <p>
     * 输入：root = [1,2]
     * 输出：[1,2]
     * 示例 5：
     * <p>
     * <p>
     * 输入：root = [1,null,2]
     * 输出：[1,2]
     * <p>
     * <p>
     * 提示：
     * <p>
     * 树中节点数目在范围 [0, 100] 内
     * -100 <= Node.val <= 100
     * <p>
     * <p>
     * 进阶：递归算法很简单，你可以通过迭代算法完成吗？
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
     * 1022. 从根到叶的二进制数之和
     * <p>
     * 给出一棵二叉树，其上每个结点的值都是 0 或 1 。每一条从根到叶的路径都代表一个从最高有效位开始的二进制数。
     * <p>
     * 例如，如果路径为 0 -> 1 -> 1 -> 0 -> 1，那么它表示二进制数 01101，也就是 13 。
     * 对树上的每一片叶子，我们都要找出从根到该叶子的路径所表示的数字。
     * <p>
     * 返回这些数字之和。题目数据保证答案是一个 32 位 整数。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * <img src="https://assets.leetcode.com/uploads/2019/04/04/sum-of-root-to-leaf-binary-numbers.png" />
     * <p>
     * 输入：root = [1,0,1,0,1,0,1]
     * 输出：22
     * 解释：(100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22
     * 示例 2：
     * <p>
     * 输入：root = [0]
     * 输出：0
     * <p>
     * <p>
     * 提示：
     * <p>
     * 树中的节点数在 [1, 1000] 范围内
     * Node.val 仅为 0 或 1
     * <p>
     * 解题思路
     * <p>
     * 此题本质就是要找到二叉树的所有路径（用前序 + 回溯）
     * @param root
     * @return
     */
    public int sumRootToLeaf(TreeNode root) {
        List<String> result = new ArrayList<>();
        sumNumbers(root, new ArrayList<>(), result);
        return result.stream().map(n -> Integer.parseInt(n, 2))
                .reduce((v1, v2) -> v1 + v2).orElse(0);
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
        // 递归终止条件，遍历到叶子节点终止
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

    /**
     * LCP 44. 开幕式焰火
     * 「力扣挑战赛」开幕式开始了，空中绽放了一颗二叉树形的巨型焰火。
     * 给定一棵二叉树 root 代表焰火，节点值表示巨型焰火这一位置的颜色种类。请帮小扣计算巨型焰火有多少种不同的颜色。
     * <p>
     * 示例 1：
     * <p>
     * 输入：root = [1,3,2,1,null,2]
     * <p>
     * 输出：3
     * <p>
     * 解释：焰火中有 3 个不同的颜色，值分别为 1、2、3
     * <p>
     * 示例 2：
     * <p>
     * 输入：root = [3,3,3]
     * <p>
     * 输出：1
     * <p>
     * 解释：焰火中仅出现 1 个颜色，值为 3
     * <p>
     * 提示：
     * <p>
     * 1 <= 节点个数 <= 1000
     * 1 <= Node.val <= 1000
     * @param root
     * @return
     */
    public int numColor(TreeNode root) {
        Set<Integer> count = new HashSet<>();
        numColor(root, count);
        return count.size();
    }

    private void numColor(TreeNode root, Set<Integer> count) {
        if (root == null) {
            return;
        }
        // 中
        count.add(root.val);
        // 左
        numColor(root.left, count);
        // 右
        numColor(root.right, count);
    }

    int ans;
    int rootValue;

    /**
     * 671. 二叉树中第二小的节点
     * <p>
     * 给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。
     * 如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个。
     * <p>
     * 更正式地说，即 root.val = min(root.left.val, root.right.val) 总成立。
     * <p>
     * 给出这样的一个二叉树，你需要输出所有节点中的 第二小的值 。
     * <p>
     * 如果第二小的值不存在的话，输出 -1 。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * <img src="https://assets.leetcode.com/uploads/2020/10/15/smbt1.jpg"/>
     * </p>
     * <p>
     * 输入：root = [2,2,5,null,null,5,7]
     * 输出：5
     * 解释：最小的值是 2 ，第二小的值是 5 。
     * <p>
     * 示例 2：
     * <p>
     * <img src="https://assets.leetcode.com/uploads/2020/10/15/smbt2.jpg"/>
     * <p>
     * 输入：root = [2,2,2]
     * 输出：-1
     * 解释：最小的值是 2, 但是不存在第二小的值。
     * <p>
     * <p>
     * 提示：
     * <p>
     * 树中节点数目在范围 [1, 25] 内
     * 1 <= Node.val <= 231 - 1
     * 对于树中每个节点 root.val == min(root.left.val, root.right.val)
     * @param root
     * @return
     */
    public int findSecondMinimumValue(TreeNode root) {
        ans = -1;
        rootValue = root.val;
        findSecond(root);
        return ans;
    }

    private void findSecond(TreeNode node) {
        if (node == null) {
            return;
        }
        // ans 不等于-1 表示已经赋值过了
        // node.val如果抖都比ans大那么后续子节点会更大，也不需要再遍历了
        if (ans != -1 && node.val >= ans) {
            return;
        }
        if (node.val > rootValue) {
            // 满足此条件说明 rootValue < node.val < ans 就更新结果
            ans = node.val;
        }
        // 左
        findSecond(node.left);
        // 右
        findSecond(node.right);
    }

    private boolean res;
    private int depth;
    private TreeNode father;

    /**
     * 993. 二叉树的堂兄弟节点
     * 在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。
     * <p>
     * 如果二叉树的两个节点深度相同，但 父节点不同 ，则它们是一对堂兄弟节点。
     * <p>
     * 我们给出了具有唯一值的二叉树的根节点 root ，以及树中两个不同节点的值 x 和 y 。
     * <p>
     * 只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true 。否则，返回 false。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * <img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/02/16/q1248-01.png" />
     * <p>
     * 输入：root = [1,2,3,4], x = 4, y = 3
     * 输出：false
     * 示例 2：
     * <p>
     * <img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/02/16/q1248-02.png" />
     * <p>
     * 输入：root = [1,2,3,null,4,null,5], x = 5, y = 4
     * 输出：true
     * 示例 3：
     * <p>
     * <img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/02/16/q1248-03.png" />
     * <p>
     * <p>
     * 输入：root = [1,2,3,null,4], x = 2, y = 3
     * 输出：false
     * <p>
     * <p>
     * 提示：
     * <p>
     * 二叉树的节点数介于 2 到 100 之间。
     * 每个节点的值都是唯一的、范围为 1 到 100 的整数。
     * @param root
     * @param x
     * @param y
     * @return
     */
    public boolean isCousins(TreeNode root, int x, int y) {
        dfs(root, null, 1, x, y);
        return res;
    }

    private boolean dfs(TreeNode node, TreeNode fa, int d, int x, int y) {
        // 如果我们已经找到了 x 和 y 的其中一个，此时 depth>0，我们无需递归深度超过 depth 的点。
        if (node == null || depth > 0 && d > depth) {
            return false;
        }
        if (node.val == x || node.val == y) { // 找到 x 或 y
            if (depth > 0) {
                // 之前已找到 x y 其中一个
                res = depth == d && father != fa;
                // 表示 x 和 y 都找到
                return true;
            }
            depth = d; // 之前没找到，记录信息
            father = fa;
        }
        return dfs(node.left, node, d + 1, x, y) ||
                dfs(node.right, node, d + 1, x, y);
    }

    /**
     * 1379. 找出克隆二叉树中的相同节点
     * <p>
     * 给你两棵二叉树，原始树 original 和克隆树 cloned，以及一个位于原始树 original 中的目标节点 target。
     * <p>
     * 其中，克隆树 cloned 是原始树 original 的一个 副本 。
     * <p>
     * 请找出在树 cloned 中，与 target 相同 的节点，并返回对该节点的引用（在 C/C++ 等有指针的语言中返回 节点指针，其他语言返回节点本身）。
     * <p>
     * <p>
     * <p>
     * 注意：你 不能 对两棵二叉树，以及 target 节点进行更改。只能 返回对克隆树 cloned 中已有的节点的引用。
     * <p>
     * <p>
     * <p>
     * 示例 1:
     * <p>
     * <img src="https://assets.leetcode.com/uploads/2020/02/21/e1.png" />
     * <p>
     * <p>
     * 输入: tree = [7,4,3,null,null,6,19], target = 3
     * 输出: 3
     * 解释: 上图画出了树 original 和 cloned。target 节点在树 original 中，用绿色标记。答案是树 cloned 中的黄颜色的节点（其他示例类似）。
     * 示例 2:
     * <p>
     * <img src="https://assets.leetcode.com/uploads/2020/02/21/e2.png" />
     * <p>
     * <p>
     * 输入: tree = [7], target =  7
     * 输出: 7
     * 示例 3:
     * <p>
     * <img src="https://assets.leetcode.com/uploads/2020/02/21/e3.png" />
     * <p>
     * <p>
     * 输入: tree = [8,null,6,null,5,null,4,null,3,null,2,null,1], target = 4
     * 输出: 4
     * <p>
     * <p>
     * 提示：
     * <p>
     * 树中节点的数量范围为 [1, 104] 。
     * 同一棵树中，没有值相同的节点。
     * target 节点是树 original 中的一个节点，并且不会是 null 。
     * <p>
     * <p>
     * 进阶：如果树中允许出现值相同的节点，将如何解答？
     * @param original
     * @param cloned
     * @param target
     * @return
     */
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        List<TreeNode> originalNode = new ArrayList<>();
        List<TreeNode> clonedNode = new ArrayList<>();
        getTargetCopy(original, originalNode);
        // target节点在original树中的为止
        int idx = 0;
        for (int i = 0; i < originalNode.size(); i++) {
            if (originalNode.get(i) == target) {
                idx = i;
                break;
            }
        }
        getTargetCopy(cloned, clonedNode);
        return clonedNode.get(idx);
    }

    private void getTargetCopy(final TreeNode node, List<TreeNode> nodes) {
        if (node == null) {
            return;
        }
        // 中
        nodes.add(node);
        // 左
        getTargetCopy(node.left, nodes);
        // 右
        getTargetCopy(node.right, nodes);
    }

    /**
     * 1469. 寻找所有的独生节点
     * 简单
     * 相关标签
     * 相关企业
     * 提示
     * 二叉树中，如果一个节点是其父节点的唯一子节点，则称这样的节点为 “独生节点” 。二叉树的根节点不会是独生节点，因为它没有父节点。
     * <p>
     * 给定一棵二叉树的根节点 root ，返回树中 所有的独生节点的值所构成的数组 。数组的顺序 不限 。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * <img src="https://assets.leetcode.com/uploads/2020/06/03/e1.png" />
     * <p>
     * <p>
     * 输入：root = [1,2,3,null,4]
     * 输出：[4]
     * 解释：浅蓝色的节点是唯一的独生节点。
     * 节点 1 是根节点，不是独生的。
     * 节点 2 和 3 有共同的父节点，所以它们都不是独生的。
     * 示例 2：
     * <p>
     * <img src="https://assets.leetcode.com/uploads/2020/06/03/e2.png" />
     * <p>
     * <p>
     * 输入：root = [7,1,4,6,null,5,3,null,null,null,null,null,2]
     * 输出：[6,2]
     * 输出：浅蓝色的节点是独生节点。
     * 请谨记，顺序是不限的。 [2,6] 也是一种可接受的答案。
     * 示例 3：
     * <p>
     * <img src="https://assets.leetcode.com/uploads/2020/06/03/tree.png" />
     * <p>
     * <p>
     * 输入：root = [11,99,88,77,null,null,66,55,null,null,44,33,null,null,22]
     * 输出：[77,55,33,66,44,22]
     * 解释：节点 99 和 88 有共同的父节点，节点 11 是根节点。
     * 其他所有节点都是独生节点。
     * 示例 4：
     * <p>
     * 输入：root = [197]
     * 输出：[]
     * 示例 5：
     * <p>
     * 输入：root = [31,null,78,null,28]
     * 输出：[78,28]
     * <p>
     * <p>
     * 提示：
     * <p>
     * tree 中节点个数的取值范围是 [1, 1000]。
     * 每个节点的值的取值范围是 [1, 10^6]。
     * @param root
     * @return
     */
    public List<Integer> getLonelyNodes(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        getLonelyNodes(root, ans);
        return ans;
    }

    private void getLonelyNodes(TreeNode node, List<Integer> ans) {
        if (node == null || (node.left == null && node.right == null)) {
            return;
        }
        // 中
        if (node.left != null && node.right == null) {
            // 满足独生节点的条件
            ans.add(node.left.val);
        }
        if (node.left == null && node.right != null) {
            // 满足独生节点的条件
            ans.add(node.right.val);
        }
        // 左
        if (node.left != null) {
            getLonelyNodes(node.left, ans);
        }
        // 右
        if (node.right != null) {
            getLonelyNodes(node.right, ans);
        }
    }

    /**
     * 654. 最大二叉树
     * <p>
     * 给定一个不重复的整数数组 nums 。 最大二叉树 可以用下面的算法从 nums 递归地构建:
     * <p>
     * 创建一个根节点，其值为 nums 中的最大值。
     * 递归地在最大值 左边 的 子数组前缀上 构建左子树。
     * 递归地在最大值 右边 的 子数组后缀上 构建右子树。
     * 返回 nums 构建的 最大二叉树 。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * <img src="https://assets.leetcode.com/uploads/2020/12/24/tree1.jpg" />
     * <p>
     * 输入：nums = [3,2,1,6,0,5]
     * 输出：[6,3,5,null,2,0,null,null,1]
     * 解释：递归调用如下所示：
     * - [3,2,1,6,0,5] 中的最大值是 6 ，左边部分是 [3,2,1] ，右边部分是 [0,5] 。
     * - [3,2,1] 中的最大值是 3 ，左边部分是 [] ，右边部分是 [2,1] 。
     * - 空数组，无子节点。
     * - [2,1] 中的最大值是 2 ，左边部分是 [] ，右边部分是 [1] 。
     * - 空数组，无子节点。
     * - 只有一个元素，所以子节点是一个值为 1 的节点。
     * - [0,5] 中的最大值是 5 ，左边部分是 [0] ，右边部分是 [] 。
     * - 只有一个元素，所以子节点是一个值为 0 的节点。
     * - 空数组，无子节点。
     * 示例 2：
     * <p>
     * <p>
     * 输入：nums = [3,2,1]
     * 输出：[3,null,2,null,1]
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= nums.length <= 1000
     * 0 <= nums[i] <= 1000
     * nums 中的所有整数 互不相同
     * @param nums
     * @return
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return construct(nums, 0, nums.length);
    }

    private TreeNode construct(int[] nums, int start, int end) {
        // 终止条件，当数组只有一个元素的时候说明递归遍历到了叶子节点，此时应该终止，或一开始数组就只有一个元素那么构造完根节点就可以终止了
        if (end - start == 1) {
            // 保证数组中有一个元素
            return new TreeNode(nums[start]);
        }
        // 数组中最大的值的下标
        int idx = start;
        // 数组中最大的值
        int max = nums[idx];
        // 遍历数组找到最大值 [start,end) 左闭右开区间
        for (int i = start + 1; i < end; i++) {
            if (nums[i] > max) {
                max = nums[i];
                idx = i;
            }
        }
        // 中 构造根节点
        TreeNode node = new TreeNode(max);
        // 左
        if (idx > start) {
            // idx大于0说明 max值左边还有值,有值就可以进行左边遍历  [start,idx) 左闭右开区间
            node.left = construct(nums, start, idx);
        }
        if (idx + 1 < end) {
            // idx + 1 小于 end 说明 max值右边还有值,有值就可以进行右边遍历  [idx + 1,end) 左闭右开区间
            node.right = construct(nums, idx + 1, end);
        }
        return node;
    }

    /**
     * 998. 最大二叉树 II
     * <p>
     * 最大树 定义：一棵树，并满足：其中每个节点的值都大于其子树中的任何其他值。
     * <p>
     * 给你最大树的根节点 root 和一个整数 val 。
     * <p>
     * 就像 之前的问题 那样，给定的树是利用 Construct(a) 例程从列表 a（root = Construct(a)）递归地构建的：
     * <p>
     * 如果 a 为空，返回 null 。
     * 否则，令 a[i] 作为 a 的最大元素。创建一个值为 a[i] 的根节点 root 。
     * root 的左子树将被构建为 Construct([a[0], a[1], ..., a[i - 1]]) 。
     * root 的右子树将被构建为 Construct([a[i + 1], a[i + 2], ..., a[a.length - 1]]) 。
     * 返回 root 。
     * 请注意，题目没有直接给出 a ，只是给出一个根节点 root = Construct(a) 。
     * <p>
     * 假设 b 是 a 的副本，并在末尾附加值 val。题目数据保证 b 中的值互不相同。
     * <p>
     * 返回 Construct(b) 。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * <img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/02/23/maximum-binary-tree-1-1.png" />
     * <p>
     * <p>
     * 输入：root = [4,1,3,null,null,2], val = 5
     * 输出：[5,4,null,1,3,null,null,2]
     * 解释：a = [1,4,2,3], b = [1,4,2,3,5]
     * 示例 2：
     * <p>
     * <img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/02/23/maximum-binary-tree-2-2.png" />
     * <p>
     * 输入：root = [5,2,4,null,1], val = 3
     * 输出：[5,2,4,null,1,null,3]
     * 解释：a = [2,1,5,4], b = [2,1,5,4,3]
     * 示例 3：
     * <p>
     * <img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/02/23/maximum-binary-tree-3-1.png" />
     * <p>
     * 输入：root = [5,2,3,null,1], val = 4
     * 输出：[5,2,4,null,1,3]
     * 解释：a = [2,1,5,3], b = [2,1,5,3,4]
     * <p>
     * <p>
     * 提示：
     * <p>
     * 树中节点数目在范围 [1, 100] 内
     * 1 <= Node.val <= 100
     * 树中的所有值 互不相同
     * 1 <= val <= 100
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        List<Integer> ans = new ArrayList<>();
        traversal(root, ans);
        ans.add(val);
        int[] nums = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            nums[i] = ans.get(i);
        }
        return constructMaximumBinaryTree(nums);
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
        // 递归终止条件
        if (root == null || root.val == val) {
            // 节点为空或找到要搜索的节点
            return root;
        }
        TreeNode result = null;
        // 左
        if (root.val > val) {
            result = searchBST(root.left, val);
        }
        // 右
        if (root.val < val) {
            result = searchBST(root.right, val);
        }
        return result;
    }

    /**
     * 450. 删除二叉搜索树中的节点
     * 中等
     * 相关标签
     * 相关企业
     * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
     * <p>
     * 一般来说，删除节点可分为两个步骤：
     * <p>
     * 首先找到需要删除的节点；
     * 如果找到了，删除它。
     * <p>
     * <p>
     * 示例 1:
     * <p>
     * <img src="https://assets.leetcode.com/uploads/2020/09/04/del_node_1.jpg" />
     * <p>
     * <p>
     * 输入：root = [5,3,6,2,4,null,7], key = 3
     * 输出：[5,4,6,2,null,null,7]
     * 解释：给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
     * 一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
     * 另一个正确答案是 [5,2,6,null,4,null,7]。
     * <p>
     * <img src="https://assets.leetcode.com/uploads/2020/09/04/del_node_supp.jpg" />
     * <p>
     * 示例 2:
     * <p>
     * 输入: root = [5,3,6,2,4,null,7], key = 0
     * 输出: [5,3,6,2,4,null,7]
     * 解释: 二叉树不包含值为 0 的节点
     * 示例 3:
     * <p>
     * 输入: root = [], key = 0
     * 输出: []
     * <p>
     * <p>
     * 提示:
     * <p>
     * 节点数的范围 [0, 104].
     * -105 <= Node.val <= 105
     * 节点值唯一
     * root 是合法的二叉搜索树
     * -105 <= key <= 105
     * <p>
     * <p>
     * 进阶： 要求算法时间复杂度为 O(h)，h 为树的高度。
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            // 1.没找到删除的节点
            return null;
        }
        // 中
        // 找到了要删除的节点
        if (root.val == key) {
            if (root.left == null && root.right == null) {
                // 2.左右节点为空，叶子节点
                return null;
            } else if (root.left != null && root.right == null) {
                // 3.左不空，右为空
                return root.left;
            } else if (root.left == null && root.right != null) {
                // 4.左为空，右不空
                return root.right;
            } else {
                // 5.左右都不空，最复杂场景，需要调整二叉树结构
                TreeNode cur = root.right;
                while (cur.left != null) {
                    cur = cur.left;
                }
                cur.left = root.left;
                return root.right;
            }
        }
        // 左
        if (root.val > key) {
            root.left = deleteNode(root.left, key);
        }
        // 右
        if (root.val < key) {
            root.right = deleteNode(root.right, key);
        }
        return root;
    }

    /**
     * 通用二叉树删除方法，可以删除普通二叉树和二叉搜索树
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNodeCommon(TreeNode root, int key) {
        // 通用二叉树删除方法，可以删除普通二叉树和二叉搜索树
        if (root == null) {
            // 1.没找到删除的节点
            return null;
        }
        // 中
        // 找到了要删除的节点
        if (root.val == key) {
            if (root.left == null && root.right == null) {
                // 删除叶子节点
                return null;
            }
            if (root.right == null && root.left != null) {
                // 这里第二次操作目标值：最终删除的作用
                return root.left;
            }
            TreeNode cur = root.right;
            while (cur.left != null) {
                // 遍历终止时就遍历到了叶子节点
                cur = cur.left;
            }
            // 交换值 这里第一次操作目标值：交换目标值其右子树最左面节点。
            // 把要删除节点的值和叶子节点交换一下，最终就变成了删除叶子节点
            int temp = root.val;
            root.val = cur.val;
            cur.val = temp;
        }
        // 左
        root.left = deleteNode(root.left, key);
        // 右
        root.right = deleteNode(root.right, key);
        return root;
    }

    /**
     * 669. 修剪二叉搜索树
     * 中等
     * 相关标签
     * 相关企业
     * 给你二叉搜索树的根节点 root ，同时给定最小边界low 和最大边界 high。通过修剪二叉搜索树，使得所有节点的值在[low, high]中。修剪树 不应该 改变保留在树中的元素的相对结构 (即，如果没有被移除，原有的父代子代关系都应当保留)。 可以证明，存在 唯一的答案 。
     * <p>
     * 所以结果应当返回修剪好的二叉搜索树的新的根节点。注意，根节点可能会根据给定的边界发生改变。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * <img src="https://assets.leetcode.com/uploads/2020/09/09/trim1.jpg" />
     * <p>
     * 输入：root = [1,0,2], low = 1, high = 2
     * 输出：[1,null,2]
     * 示例 2：
     * <p>
     * <img src="https://assets.leetcode.com/uploads/2020/09/09/trim2.jpg" />
     * <p>
     * 输入：root = [3,0,4,null,2,null,null,1], low = 1, high = 3
     * 输出：[3,2,null,1]
     * <p>
     * <p>
     * 提示：
     * <p>
     * 树中节点数在范围 [1, 104] 内
     * 0 <= Node.val <= 104
     * 树中每个节点的值都是 唯一 的
     * 题目数据保证输入是一棵有效的二叉搜索树
     * 0 <= low <= high <= 104
     * @param root
     * @param low
     * @param high
     * @return
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        // 中
        if (root.val < low) {
            // root 节点的右子树可能在[low,high]区间
            return trimBST(root.right, low, high);
        }
        if (root.val > high) {
            // root 节点的左子树可能在[low,high]区间
            return trimBST(root.left, low, high);
        }
        // 左
        root.left = trimBST(root.left, low, high);
        // 右
        root.right = trimBST(root.right, low, high);
        return root;
    }

    /**
     * 776. 拆分二叉搜索树
     * 给你一棵二叉搜索树（BST）的根结点 root 和一个整数 target 。
     * 请将该树按要求拆分为两个子树：
     * 1.其中一个子树结点的值都必须小于等于给定的目标值；
     * 2.另一个子树结点的值都必须大于目标值；
     * 3.树中并非一定要存在值为 target 的结点。
     * <p>
     * 除此之外，树中大部分结构都需要保留，也就是说原始树中父节点 p 的任意子节点 c ，假如拆分后它们仍在同一个子树中，那么结点 p 应仍为 c 的父结点。
     * <p>
     * 返回 两个子树的根结点的数组 。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * <img src="https://assets.leetcode.com/uploads/2021/06/13/split-tree.jpg" />
     * <p>
     * <p>
     * 输入：root = [4,2,6,1,3,5,7], target = 2
     * 输出：[[2,1],[4,3,6,null,null,5,7]]
     * 示例 2:
     * <p>
     * 输入: root = [1], target = 1
     * 输出: [[1],[]]
     * <p>
     * <p>
     * 提示：
     * <p>
     * 二叉搜索树节点个数在 [1, 50] 范围内
     * 0 <= Node.val, target <= 1000
     * @param root
     * @param target
     * @return
     */
    public TreeNode[] splitBST(TreeNode root, int target) {
        if (root == null) {
            return new TreeNode[]{null, null};
        }
        if (root.val <= target) {
            TreeNode[] nodes = splitBST(root.right, target);
            root.right = nodes[0];
            nodes[0] = root;
            return nodes;
        } else {
            TreeNode[] nodes = splitBST(root.left, target);
            root.left = nodes[1];
            nodes[1] = root;
            return nodes;
        }
    }

    /**
     * 108. 将有序数组转换为二叉搜索树
     *
     * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
     * <p>
     * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     *     <img src="https://assets.leetcode.com/uploads/2021/02/18/btree1.jpg" />
     * <p>
     * 输入：nums = [-10,-3,0,5,9]
     * 输出：[0,-3,9,-10,null,5]
     * 解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案：
     * <p>
     *     <img src="https://assets.leetcode.com/uploads/2021/02/18/btree2.jpg" />
     * <p>
     * 示例 2：
     * <p>
     *     <img src="https://assets.leetcode.com/uploads/2021/02/18/btree.jpg" />
     * <p>
     * 输入：nums = [1,3]
     * 输出：[3,1]
     * 解释：[1,null,3] 和 [3,1] 都是高度平衡二叉搜索树。
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= nums.length <= 104
     * -104 <= nums[i] <= 104
     * nums 按 严格递增 顺序排列
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return buildBST(nums, 0, nums.length);
    }

    private TreeNode buildBST(int[] nums, int start, int end) {
        if (end - start == 1) {
            return new TreeNode(nums[start]);
        }
        // 寻找中间节点的值和下标 左闭右开 [start,end)
        int idx = start + (end - start) / 2;
        int mid = nums[idx];
        TreeNode root = new TreeNode(mid);
        if (start < idx) {
            root.left = buildBST(nums, start, idx);
        }
        if (idx + 1 < end) {
            root.right = buildBST(nums, idx + 1, end);
        }
        return root;
    }

    static class ListNode {
        // 值
        int val;
        // next节点
        ListNode next;

        public ListNode(int val) {
            this.val = val;
            next = null;
        }
    }

    /**
     * 109. 有序链表转换二叉搜索树
     *
     * 给定一个单链表的头节点  head ，其中的元素 按升序排序 ，将其转换为高度平衡的二叉搜索树。
     *
     * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差不超过 1。
     *
     *
     *
     * 示例 1:
     *
     * <img src="https://assets.leetcode.com/uploads/2020/08/17/linked.jpg" />
     *
     * 输入: head = [-10,-3,0,5,9]
     * 输出: [0,-3,9,-10,null,5]
     * 解释: 一个可能的答案是[0，-3,9，-10,null,5]，它表示所示的高度平衡的二叉搜索树。
     * 示例 2:
     *
     * 输入: head = []
     * 输出: []
     *
     *
     * 提示:
     *
     * head 中的节点数在[0, 2 * 104] 范围内
     * -105 <= Node.val <= 105
     *
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        return buildTree(head, null);
    }

    private TreeNode buildTree(ListNode left, ListNode right) {
        if (left == right) {
            return null;
        }
        ListNode mid = getMedian(left, right);
        // 中
        TreeNode root = new TreeNode(mid.val);
        // 左
        root.left = buildTree(left, mid);
        // 右
        root.right = buildTree(mid.next, right);
        return root;
    }

    private ListNode getMedian(ListNode left, ListNode right) {
        ListNode fast = left;
        ListNode slow = left;
        while (fast != right && fast.next != right) {
            // 通过快指针2倍于慢指针移动来找到中间节点
            fast = fast.next;
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
