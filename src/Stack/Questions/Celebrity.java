package Stack.Questions;

import java.util.Scanner;
import java.util.Stack;

public class Celebrity {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int size = sc.nextInt();
        int[][] arr = new int[size][size];

        for(int i = 0; i < size; i ++){
            for(int j =0; j < size; j++){
                arr[i][j] = sc.nextInt();
            }
        }

        int answer = FindCelebrity(arr, size);

        System.out.println(answer);

        sc.close();
    }

    private static int FindCelebrity(int[][] arr, int size) {
        Stack<Integer> st = new Stack<>();

        for(int i =0; i < size; i ++){
            st.push(i); // push all the person in the stack 
        }

        while(st.size() > 1){ // take two elements out of the array and see if they know each other .
            int a = st.pop();
            int b = st.pop();

            if(knows(arr,a,b)){ // if a knows b , then a is not a celebrity and vice versa
                st.push(b);
            }else{
                st.push(a);
            }
        }

        // after the while loop , we are left with one person in the stack , who is a potential Celebrity ( he could be the celebrity ) .
        // now we need to check whether he is the celebrity or not 

        int celeb = st.pop();

        boolean rowcheck = false;
        int zeroCount = 0;
        for(int i = 0 ; i < size; i ++){
            if(arr[celeb][i] == 0){
                zeroCount ++;
            }
        }
        if(zeroCount == size){
            rowcheck = true;
        }

        boolean colcheck = false;
        int oneCount = 0;
        for(int i = 0; i < size; i ++){
            if(arr[i][celeb] == 1){
                oneCount ++;
            }
        }
        if(oneCount == size-1){
            colcheck = true;
        }

        if(rowcheck == true && colcheck == true){
            return celeb;
        }else{
            return -1;
        }
    }

    private static boolean knows(int[][] arr, int a, int b) {
        return arr[a][b] == 1;
    }
}
