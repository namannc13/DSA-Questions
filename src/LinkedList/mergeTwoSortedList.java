package LinkedList;

import LinkedList.reverseList.ListNode;

public class mergeTwoSortedList {
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode ans = new ListNode();
        ListNode ansp = ans;
        ListNode p1 = list1;
        ListNode p2 = list2;
        while(p1 != null && p2 != null){
            if(p1.val <= p2.val){
                ansp.next = p1;
                ansp = ansp.next;
                p1 = p1.next;
            }else{
                ansp.next = p2;
                ansp = ansp.next;
                p2 = p2.next;
            }
        }
        ansp.next = p1 == null ? p2 : p1;
        return ans.next;
    }
    public static void main(String[] args) {
        ListNode head5 = new ListNode(5, null);
        ListNode head4 = new ListNode(4, head5);
        ListNode head3 = new ListNode(3, head4);
        ListNode head2 = new ListNode(2,head3);
        ListNode head = new ListNode(1,head2);

        ListNode head10 = new ListNode(10, null);
        ListNode head9 = new ListNode(9, head10);
        ListNode head8 = new ListNode(8, head9);
        ListNode head7 = new ListNode(7,head8);
        ListNode head6 = new ListNode(6,head7);

        ListNode ans = mergeTwoLists(head, head6);
        ListNode trav = ans;
        while(trav != null){
            System.out.print(trav.val + " ");
            trav = trav.next;
        }
    }
}
