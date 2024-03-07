package com.leetcode.study.tree.binary;

import com.leetcode.study.tree.binary.node.Node;
import com.leetcode.study.tree.binary.node.TreeNode;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * @author dreamyao
 * @title 二叉树层序遍历
 * @date 2024/2/26 17:16
 * @since 1.0.0
 */
@SuppressWarnings("all")
public class LevelOrder {

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
     * LCR 150. 彩灯装饰记录 II
     * 一棵圣诞树记作根节点为 root 的二叉树，节点值为该位置装饰彩灯的颜色编号。请按照从左到右的顺序返回每一层彩灯编号，每一层的结果记录于一行。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * <img src="https://pic.leetcode.cn/1694758674-XYrUiV-%E5%89%91%E6%8C%87%20Offer%2032%20-%20I_%E7%A4%BA%E4%BE%8B1.png" />
     * <p>
     * <p>
     * 输入：root = [8,17,21,18,null,null,6]
     * 输出：[[8],[17,21],[18,6]]
     * 提示：
     * <p>
     * 节点总数 <= 1000
     * 注意：本题与主站 102 题相同：https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
     * @param root
     * @return
     */
    public List<List<Integer>> decorateRecord(TreeNode root) {
        // 定义结果
        List<List<Integer>> ans = new ArrayList<>();
        // 定义队列
        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root != null) {
            queue.offer(root);
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> res = new ArrayList<>();
            while (size-- > 0) {
                TreeNode poll = queue.poll();
                if (poll != null) {
                    res.add(poll.val);
                    if (poll.left != null) {
                        queue.offer(poll.left);
                    }
                    if (poll.right != null) {
                        queue.offer(poll.right);
                    }
                }
            }
            ans.add(res);
        }
        return ans;
    }

    /**
     * 297. 二叉树的序列化与反序列化
     *
     * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
     *
     * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
     *
     * 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
     *
     *
     *
     * 示例 1：
     *
     * <img src="https://assets.leetcode.com/uploads/2020/09/15/serdeser.jpg" />
     *
     * 输入：root = [1,2,3,null,null,4,5]
     * 输出：[1,2,3,null,null,4,5]
     * 示例 2：
     *
     * 输入：root = []
     * 输出：[]
     * 示例 3：
     *
     * 输入：root = [1]
     * 输出：[1]
     * 示例 4：
     *
     * 输入：root = [1,2]
     * 输出：[1,2]
     *
     *
     * 提示：
     *
     * 树中结点数在范围 [0, 104] 内
     * -1000 <= Node.val <= 1000
     *
     */
    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) {
                return "[]";
            }
            StringBuilder builder = new StringBuilder("[");
            Queue<TreeNode> queue = new ArrayDeque<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                TreeNode cur = queue.poll();
                if (cur != null) {
                    builder.append(cur.val + ",");
                    if (cur.left != null) {
                        queue.offer(cur.left);
                    }
                    if (cur.right != null) {
                        queue.offer(cur.right);
                    }
                } else {
                    builder.append("null,");
                }
            }
            builder.deleteCharAt(builder.length() - 1);
            builder.append("]");
            return builder.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data.equals("[]")) {
                return null;
            }
            String[] vals = data.substring(1, data.length() - 1).split(",");
            TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
            Queue<TreeNode> queue = new LinkedList<>() {{
                add(root);
            }};
            int i = 1;
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (!vals[i].equals("null")) {
                    node.left = new TreeNode(Integer.parseInt(vals[i]));
                    queue.add(node.left);
                }
                i++;
                if (!vals[i].equals("null")) {
                    node.right = new TreeNode(Integer.parseInt(vals[i]));
                    queue.add(node.right);
                }
                i++;
            }
            return root;
        }
    }
}
