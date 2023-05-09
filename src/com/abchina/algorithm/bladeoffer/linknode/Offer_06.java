package com.abchina.algorithm.bladeoffer.linknode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 剑指 Offer 06. 从尾到头打印链表
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。

 * 示例 1：
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 */

/**
 * 注意集合转数组的方法
 */
public class Offer_06 {
    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
    public int[] reversePrint(ListNode head) {
        //用栈
      Deque<Integer> stack = new ArrayDeque<>();
      while(head != null){
        stack.push(head.val);
        head=head.next;
      }
      return stack.stream().mapToInt( x -> x).toArray();
    }
}
