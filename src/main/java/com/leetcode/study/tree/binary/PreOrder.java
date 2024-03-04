package com.leetcode.study.tree.binary;

import com.leetcode.study.tree.binary.node.TreeNode;

import java.util.*;

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
}
