package DP.Questions2D.StringQuestions;

import java.util.Arrays;

public class printLongestCommonSubstring {
    public static void main(String[] args) {
        String a = "aacabdkacaa";
        StringBuilder a2 = new StringBuilder(a);
        String b = a2.reverse().toString();
        char[] arr1 = a.toCharArray();  
        char[] arr2 = b.toCharArray();

        int[][] dp = new int[arr1.length+1][arr2.length+1]; // AFTER INDEX SHIFTING TO RIGHT BY ONCE
        for(int i =0; i< dp.length; i++){
            Arrays.fill(dp[i], 0);
        }
        System.out.println(longestCommonSubstringTabulation(arr1, arr2, dp));
    }

    private static String longestCommonSubstringTabulation(char[] arr1, char[] arr2, int[][] dp) {
        for(int i=0; i<= arr2.length; i++){ // we can actually not include these 2 for loops as the array is already initialized to 0's 
            dp[0][i] = 0;
        }
        for(int i=0; i<= arr1.length; i++){ // we can actually not include these 2 for loops as the array is already initialized to 0's 
            dp[i][0] = 0;
        }

        int max = 0;
        int index1 = 0;
        int index2 = 0;
        for(int i =1; i<= arr1.length; i++){
            for(int j=1; j<= arr2.length; j++){
                if(arr1[i-1] == arr2[j-1]){
                    dp[i][j] = 1 + dp[i-1][j-1]; // match
                    if(dp[i][j] > max){
                        max = dp[i][j];
                        index1 = i;
                        index2 = j;
                    }
                }
                else dp[i][j] = 0; // not match // in case of finding the substring , just set the value to 0 if it doesn't match
            }
        }
        for(int i =0; i< dp.length; i++){
            System.out.println(Arrays.toString(dp[i]));
        }
        
        
        String ans = "";

        while(index1 > 0 && index2 >0 && dp[index1-1][index2-1] == dp[index1][index2] -1){
            System.out.println(index1);
        System.out.println(index2);
            ans += arr1[index1-1];
            index1--;
            index2--;
        }
        
        StringBuilder reversedString = new StringBuilder(ans);
        reversedString.reverse();
        return reversedString.toString();
    }
}
