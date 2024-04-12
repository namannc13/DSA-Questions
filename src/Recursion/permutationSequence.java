package Recursion;

import java.util.ArrayList;
import java.util.List;

public class permutationSequence {
    public static String getPermutation(int n, int k) {
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < n; i++){
            list.add(i+1);
        }
        permutation(list, k-1, n, "");
        return ans;
    }

    static String ans = "";
    
    public static void permutation(List<Integer> list, int k, int n, String s){
        if(n == 1){
            ans = s+String.valueOf(list.get(0));
            return;
        }

        int fact = factorial(n);
        int segments = fact/n;
        int index = k/segments;
        int val = list.remove(index);
        int newK = k % segments;

        if(index == 0){
            permutation(list, k, n-1, s+String.valueOf(val));
        }else{
            permutation(list, newK, n-1, s+String.valueOf(val));
        }
    }
    public static int factorial(int n){
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
    public static void main(String[] args) {
        System.out.println(getPermutation(3, 5));
    }
}
