package ink.putin.study.leetcode.array;

/**
 * 链表交换相邻的元素
 * [1,2,3,4] -> [2,1,4,3]
 * [1,2,3,4,5] -> [2,1,4,3,5]
 * 要点：
 * [1,2,3] -> [2,1,3]
 * 所以 arr[0] = 2, arr[1] = f(3),放回链表头部 2
 *
 */
public class SwapPairs {
    public static void main(String[] args) {
        ListNode head = new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4,new ListNode(5)))));
        System.out.println(head);
        System.out.println(swapPairs01(head));
    }


    public static ListNode swapPairs01(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode one = head;
        ListNode two = head.next;
        ListNode three = head.next.next;

        two.next = one;
        one.next = swapPairs01(three);
        return two;
    }

    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        head.next = swapPairs(newHead.next);
        newHead.next = head;
        return newHead;
    }
}
