package SlidingWindow;

import java.util.HashMap;

public class minWindowSubstring {
    public static String minWindow(String s, String t) {
        int l = 0, r = 0, minlen = Integer.MAX_VALUE, cnt = 0, sind = -1;
        HashMap<Character, Integer> hm = new HashMap<>();
        for(int i = 0; i < t.length(); i++){
            if(hm.containsKey(t.charAt(i))){
                int num = hm.get(t.charAt(i));
                hm.put(t.charAt(i), num+1);
            }else{
                hm.put(t.charAt(i), 1);
            }
        }
        while(r < s.length()){
            if(!hm.containsKey(s.charAt(r))) hm.put(s.charAt(r), -1);
            else{
                int num = hm.get(s.charAt(r));
                if(num > 0) cnt++;
                hm.put(s.charAt(r), num-1);
            }
            while(cnt == t.length()){
                if((r-l+1) < minlen){
                    minlen = Math.min(minlen, r-l+1);
                    sind = l;
                }
                int num = hm.get(s.charAt(l));
                hm.put(s.charAt(l), num+1);
                if(hm.get(s.charAt(l)) > 0){
                    cnt--;
                }
                l++;
            }
            r++;
        }
        return sind == -1 ? "" : s.substring(sind, sind + minlen);
    }
}
