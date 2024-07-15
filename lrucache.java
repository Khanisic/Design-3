
// Time Complexity : O(1)
// Space Complexity : O(n) -> capacity
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


class LRUCache {
    class Node { // defining node
        int key, value;
        Node next, prev;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public LRUCache(int capacity) {
        map = new HashMap<>();
        head = new Node(-1, -1); // dummy head
        tail = new Node(-1, -1); // dummy node, to keep track of last element
        head.next = tail; // connecting head and tail to eachother
        tail.prev = head;
        this.capacity = capacity;
    }

    Node head, tail;

    private void addToHead(Node node) { // adding to head, adding at start
        node.next = head.next;
        node.prev = head;
        node.next.prev = node;
        head.next = node;

    }

    private void removeNode(Node node) { 
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    HashMap<Integer, Node> map;
    int capacity;

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            removeNode(node);
            addToHead(node);
            node.value = value;
            return;
        }
        if (map.size() == capacity) {
            Node tailPrev = tail.prev;
            removeNode(tailPrev);
            map.remove(tailPrev.key);
        }
        Node node = new Node(key, value);
        map.put(key, node);
        addToHead(node);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */