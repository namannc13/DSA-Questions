package SlidingWindow;

import java.util.HashMap;

public class longestRepeatingCharReplacement {
    public static int characterReplacement(String s, int k) {
        int l = 0, r = 0, maxf = 0, answer = 0;
        HashMap<Character, Integer> hm = new HashMap<>();
        while (r < s.length()) {
            if (hm.containsKey(s.charAt(r))) {
                int num = hm.get(s.charAt(r));
                maxf = Math.max(maxf, num + 1);
                hm.put(s.charAt(r), num + 1);
            } else {
                if (maxf == 0)
                    maxf = 1;
                hm.put(s.charAt(r), 1);
            }
            if ((r - l + 1) - maxf > k) {
                if (hm.containsKey(s.charAt(l))) {
                    int num = hm.get(s.charAt(l));
                    if (num > 1) {
                        hm.put(s.charAt(l), num - 1);
                    } else {
                        hm.remove(s.charAt(l));
                    }
                    maxf = 0;
                    for (int i : hm.values()) {
                        maxf = Math.max(maxf, i);
                    }
                }
                l++;
            }
            if ((r - l + 1) - maxf <= k)
                answer = Math.max(answer, r - l + 1);
            r++;
        }
        return answer;
    }

    public static int characterReplacementOptimal(String s, int k) {
        int l = 0, r = 0, maxf = 0, answer = 0;
        HashMap<Character, Integer> hm = new HashMap<>();
        while (r < s.length()) {
            if (hm.containsKey(s.charAt(r))) {
                int num = hm.get(s.charAt(r));
                maxf = Math.max(maxf, num + 1);
                hm.put(s.charAt(r), num + 1);
            } else {
                if (maxf == 0)
                    maxf = 1;
                hm.put(s.charAt(r), 1);
            }
            if ((r - l + 1) - maxf > k) {
                if (hm.containsKey(s.charAt(l))) {
                    int num = hm.get(s.charAt(l));
                    if (num > 1) {
                        hm.put(s.charAt(l), num - 1);
                    } else {
                        hm.remove(s.charAt(l));
                    }
                }
                l++;
            }
            if ((r - l + 1) - maxf <= k)
                answer = Math.max(answer, r - l + 1);
            r++;
        }
        return answer;
    }

    public static void main(String[] args) {
        String s = "AABABBA";
        int k = 1;
        System.out.println(characterReplacementOptimal(s, k));
    }
}
