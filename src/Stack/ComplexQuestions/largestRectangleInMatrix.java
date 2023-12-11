package Stack.ComplexQuestions;

import java.util.Scanner;

public class largestRectangleInMatrix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int rowSize = sc.nextInt();
        int colSize = sc.nextInt();

        int[][] arr = new int[rowSize][colSize];

        for(int i = 0; i < rowSize; i ++){
            for(int j =0; j < colSize; j++){
                arr[i][j] = sc.nextInt();
            }
        }

        int answer = largestRectangle(rowSize, colSize, arr);

        System.out.println(answer);

        sc.close();
    }

    private static int largestRectangle(int rowSize, int colSize, int[][] arr) {
        //get the largest Rectangle in the first row
        int max = largestRectangleInHistogram.largestRectangularAreaInHistogram(arr[0], colSize); // Using the function largestRectangularAreaInHistogram from the largestRectangleInHistogram.java file

        for(int i = 1; i < rowSize; i++){
            for(int j = 0; j < colSize; j++){
                if(arr[i][j]!=0){
                    arr[i][j] += arr[i-1][j]; // updating this row by adding the values of the row above this one so that it seems like a graph
                }
            }

            // this row of the matrix is modified with new added values and is ready
            int newMax = largestRectangleInHistogram.largestRectangularAreaInHistogram(arr[i], colSize);
            max = Math.max(max, newMax);
        }

        return max;
    }
}
