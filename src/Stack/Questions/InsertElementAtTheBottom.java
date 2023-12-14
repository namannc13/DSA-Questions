package Stack.Questions;

import java.util.Scanner;
import java.util.Stack;

public class InsertElementAtTheBottom {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();

        Stack<Integer> st = new Stack<>();
        for(int i = 0; i < size; i ++){
            st.push(sc.nextInt());
        }

        int num = sc.nextInt();

        insertAtBottom(st, size, num);

        System.out.println(st);
        sc.close();
    }

    static void insertAtBottom(Stack<Integer> st,int size, int num) {
        if(st.isEmpty()){
            st.push(num);
            return;
        }

        int x = st.pop();
        insertAtBottom(st, size, num);
        st.push(x);

    }
}
