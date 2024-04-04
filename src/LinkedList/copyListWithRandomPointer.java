package LinkedList;

import java.util.HashMap;

public class copyListWithRandomPointer{
    static class Node {
        int val;
        Node next;
        Node random;
    
        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
        public Node(int val, Node next){
            this.val = val;
            this.next = next;
            this.random = null;
        }
    }
    //my method ( bad approach )
    public static Node copyRandomList(Node head) {
        if(head == null) return null;
        Node head2 = new Node(head.val);
        Node h2p = head2;
        if(head.next == null){
            head2.next = null;
            if(head.random == head) head2.random = head2;
            if(head.random == null) head2.random = null;
        }
        Node hp = head.next;
        while(hp!= null){
            Node newNode = new Node(hp.val);
            hp = hp.next;
            h2p.next = newNode;
            h2p = h2p.next;
        }
        hp = head;
        Node p2 = head2;
        while(hp!=null){
            Node p = head;
            int count = 0;
            while(p!=hp.random){
                p = p.next;
                count++;
            }
            
            if(p == null){
                System.out.println("null");
                p2.random = null;
            }else{
                System.out.println(p.val + " ");
                Node p3 = head2;
                while(count!=0){
                    p3 = p3.next;
                    count--;
                }
                p2.random = p3;
            }
            p2 = p2.next;
            hp = hp.next;
        }
        return head2;
    }

    //better approach - hashmap
    public static Node copyRandomListHashmap(Node head) {
        if(head == null) return null;
        Node head2 = new Node(head.val);
        Node h2p = head2;
        HashMap<Node, Node> hm = new HashMap<>();
        hm.put(head, head2);
        if(head.next == null){
            head2.next = null;
            if(head.random == head) head2.random = head2;
            if(head.random == null) head2.random = null;
        }
        Node hp = head.next;
        while(hp!= null){
            Node newNode = new Node(hp.val);
            h2p.next = newNode;
            h2p = h2p.next;
            hm.put(hp, h2p);
            hp = hp.next;
        }
        hp = head;
        Node p2 = head2;
        while(hp!=null){
            p2.random = hm.get(hp.random);
            p2 = p2.next;
            hp = hp.next;
        }
        return head2;
    }
    public static void main(String[] args) {
        Node head5 = new Node(5, null);
        Node head4 = new Node(4, head5);
        Node head3 = new Node(3, head4);
        Node head2 = new Node(2,head3);
        Node head = new Node(1,head2);
        Node trav = head;
        while(trav != null){
            System.out.print(trav.val + " ");
            trav = trav.next;
        }
        Node ans = copyRandomList(head);
        trav = ans;
        System.out.println();
        while(trav != null){
            System.out.print(trav.val + " ");
            trav = trav.next;
        }
        ans = copyRandomListHashmap(head);
        trav = ans;
        System.out.println();
        while(trav != null){
            System.out.print(trav.val + " ");
            trav = trav.next;
        }
    }
}