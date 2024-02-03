package DP.Questions.Subset_Subsequence;

import java.util.Arrays;

public class partitionASetIntoTwoSubsetsWithMinAbsSumDifference {
    public static void main(String[] args) {
        int[] arr = {2,3,7};

        System.out.println(partitionASetIntoTwoSubsetsWithMinAbsSumDifferenceTabulationHelper(arr));

        System.out.println(partitionASetIntoTwoSubsetsWithMinAbsSumDifferenceOptimalHelper(arr));
    }

    private static int partitionASetIntoTwoSubsetsWithMinAbsSumDifferenceTabulationHelper(int[] arr) {
        int sum = 0;
        for(int i = 0; i< arr.length; i++){
            sum += arr[i];
        }

        boolean[][] dpBoolean = new boolean[arr.length][sum+1]; 
        for(int i = 0; i< dpBoolean.length; i++){
            for(int j = 0; j<= sum; j++){
                dpBoolean[i][j] = false;
            }
        }

        subsetSumEqualsToTargetTabulation(arr, sum, dpBoolean); // when dpBoolean returns , it will have all the answers in the last row ( answers -> from 1 to sum , the elements of arr can form what sum? )( for ex -> 2 and 3 can form 5 so the value 5(col side) in the last row of dpBoolean will be true )

        for(int i=0; i< dpBoolean.length; i++){
            System.out.println(Arrays.toString(dpBoolean[i]));
        }

        int answer = Integer.MAX_VALUE;
        for(int i = 0; i< dpBoolean[dpBoolean.length-1].length/2 + 1; i++){ // now we will traverse through all the values and pick out which values are valid ( a vlaue is valid if the elements of the array can form that value by addition )
            if(dpBoolean[dpBoolean.length-1][i]){ // means it is valid
                int sum1 = i;
                int sum2 = sum - sum1; // if the sum of first subset is 1, and the total sum is 12. then we are finding the sum of the second subset here which will be 11
                answer = Math.min(answer, Math.abs(sum1-sum2)); // finding whether the difference of these pair of subset sums are minimum or not than the answer ( we have to find the minimum difference in the sum of both subsets )
            }
        }
        return answer;

        // dpBoolean[dpBoolean.length-1].length/2 (9 lines above) BECAUSE if the arr is 2,3,7 then the sum/target will be 12
        // valid elements from the last row of dpBoolean will be 
        // 0  2  3 5 7 9 10 12 -> sum1 ( i )
        // 12 10 9 7 5 3 2  0  -> sum2 
        // 12 8  6 2 2 6 8  12 -> the absolute difference

        // SEE the pattern above , that is why we only need to traverse half of the dpBoolean last row and we will get our answer
    }
    static void subsetSumEqualsToTargetTabulation(int[] arr,int target, boolean[][] dpBoolean){ // i took this function from subsetSumEqualsToTarget file
        for(int i = 0; i< arr.length; i++){
            dpBoolean[i][0] = true;
        }

        if(arr[0] <= target) dpBoolean[0][arr[0]] = true; 

        for(int i = 1; i< arr.length; i++){ // for index
            for(int j = 1; j <= target; j++){ // for target
                boolean notPick = dpBoolean[i-1][j];
                boolean Pick = false; 
                if(j >= arr[i]){ 
                    Pick = dpBoolean[i-1][j - arr[i]];
                }
                dpBoolean[i][j] = notPick || Pick;
            }
        } 
    }

    public static int partitionASetIntoTwoSubsetsWithMinAbsSumDifferenceOptimalHelper(int[] arr){
        int sum = 0;
        for(int i = 0; i< arr.length; i++){
            sum += arr[i];
        }

        Boolean[] prev = new Boolean[sum+1];
        Arrays.fill(prev, false);
        Boolean[] curr = new Boolean[sum+1];
        Arrays.fill(curr, false);

        prev[0] = curr[0] = true;

        subsetSumEqualsToTargetOptimal(arr, sum, prev, curr);

        for(int i=0; i< curr.length; i++){
            System.out.print(curr[i] + " ");
        }

        int answer = Integer.MAX_VALUE;
        for(int i = 0; i<= sum/2; i++){ // now we will traverse through all the values and pick out which values are valid ( a vlaue is valid if the elements of the array can form that value by addition )
            if(curr[i]){ // means it is valid
                int sum1 = i;
                int sum2 = sum - sum1; // if the sum of first subset is 1, and the total sum is 12. then we are finding the sum of the second subset here which will be 11
                answer = Math.min(answer, Math.abs(sum1-sum2)); // finding whether the difference of these pair of subset sums are minimum or not than the answer ( we have to find the minimum difference in the sum of both subsets )
            }
        }
        return answer;
    }
    static void subsetSumEqualsToTargetOptimal(int[] arr,int target,Boolean[] prev, Boolean[] curr){
        if(arr[0] <= target) prev[arr[0]] = true; // (arr[0] <= target) for cases when the arr is {100} .and we sent the target as half the sum plus 1 (50 + 1)

        for(int i = 1; i< arr.length; i++){ // for index
            for(int j = 1; j <= target; j++){ // for target
                boolean notPick = prev[j];
                boolean Pick = false; 
                if(j >= arr[i]){ 
                    Pick = prev[j - arr[i]];
                }
                curr[j] = notPick || Pick;
            }
            prev = Arrays.copyOf(curr, curr.length);
        }
    }
}
