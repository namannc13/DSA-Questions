package SlidingWindow;

import java.util.HashMap;

public class longestSubSWithoutRepeatingChar {
    public int lengthOfLongestSubstring(String s) {
        int l = 0, r = 0, maxlen = 0;
        HashMap<Character, Integer> hm = new HashMap<>();
        while (r < s.length()) {
            if (hm.containsKey(s.charAt(r))) {
                int ind = hm.get(s.charAt(r));
                if (ind >= l) {
                    l = hm.get(s.charAt(r)) + 1;
                    hm.remove(s.charAt(r));
                    hm.put(s.charAt(r), r);
                    maxlen = Math.max(maxlen, r - l + 1);
                    r++;
                } else {
                    hm.remove(s.charAt(r));
                    hm.put(s.charAt(r), r);
                    maxlen = Math.max(maxlen, r - l + 1);
                    r++;
                }
            } else {
                hm.put(s.charAt(r), r);
                maxlen = Math.max(maxlen, r - l + 1);
                r++;
            }
        }
        return maxlen;
    }
}
