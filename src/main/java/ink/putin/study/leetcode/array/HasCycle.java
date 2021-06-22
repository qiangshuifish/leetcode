package ink.putin.study.leetcode.array;

import java.util.HashSet;
import java.util.Set;

/**
 * 判断一个列表是否有环
 */
public class HasCycle {
    public static void main(String[] args) {
        ListNode four = new ListNode(4);
        ListNode three = new ListNode(3, four);
        ListNode two = new ListNode(2, three);
        ListNode head = new ListNode(1, two);
        four.next = two;

        System.out.println(hasCycle(head));
        System.out.println(hasCycle1(head));
    }

    public static boolean hasCycle1(ListNode head){
        if(head == null || head.next == null){
            return false;
        }
        if(head.next == head || head.next.next == head){
            return true;
        }
        Set<ListNode> set = new HashSet<>();
        ListNode current = head;
        while (current != null){
            if(set.contains(current)){
                return true;
            }else {
                set.add(current);
            }
            current = current.next;
        }
        return false;
    }


    public static boolean hasCycle(ListNode head) {
        if(head == null || head.next == null){
            return false;
        }
        if(head.next == head || head.next.next == head){
            return true;
        }
        ListNode one = head.next;
        ListNode two = head.next.next;
        while(one != null && two != null){
            if(one == two){
                return true;
            }
            one = one.next;
            two = two.next;
            if(two == null){
                return false;
            }else{
                two = two.next;
            }
        }
        return false;
    }
}
