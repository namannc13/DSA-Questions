package LinkedList;

import LinkedList.reverseList.ListNode;

public class removenthNodeFromEnd {
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode temp =  head;
        int count = 0;
        while(temp!= null){
            temp = temp.next;
            count++;
        }
        int a = count - n-1;
        if( a < 0 ) return head.next;
        temp = head;
        while(a!= 0){
            temp = temp.next;
            a--;
        }
        temp.next = temp.next.next;
        return head;
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
        ListNode ans = removeNthFromEnd(head, 2);
        trav = ans;
        System.out.println();
        while(trav != null){
            System.out.print(trav.val + " ");
            trav = trav.next;
        }
    }
}
