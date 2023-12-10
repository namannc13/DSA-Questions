package ADI.PatternSearchingAlgorithms;

import java.util.Arrays;

// Returns the index on which the String pattern occured in the String text 
public class Zalgorithm { 
    public static void main(String[] args) {
        char[] text = {'a','b','a','b'};
        char[] pattern = {'b','a'};
        
        char[] ans = new char[text.length + pattern.length + 1];
        int i =0;
        for(char c : pattern){
            ans[i++] = c;
        }
        
        ans[i++] = 'x';
        
        for(char c : text){
            ans[i++] = c;
        }
        
        int[] z = calculatez(ans);
        
        System.out.print(Arrays.toString(z));
        System.out.println();
        int answer = -1;
        for(int y = 0; y< z.length; y++){
            if(z[y] == pattern.length){
                answer = y - pattern.length -1 ;
            }
        }
        
        System.out.println(answer);
    }
    
    public static int[] calculatez(char[] ans){
        int[] z = new int[ans.length];
        
        int left = 0 ;
        int right = 0;
        
        z[0] = 0;
        
        for(int k =1; k<ans.length; k++){
            if(k > right){
                left  = right = k;
                while(right < ans.length && ans[right] == ans[right-left]){
                    right++;
                }
                z[k] = right - left;
                right --;
            }else{
                int k1 = k - left;
                if(z[k1] < right - k + 1){
                    z[k]= z[k1];
                }else{
                    left = k;
                    while(right < ans.length && ans[right] == ans[right-left]){
                        right++;
                    }
                    z[k] = right - left;
                    right --;
                }
            }
        }
        return z;
    }
}