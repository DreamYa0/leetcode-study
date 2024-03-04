package com.leetcode.study.list;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dreamyao
 * @title
 * @date 2024/3/4 16:22
 * @since 1.0.0
 */
public class LRUCache {

    private static class Node {
        int key, value;
        Node prev, next;

        Node(int k, int v) {
            key = k;
            value = v;
        }
    }

    // 缓存容量
    private final int capacity;
    // 虚拟头节点
    private final Node dummyHead = new Node(0, 0);
    // 缓存Key与节点映射
    private final Map<Integer, Node> keyToNode = new HashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        dummyHead.prev = dummyHead;
        dummyHead.next = dummyHead;
    }

    /**
     * 设置缓存
     * @param key   缓存key
     * @param value 缓存值
     */
    public void put(int key, int value) {
        Node node = move(key);
        // 如果缓存中存在
        if (node != null) {
            // 修改节点的值
            node.value = value;
        } else {
            // 如果不存在，需要判断缓存是否已经满了，如果满了需要删除最少使用的才能再加入
            Node newNode = new Node(key, value);
            // 存入哈希表
            keyToNode.put(key, newNode);
            // 放入表头
            pushFront(newNode);
            if (keyToNode.size() > capacity) {
                // 缓存满了，需要删除掉最少使用的
                // 在双链表中虚拟头结点的前驱节点就是尾节点
                Node prev = dummyHead.prev;
                keyToNode.remove(prev.key);
                // 删除掉
                remove(prev);
            }
        }
    }

    /**
     * 获取缓存
     * @param key 缓存key
     * @return 缓存值
     */
    public int get(int key) {
        Node node = move(key);
        if (node == null) {
            return -1;
        }
        return node.value;
    }

    /**
     * 移动节点
     * @param key 缓存key
     * @return 节点
     */
    private Node move(int key) {
        if (!keyToNode.containsKey(key)) {
            // 缓存中不存在
            return null;
        }
        Node node = keyToNode.get(key);
        // 从原来的为止删除掉，再从新放入表头
        remove(node);
        pushFront(node);
        return node;
    }

    /**
     * 删除节点
     * @param node 要删除的节点
     */
    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /**
     * 表头插入节点
     * @param node 需要插入的节点
     */
    private void pushFront(Node node) {
        node.next = dummyHead.next;
        node.prev = dummyHead;
        node.prev.next = node;
        node.next.prev = node;
    }
}
