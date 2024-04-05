package LinkedList;

import LinkedList.reverseList.ListNode;

public class hasCycle{
    public static boolean hasCycleInList(ListNode head) {
        if(head == null || head.next == null ) return false;
        ListNode slow = head;
        ListNode fast = head;
        while(fast!= null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        ListNode head3 = new ListNode(3, null);
        ListNode head2 = new ListNode(2,head3);
        ListNode head = new ListNode(1,head2);
        head3.next = head;

        // ListNode trav = head;
        // while(trav != null){
        //     System.out.print(trav.val + " ");
        //     trav = trav.next;
        // }
        // System.out.println();

        System.out.println(hasCycleInList(head));
    }
}