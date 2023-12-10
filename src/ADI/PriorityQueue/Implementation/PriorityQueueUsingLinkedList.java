package ADI.PriorityQueue.Implementation;

class Node{
    int data;
    Node next;
    int priority;
    
    Node(int data, int priority){
        this.data = data;
        this.priority = priority;
        this.next = null;
    }
}

public class PriorityQueueUsingLinkedList {
    Node front;
    
    public PriorityQueueUsingLinkedList(){
        this.front = null;
    }
    
    public void push(int data , int priority){
        Node newNode = new Node(data, priority);
        if(front == null || priority > front.priority){
            newNode.next = front;
            front = newNode;
        }else{
            Node temp = front;
            while(temp.next != null && temp.next.priority > priority){
                temp = temp.next;
            }
            newNode.next = temp.next;
            temp.next = newNode;
        }
    }
    
    public int remove(){
        if(isEmpty()){
            System.out.print("queue is empty");
            return -1;
        }    
        
        int removedData = front.data;
        front = front.next;
        return removedData;
    }
    
    public boolean isEmpty(){
        return front == null;
    }
    
    public void display(){
        if(isEmpty()){
            System.out.print("empty Queue");
        }
        
        Node temp = front;
        while(temp != null){
            System.out.print("(" + temp.data + ", " + temp.priority + ") ");
            temp = temp.next;
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        PriorityQueueUsingLinkedList pq = new PriorityQueueUsingLinkedList();
        
        pq.push(3,1);
        pq.push(5,3);
        pq.push(8,2);
        
        pq.display();
    }
}