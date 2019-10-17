package divide.and.conquer;

import list.ListNode;

import java.util.PriorityQueue;

/**
 * Eason
 * 2019/10/16
 **/
public class MergekSortedLists {

    /**
     * 用Java优先队列（堆）来实现
     */
    public ListNode mergeKListByMinHeap(ListNode[] lists) {
        PriorityQueue<ListNode> listNodePriorityQueue = new PriorityQueue<>();

        for(ListNode node : lists) {
            if(node != null) {
                listNodePriorityQueue.add(node);
            }
        }

        ListNode head = new ListNode(0);
        ListNode curr = head;

        while(!listNodePriorityQueue.isEmpty()) {
            if(curr != null) {
                curr.next = listNodePriorityQueue.poll();
                curr = curr.next;
            }
            if(curr != null) {
                listNodePriorityQueue.offer(curr);
            }
        }

        return head.next;
    }

    /**
     * 分治实现
     * 1、分治list数组
     * 2、分治两个list
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) {
            return null;
        }
        if(lists.length == 1) {
            return lists[0];
        }
        if(lists.length == 2) {
            return mergeTwoLists(lists[0], lists[1]);
        }

        int mid = lists.length / 2;

        ListNode[] l1 = new ListNode[mid];
        for(int i = 0; i < mid; i++) {
            l1[i] = lists[i];
        }

        ListNode[] l2 = new ListNode[lists.length - mid];
        for(int i = mid; i < lists.length; i++) {
            l2[i - mid] = lists[i];
        }

        return mergeTwoLists(mergeKLists(l1), mergeKLists(l2));
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) {
            return l2;
        }
        if(l2 == null) {
            return l1;
        }

        ListNode head = null;
        if(l1.val < l2.val) {
            head = l1;
            head.next = mergeTwoLists(l1.next, l2);
        }else {
            head = l2;
            head.next = mergeTwoLists(l2.next, l1);
        }

        return head;
    }
}
