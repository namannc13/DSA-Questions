package Stack.Questions;

import java.util.Scanner;
import java.util.Stack;

public class nextGreater {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
    
        int size = sc.nextInt();

        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = sc.nextInt();
        }

        int ans[] = nextGreaterArray(arr, size);

        for (int i = 0; i < size; i++) {
            System.out.print(ans[i] + " ");
        }

        sc.close();
    }

    private static int[] nextGreaterArray(int[] arr, int size) {
        Stack<Integer> st = new Stack<>();
        st.push(Integer.MAX_VALUE);

        int[] ans = new int[size];

        for(int i = size-1; i >= 0; i --){
            int curr = arr[i];
            while(st.peek() != Integer.MAX_VALUE && arr[st.peek()] <= curr){
                st.pop();
            }
            ans[i] = (st.peek() == Integer.MAX_VALUE)? -1: st.peek();
            st.push(i);
        }

        return ans;
    }
}
