package Stack.Questions;

import java.util.Scanner;
import java.util.Stack;

public class ValidParenthesis {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input= sc.nextLine();
        System.out.println(ValidParenthesisOrNot(input));

        sc.close();
    }

    private static boolean ValidParenthesisOrNot(String input) {
        Stack<Character> st = new Stack<>();
        for(char ch : input.toCharArray()){
            if(ch == '(' || ch == '{' || ch == '[' ){
                st.push(ch);
            }else{
                if(ch == ')' && (st.isEmpty() || st.pop() != '(')){
                    return false;
                }
                if(ch == '}' && (st.isEmpty() || st.pop() != '{')){
                        return false;
                }
                if(ch == ']' && (st.isEmpty() || st.pop() != '[')){
                        return false;
                }
            }
        }
        return st.isEmpty();
    }
}
