package SlidingWindow;

import java.util.HashMap;

public class longestSubStringWithAtMostKDistinctCharacters {
    public static int func(String s, int k){
        int r = 0, l = 0, maxlen = 0;
        HashMap<Character, Integer> hm = new HashMap<>();
        while(r < s.length()){
            if(hm.containsKey(s.charAt(r))){
                int num = hm.get(s.charAt(r));
                hm.put(s.charAt(r), num+1);
            }else{
                hm.put(s.charAt(r), 1);
            }    
            if(hm.size() > k){
                int num = hm.get(s.charAt(l));
                if(num > 1){
                    hm.put(s.charAt(l), num-1);
                }else{
                    hm.remove(s.charAt(l));
                }
                l++;
            }
            maxlen = Math.max(maxlen, r - l + 1);
            r++;
        }
        return maxlen;
    }
}
