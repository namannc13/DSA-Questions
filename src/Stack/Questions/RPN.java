package Stack.Questions;

import java.util.Stack;

public class RPN {

    public static void main(String[] args) {
        String[] tokens = { "4","13","5","/","+" };
        System.out.print(evalRPN(tokens));
    }

    // Better Solution
    public static int evalRPN(String[] tokens) {
        Stack<Integer> st = new Stack<>();
        for( String token: tokens){
            if(token.equals("+")){
                int one = st.pop();
                int two = st.pop();
                st.push(two+one);
            }
            else if(token.equals("-")){
                int one = st.pop();
                int two = st.pop();
                st.push(two-one);
            }
            else if(token.equals("/")){
                int one = st.pop();
                int two = st.pop();
                if(two == 0 || one == 0){
                    st.push(0);
                }else{
                    st.push(two/one);
                }
            }
            else if(token.equals("*")){
                int one = st.pop();
                int two = st.pop();
                st.push(two*one);
            }
            else{
                st.push(Integer.parseInt(token));
            }
        }
        return st.peek();
    }
}