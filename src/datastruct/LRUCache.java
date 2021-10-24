package datastruct;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    static class Node {
        int key, val;
        Node before, after;
        Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.before = this.after = this;
        }
    }

    private Map<Integer, Node> map;
    private Node tail;
    private int capacity;
    private int size;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.size = 0;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node != null && tail != node) {
            removeFromList(node);
            moveToHead(node);
        }
        return node == null ? -1 : node.val;
    }

    public void put(int key, int val) {
        Node node = map.get(key);
        if (node != null) {
            node.val = val;
            removeFromList(node);
            moveToHead(node);
            return;
        }
        Node newNode = new Node(key, val);
        map.put(newNode.key, newNode);
        if (size == capacity) {
            Node oldest = tail.after;
            removeFromList(oldest);
            moveToHead(newNode);
        } else {
            size++;
            moveToHead(newNode);
        }
    }

    private void moveToHead(Node node) {
        map.put(node.key, node);
        if (tail == null) {
            tail = node;
            return;
        }
        node.before = tail;
        node.after = tail.after;
        tail.after.before = node;
        tail.after = node;
        tail = node;
    }

    private void removeFromList(Node node) {
        map.remove(node.key);
        if (node == tail) {
            if (tail.after == tail) {
                tail = null;
            } else {
                Node ntail = tail.before;
                tail.before.after = tail.after;
                tail.after.before = tail.before;
                tail = ntail;
            }
        } else {
            node.after.before = node.before;
            node.before.after = node.after;
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println(cache.get(2));
        cache.put(4, 4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }
}
