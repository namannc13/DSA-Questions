package SlidingWindow;

import java.util.HashMap;

public class fruitIntoBaskets {
    public int totalFruit(int[] fruits) {
        int l = 0, r = 0, maxlen = 0;
        HashMap<Integer, Integer> hm = new HashMap<>();
        while (r < fruits.length) {
            if (hm.containsKey(fruits[r])) {
                int num = hm.get(fruits[r]);
                hm.put(fruits[r], num + 1);
            } else {
                hm.put(fruits[r], 1);
            }
            while (hm.size() > 2) {
                int num = hm.get(fruits[l]);
                if (num > 1) {
                    hm.put(fruits[l], num - 1);
                } else {
                    hm.remove(fruits[l]);
                }
                l++;
            }
            maxlen = Math.max(maxlen, r - l + 1);
            r++;
        }
        return maxlen;
    }

    public int totalFruitOptimal(int[] fruits) {
        int l = 0, r = 0, maxlen = 0;
        HashMap<Integer, Integer> hm = new HashMap<>();
        while (r < fruits.length) {
            if (hm.containsKey(fruits[r])) {
                int num = hm.get(fruits[r]);
                hm.put(fruits[r], num + 1);
            } else {
                hm.put(fruits[r], 1);
            }
            if (hm.size() > 2) {
                int num = hm.get(fruits[l]);
                if (num > 1) {
                    hm.put(fruits[l], num - 1);
                } else {
                    hm.remove(fruits[l]);
                }
                l++;
            }
            maxlen = Math.max(maxlen, r - l + 1);
            r++;
        }
        return maxlen;
    }
}