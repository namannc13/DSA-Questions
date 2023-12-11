package Stack.ComplexQuestions;

import java.util.Scanner;
import java.util.Stack;

public class largestRectangleInHistogram {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int size = sc.nextInt();

        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = sc.nextInt();
        }

        int ans = largestRectangularAreaInHistogram(arr, size);

        System.out.println(ans);

        sc.close();
    }

    public static int largestRectangularAreaInHistogram(int[] arr, int size) {
        int[] prevSmaller = previousSmaller(arr, size);

        int[] nextSmaller = nextSmaller(arr, size);

        int area = Integer.MIN_VALUE;
        for( int i =0; i < size; i ++){
            int l = arr[i];
            if(nextSmaller[i] == -1){
                nextSmaller[i] = size; // if the array is [2, 2, 2, 2], the nextSmaller[i] will be -1 and same for the prevSmaller[i] . b will become -1 and then area will be in negative so we need to handle that condition
            }
            int b = nextSmaller[i] - prevSmaller[i] - 1; 

            int newArea = l*b;
            area = Math.max(area, newArea);
        }
        return area;
    }

    private static int[] nextSmaller(int[] arr, int size) { //returns an array of elements which tells what is the next smaller element after that index
        Stack<Integer> st = new Stack<>();
        st.push(-1);

        int[] ans = new int[size];

        for (int i = size - 1; i >= 0; i--) {
            while (st.peek()!= -1 && arr[st.peek()] >= arr[i]) {
                st.pop();
            }
            ans[i] = st.peek();
            st.push(i);
        }

        return ans;
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
