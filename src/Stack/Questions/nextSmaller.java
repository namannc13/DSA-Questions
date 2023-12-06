package Stack.Questions;

import java.util.Scanner;
import java.util.Stack;

public class nextSmaller {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int size = sc.nextInt();

        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = sc.nextInt();
        }

        int ans[] = nextSmallerIndex(arr, size);

        for (int i = 0; i < size; i++) {
            System.out.print(ans[i] + " ");
        }

        sc.close();
    }

    private static int[] nextSmallerIndex(int[] arr, int size) {
        Stack<Integer> st = new Stack<>();
        st.push(-1);

        int[] ans = new int[size];

        for(int i = size-1; i >= 0; i --){
            int curr = arr[i];
            while(st.peek() >= curr){
                st.pop();
            }
            ans[i] = st.peek();
            st.push(curr);
        }

        return ans;
    }
}

