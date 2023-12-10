package SortingAlgorithms.DivideAndConquer.QuickSort;

import java.util.Arrays;

public class QuickSortUsingVoidTypeFunction {
    public static void quickSort(int[] arr , int low, int hi){
        if(low >= hi){
            return ;
        }
        
        int s = low;
        int e = hi;
        
        int mid = s + ( e -s )/2;
        int pivot = arr[mid];
        
        while(s <= e){
            while(arr[s] < pivot){
                s++;
            }
            
            while(arr[e] > pivot){
                e--;
            }
            
            if(s<=e){
                int temp = arr[s];
                arr[s] = arr[e];
                arr[e]= temp;
                s++;
                e--;
            }
        }
        
        //now my pivot is at the correct index
        quickSort(arr,low,e);
        quickSort(arr,s,hi);
    }
    public static void main(String[] args) {
        int[] arr = {23,54,12,76,34,1};
        
        quickSort(arr, 0, arr.length-1);
        
        System.out.print(Arrays.toString(arr));
    }
}

