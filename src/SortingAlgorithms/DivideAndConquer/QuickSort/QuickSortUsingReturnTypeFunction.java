package SortingAlgorithms.DivideAndConquer.QuickSort;

import java.util.Arrays;

public class QuickSortUsingReturnTypeFunction {
    public static int[] quickSort(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }

        int s = 0;
        int e = arr.length - 1;

        int mid = s + (e - s) / 2;
        int pivot = arr[mid];

        while (s <= e) {
            while (arr[s] < pivot) {
                s++;
            }

            while (arr[e] > pivot) {
                e--;
            }

            if (s <= e) {
                int temp = arr[s];
                arr[s] = arr[e];
                arr[e] = temp;
                s++;
                e--;
            }
        }
       
        // now my pivot is at the correct index
        int[] left = quickSort(Arrays.copyOfRange(arr, 0, s)); // (arr,0,s) -> This ensures that the subarray for the left side includes elements up to (but not including) the pivot index.
        int[] right = quickSort(Arrays.copyOfRange(arr, s, arr.length));

        return merge(left, right);
    }

    public static int[] merge(int[] left, int[] right) {
        int i = 0;
        int j = 0;
        int k = 0;

        int[] ans = new int[left.length + right.length];

        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) {
                ans[k++] = left[i++];
            } else {
                ans[k++] = right[j++];
            }
        }

        while (i < left.length) {
            ans[k++] = left[i++];
        }

        while (j < right.length) {
            ans[k++] = right[j++];
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = { 23, 54, 12, 76, 34, 1 };

        System.out.print(Arrays.toString(quickSort(arr)));
    }
}
