package ADI.Heap.Implementation;

public class HeapMain {
    public static void main(String[] args) throws Exception{
        customHeap<Integer> heap = new customHeap<>();

        // heap.insert(10);
        // heap.insert(20);
        // heap.insert(15);
        // heap.insert(30);
        // heap.insert(25);

        // System.out.println(heap.remove());
        // System.out.println(heap.remove());
        // System.out.println(heap.remove());
        // System.out.println(heap.remove());
        // System.out.println(heap.remove());
        // System.out.println(heap.remove());

        // System.out.println(heap.heapSort());

        int[] arr = {4,1,3,2,16,9,10,14,8,7};
        for(int i : arr){
            heap.insert(i);
        }
        System.out.println(heap.heapSort()); // converting an unsorted Array into a min heap
        System.out.println(heap.heapSort().reversed()); // converting an unsorted Array into a max heap

    }
}
