package DP.Questions2D.StringQuestions.ThreehardQuestions;

import java.util.Arrays;

public class distinctSubsequences {
    public static void main(String[] args) {
        char[] arr1 = {'b','a','b','g','b','a','g'};    
        char[] arr2 = {'b','a','g'}; 

        System.out.println(distinctSubsequencesRecursion(arr1, arr2, arr1.length-1, arr2.length-1));

        int[][] dp = new int[arr1.length][arr2.length];
        for(int i = 0; i < arr1.length; i++){
            Arrays.fill(dp[i],-1);
        }
        System.out.println(distinctSubsequencesRecursionDP(arr1, arr2, arr1.length-1, arr2.length-1, dp));

        int[][] dp2 = new int[arr1.length+1][arr2.length+1];
        for(int i = 0; i <= arr1.length; i++){
            Arrays.fill(dp2[i],-1);
        }
        System.out.println(distinctSubsequencesRecursionDPWithIndexShifting(arr1, arr2, arr1.length, arr2.length, dp2));

        for(int i = 0; i <= arr1.length; i++){
            Arrays.fill(dp2[i],0);
        }
        System.out.println(distinctSubsequencesTabulation(arr1, arr2, dp2));

        System.out.println(distinctSubsequencesOptimal(arr1, arr2));

        System.out.println(distinctSubsequencesBest(arr1, arr2));
    }

    private static int distinctSubsequencesRecursion(char[] arr1, char[] arr2, int index1, int index2) {
        if(index2 < 0) return 1;
        if(index1 < 0) return 0;

        if(arr1[index1] == arr2[index2]){
            return distinctSubsequencesRecursion(arr1, arr2, index1-1, index2-1) + distinctSubsequencesRecursion(arr1, arr2, index1-1, index2);
        }else{
            return distinctSubsequencesRecursion(arr1, arr2, index1-1, index2);
        }
    }

    private static int distinctSubsequencesRecursionDP(char[] arr1, char[] arr2, int index1, int index2,int[][] dp) {
        if(index2 < 0) return 1;
        if(index1 < 0) return 0;
    
        if(dp[index1][index2] != -1) return dp[index1][index2];

        if(arr1[index1] == arr2[index2]){
            return dp[index1][index2] = distinctSubsequencesRecursion(arr1, arr2, index1-1, index2-1) + distinctSubsequencesRecursion(arr1, arr2, index1-1, index2);
        }else{
            return dp[index1][index2] = distinctSubsequencesRecursion(arr1, arr2, index1-1, index2);
        }
    }

    private static int distinctSubsequencesRecursionDPWithIndexShifting(char[] arr1, char[] arr2, int index1, int index2,int[][] dp) {
        if(index2 == 0) return 1;
        if(index1 == 0) return 0;
    
        if(dp[index1][index2] != -1) return dp[index1][index2];

        if(arr1[index1-1] == arr2[index2-1]){
            return dp[index1][index2] = distinctSubsequencesRecursionDPWithIndexShifting(arr1, arr2, index1-1, index2-1,dp) + distinctSubsequencesRecursionDPWithIndexShifting(arr1, arr2, index1-1, index2, dp);
        }else{
            return dp[index1][index2] = distinctSubsequencesRecursionDPWithIndexShifting(arr1, arr2, index1-1, index2, dp);
        }
    }

    private static int distinctSubsequencesTabulation(char[] arr1, char[] arr2, int[][] dp){
        for(int i = 0; i<= arr1.length; i++){
            dp[i][0] = 1;
        }

        for(int i= 1; i<= arr1.length; i++){
            for(int j = 1; j<=arr2.length; j++){
                if(arr1[i-1] == arr2[j-1]){
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[arr1.length][arr2.length];
    }

    private static int distinctSubsequencesOptimal(char[] arr1, char[] arr2){
        int[] prev = new int[arr2.length+1];
        Arrays.fill(prev,0);
        int[] curr = new int[arr2.length+1];
        Arrays.fill(curr,0);
        
        prev[0] = 1;
        curr[0] = 1;

        for(int i= 1; i<= arr1.length; i++){
            for(int j = 1; j<=arr2.length; j++){
                if(arr1[i-1] == arr2[j-1]){
                    curr[j] = prev[j-1] + prev[j];
                }else{
                    curr[j] = prev[j];
                }
            }
            prev = Arrays.copyOf(curr, curr.length);
        }
        return prev[arr2.length];
    }

    private static int distinctSubsequencesBest(char[] arr1, char[] arr2){
        int[] prev = new int[arr2.length+1];
        Arrays.fill(prev,0);
        
        prev[0] = 1;

        for(int i= 1; i<= arr1.length; i++){
            for(int j = arr2.length; j>=1; j--){
                if(arr1[i-1] == arr2[j-1]){
                    prev[j] = prev[j-1] + prev[j];
                } 
            }
        }
        return prev[arr2.length];
    }
}
