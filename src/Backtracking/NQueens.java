package Backtracking;

import java.util.ArrayList;
import java.util.List;

public class NQueens {
    public List<List<String>> solveNQueens(int n) {
        int[][] arr = new int[n][n];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = 0;
            }
        }
        List<List<String>> list = new ArrayList<>();
        List<String> curr = new ArrayList<>();
        NQueen(arr, list, curr, 0);
        return list;
    }

    public void NQueen(int[][] arr, List<List<String>> list, List<String> curr, int col) {
        if (col >= arr.length) {
            list.add(new ArrayList<>(curr));
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (isSafe(arr, i, col)) {
                arr[i][col] = 1;
                StringBuilder n = new StringBuilder(); // Using StringBuilder for efficient string manipulation
                for (int j = 0; j < arr.length; j++) { // Iterate over each column to construct the row
                    if (j == i) {
                        n.append("Q");
                    } else {
                        n.append(".");
                    }
                }
                curr.add(n.toString());
                NQueen(arr, list, curr, col + 1);
                curr.remove(curr.size() - 1);
                arr[i][col] = 0;
            }
        }
    }

    public Boolean isSafe(int[][] arr, int row, int col) {
        int i, j;
        for (i = 0; i < col; i++) {
            if (arr[row][i] == 1)
                return false;
        }
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (arr[i][j] == 1)
                return false;
        }
        for (i = row, j = col; i < arr.length && j >= 0; i++, j--) {
            if (arr[i][j] == 1)
                return false;
        }
        return true;
    }
}
