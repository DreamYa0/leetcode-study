package com.leetcode.study;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * @author dreamyao
 * @title
 * @date 2024/1/26 01:00
 * @since 1.0.0
 */
@SuppressWarnings("all")
public class Solution {

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
     * 206. 反转链表
     * @param head 链表头节点
     * @return 反转后的链表
     */
    public ListNode reverseList(ListNode head) {
        // 定义pre节点
        ListNode pre = null;
        // 定义cur节点
        ListNode cur = head;
        while (cur != null) {
            // 定义一个临时节点
            ListNode temp = cur;
            // 移动cur指针
            cur = cur.next;
            // 将pre指针指向cur
            temp.next = pre;
            // 将pre指针指向cur
            pre = temp;
        }

        return pre;
    }

    /**
     * 92. 反转链表 II
     * @param head  链表头节点
     * @param left  左边界
     * @param right 右边界
     * @return 反转后的链表
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 定义一个dummy节点
        ListNode dummy = new ListNode(-1);
        // 将dummy节点指向head
        dummy.next = head;
        // 定义p0节点
        ListNode p0 = dummy;
        for (int i = 0; i < left - 1; i++) {
            // 移动p0指针到left的前一个节点
            p0 = p0.next;
        }
        // 定义pre节点
        ListNode pre = null;
        // 定义cur节点
        ListNode cur = p0.next;
        for (int i = 0; i < right - left + 1; i++) {
            // 定义一个临时节点来存储cur
            ListNode temp = cur;
            // 移动cur指针
            cur = cur.next;
            // 将临时cur节点指向null
            temp.next = pre;
            // 将pre指针移动到临时cur节点位置
            pre = temp;
        }

        // 将p0的next的next指向cur节点
        p0.next.next = cur;
        // 将p0的next指向pre节点
        p0.next = pre;

        return dummy.next;
    }

    /**
     * 25. K 个一组翻转链表
     * @param head 链表头节点
     * @param k    反转的个数
     * @return 反转后的链表
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        // 定义一个dummy节点
        ListNode dummy = new ListNode(-1);
        // 将dummy节点指向head
        dummy.next = head;

        // 链表长度
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }

        // 定义p0节点
        ListNode p0 = dummy;
        // 定义pre节点
        ListNode pre = null;
        // 定义cur节点
        ListNode cur = p0.next;
        // 计算需要反转的次数
        while (len >= k) {
            // 反转前k个元素
            len -= k;
            for (int i = 0; i < k; i++) {
                // 定义一个临时节点来存储cur
                ListNode temp = cur;
                // 移动cur指针
                cur = cur.next;
                // 将临时cur节点指向null
                temp.next = pre;
                // 将pre指针移动到临时cur节点位置
                pre = temp;
            }

            // 定义一个临时节点来存储p_0_的next
            ListNode nxt = p0.next;
            // 将p0的next的next指向cur节点
            p0.next.next = cur;
            // 将p0的next指向pre节点
            p0.next = pre;
            // 移动p0指针
            p0 = nxt;
        }

        return dummy.next;
    }

    /**
     * 2. 两数相加
     * @param l1 链表1
     * @param l2 链表2
     * @return 相加后的链表
     */
    public ListNode addTwoNum(ListNode l1, ListNode l2) {
        // 定义一个新联表伪指针，用来指向头指针，返回结果
        ListNode dummyHead = new ListNode(0);
        // 定义一个进位数的指针，用来存储当两数之和大于10的时候，
        int carry = 0;
        // 定义一个可移动的指针，用来指向存储两个数之和的位置
        ListNode cur = dummyHead;
        // 当l1 不等于null或l2 不等于空时，就进入循环
        while (l1 != null || l2 != null) {
            int sum = carry;
            // 如果l1不等于null
            if (l1 != null) {
                // 将l1的值赋值给sum
                sum += l1.val;
                // 将l1的指针后移
                l1 = l1.next;
            }

            // 如果l2不等于null
            if (l2 != null) {
                // 将l2的值赋值给sum
                sum += l2.val;
                // 将l2的指针后移
                l2 = l2.next;
            }

            // 计算进位数
            carry = sum / 10;
            // 计算两个数的和，此时排除超过10的请况（大于10，取余数）
            sum = sum % 10;
            // 将求和数赋值给新链表的节点，
            // 注意这个时候不能直接将sum赋值给cur.next = sum。这时候会报，类型不匹配。
            // 所以这个时候要创一个新的节点，将值赋予节点
            cur.next = new ListNode(sum);
            // 将新链表的节点后移
            cur = cur.next;
        }
        // 如果最后两个数，相加的时候有进位数的时候，就将进位数，赋予链表的新节点。
        // 两数相加最多小于20，所以的的值最大只能时1
        if (carry == 1) {
            cur.next = new ListNode(carry);
        }
        // 返回链表的头节点
        return dummyHead.next;
    }

