package Stack.Questions;

import java.util.Scanner;
import java.util.Stack;

public class MakeStringValid {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s= sc.nextLine();
        int answer = makeValid(s); // answer is no. of brackets we need to reverse to make the string valid

        System.out.println(answer);
        sc.close();
    }

    private static int makeValid(String s) {
        //step 1 -> eliminate all odd strings
        if(s.length() % 2 == 1){
            return -1; // for odd strings , we cannot make it valid . {{}}} -> if we remove all the valid braces , we cannot make it valid
        }

        //step 2 -> eliminate all valid strings
        Stack<Character> st = new Stack<>();
        for(char c : s.toCharArray()){
            if(c == '{'){
                st.push(c);
            }else{
                if(!st.isEmpty() && st.peek() == '{'){
                    st.pop();
                }else{
                    st.push(c);
                }
            }
        }

        //step 3 -> now stack contains only invalid expressions -> either odd(}}}{{{) or even (}}}}{{{{) . we need to find the count of both the braces and then we have a formula that we can use to find the answer
        int a = 0;// count of closed braces
        int b = 0;// count of open braces

        while(!st.isEmpty()){
            if(st.peek() == '{'){
                b++;
            }else{
                a++;
            }
            st.pop();
        }

        int ans = (a+1)/2 + (b+1)/2; // this returns the number of braces we need to reverse in order to make the string valid
        return ans;
    }
}
