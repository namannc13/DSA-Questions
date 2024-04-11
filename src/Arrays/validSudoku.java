package Arrays;

import java.util.HashSet;

public class validSudoku{
    public static boolean isValidSudoku(char[][] board) {
        return hasValidCol(board) && hasValidRow(board) && isValidBox(board);
    }
    public static boolean hasValidCol(char[][] board){
        for(int i = 0; i < board.length; i++){
            if(!isValidSubBox(board, i, i+1, 0, board.length)) return false;
        }
        return true;
    }
    public static boolean hasValidRow(char[][] board){
        for(int i = 0; i < board.length; i++){
            if(!isValidSubBox(board, 0, board.length, i, i+1)) return false;
        }
        return true;
    }
    public static boolean isValidBox(char[][] board){
        for(int i=0, j=3; i<=board.length && j<=board.length; i+=3, j+=3){
            for(int x=0, y=3; x<=board.length && y<=board.length; x+=3, y+=3){
                if(!isValidSubBox(board, i, j, x, y)) return false;
            }
        }
        return true;
    }
    public static boolean isValidSubBox(char[][] board, int ri, int rj, int ci, int cj){
        HashSet<Character> hs = new HashSet<>();
        int count = 0;
        for(int i = ri; i < rj; i++){
            for(int j = ci; j < cj; j++){
                if(board[i][j] != '.'){
                    hs.add(board[i][j]);
                    count++;
                }
            }
        }
        if(hs.size() != count) return false;
        return true;
    }
    public static void main(String[] args) {
        char[][] board =
{{'5','3','.','.','7','.','.','.','.'}
,{'6','.','.','1','9','5','.','.','.'}
,{'.','9','8','.','.','.','.','6','.'}
,{'8','.','.','.','6','.','.','.','3'}
,{'4','.','.','8','.','3','.','.','1'}
,{'7','.','.','.','2','.','.','.','6'}
,{'.','6','.','.','.','.','2','8','.'}
,{'.','.','.','4','1','9','.','.','5'}
,{'.','.','.','.','8','.','.','7','9'}};
        System.out.println(isValidSudoku(board));
    }
}