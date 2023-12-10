package ADI.PriorityQueue;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueMain {
    public static void main(String[] args) {
        int[] arr ={2,6,9,4,3,7};
        PriorityQueue<Integer> p = new PriorityQueue<>(Comparator.reverseOrder());
        
        for(int i: arr){
            p.add(i);
        }
        
        System.out.println();
        System.out.print(p); // is not stored in a decreased sorted manner but when we poll() it one by one , it comes out in a decreasing sorted manner 
        System.out.println();
        
        for(int i =0; i< arr.length; i++){
            System.out.print(p.poll() + " "); // when we poll() it one by one , it comes out in a decreasing sorted manner 
        }
        
    }    
}

