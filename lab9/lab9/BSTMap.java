package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */
    private V deletedValue;

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    public BSTMap(K key, V value) {
        this.root = new Node(key, value);
        this.size = 1;
    }
    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p==null) {
            return null;
        }
        if (key.compareTo(p.key)==0) {
            V value = p.value;
            return value;
        }
        else if (key.compareTo(p.key) < 0) {
            return getHelper(key, p.left);
        } else if (key.compareTo(p.key) > 0) {
            return getHelper(key, p.right);
        }
        return null;
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     * @return
     */
    private Node putHelper(K key, V value, Node p) {
        if (p==null) {
            this.size += 1;
            p = new Node(key, value);
            return p;
        }
        if (key.compareTo(p.key) < 0) {
            p.left = putHelper(key, value, p.left);
        } else if (key.compareTo(p.key) > 0) {
            p.right = putHelper(key, value, p.right);
        } else {
            p.value = value;
        }
        return p;
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        root = putHelper(key, value, root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return this.size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        LinkedList<K> keys = new LinkedList<>();
        keySetHelper(keys, root);
        HashSet<K> set = new HashSet<>();
        set.addAll(keys);
        return set;
    }

    private void keySetHelper(LinkedList<K> keys, Node p) {
        if (p!=null) {
            keys.add(p.key);
            keySetHelper(keys, p.left);
            keySetHelper(keys, p.right);
        }
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    private Node minNode(Node x) {
        if (x.left == null) {
            return x;
        } else {
            return minNode(x.left);
        }
    }

    @Override
    public V remove(K key) {
        root = removeHelper(root, key);
        return deletedValue;
    }

    private Node removeMin(Node x, K key) {
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x.right;
        } else {
            x.left = removeMin(x.left, key);
            return x;
        }
    }

    private Node removeHelper(Node x, K key) {
        if (x == null) {return null;}
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {x.left = removeHelper(x.left, key);}
        if (cmp > 0) {x.right = removeHelper(x.right, key);}
        if (cmp == 0) {
            size -= 1;
            deletedValue = x.value;
            if (x.left == null) {
                return x.right;
            } else if (x.right == null) {
                return x.left;
            } else {
                Node t = minNode(x.right);
                t.left = x.left;
                t.right = removeMin(x.right, t.key);
                return t;
            }
        }
        return x;
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        V got = get(key);
        if (got == value) {
            return remove(key);
        } else {
            return null;
        }
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
