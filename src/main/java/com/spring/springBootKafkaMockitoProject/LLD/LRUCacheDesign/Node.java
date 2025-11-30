package com.spring.springBootKafkaMockitoProject.LLD.LRUCacheDesign;

import lombok.Data;

@Data
public class Node {
    private final String key;
    private int value;

    private Node prev;
    private Node next;

    public Node(String key, int value) {
        this.key = key;
        this.value = value;
    }
}
