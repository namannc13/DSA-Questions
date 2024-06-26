package SlidingWindow;

import java.util.Arrays;

public class permutationInString {
    public static boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length() || s2.length() == 0)
            return false;
        if (s1.length() == 0)
            return true;
        int[] arr1 = new int[26];
        Arrays.fill(arr1, 0);
        int[] arr2 = new int[26];
        Arrays.fill(arr2, 0);
        for (int i = 0; i < s1.length(); i++) {
            arr1[s1.charAt(i) - 'a'] += 1;
            arr2[s2.charAt(i) - 'a'] += 1;
        }
        for (int i = s1.length(); i < s2.length(); i++) {
            if (Arrays.equals(arr1, arr2)) {
                return true;
            }
            arr2[s2.charAt(i - s1.length()) - 'a']--;
            arr2[s2.charAt(i) - 'a']++;
        }
        if (Arrays.equals(arr1, arr2)) {
            return true;
        } else
            return false;
    }
    public static void main(String[] args) {
        System.out.println(checkInclusion("ab", "eidbaooo"));
    }
}