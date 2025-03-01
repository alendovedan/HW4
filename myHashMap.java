/*
 * *** Alen Dovedan / Section 002 ***
 *
 * This hashMap object represents an over simplification of Java's implementation of HashMap within
 * Java's Collection Framework Library. You are to complete the following methods:
 *  - remove(K)
 *  - replace(K,V)
 *  - replace(K,V,V)
 *
 * In addition to the documentation below, you can read the online Java documentation for HashMap for
 * the expected behavior / return values of each method below. This object follows the same behavior
 * of those methods implemented in this Java library.
 *
 */


/**
 *
 *  This sample code is illustrating a hash table using separate chaining. To illustrate this,
 *  the code is building a Hash Map implementation that emulates Java's HashMap class. This class
 *  implements many of the java library's class's methods and emulates the behavior of the Map
 *  interface which is what the Java Library does.
 *
 *  This class is demonstrating the use of separate chaining hashing, which is also used by
 *  Java's library class. This class is not intended to be a full-blown Hash Map / Hash Table
 *  implementation, nor does it implement all methods in Java's HashMap class. But the ones that
 *  are implemented emulate how those methods work in Java's HashMap.
 *
 *  CAVEAT: as indicated, Java provides a HashMap class that is implemented on the Map Interface
 *  that is more robust, and is more expansive than this implementation. But what is implemented
 *  operates the same way. This coding example is illustrating sample coding for how hash tables
 *  using separate chaining (versus open addressing techniques). And the behavior emulates the Map
 *  interface that Java's HashMap follows.
 *
 *  PUBLIC METHODS:
 *  ---------------
 *
 *     void  clear()               - Removes all of the mappings from this map.
 *  boolean  containsValue(V)      - Returns true if this map maps one or more keys to the specified value
 *  boolean  containsKey(K)        - Returns true if this map contains a mapping for the specified key.
 *       V   get(K)                - Returns the value to which the specified key is mapped, or null
 *                                   if this map contains no mapping for the key
 *       V   put(K, V)             - Associates the specified value with the specified key in this map
 *       V   putIfAbsent(K, V)     - If the specified key is not already associated with a value (or
 *                                   is mapped to null) associates it with the given value and returns
 *                                   null, else returns the current value
 *       V   remove(K)             - Removes the entry for the specified key only if it is currently
 *                                   mapped to the specified value
 *  boolean  remove(K, V)          - Removes the entry for the specified key only if it is currently
 *                                   mapped to the specified value.
 *  boolean  replace(K, V)         - Replaces the entry for the specified key only if it is currently
 *                                   mapped to some value
 *        V  replace(K, V1, V2)    - Replaces the entry for the specified key only if currently mapped
 *                                   to the specified value.
 *  Set<K>   keySet()              - Returns a 'Set' view of the keys contained in the map.
 *  Set<Map.Entry<K,V>> entrySet() - Returns a 'Set' view of the mappings contains in the map.
 *      int  size()                - returns the number of <k,v> pairs in hashmap
 *      boolean isEmpty()          - returns true if this map contains no key-value mappings.
 *
 *
 *  Methods *NOT* implemented to fully emulate the behavior 
 *  of Java's HashMap Class
 *      - clone()
 *      - compute()
 *      - computeIfAbsent()
 *      - computeIfPresent()
 *      - foreach()
 *      - merge(()
 *      - putAll()
 *      - replaceAll()
 *      - values()
 *
 ****************************************/

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

/**
 * Class HashNode
 *
 * Node object representing a <Key, Value> pair stored in the Hash Map, elements
 * hashed to the same bucket slot will be chained through a singly linked-list.
 */

class HashNode<K, V> {
    K key;
    V value;
    HashNode<K, V> next;

    public HashNode() {
        this.key = key;
        this.value = value;
    }
}

/**
 * A simple implementation of a HashMap that is built to emulate the Map
 * Interface.
 * The <key, values> pairs are stored in a Map, where the key represents a hash
 * bucket slot number and the value represents a node which will form as
 * linked-list
 * for hash collisions on that bucket's slot.
 */

class myHashMap<K, V> {

    private static final float DEFAULT_LOAD_FACTOR = 0.7f;
    private static final int INITIAL_NUM_BUCKETS = 10;

    ArrayList<HashNode<K, V>> bucket = new ArrayList<>();
    int numBuckets = INITIAL_NUM_BUCKETS;
    int size = 0;

    public myHashMap() {
        for (int i = 0; i < numBuckets; i++) {
            bucket.add(null);
        }
    }

