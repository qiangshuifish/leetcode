package ink.putin.study.leetcode.array;

import java.util.*;

/**
 * 设计和构建一个“最近最少使用”缓存，该缓存会删除最近最少使用的项目。缓存应该从键映射到值(允许你插入和检索特定键对应的值)，并在初始化时指定最大容量。当缓存被填满时，它应该删除最近最少使用的项目

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/lru-cache-lcci
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LRUCache {

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        System.out.println(cache.get(2));
        cache.put(2,6);
        System.out.println(cache.get(1));
        cache.put(1,5);
        cache.put(1,2);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
    }

    private Map<Integer,Integer> cache;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new LinkedHashMap<>((int)((float)capacity / 0.75f + 1.0f), 0.75f, true);
    }
    
    public int get(int key) {
        Integer value = cache.get(key);
        if(value == null){
            return -1;
        }
        cache.remove(key);
        cache.put(key,value);
        return cache.get(key);
    }
    
    public void put(int key, int value) {
        cache.put(key, value);
        if (cache.size() > capacity) {
            cache.remove(cache.entrySet().iterator().next().getKey());
        }
    }
}