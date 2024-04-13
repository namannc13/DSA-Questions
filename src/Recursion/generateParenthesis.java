package Recursion;

import java.util.ArrayList;
import java.util.List;

public class generateParenthesis {
    public static List<String> generateParenthe(int n) {
        List<String> list = new ArrayList<>();
        generateParen(n, 0, 0, list, "");
        return list;
    }
    public static void generateParen(int n, int o, int c, List<String> list, String s){
        if(o>n) return;
        
        if(o == n && c == n){
            list.add(s);
            return;
        }
        generateParen(n, o+1, c, list, s+"(");
        if(c < o) generateParen(n, o, c+1, list, s+")");
    }
    public static void main(String[] args) {
        System.out.println(generateParenthe(3));
    }
}
