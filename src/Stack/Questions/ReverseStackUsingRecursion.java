package Stack.Questions;

import java.util.Scanner;
import java.util.Stack;

public class ReverseStackUsingRecursion {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();

        Stack<Integer> st = new Stack<>();
        for(int i = 0; i < size; i ++){
            st.push(sc.nextInt());
        }

        reverseStack(st, size);

        System.out.println(st);
        sc.close();
    }

    private static void reverseStack(Stack<Integer> st, int size) {
        if(st.isEmpty()){ // do nothing till the stack is empty // then when we go back , insert each element at the bottom 
            return;
        }

        int num = st.pop();
        reverseStack(st, size);
        InsertElementAtTheBottom.insertAtBottom(st, size, num); // used insertAtBottom from InsertElementAtTheBottom.java

    }


}
