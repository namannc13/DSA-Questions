package Stack.Questions;

import java.util.Stack;

public class RPN {

    public static void main(String[] args) {
        String[] tokens = { "4","13","5","/","+" };
        System.out.print(evalRPN(tokens));
    }

    // Better Solution
    private static int evalRPN(String[] tokens) {
        Stack<Integer> st = new Stack<>();
        for (String token : tokens) {
            if (token.equals("+") || token.equals("-") || token.equals("/") || token.equals("*")) {
                int one = st.pop();
                int two = st.pop();

                if (token.equals("+")) {
                    st.push(one + two);
                } else if (token.equals("-")) {
                    st.push(two - one); // keep in mind the order 
                } else if (token.equals("/")) {
                    if (one < 0 || two < 0) { // while dividing , if one number is negative , the ans is 0 ( leetcode me
                                              // likha tha )
                        st.push(0);
                    } else {
                        st.push(two / one);
                    }
                } else {
                    st.push(one * two);
                }
            } else {
                st.push(Integer.parseInt(token));
            }
        }
        return st.peek();
    }

    // My solution
    // public static int evalRPN(String[] tokens) {
    //     Stack<Integer> st = new Stack<>();
    //     int ans = 0;
    //     for(String token : tokens){
    //         if(token.equals("+") || token.equals("-") ||token.equals("/") || token.equals("*")){
    //             if(ans == 0 && st.size()>1){ // only letting this loop run if this is the first iteration of the program
    //                 int one = st.pop();
    //                 int two = st.pop();
    //                 if(token == "+"){
    //                     ans = one + two;
    //                 }else if(token == "-"){
    //                     ans = two - one; // keep in mind the order
    //                 }else if(token == "/"){
    //                     if(one < 0 || two < 0){ // while dividing , if one number is negative , the ans is 0
        
    //                         ans = 0;
    //                     }else{
    //                         ans = two/ one; // keep in mind the order
    //                     }
    //                 }else{
    //                     ans = one*two;
    //                 }
    //             }else{ // if this is not the first iteration of the program , then this will run ( everytime but once )
        
    //                 int num = st.pop();
    //                 if(token.equals("+")){
    //                     ans += num;
    //                 }else if(token.equals("-")){
    //                     ans -= num;
    //                 }else if(token.equals("/")){
    //                     if(num < 0 || ans < 0){ // while dividing , if one number is negative , the ans is 0
        
    //                     ans = 0;
    //                     }else{
    //                         ans /= num;
    //                     }
    //                 }else{
    //                     ans *= num;
    //                 }
    //             }
    //         }else{
    //             st.push(Integer.parseInt(token)); // converts an String to an int
    //         }
    //     }
    //     return ans;

    // }
}