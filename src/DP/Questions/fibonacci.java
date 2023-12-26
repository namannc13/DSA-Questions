package DP.Questions;

public class fibonacci{
    public static int fibonacciNumberRecursion(int n){ // Using Recursion 
        if(n<=1){
            return n;
        }
        return fibonacciNumberRecursion(n-1) + fibonacciNumberRecursion(n-2);
    }

    // Recursion to DP 
    // Step 1 -> make a dp array 
    // Step 2 -> store ans in the dp array on the go -> dp[n] = fibonacciNumberDPMemoization(n-1, dp) + fibonacciNumberDPMemoization(n-2, dp)
    // Step 3 -> check whether we have the value for this call dp array already stored or not -> if(dp[n] != -1) return dp[n]

    // O(n) TC and O(n)+O(n) SC ( SC -> recursion stack memory and dp array )
    public static int fibonacciNumberDPMemoization(int n,int[] dp){ // Using Dynamic Programming // Memoization 
        if(n<=1){
            return n;
        }

        if(dp[n] != -1){
            return dp[n];
        }

        return dp[n] = fibonacciNumberDPMemoization(n-1, dp) + fibonacciNumberDPMemoization(n-2, dp);
    }

    // O(n) TC and O(n) SC ( SC -> dp array )
    public static int fibonacciNumberDPTabulation(int n,int[] dp){
        // If you notice, these two are just the base conditions of Memoization
        dp[0] = 0; 
        dp[1] = 1;

        // Tabulation -> bottom up
        for(int i = 2; i <= n; i ++){
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }

    // O(n) TC and O(1) SC
    public static int fibonacciNumberDPEvenBetter(int n){
        // Using pointers instead of using an array
        int prev2 = 0; 
        int prev1 = 1;
        int curi = prev1 + prev2;

        for(int i = 2; i <= n; i ++){
            curi = prev1 + prev2;
            prev2 = prev1;
            prev1 = curi;
        }

        return prev1;
    }

    public static void main(String[] args) {
        System.out.println(fibonacciNumberRecursion(6));

        int[] dp = new int[7];
        for(int i = 0 ; i < dp.length; i++){
            dp[i]= -1;
        }
        System.out.println(fibonacciNumberDPMemoization(6,dp));

        int[] dp2 = new int[7];
        System.out.println(fibonacciNumberDPTabulation(6, dp2));

        System.out.println(fibonacciNumberDPEvenBetter(6));
    }
}