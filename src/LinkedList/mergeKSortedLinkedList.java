package LinkedList;

import java.util.Comparator;
import java.util.PriorityQueue;

import LinkedList.reverseList.ListNode;

public class mergeKSortedLinkedList{
    public static class ElementWithNode {
        int intValue;
        ListNode node;

        public ElementWithNode(int intValue, ListNode node) {
            this.intValue = intValue;
            this.node = node;
        }
    }   
    public static ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) return null;
        PriorityQueue<ElementWithNode> minHeap = new PriorityQueue<>(Comparator.comparingInt(o -> o.intValue));

        for(int i = 0; i < lists.length; i++){
            if(lists[i] != null)
                minHeap.add(new ElementWithNode(lists[i].val, lists[i]));
        }

        ListNode ans = new ListNode(-1);
        ListNode ansp = ans;

        while(!minHeap.isEmpty()){
            ElementWithNode element = minHeap.poll();
            ListNode node = element.node;
            ansp.next = node;
            if(node.next != null) minHeap.add(new ElementWithNode(node.next.val, node.next));
            ansp = ansp.next;
        }
        return ans.next;
    }
    public static void main(String[] args) {
        ListNode head3 = new ListNode(5, null);
        ListNode head2 = new ListNode(3,head3);
        ListNode head = new ListNode(1,head2);

        ListNode head6 = new ListNode(6, null);
        ListNode head5 = new ListNode(4,head6);
        ListNode head4 = new ListNode(2,head5);

        ListNode head9 = new ListNode(9, null);
        ListNode head8 = new ListNode(8,head9);
        ListNode head7 = new ListNode(7,head8);

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
        trav = head7;
        while(trav != null){
            System.out.print(trav.val + " ");
            trav = trav.next;
        }
        System.out.println();

        ListNode[] ll = {head, head4, head7};
        trav = mergeKLists(ll);
        while(trav != null){
            System.out.print(trav.val + " ");
            trav = trav.next;
        }
        System.out.println();
    }
}