package Stack.Implementation;

import Stack.Implementation.ImplementationQuestions.MinStack;
import Stack.Implementation.usingArray.customStack;
import Stack.Implementation.usingArray.dynamicStack;

public class StackMain {
    public static void main(String[] args) throws Exception {
        // customStack

        // customStack st = new customStack(2);

        // st.push(3);
        // st.push(5);
        // st.push(5);

        // dynamicStack st2 = new dynamicStack(2);

        // st2.push(3);
        // st2.push(5);
        // st2.push(5);

        // System.out.println(st2.pop());
        // System.out.println(st2.pop());
        // System.out.println(st2.pop());

        // MinStack

        MinStack s = new MinStack();
        s.push(4);
        s.push(7);
        s.push(1);
        s.push(3);

        System.out.println(s.getMin());
        System.out.println(s.top());
        s.pop();
        System.out.println(s.getMin());
        System.out.println(s.top());
        s.pop();
        System.out.println(s.getMin());
        System.out.println(s.top());
    }
}
