package SortingAlgorithms.DivideAndConquer.MergeSort;

import java.util.Arrays;

public class MergeSortUsingReturnTypeFunction {
    public static int[] mergeSort(int[] arr){
        if(arr.length <= 1){
            return arr;
        }
        
        int mid = arr.length/2;
        
        int[] left = mergeSort(Arrays.copyOfRange(arr, 0 , mid));
        int[] right = mergeSort(Arrays.copyOfRange(arr, mid , arr.length));
        
        return merge(left,right);
    }
    public static int[] merge(int[] left, int[] right){
        int i =0;
        int j =0;
        int k =0;
        
        int[] ans = new int[left.length + right.length]; 
        
        while( i < left.length && j < right.length){
            if(left[i] < right[j]){
                ans[k++] = left[i++];
            }else{
                ans[k++] = right[j++];
            }
        }
        
        while(i < left.length){
            ans[k++] = left[i++];
        }
        
        while(j < right.length){
            ans[k++] = right[j++];
        }
        
        return ans;
    }
    public static void main(String[] args) {
        int[] arr = {23,54,12,76,34,1};
        
        int[] ans = mergeSort(arr);
        
        System.out.print(Arrays.toString(ans));
    }
}
