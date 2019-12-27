package design;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
 *
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity,
 * it should invalidate the least recently used item before inserting a new item.
 *
 * The cache is initialized with a positive capacity.
 *
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 *
 * Example:
 *
 * LRUCache cache = new LRUCache( 2 capacity  )
        *
        *cache.put(1,1);
        *cache.put(2,2);
        *cache.get(1);       // returns 1
        *cache.put(3,3);    // evicts key 2
        *cache.get(2);       // returns -1 (not found)
        *cache.put(4,4);    // evicts key 1
        *cache.get(1);       // returns -1 (not found)
        *cache.get(3);       // returns 3
        *cache.get(4);       // returns 4
        *

 */

/**
 *
 * 继承LinkedHashMap解法
 *
 * class LRUCache extends LinkedHashMap<Integer, Integer>{
 *     private int capacity;
 *
 *     public LRUCache(int capacity) {
 *         super(capacity, 0.75F, true);
 *         this.capacity = capacity;
 *     }
 *
 *     public int get(int key) {
 *         return super.getOrDefault(key, -1);
 *     }
 *
 *     public void put(int key, int value) {
 *         super.put(key, value);
 *     }
 *
 *
 *     protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
 *         return size() > capacity;
 *     }
 * }
 *
 */
public class LRUCache {

    private int capacity;
    private int size;
    private HashMap<Integer, Node> map = new HashMap<>();
    private Node head;
    private Node tail;

    class Node{
        int key;
        int value;
        Node pre;
        Node next;
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        Node node = map.get(key);
        if(node == null) {
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if(node == null) {
            if(size + 1 > capacity) {
                Node tail = removeLast();
                map.remove(tail.key);
            }
            node = new Node();
            node.key = key;
            node.value = value;
        }else {
            node.value = value;
            remove(node);
        }
        map.put(key, node);
        addNode(node);
    }

    private void addNode(Node node) {
        Node next = head.next;
        next.pre = node;
        node.next = next;
        node.pre = head;
        head.next = node;
        size++;
    }

    private void moveToHead(Node node) {
        remove(node);
        addNode(node);
    }

    private void remove(Node node) {
        Node pre = node.pre;
        Node next = node.next;
        pre.next = next;
        next.pre = pre;
        size--;
    }

    private Node removeLast() {
        Node node = tail.pre;
        remove(node);
        return node;
    }
}