    public int Size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        size = 0;
        numBuckets = INITIAL_NUM_BUCKETS;
        bucket = new ArrayList<>();
        for (int i = 0; i < numBuckets; i++) {
            bucket.add(null);
        }
    }

    private int getBucketIndex(K key) {
        return (key.hashCode() & 0x7fffffff) % numBuckets;
    }

    public V get(K key) {
        int index = getBucketIndex(key);
        HashNode<K, V> head = bucket.get(index);
        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    /**
     * Method remove(K)
     * Removes the entry for the specified key.
     */
    public V remove(K key) {
        int index = getBucketIndex(key);
        HashNode<K, V> head = bucket.get(index);
        HashNode<K, V> prev = null;

        while (head != null) {
            if (head.key.equals(key)) {
                if (prev != null) {
                    prev.next = head.next;
                } else {
                    bucket.set(index, head.next);
                }
                size--;
                return head.value;
            }
            prev = head;
            head = head.next;
        }
        return null; // Return null if key is not found
    }

    /**
     * Method remove(K, V)
     * Removes the entry for the specified key only if it is currently mapped to the
     * specified value.
     */
    public boolean remove(K key, V val) {
        V originalValue = get(key);
        if (originalValue == null || !originalValue.equals(val)) {
            return false; // Return false if the key-value pair doesn't match
        }
        remove(key); // Remove the key-value pair
        return true; // Return true after successful removal
    }

    /**
     * Method replace(K, V)
     * Replaces the entry for the specified key only if it is currently mapped to
     * some value.
     */
    public V replace(K key, V val) {
        int index = getBucketIndex(key);
        HashNode<K, V> head = bucket.get(index);

        while (head != null) {
            if (head.key.equals(key)) {
                V oldValue = head.value;
                head.value = val;
                return oldValue; // Return the old value if key is found and replaced
            }
            head = head.next;
        }
        return null; // Return null if key is not found
    }

    /**
     * Method replace(K, V, V)
     * Replaces the entry for the specified key only if currently mapped to the
     * specified old value.
     */
    public boolean replace(K key, V oldVal, V newVal) {
        int index = getBucketIndex(key);
        HashNode<K, V> head = bucket.get(index);

        while (head != null) {
            if (head.key.equals(key) && head.value.equals(oldVal)) {
                head.value = newVal;
                return true; // Return true if the key-value pair is replaced
            }
            head = head.next;
        }
        return false; // Return false if the key-value pair with old value is not found
    }

    public V put(K key, V value) {
        V oldValue = get(key);
        if (oldValue != null) {
            replace(key, value);
            return oldValue;
        }

        int index = getBucketIndex(key);
        HashNode<K, V> head = bucket.get(index);
        HashNode<K, V> toAdd = new HashNode<>();
        toAdd.key = key;
        toAdd.value = value;

        if (head == null) {
            bucket.set(index, toAdd);
            size++;
        } else {
            while (head != null) {
                if (head.key.equals(key)) {
                    head.value = value;
                    size++;
                    break;
                }
                head = head.next;
            }
            if (head == null) {
                head = bucket.get(index);
                toAdd.next = head;
                bucket.set(index, toAdd);
                size++;
            }
        }

        if ((1.0 * size) / numBuckets > DEFAULT_LOAD_FACTOR) {
            ArrayList<HashNode<K, V>> tmp = bucket;
            bucket = new ArrayList<>();
            numBuckets = 2 * numBuckets;
            size = 0;

            for (int i = 0; i < numBuckets; i++) {
                bucket.add(null);
            }

            for (HashNode<K, V> headNode : tmp) {
                while (headNode != null) {
                    put(headNode.key, headNode.value);
                    headNode = headNode.next;
                }
            }
        }

        return null;
    }

    public V putIfAbsent(K key, V value) {
        V originalValue = get(key);
        if (originalValue == null) {
            put(key, value);
            return null;
        }
        return originalValue;
    }

    public boolean containsValue(V val) {
        for (HashNode<K, V> headNode : bucket) {
            while (headNode != null) {
                if (headNode.value.equals(val)) {
                    return true;
                }
                headNode = headNode.next;
            }
        }
        return false;
    }

    public boolean containsKey(K key) {
        return (get(key) != null);
    }

    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> returnSet = new HashSet<>();
        for (HashNode<K, V> headNode : bucket) {
            while (headNode != null) {
                returnSet.add(Map.entry(headNode.key, headNode.value));
                headNode = headNode.next;
            }
        }
        return returnSet;
    }

    public Set<K> keySet() {
        Set<K> returnSet = new HashSet<>();
        for (HashNode<K, V> headNode : bucket) {
            while (headNode != null) {
                returnSet.add(headNode.key);
                headNode = headNode.next;
            }
        }
        return returnSet;
    }
} /* end class myHashMap */