    /**
     * 反转链表
     * @param head 链表头节点
     * @return 反转后的链表
     */
    private ListNode reverse(ListNode head) {
        // 定义pre节点
        ListNode pre = null;
        // 定义cur节点
        ListNode cur = head;
        while (cur != null) {
            // 定义一个temp节点,来保存当前节点
            ListNode temp = cur;
            // 移动cur指针
            cur = cur.next;
            // 将temp节点指向pre节点
            temp.next = pre;
            // 将pre节点指向temp节点
            pre = temp;
        }
        return pre;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode reverseL1 = reverse(l1);
        ListNode reverseL2 = reverse(l2);
        ListNode listNode = addTwoNum(reverseL1, reverseL2);
        return reverse(listNode);
    }

    /**
     * 21. 合并两个有序链表
     * @param list1 链表1
     * @param list2 链表2
     * @return 合并后的链表
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        } else if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }

    /**
     * 876. 链表的中间结点
     * @param head 链表头节点
     * @return 链表的中间结点
     */
    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        // 链表长度
        int len = 0;
        while (cur != null) {
            // 计算链表长度
            len++;
            cur = cur.next;
        }
        // 定义一个新的链表指针
        len = len / 2;
        while (len > 0) {
            head = head.next;
            len--;
        }
        return head;
    }

    /**
     * 143. 重排链表
     * @param head 链表头节点
     */
    public void reorderList(ListNode head) {
        // 找到链表的中间节点
        ListNode midNode = middleNode(head);
        // 从中间节点断开链表
        ListNode newHead = midNode.next;
        midNode.next = null;
        // 反转后半部分链表
        newHead = reverse(newHead);
        // 合并两个链表
        while (newHead != null) {
            ListNode temp = newHead;
            newHead = newHead.next;
            temp.next = head.next;
            head.next = temp;
            head = head.next.next;
        }
    }

    /**
     * 160. 相交链表
     * @param headA 链表A
     * @param headB 链表B
     * @return 相交的节点
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        // 定义链表A的指针
        ListNode curA = headA;
        // 定义链表B的指针
        ListNode curB = headB;
        int lenA = 0;
        int lenB = 0;
        while (curA != null) {
            // 计算A链表的长度
            lenA++;
            curA = curA.next;
        }
        while (curB != null) {
            // 计算B链表的长度
            lenB++;
            curB = curB.next;
        }
        if (lenB > lenA) {
            // 交换两个链表的指针 和 长度
            int temp = lenA;
            lenA = lenB;
            lenB = temp;
            ListNode tempNode = headA;
            headA = headB;
            headB = tempNode;
        }
        int diff = lenA - lenB;
        while (diff > 0) {
            // 移动链表A的指针到和B链表相同的位置
            headA = headA.next;
            diff--;
        }
        while (headA != null) {
            // 判断两个链表是否相交
            if (headA == headB) {
                return headA;
            }
            // 移动两个链表的指针
            headA = headA.next;
            headB = headB.next;
        }

        return null;
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
     * 104. 二叉树的最大深度
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
        if (root == null) {
            return 0;
        }
        // 这道题递归条件里分为三种情况
        // 1.左孩子和有孩子都为空的情况，说明到达了叶子节点，直接返回1即可
        if (root.left == null && root.right == null) {
            return 1;
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
     * 100. 相同的树
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
