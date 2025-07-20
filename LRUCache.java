// Time Complexity : get and put both O(1)
// Space Complexity : O(capacity) for doubly linked list and hashmap
// Did this code successfully run on Leetcode :yes
// Three line explanation of solution in plain english: Maintain a hashmap to get nodes in cache in O(1), to keep track of recency of nodes,
/// let's use doubly linked list with head as most recenlty used node and tail = least recently used. In hashmap save key and corresponding nodes
/// For get method, check if map if exists, if yes, get corresponding node in doubly linked list and move it to head. For put, if node already exists update value and
/// move it to head or remove least recenlty used node and create a new node with current key and values at head of list

class LRUCache {
    class Node{
        int key;
        int val;
        Node prev;
        Node next;
        Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
    HashMap<Integer, Node> map;
    Node head;
    Node tail;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }
    private void addToHead(Node node){
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }
    private void removeNode(Node node){
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }
        Node node = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addToHead(node);
        }
        else{
            if(map.size()==capacity){
                Node tailPrev = tail.prev;
                removeNode(tailPrev);
                map.remove(tailPrev.key);
            }
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            addToHead(newNode);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */