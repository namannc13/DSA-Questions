package ADI.PatternSearchingAlgorithms;

import java.util.Arrays;

public class Zalgorithm {
    public static void main(String[] args) {
        // char[] text = {'a','b','a','b'};
        // char[] pattern = {'a','b','a'};
        
        char[] ans = {'a','b','a','x','a','b','a','b'}; // text and pattern seperate by 'x'
        
        int[] z = calculatez(ans);
        
        System.out.print(Arrays.toString(z));
    }
    public static int[] calculatez(char[] ans){
        int[] z = new int[ans.length];
        
        int left = 0;
        int right = 0;
        z[0]=0;
        for(int k=1; k < ans.length; k ++){
            if(k > right){
                left =right = k;
                while(right < ans.length && ans[right] == ans[right-left]){
                    right ++;
                }
                z[k] = right - left;
                right --;
            }else{
                int k1 = k - left;
                if(z[k1] < right - k + 1){
                    z[k] = z[k1];
                }else{
                    left = k;
                    while(right < ans.length && ans[right] == ans[right-left]){
                        right ++;
                    }
                    z[k] = right - left;
                    right --;
                }
            }
        }
        return z;
    }
}
