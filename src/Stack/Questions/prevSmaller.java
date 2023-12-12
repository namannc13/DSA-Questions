package Stack.Questions;

import java.util.Scanner;
import java.util.Stack;

public class prevSmaller {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
    
        int size = sc.nextInt();

        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = sc.nextInt();
        }

        int ans[] = previousSmaller(arr, size);

        for (int i = 0; i < size; i++) {
            System.out.print(ans[i] + " ");
        }

        sc.close();
    }

    private static int[] previousSmaller(int[] arr, int size) { //returns an array of elements which tells what is the previous smaller element before that index
        Stack<Integer> st = new Stack<>();
        st.push(-1);

        int[] ans = new int[size];

        for (int i = 0; i < size; i++) {
            while (st.peek()!= -1 && arr[st.peek()] >= arr[i]) {
                st.pop();
            }
            ans[i] = st.peek();
            st.push(i);
        }

        return ans;
    }
}

