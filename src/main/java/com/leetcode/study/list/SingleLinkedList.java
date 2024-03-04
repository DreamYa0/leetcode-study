package com.leetcode.study.list;

/**
 * @author dreamyao
 * @title 单链表
 * @date 2024/2/26 17:18
 * @since 1.0.0
 */
@SuppressWarnings("all")
public class SingleLinkedList {

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
            // 移动list1到达值和list2相等的地方
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
     * LCR 023. 相交链表
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
     * 234. 回文链表
     * @param head 头节点
     * @return 是否回文链表
     */
    public boolean isPalindrome(ListNode head) {
        // 如果链表为空直接返回
        if (head == null) {
            return true;
        }

        // 找到链表中间节点
        ListNode firstHalfEnd = endOfFirstHalf(head);
        // 反转链表后半段
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);

        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        // 遍历后半段链表
        while (p2 != null) {
            if (p1.val != p2.val) {
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        // 还原链表
        firstHalfEnd.next = reverseList(secondHalfStart);
        return true;
    }

    /**
     * 找到链表中间节点
     * @param head 链表头节点
     * @return 中间节点
     */
    private ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * LCR 140. 训练计划 II
     * @param head 头节点
     * @param cnt  倒数第 cnt 个训练项目编号
     * @return 训练项目编号
     */
    public ListNode trainingPlan(ListNode head, int cnt) {
        // 定义虚拟头节点
        ListNode dummyHead = new ListNode(0);
        // 虚拟头节点next指针指向head
        dummyHead.next = head;
        // 定义快慢指针
        ListNode slow = dummyHead;
        ListNode fast = dummyHead;
        // 先让fast指针移动 cnt步
        while (cnt > 0) {
            fast = fast.next;
            cnt--;
        }
        // 在遍历链表
        while (fast.next != null) {
            // 一起移动slow fast指针,目的是让slow指针停在需要返回的节点数据之前的节点
            fast = fast.next;
            slow = slow.next;
        }
        // slow.next节点就是需要返回的节点
        return slow.next;
    }

    /**
     * LCR 123. 图书整理 I
     * @param head
     * @return
     */
    public int[] reverseBookList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        // 定义链表长度
        int len = 0;
        while (cur != null) {
            // 统计链表长度
            len++;
            // 反转链表
            ListNode temp = cur;
            cur = cur.next;
            temp.next = pre;
            pre = temp;
        }
        // 定义结果集
        int[] ans = new int[len];
        int i = 0;
        while (pre != null) {
            // 遍历链表获取值
            ans[i] = pre.val;
            pre = pre.next;
            i++;
        }
        return ans;
    }

    /**
     * 面试题 02.02. 返回倒数第 k 个节点
     * @param head 头节点
     * @param k 单向链表中倒数第 k 个节点
     * @return 返回该节点的值
     */
    public int kthToLast(ListNode head, int k) {
        // 定义虚拟头节点
        ListNode dummyHead=new ListNode(0);
        dummyHead.next = head;
        // 定义慢指针
        ListNode slow = dummyHead;
        // 定义快指针
        ListNode fast = dummyHead;
        // 让快指针先走k个节点
        while (k-- > 0) {
            fast = fast.next;
        }
        // 遍历链表
        while (fast.next != null) {
            fast = fast.next;
            // 当链表遍历结束时slow指针指向位置的next节点就是，单向链表中倒数第 k 个节点
            slow = slow.next;
        }
        return slow.next.val;
    }
}
