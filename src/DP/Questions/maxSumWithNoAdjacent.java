// Also knows as the famous House Robber question
package DP.Questions;

public class maxSumWithNoAdjacent {
    public static int maxSumWithNoAdjacentRecursion(int[] arr, int n) {
        if (n == 0)
            return arr[n]; // if n is 0, then it means it skipped the n==1 part right ? so we have to take
                           // it in the sum so that's why we return it
        if (n < 0)
            return 0; // for n==-1

        int pick = arr[n] + maxSumWithNoAdjacentRecursion(arr, n - 2); // if we picked the current element , then we
                                                                       // don't have to go the previous index as it will
                                                                       // be adjacent so we cannot pick it anyway
        int notPick = 0 + maxSumWithNoAdjacentRecursion(arr, n - 1); // if we didn't pick it , then we can go to the
                                                                     // previous index as we can pick it

        return Math.max(pick, notPick);
    }

    public static int maxSumWithNoAdjacentDP(int[] arr, int n, int[] dp) {
        if (n == 0)
            return arr[n]; // if n is 0, then it means it skipped the n==1 part right ? so we have to take
                           // it in the sum so that's why we return it
        if (n < 0)
            return 0; // for n==-1

        if (dp[n] != -1)
            return dp[n];

        int pick = arr[n] + maxSumWithNoAdjacentRecursion(arr, n - 2); // if we picked the current element , then we
                                                                       // don't have to go the previous index as it will
                                                                       // be adjacent so we cannot pick it anyway
        int notPick = 0 + maxSumWithNoAdjacentRecursion(arr, n - 1); // if we didn't pick it , then we can go to the
                                                                     // previous index as we can pick it

        return dp[n] = Math.max(pick, notPick);
    }

    public static int maxSumWithNoAdjacentTabulation(int[] arr, int n, int[] dp) {
        dp[0] = arr[0];

        for (int i = 1; i <= n; i++) { // ( n == arr.length -1 )
            int notPick = 0 + dp[i - 1];
            int pick = 0;
            if ((i - 2) >= 0) // handling i==-1 case
                pick = dp[i - 2] + arr[i];

            dp[i] = Math.max(pick, notPick);
        }

        return dp[n];
    }

    public static int maxSumWithNoAdjacentOptimal(int[] arr, int n) {
        int prev2 = 0;
        int prev = arr[0];
        int curi = 0;

        for (int i = 1; i <= n; i++) {
            int notPick = 0 + prev;
            int pick = 0;
            pick = prev2 + arr[i];
            curi = Math.max(pick,notPick);

            prev2 = prev;
            prev = curi;
        }
        return prev;
    }

    public static void main(String[] args) {
        int[] arr = { 2, 1, 4, 9 };
        System.out.println(maxSumWithNoAdjacentRecursion(arr, arr.length - 1));
        int[] dp = new int[arr.length + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = -1;
        }
        System.out.println(maxSumWithNoAdjacentDP(arr, arr.length - 1, dp));
        for (int i = 0; i < dp.length; i++) {
            dp[i] = -1;
        }
        System.out.println(maxSumWithNoAdjacentTabulation(arr, arr.length - 1, dp));
        System.out.println(maxSumWithNoAdjacentOptimal(arr, arr.length-1));
    }
}


