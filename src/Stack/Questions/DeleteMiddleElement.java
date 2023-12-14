package Stack.Questions;

import java.util.Scanner;
import java.util.Stack;

public class DeleteMiddleElement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();

        Stack<Integer> st = new Stack<>();
        for(int i = 0; i < size; i ++){
            st.push(sc.nextInt());
        }

        deleteMiddle(st, 0, size);

        System.out.println(st);
        sc.close();
    }

    public static void deleteMiddle(Stack<Integer> st, int count, int size){ // recursive void function ( need arguments to keep track of )
        if(count == size/2){
            st.pop();
            return;
        }

        int num = st.pop();
        deleteMiddle(st, count+1, size);
        st.push(num);

    }   
}
