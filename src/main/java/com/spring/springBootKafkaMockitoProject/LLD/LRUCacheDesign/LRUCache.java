package com.spring.springBootKafkaMockitoProject.LLD.LRUCacheDesign;

import java.util.HashMap;
import java.util.Map;

import static org.apache.catalina.security.SecurityUtil.remove;

public class LRUCache {
    private final int capacity;
    private final Map<String,Node> map;

    private Node head;
    private Node tail;

    public LRUCache(int capacity, Map<String, Node> map) {
        this.capacity = capacity;
        this.map = new HashMap<>();
    }

    public int get(String key){
        if(!map.containsKey(key)){
            return -1;
        }
        Node node = map.get(key);
        deleteFromList(node); // from current position
        addToHead(node); //adding to most recently used position that is head
        return node.getValue();
    }

    public void put(String key,int value){
        if(map.containsKey(key)){
           map.get(key).setValue(value);
            Node node = map.get(key);
            deleteFromList(node);//from current position
            addToHead(node);//adding to most recently used position that is head
        }else{
            if(map.size() == capacity) {
                map.remove(tail.getKey());
                deleteFromList(tail);
            }
            Node newNode = new Node(key,value);
            addToHead(newNode);
            map.put(key,newNode);
        }
    }

    private void deleteFromList(Node node) {
        if(node ==head && node==tail){
            // if only one node is present
            head =null;
            tail =null;
        }else if(node == head){
            head = head.getNext();
            head.setPrev(null);
        }else if(node == tail){
            tail = tail.getPrev();
            tail.setNext(null);
        }else{
            // working in middle-
            node.getPrev().setNext(node.getNext());
            node.getNext().setPrev(node.getPrev());
        }
    }

    private void addToHead(Node newNode) {
        if(head ==null){
            head = newNode;
            tail = newNode;
        }else{
            newNode.setNext(head);
            head.setPrev(newNode);
            head = newNode;
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2,new HashMap<>());
        lruCache.put("1",1);
        lruCache.put("2",2);
        System.out.println(lruCache.get("1")); // return 1
        lruCache.put("3",3); // evicts key 2
        System.out.println(lruCache.get("2")); // return -1 (not found)
        lruCache.put("4",4); // evicts key 1
        System.out.println(lruCache.get("1")); // return -1 (not found)
        System.out.println(lruCache.get("3")); // return 3
        System.out.println(lruCache.get("4")); // return 4
    }

}
