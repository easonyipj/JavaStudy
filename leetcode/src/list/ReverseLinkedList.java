package list;

/**
 * Eason
 * 2019/10/17
 * Reverse a singly linked list.
 *
 * Example:
 *
 * Input: 1->2->3->4->5->NULL
 * Output: 5->4->3->2->1->NULL
 * Follow up:
 *
 * A linked list can be reversed either iteratively or recursively. Could you implement both?
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class ReverseLinkedList {

    /**
     * 迭代法
     */
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        while(curr != null) {
            ListNode temp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = temp;
        }
        return pre;
    }

    /**
     * 尾递归
     */
    public ListNode reverseListByRecursion(ListNode head) {
        return reverse(null,head);
    }
    private ListNode reverse(ListNode pre, ListNode curr) {
        if(curr == null) {
            return pre;
        }
        ListNode next = curr.next;
        curr.next = pre;
        return reverse(curr, next);
    }
}
