package LinkedList;

public class reverseList {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode reverseListNode(ListNode head) {
        if (head == null)
            return null;
        ListNode prev = null;
        ListNode curr = head;
        ListNode ahead = head.next;
        if (ahead == null)
            return head;
        while (curr != null) {
            curr.next = prev;
            prev = curr;
            curr = ahead;
            if (ahead != null)
                ahead = ahead.next;
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
        ListNode ans = reverseListNode(head);
        trav = ans;
        System.out.println();
        while(trav != null){
            System.out.print(trav.val + " ");
            trav = trav.next;
        }
    }
}
