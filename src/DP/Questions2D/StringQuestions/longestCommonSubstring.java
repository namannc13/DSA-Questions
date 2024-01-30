package DP.Questions2D.StringQuestions;

import java.util.Arrays;

public class longestCommonSubstring {
    public static void main(String[] args) {
        char[] arr1 = {'d','i','g','g','e','r'};    
        char[] arr2 = {'b','i','g','g','e','r','d','i','a','g','r','a','m'};

        int[][] dp = new int[arr1.length+1][arr2.length+1]; // AFTER INDEX SHIFTING TO RIGHT BY ONCE
        for(int i =0; i< dp.length; i++){
            Arrays.fill(dp[i], 0);
        }
        System.out.println(longestCommonSubstringTabulation(arr1, arr2, dp));
    }

    private static int longestCommonSubstringTabulation(char[] arr1, char[] arr2, int[][] dp) {
        for(int i=0; i<= arr2.length; i++){ // we can actually not include these 2 for loops as the array is already initialized to 0's 
            dp[0][i] = 0;
        }
        for(int i=0; i<= arr1.length; i++){ // we can actually not include these 2 for loops as the array is already initialized to 0's 
            dp[i][0] = 0;
        }

        int max = 0;
        for(int i =1; i<= arr1.length; i++){
            for(int j=1; j<= arr2.length; j++){
                if(arr1[i-1] == arr2[j-1]){
                    dp[i][j] = 1 + dp[i-1][j-1]; // match
                    max = Math.max(max, dp[i][j]);
                }
                else dp[i][j] = 0; // not match // in case of finding the substring , just set the value to 0 if it doesn't match
            }
        }
        return max;
    }
}
