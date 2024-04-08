package LinkedList;

import LinkedList.reverseList.ListNode;

public class reverseListInKGroup {
    // My approach : Recursion
    public static ListNode reverseKGroupBadApproach(ListNode head, int k) {
        if(head == null) return null;
        ListNode p = head;
        Boolean bool = true;
        ListNode ans = new ListNode();
        ListNode ansp = ans;
        for(int i = 0; i < k; i++){
            ansp.next = new ListNode(p.val);
            ansp = ansp.next;
            p = p.next;
            if(p == null && i!= k-1){
                bool = false;
                break;
            }
        }
        if(bool == false) return head;
        ListNode answer = reverseList(ans.next);
        ListNode nextHead = p;
        ListNode a = answer;
        while(a.next!=null){
            a = a.next;
        }
        a.next = reverseKGroupBadApproach(nextHead, k);
        return answer;
    }

    // Better Approach : Iterative
    public static ListNode findKthNode(ListNode temp, int k){
        k-=1;
        while(temp !=null && k> 0){
            k--;
            temp = temp.next;
        }
        return temp;
    }
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode temp = head;
        ListNode nextNode = null;
        ListNode prevNode = null;
        while(temp!=null){
            ListNode kNode = findKthNode(temp, k);
            if(kNode == null){
                if(prevNode != null)
                    prevNode.next = temp;
                break;
            }
            nextNode = kNode.next;
            kNode.next = null;
            @SuppressWarnings("unused")
            ListNode ans = reverseList(temp);
            if(temp == head){
                head = kNode;
            }else{
                prevNode.next = kNode;

            }
            prevNode = temp;
            temp = nextNode;
        }
        return head;
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
        // ListNode head3 = new ListNode(3, null);
        // ListNode head2 = new ListNode(2,head3);
        // ListNode head = new ListNode(1,head2);

        // ListNode trav = head;
        // while(trav != null){
        //     System.out.print(trav.val + " ");
        //     trav = trav.next;
        // }
        // System.out.println();

        // ListNode ans = reverseKGroupBadApproach(head, 2);
        // trav = ans;
        // while(trav != null){
        //     System.out.print(trav.val + " ");
        //     trav = trav.next;
        // }
        // System.out.println();

        ListNode head3 = new ListNode(3, null);
        ListNode head2 = new ListNode(2,head3);
        ListNode head = new ListNode(1,head2);

        ListNode trav = head;
        while(trav != null){
            System.out.print(trav.val + " ");
            trav = trav.next;
        }
        System.out.println();

        ListNode ans = reverseKGroup(head, 2);
        trav = ans;
        while(trav != null){
            System.out.print(trav.val + " ");
            trav = trav.next;
        }
        System.out.println();
    }
}
