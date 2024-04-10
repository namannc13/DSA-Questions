package Arrays;

import java.util.Arrays;

public class isAnagram {
    public static boolean isAnagramfunc(String s, String t) {
        char[] S = s.toCharArray();
        char[] T = t.toCharArray();
        Arrays.sort(S);
        Arrays.sort(T);
        if(Arrays.toString(S).equals(Arrays.toString(T))) return true;
        else return false;
    }
    public static void main(String[] args) {
        System.out.println(isAnagramfunc("hi", "hi"));
    }
}
