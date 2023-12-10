package ADI.PatternSearchingAlgorithms;

import java.util.Arrays;

// Returns the Longest palindromic Substring
public class Manacher {
    public static void main(String[] args) {
        String s = "babbabbabc";
        char[] c = {'#', 'b', '#', 'a', '#', 'b', '#', 'b', '#', 'a', '#', 'b', '#', 'b', '#', 'a', '#', 'b', '#', 'c', '#'};

        int[] ans = runManacher(c);
        System.out.println(Arrays.toString(ans));
        System.out.println();
        
        int center = 4;
        int longest = getLongest(ans , center, 0); //o->odd , 1->even
        String st = s.substring(center - (longest/2), center + (longest/2) + 1);
        System.out.println(st);
        
        
    }
   
    public static int getLongest(int[] ans, int center, int whetherOdd){
        int pos = 2*center + 1 + (whetherOdd == 0 ? 0 : 1);
        return ans[pos] - 1;
    }

    public static int[] runManacher(char[] c) {
        int n = c.length;
        int[] arr = new int[n];

        int l = 0;
        int r = -1;

        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }
        for (int i = 1; i < arr.length; i++) {
            arr[i] = (i < r) ? Math.max(0, Math.min(r - i, arr[l + r - i])) : 1;
            while (i + arr[i] < n && i - arr[i] >= 0 && c[i + arr[i]] == c[i - arr[i]]) {
                arr[i]++;
            }
            if (i + arr[i] > r) {
                l = i - arr[i];
                r = i + arr[i];
            }
        }

        return arr;
         
    }   
}