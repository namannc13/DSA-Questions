package Stack.Implementation.ImplementationQuestions;

import java.util.Stack;

public class MinStack { // Implementing a stack where pop , push , getMin , etc functions takes 0(1) time and 0(1) space . // watch love babbar vdo if bhul gaya
    Stack<Integer> st;
    int min; // we will use this value and try to solve this question without using any other data structure

    public MinStack() {
        st = new Stack<>();
        min = Integer.MAX_VALUE;
    }
    
    public void push(int val) {
        if(st.isEmpty()){ // simple push for the first element
            st.push(val);
            min = val;
        }

        if(val < min){ // if
            int newNum = 2*val - min; // new value which will be less than min initially and pushed in the stack ( so we will know if this is the value changed and pushed into stack when we try to pop )
            st.push(newNum);
            min = val; // the value is assigned to min which should have been pushed
        }else{
            st.push(val);
        }
    }
    
    public void pop() {
        if(st.isEmpty())
            return;

        int curr = st.pop();
        if(curr < min){
            // int prevMin = min; // if an int method

            min = 2*min - curr; // when we find out the the element we popped is less than the min , then we know that this is the element we changed and pushed to the stack which pushing elements . And we remember that we updated the min value with the actual value which should have been pushed at that time . so we revert back the min value now 

            // return prevMin; // if an int method
        } 
    }
    
    public int top() {
        if(st.isEmpty())
            return -1;

        int curr = st.peek();
        if(curr < min){
            return min; // if the element is less than min , then this is the value which was updated and manipulated and the actual value of the element which should have been pushed here is in the min variable
        }else{
            return curr;
        }
    }
    
    public int getMin() {
        if(st.isEmpty())
            return -1;
        return min;
    }   
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
