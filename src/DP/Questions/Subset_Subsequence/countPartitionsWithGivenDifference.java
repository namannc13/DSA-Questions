package DP.Questions.Subset_Subsequence;

import java.util.Arrays;

public class countPartitionsWithGivenDifference {
    public static void main(String[] args) {
        int[] arr = {5,2,6,4};
        int difference = 3;
        System.out.println(countPartitionsWithGivenDifferenceRecursionHelper(arr, arr.length-1, difference));
        System.out.println(countPartitionsWithGivenDifferenceRecursionDPHelper(arr, arr.length-1, difference));
        System.out.println(countPartitionsWithGivenDifferenceTabulationHelper(arr, difference));
        System.out.println(countPartitionsWithGivenDifferenceOptimalHelper(arr, difference));
    }

    private static int countPartitionsWithGivenDifferenceRecursionHelper(int[] arr, int index, int difference) {
        int sum = 0; 
        for(int i : arr){
            sum += i;
        }
        if(sum-difference < 0) return 0;
        if((sum-difference) % 2 != 0) return 0;
        return countOfSubsetsWithSumKIncludingZeroes.countOfSubsetsWithSumKIncludingZeroesRecursion(arr, index, (sum-difference)/2);
    }

    private static int countPartitionsWithGivenDifferenceRecursionDPHelper(int[] arr, int index, int difference) {
        int sum = 0; 
        for(int i : arr){
            sum += i;
        }
        if(sum-difference < 0) return 0;
        if((sum-difference) % 2 != 0) return 0;
        int[][] dp = new int[arr.length][((sum-difference)/2)+1];
        for(int i =0; i < dp.length; i++){
            Arrays.fill(dp[i], -1);
        }
        return countOfSubsetsWithSumKIncludingZeroes.countOfSubsetsWithSumKIncludingZeroesRecursionDP(arr, index, (sum-difference)/2,dp);
    }

    private static int countPartitionsWithGivenDifferenceTabulationHelper(int[] arr,int difference) {
        int sum = 0; 
        for(int i : arr){
            sum += i;
        }
        if(sum-difference < 0) return 0;
        if((sum-difference) % 2 != 0) return 0;
        int[][] dp = new int[arr.length][((sum-difference)/2)+1];
        for(int i =0; i < dp.length; i++){
            Arrays.fill(dp[i], 0);
        }
        return countOfSubsetsWithSumKIncludingZeroes.countOfSubsetsWithSumKIncludingZeroesTabulation(arr, (sum-difference)/2,dp);
    }
    
    private static int countPartitionsWithGivenDifferenceOptimalHelper(int[] arr,int difference) {
        int sum = 0; 
        for(int i : arr){
            sum += i;
        }
        if(sum-difference < 0) return 0;
        if((sum-difference) % 2 != 0) return 0;
        return countOfSubsetsWithSumKIncludingZeroes.countOfSubsetsWithSumKIncludingZeroesOptimal(arr, (sum-difference)/2);
    }

}
