package com.example.LRUcache;

public class LRUcacheDemo {

    public static void main(String[] args) {
        LRUcache<Integer, String> cache = new LRUcache<>(3);

        cache.put(1, "Value 1");
        cache.put(2, "Value 2");
        cache.put(3, "Value 3");

        System.out.println(cache.get(1));
        System.out.println(cache.get(2));

        cache.put(4, "Value 4");

        System.out.println(cache.get(3));
        System.out.println(cache.get(4));

        cache.put(2, "Updated Value 2");

        System.out.println(cache.get(1));
        System.out.println(cache.get(2));

    }

}
