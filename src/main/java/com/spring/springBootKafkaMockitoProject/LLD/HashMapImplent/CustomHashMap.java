package com.spring.springBootKafkaMockitoProject.LLD.HashMapImplent;

class Entry<K, V> {
    final K key;
    V value;
    Entry<K, V> next;

    Entry(K key, V value, Entry<K, V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }
}

public class CustomHashMap<K, V> {
    private Entry<K, V>[] buckets;
    private int capacity = 16;
    private int size = 0;
    private final float loadFactor = 0.75f;

    public CustomHashMap() {
        buckets = new Entry[capacity];
    }

    private int hash(K key) {
        int h = key.hashCode();
        return (h ^ (h >>> 16)) & (capacity - 1); // Spread bits and mask
    }

    public void put(K key, V value) {
        int index = hash(key);
        Entry<K, V> head = buckets[index];

        for (Entry<K, V> e = head; e != null; e = e.next) {
            if (e.key.equals(key)) {
                e.value = value; // Update value
                return;
            }
        }
        // Insert new at head
        Entry<K, V> newEntry = new Entry<>(key, value, head);
        buckets[index] = newEntry;
        size++;

        if (size >= capacity * loadFactor) {
            resize();
        }
    }

    public V get(K key) {
        int index = hash(key);
        for (Entry<K, V> e = buckets[index]; e != null; e = e.next) {
            if (e.key.equals(key)) {
                return e.value;
            }
        }
        return null;
    }

    public V remove(K key) {
        int index = hash(key);
        Entry<K, V> prev = null;
        Entry<K, V> e = buckets[index];

        while (e != null) {
            if (e.key.equals(key)) {
                if (prev == null) {
                    buckets[index] = e.next;
                } else {
                    prev.next = e.next;
                }
                size--;
                return e.value;
            }
            prev = e;
            e = e.next;
        }
        return null;
    }

    private void resize() {
        int newCapacity = capacity * 2;
        Entry<K, V>[] newBuckets = new Entry[newCapacity];

        // Rehash entries
        for (Entry<K, V> head : buckets) {
            while (head != null) {
                Entry<K, V> next = head.next;
                int newIndex = (head.key.hashCode() ^ (head.key.hashCode() >>> 16)) & (newCapacity - 1);

                head.next = newBuckets[newIndex];
                newBuckets[newIndex] = head;
                head = next;
            }
        }
        buckets = newBuckets;
        capacity = newCapacity;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        CustomHashMap<String, Integer> map = new CustomHashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        System.out.println("Value for 'two': " + map.get("two")); // Output: 2
        System.out.println("Size: " + map.size()); // Output: 3

        map.remove("two");
        System.out.println("Value for 'two' after removal: " + map.get("two")); // Output: null
        System.out.println("Size after removal: " + map.size()); // Output: 2
    }
}

