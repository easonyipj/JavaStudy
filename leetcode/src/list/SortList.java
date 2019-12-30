package list;

/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 *
 * Example 1:
 *
 * Input: 4->2->1->3
 * Output: 1->2->3->4
 * Example 2:
 *
 * Input: -1->5->3->4->0
 * Output: -1->0->3->4->5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SortList {

    public ListNode sortList(ListNode head) {
        return divide(head);
    }

    private ListNode divide(ListNode node) {

        if(node == null || node.next == null) {
            return node;
        }
        // 双指针找到中点
        ListNode slow = node;
        ListNode fast = node;
        ListNode pre = node;
        while(slow != null && fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null;

        ListNode l = divide(node);
        ListNode r = divide(slow);
        return merge(l, r);
    }

    private ListNode merge(ListNode node1, ListNode node2) {
        ListNode head = new ListNode();
        ListNode next = head;
        while(node1 != null && node2 != null) {
            if(node1.val <= node2.val) {
                next.next = node1;
                node1 = node1.next;
            }else {
                next.next = node2;
                node2 = node2.next;
            }
            next = next.next;
        }

        if(node1 != null) {
            next.next = node1;
        }

        if(node2 != null) {
            next.next = node2;
        }

        return head.next;
    }


}
