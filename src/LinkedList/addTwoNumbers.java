package LinkedList;

import LinkedList.reverseList.ListNode;

public class addTwoNumbers {
    public static ListNode addTwoNums(ListNode l1, ListNode l2) {
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode ans = new ListNode();
        ListNode p = ans;
        int carry = 0;
        while(p1!=null || p2!=null){
            int sum = 0;
            if(p1!=null) sum += p1.val;
            if(p2!=null) sum += p2.val;
            sum += carry;
            carry = sum/10;
            p.next = new ListNode(sum%10);
            p = p.next;
            if(p1!=null) p1 = p1.next;
            if(p2!=null) p2 = p2.next;
        }
        if(carry > 0){
            p.next = new ListNode(carry);
        }
        return ans.next;
    }
    public static void main(String[] args) {
        ListNode head3 = new ListNode(3, null);
        ListNode head2 = new ListNode(2,head3);
        ListNode head = new ListNode(1,head2);

        ListNode head6 = new ListNode(3, null);
        ListNode head5 = new ListNode(2,head6);
        ListNode head4 = new ListNode(1,head5);

        ListNode trav = head;
        while(trav != null){
            System.out.print(trav.val + " ");
            trav = trav.next;
        }
        System.out.println();

        trav = head4;
        while(trav != null){
            System.out.print(trav.val + " ");
            trav = trav.next;
        }
        System.out.println();

        ListNode ans = addTwoNums(head, head4);
        trav = ans;
        while(trav != null){
            System.out.print(trav.val + " ");
            trav = trav.next;
        }
        System.out.println();
    }
}
