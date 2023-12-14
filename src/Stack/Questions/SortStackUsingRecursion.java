package Stack.Questions;

import java.util.Scanner;
import java.util.Stack;

public class SortStackUsingRecursion {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();

        Stack<Integer> st = new Stack<>();
        for(int i = 0; i < size; i ++){
            st.push(sc.nextInt());
        }

        sortStack(st);

        System.out.println(st);
        sc.close();
    }

    private static void sortStack(Stack<Integer> st) {
        if(st.isEmpty()){
            return;
        }

        int num = st.pop();
        sortStack(st);
        sortPush(st,num);
    }

    private static void sortPush(Stack<Integer> st, int num) {
        if((!st.isEmpty() && st.peek() < num) || st.isEmpty()){ // before trying to acess st.peek() , we need to check whether the stack is empty or not otherwise it will throw error
            st.push(num);
            return;
        }

        int x = st.pop();
        sortPush(st, num);
        st.push(x);
    }
}
