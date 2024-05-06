package SlidingWindow;

import java.util.HashMap;

public class noOfSubstringsContainingAllThreeChar {
    public static int numberOfSubstringsMyApproach(String s) {
        int count = 0, l = 0, r = 0;
        HashMap<Character, Integer> hm = new HashMap<>();
        while (r < s.length()) {
            if (hm.containsKey(s.charAt(r))) {
                int num = hm.get(s.charAt(r));
                hm.put(s.charAt(r), num + 1);
            } else {
                hm.put(s.charAt(r), 1);
            }
            while (hm.size() == 3) {
                int num = hm.get(s.charAt(l));
                if (num > 1) {
                    hm.put(s.charAt(l), num - 1);
                } else {
                    hm.remove(s.charAt(l));
                }
                l++;
            }
            count += l;
            r++;
        }
        return count;
    }

    public static int numberOfSubstringsOptimal(String s) {
        int[] lastSeen = { -1, -1, -1 };
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            lastSeen[s.charAt(i) - 'a'] = i;
            if (lastSeen[0] != -1 && lastSeen[1] != -1 && lastSeen[2] != -1) {
                count += 1 + Math.min(lastSeen[0], Math.min(lastSeen[1], lastSeen[2]));
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String s = "abcabc";
        System.out.println(numberOfSubstringsMyApproach(s));
        System.out.println(numberOfSubstringsOptimal(s));
    }
}
