package DP.Questions2D;

public class ninjaTraining {
    public static void main(String[] args) {
        int[][] arr = { // each index is day and each element in that index signifies the energy for
                        // that particular task
                { 1, 2, 3 }, // day 0 -> 1 energy for task 0, 2 energy for task 1, 3 energy for task 2
                { 4, 5, 6 }, // day 1 -> 4 energy for task 0, 5 energy for task 1, 6 energy for task 2
                { 7, 8, 9 } // day 2 -> 7 energy for task 0, 8 energy for task 1, 9 energy for task 2
        };

        int[][] dp = new int[3][4]; // there can be 4 states possible every nth time, task4 -> if there is one day
                                    // only , then we will have to take the max of all the days
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                dp[i][j] = -1;
            }
        }

        int maxEnergy = ninjaTrainingRecursion(arr, 3, arr.length - 1); // the second parameter indicates the task
                                                                            // we
                                                                            // did on the last call ( 0=task0, 1=task1,
                                                                            // 2=task2, 3=notask(we just started))
        System.out.println(maxEnergy);
        System.out.println(ninjaTrainingRecursionDP(arr, 3, arr.length - 1, dp));
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                dp[i][j] = -1;
            }
        }

        System.out.println(ninjaTrainingTabulation(arr,arr.length-1,dp));
        // for (int i = 0; i < dp.length; i++) {
        //     for (int j = 0; j < dp[i].length; j++) {
        //         System.out.print(dp[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        int[] dp2 = new int[4];
        for(int i = 0 ;i < 4; i ++){
            dp2[i] = -1;
        }
        System.out.println(ninjaTrainingOptimal(arr, arr.length-1, dp2));
    }

    private static int ninjaTrainingRecursion(int[][] arr, int lastTask, int n) {
        if (n == 0) {
            int maxi = Integer.MIN_VALUE;
            for (int i = 0; i < arr[0].length; i++) {
            if (i != lastTask) {
                maxi = Math.max(maxi, arr[0][i]);
            }
        }
        return maxi;
        }

        int maxi = Integer.MIN_VALUE;
        for (int i = 0; i < arr[n].length; i++) {
            if (i != lastTask) { // only two tasks will get through this condition , so we need to check both the
            
            // possibility // Here, lastTask = 3, so this loop will run for all the three
            // tasks on the first time
                int points = arr[n][i] + ninjaTrainingRecursion(arr, i, n - 1);
                maxi = Math.max(maxi, points);
            }
        }

        return maxi;
    }

    private static int ninjaTrainingRecursionDP(int[][] arr, int lastTask, int n, int[][] dp) {
        if (n == 0) {
            int maxi = Integer.MIN_VALUE;
            for (int i = 0; i < arr[0].length; i++) {
                if (i != lastTask) {
                    maxi = Math.max(maxi, arr[0][i]);
                }
            }
            return maxi;
        }

        if (dp[n][lastTask] != -1)
            return dp[n][lastTask]; // dp[2][0]

        int maxi = Integer.MIN_VALUE;
        for (int i = 0; i < 3; i++) {
            if (i != lastTask) { // only two tasks will get through this condition , so we need to check both the
                                 // possibility // Here, lastTask = 3, so this loop will run for all the three
                                 // tasks on the first time
                int points = arr[n][i] + ninjaTrainingRecursionDP(arr, i, n - 1, dp);
                maxi = Math.max(maxi, points);
            }
        }

        return dp[n][lastTask] = maxi; // dp[2][0] = x
    }

    private static int ninjaTrainingTabulation(int[][] arr, int n, int[][] dp){
        dp[0][0] = Math.max(arr[0][1],arr[0][2]);
        dp[0][1] = Math.max(arr[0][0],arr[0][2]);
        dp[0][2] = Math.max(arr[0][0],arr[0][1]);
        dp[0][3] = Math.max(Math.max(arr[0][0],arr[0][1]),arr[0][2]);

        for(int i = 1; i <= n; i++){
            for(int last = 0; last<4; last++){ // the last task 
                dp[i][last] = 0;

                for(int task = 0; task<3; task++){ // same loop from the above recursion function except 'i' is 'task' here
                    if(task != last){
                        int points = arr[i][task] + dp[i-1][task];
                        //arr[i][task] + dp[i-1,task];
                        dp[i][task] = Math.max(dp[i][task], points);
                    }
                }
            }
        }
        return dp[n][2];
    }

    private static int ninjaTrainingOptimal(int[][] arr, int n, int[] prev){
        prev[0] = Math.max(arr[0][1],arr[0][2]);
        prev[1] = Math.max(arr[0][0],arr[0][2]);
        prev[2] = Math.max(arr[0][0],arr[0][1]);
        prev[3] = Math.max(Math.max(arr[0][0],arr[0][1]),arr[0][2]);

        for(int i = 1; i <= n; i++){
            int[] temp = new int[prev.length];
            for(int last = 0; last<4; last++){ // the last task 
                temp[last] = 0;

                for(int task = 0; task<3; task++){ // same loop from the above recursion function except 'i' is 'task' here
                    if(task != last){
                        // int points = arr[i][task] + dp[i-1][task];
                        //arr[i][task] + dp[i-1,task];
                        temp[task] = Math.max(temp[task], arr[i][task] + prev[task]);
                    }
                }
            }
            prev = temp;
        }
        return prev[2];

    }
}
