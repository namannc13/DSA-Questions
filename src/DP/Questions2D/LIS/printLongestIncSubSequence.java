package DP.Questions2D.LIS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class printLongestIncSubSequence {
    public static void main(String[] args) {
        int[] arr = { 22, 9 , 5, 12, 15, 9, 21};

        System.out.println(printLongestIncSubSequenceBest(arr));
    }

    private static ArrayList<Integer>  printLongestIncSubSequenceBest(int[] arr) {
        int[] dp = new int[arr.length];
        int[] hash = new int[arr.length];
        Arrays.fill(dp,1);

        int maxi = 0;
        int lastInd = 0;
        for(int i= 0; i< arr.length; i++){
            hash[i] =i;
            for(int j = 0; j< i; j++){
                if(arr[j] < arr[i] && 1+ dp[j] > dp[i]){
                    dp[i] = 1 + dp[j];
                    hash[i] = j;
                }
            }
            if(dp[i] > maxi){
                maxi = dp[i];
                lastInd = i;
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i<maxi; i++){
            list.add(arr[lastInd]);
            lastInd = hash[lastInd];
        }
        Collections.reverse(list);
        return list;
    }
}
