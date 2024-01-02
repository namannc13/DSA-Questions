package DP.Questions.Subset_Subsequence;

public class partitionEqualSubsetSum {
    public static void main(String[] args) {
        int[] arr = {100};

        System.out.println(partitionEqualSubsetSumTabulationHelper(arr));
    }

    private static boolean partitionEqualSubsetSumTabulationHelper(int[] arr) {
        int sum = 0;
        for(int i = 0; i< arr.length; i++){
            sum += arr[i];
        }

        if(sum % 2 == 0){ // if the sum is even , then only we will go forward
            int target = sum/2;
            boolean[][] dpBoolean = new boolean[arr.length][target+1]; 
            for(int i = 0; i< dpBoolean.length; i++){
                for(int j = 0; j<= target; j++){
                    dpBoolean[i][j] = false;
                }
            }
            return subsetSumEqualsToTarget.subsetSumEqualsToTargetTabulation(arr, target,dpBoolean); // this will return true or false on the basis of whether the arr has elements which sum up to target ( which is sum/2 )
        }else{
            return false; // if the sum is odd, then there is no way that an array can be devided into two subsets with equal sum
        }
    }
}
