package com.abchina.algorithm.bladeoffer.stack_queue;

import java.util.*;

/**
 * 剑指 Offer 30. 包含min函数的栈
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。

 * 示例:
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.min();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.min();   --> 返回 -2.
 */

/**
 * 思路：记住入栈的最小值和最大值，每有数据出入栈时维护最大栈和最小栈，保证数据栈和辅助栈的同步
 */
class MinStack {

    /** initialize your data structure here. */

    Deque<Integer> dataStack;
    Deque<Integer> minStack;
    public MinStack() {
        dataStack = new LinkedList<>();
        minStack = new LinkedList<>();
        //min = Integer.MAX_VALUE;
        minStack.push(Integer.MAX_VALUE);
    }

    public void push(int x) {
        //min = Math.min(min,x);
        dataStack.push(x);
        minStack.push(Math.min(minStack.peek(),x));

    }

    public void pop() {
        if(dataStack.isEmpty()){
            return;
        }
        dataStack.pop();
        minStack.pop();

    }

    public int top() {
        return dataStack.peek();
    }

    public int min() {
        return minStack.peek();
    }
}
