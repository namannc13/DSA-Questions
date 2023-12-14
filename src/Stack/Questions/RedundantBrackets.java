package Stack.Questions;

import java.util.Scanner;
import java.util.Stack;

public class RedundantBrackets {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s= sc.nextLine();

        System.out.println(isRedundant(s));
        sc.close();
    }

    private static boolean isRedundant(String s) {
        Stack<Character> st = new Stack<>();
        char[] sArr = s.toCharArray();
        for(char c : sArr){
            if(c == '(' || c == '*' || c == '/' || c == '_' || c == '+'){
                st.push(c);
            }else{
                if(c == ')'){
                    boolean redundant = true;

                    while(st.peek() != '('){ // as there are a or b or other alphabets so we are using this loop
                        char peek = st.peek();
                        if(peek == '*' || peek == '/' || peek == '_' || peek == '+'){
                            redundant = false;
                        }
                        st.pop();
                    }

                    if(redundant == true){
                        return true;
                    }
                    st.pop();
                }
            }
        }
        return false;
    }
}
