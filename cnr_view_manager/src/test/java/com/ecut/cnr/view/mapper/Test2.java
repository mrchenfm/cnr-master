package com.ecut.cnr.view.mapper;

import java.util.Stack;

public class Test2 {
    public static void main(String[] args) {

    }
    public static class Node{
        private Node next = null;
        private Integer res;

        public Node(Integer res) {
            this.res = res;
        }
    }

    /**
     * 判断链表是否为回文的放法
     * @param head
     * @return
     */
    public static boolean judge(Node head){
        Stack<Integer> stack = new Stack<>();
        Node r = head;
        while(r!=null){
            stack.push(r.res);
            r = r.next;
        }
        while(head!=null){
            if(head.res!=stack.pop()) return false;
            head = head.next;
        }
        return true;
    }

    /**
     * 删除倒数第k个节点
     * @param node
     * @param k
     * @return
     */
    public static Node deleteK(Node node,int k){
            Node forward = node;
            Node backward = node;
            while(k-- != 0){
                forward = forward.next;
            }
            while(forward.next != null){   //即最后的backward为k的前一个节点
                forward = forward.next;
                backward = backward.next;
            }
            backward.next = backward.next.next;
            return node;
        }

}
