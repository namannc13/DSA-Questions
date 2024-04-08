package LinkedList;

import java.util.HashMap;
import java.util.Map;

public class lruCache {
    class Node{
        int val,key;
        Node next, prev;
        Node(int key, int val){
            this.key = key;
            this.val = val;
            this.next = null;
            this.prev = null;
        }
    }
    Node head = new Node(0,0);
    Node tail = new Node(0,0);
    int capacity;
    Map<Integer, Node> map = new HashMap<>();
    public lruCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if(map.containsKey(key)){
            Node newNode = map.get(key);
            remove(newNode);
            insert(newNode);
            return newNode.val;
        }else{
            return -1;
        }
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            remove(map.get(key));
        }
        if(map.size() == capacity){
            remove(tail.prev);
        }
        insert(new Node(key, value));
    }

    public void remove(Node node){
        map.remove(node.key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void insert ( Node node){
        map.put(node.key, node);
        node.next = head.next;
        node.next.prev = node;
        head.next = node;
        node.prev = head;
    }

    public static void main(String[] args) {
        lruCache lc = new lruCache(2);
        lc.put(1, 2);
        lc.put(2, 3);
        System.out.println(lc.get(1));
        System.out.println(lc.get(2));
        lc.put(3, 4);
        System.out.println(lc.get(2));
    }
}
