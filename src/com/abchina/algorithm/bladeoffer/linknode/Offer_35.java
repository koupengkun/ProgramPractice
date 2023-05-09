package com.abchina.algorithm.bladeoffer.linknode;

/**
 * 剑指 Offer 35. 复杂链表的复制
 * 请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，
 * 还有一个 random 指针指向链表中的任意节点或者 null。
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 思路：回溯+哈希表的思路
 * 建立一个Map存放新旧节点的映射关系；
 * 找到入参对应的新节点，新节点对应的下个节点关系在每次处理中维护。
 * 对回溯的理解不深。
 */
public class Offer_35 {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
    Map<Node,Node> cacheMap = new HashMap<>();
    public Node copyRandomList(Node head) {
        if(head == null){
            return null;
        }
        if(!cacheMap.containsKey(head)){
            Node newHead = new Node(head.val);
            cacheMap.put(head,newHead);
            newHead.next = copyRandomList(head.next);
            newHead.random = copyRandomList(head.random);
        }
        return cacheMap.get(head);
    }
}
