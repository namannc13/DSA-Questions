package DP.Questions2D.StringQuestions.ThreehardQuestions;

import java.util.Arrays;

public class editDistance {
    public static void main(String[] args) {
        char[] arr1 = {'h','o','r','s','e'};    
        char[] arr2 = {'r','o','s'}; 

        System.out.println(editDistanceRecursion(arr1, arr2, arr1.length-1,arr2.length-1));

        int[][] dp = new int[arr1.length][arr2.length];
        for(int i =0 ;i < dp.length; i++){
            Arrays.fill(dp[i], -1);
        }
        System.out.println(editDistanceRecursionDP(arr1, arr2, arr1.length-1,arr2.length-1, dp));
        
        int[][] dp2 = new int[arr1.length+1][arr2.length+1];
        for(int i =0 ;i < dp2.length; i++){
            Arrays.fill(dp2[i], -1);
        }
        System.out.println(editDistanceRecursionDPWithIndexShifting(arr1, arr2, arr1.length-1,arr2.length-1, dp2));
        
        for(int i =0 ;i < dp2.length; i++){
            Arrays.fill(dp2[i], 0);
        }
        System.out.println(editDistanceTabulation(arr1, arr2, dp2));

        System.out.println(editDistanceOptimal(arr1, arr2));

    }

    private static int editDistanceRecursion(char[] arr1, char[] arr2, int index1, int index2) {
        if(index1 < 0) return index2+1;
        if(index2 < 0) return index1+1;

        if(arr1[index1] == arr2[index2]) return 0 + editDistanceRecursion(arr1, arr2, index1-1, index2-1);
        else{
            return Math.min(1+editDistanceRecursion(arr1, arr2, index1, index2-1),Math.min(1+editDistanceRecursion(arr1, arr2, index1-1, index2),1+editDistanceRecursion(arr1, arr2, index1-1, index2-1)));
        }
    }

    private static int editDistanceRecursionDP(char[] arr1, char[] arr2, int index1, int index2, int[][] dp) {
        if(index1 < 0) return index2+1;
        if(index2 < 0) return index1+1;

        if(dp[index1][index2] != -1) return dp[index1][index2];

        if(arr1[index1] == arr2[index2]) return dp[index1][index2] = 0 + editDistanceRecursion(arr1, arr2, index1-1, index2-1);
        else{
            return dp[index1][index2] =  Math.min(1+editDistanceRecursion(arr1, arr2, index1, index2-1),Math.min(1+editDistanceRecursion(arr1, arr2, index1-1, index2),1+editDistanceRecursion(arr1, arr2, index1-1, index2-1)));
        }
    }

    private static int editDistanceRecursionDPWithIndexShifting(char[] arr1, char[] arr2, int index1, int index2, int[][] dp2) {
        if(index1 == 0) return index2;
        if(index2 == 0) return index1;

        if(dp2[index1][index2] != -1) return dp2[index1][index2];

        if(arr1[index1-1] == arr2[index2-1]) return dp2[index1][index2] = 0 + editDistanceRecursion(arr1, arr2, index1-1, index2-1);
        else{
            return dp2[index1][index2] =  Math.min(1+editDistanceRecursion(arr1, arr2, index1, index2-1),Math.min(1+editDistanceRecursion(arr1, arr2, index1-1, index2),1+editDistanceRecursion(arr1, arr2, index1-1, index2-1)));
        }
    }

    private static int editDistanceTabulation(char[] arr1, char[] arr2, int[][] dp2){
        for(int i = 0; i< dp2[0].length; i++){
            dp2[0][i] = i; 
        }
        for(int i = 0; i< dp2.length; i++){
            dp2[i][0] = i; 
        }

        for(int i = 1; i<=arr1.length; i++){
            for(int j = 1; j<=arr2.length; j++){
                if(arr1[i-1] == arr2[j-1]) dp2[i][j] = 0 + dp2[i-1][j-1];
                else{
                    dp2[i][j] =  Math.min(1+dp2[i][j-1],Math.min(1+dp2[i-1][j],1+dp2[i-1][j-1]));
                }
            }
        }
        return dp2[arr1.length][arr2.length];
    }
    
    private static int editDistanceOptimal(char[] arr1, char[] arr2){
        int[] prev = new int[arr2.length+1];
        int[] curr = new int[arr2.length+1];

        for(int i = 0; i< prev.length; i++){
            prev[i] = i; 
        }
        
        for(int i = 1; i<=arr1.length; i++){
            curr[0] = 1;
            for(int j = 1; j<=arr2.length; j++){
                if(arr1[i-1] == arr2[j-1]) curr[j] = 0 + prev[j-1];
                else{
                    curr[j] =  Math.min(1+curr[j-1],Math.min(1+prev[j],1+prev[j-1]));
                }
            }
            prev = Arrays.copyOf(curr, curr.length);
        }
        return prev[arr2.length];
    }
}
