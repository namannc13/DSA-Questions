package ADI.Heap.Implementation;

import java.util.ArrayList;

public class customHeap<T extends Comparable<T>> {
    private ArrayList<T> list;

    public customHeap(){
        list = new ArrayList<>();
    }

    private void swap(int first , int second){
        T temp = list.get(first);
        list.set(first, list.get(second));
        list.set(second,temp);
    }

    private int left(int index){
        return 2 * index + 1;
    }

    private int right(int index){
        return 2 * index + 2;
    }

    private int parent(int index){
        return index/2;
    }

    public void insert(T data){
        list.add(data);
        upheap(list.size() - 1);
    }

    private void upheap(int index) {
        if(index == 0){
            return;
        }
        int p = parent(index);

        if(list.get(index).compareTo(list.get(p)) < 0){
            swap(index, p);
            upheap(p);
        }
    }

    public T remove() throws Exception{
        if(list.isEmpty()){
            throw new Exception("Heap is empty!");
        }

        T temp = list.get(0);

        T last = list.remove(list.size() - 1);
        if(!list.isEmpty()){
            list.set(0,last);
            downheap(0);
        }

        return temp;
    }

    private void downheap(int index) {
        int min = index;
        int left = left(min);
        int right = right(min);

        if(left < list.size() && list.get(min).compareTo(list.get(left)) > 0){
            min = left;
        }

        if(right < list.size() && list.get(min).compareTo(list.get(right)) > 0){
            min = right;
        }

        if(min != index){
            swap(min, index);
            downheap(min);
        }
    }

    public ArrayList<T> heapSort() throws Exception{
        ArrayList<T> data = new ArrayList<>();

        while(!list.isEmpty()){
            data.add(this.remove());
        }

        return data;
    }

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

        int[] arr = {12 ,534, 32, 2, 123};
        for(int i : arr){
            heap.insert(i);
        }
        System.out.println(heap.list);
        System.out.println(heap.heapSort()); // converting an unsorted Array into a min heap
        // System.out.println(heap.heapSort().reversed()); // converting an unsorted Array into a max heap

    }
}
