package LinkedList;

import LinkedList.reverseList.ListNode;

public class reorderList {
    public static void reorder(ListNode head) {
        if(head == null || head.next == null ) return;
        ListNode slow = head;
        ListNode fast = head;
        ListNode a = null;
        while(fast!= null && fast.next!= null) {
            a = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        a.next = null;

        ListNode h1 = head;
        ListNode h2 = reverseList(slow);
        ListNode ans = new ListNode();
        ListNode ansp = ans;
        while(h1!= null && h2!= null){
            ansp.next = h1;
            h1 = h1.next;
            ansp = ansp.next;
            ansp.next = h2;
            h2 = h2.next;
            ansp = ansp.next;
        }
        ansp.next = h1 == null ? h2 : h1;
        head = ans.next;
        return;
    }
    public static ListNode reverseList(ListNode head) {
        if(head == null) return null;
        ListNode prev = null;
        ListNode curr = head;
        ListNode ahead = head.next;
        if(ahead == null) return head;
        while(curr!= null){
            curr.next = prev;
            prev = curr;
            curr = ahead;
            if(ahead!= null) ahead = ahead.next;
        }
        head = prev;
        return prev;
    }
    public static void main(String[] args) {
        ListNode head5 = new ListNode(5, null);
        ListNode head4 = new ListNode(4, head5);
        ListNode head3 = new ListNode(3, head4);
        ListNode head2 = new ListNode(2,head3);
        ListNode head = new ListNode(1,head2);
        ListNode trav = head;
        while(trav != null){
            System.out.print(trav.val + " ");
            trav = trav.next;
        }
        reorder(head);
        System.out.println();
        trav = head;
        while(trav != null){
            System.out.print(trav.val + " ");
            trav = trav.next;
        }
    }
}
