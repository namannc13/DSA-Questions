package SlidingWindow;

import java.util.HashMap;

public class subarraysWithKDifferentIntegers {
    public static int subarraysWithKDistinct(int[] nums, int k) {
        return subarraysWithKDistinctHelper(nums, k) - subarraysWithKDistinctHelper(nums, k-1);
    }
    public static int subarraysWithKDistinctHelper(int[] nums, int k){
        int l = 0, r = 0, cnt = 0;
        HashMap<Integer, Integer> hm = new HashMap<>();
        while(r < nums.length){
            if(hm.containsKey(nums[r])){
                int num = hm.get(nums[r]);
                hm.put(nums[r], num+1);
            }else{
                hm.put(nums[r], 1);
            }
            while(hm.size() > k){
                int num = hm.get(nums[l]);
                if(num > 1){
                    hm.put(nums[l], num-1);
                }else{
                    hm.remove(nums[l]);
                }
                l++;
            }
            cnt+= r-l+1;
            r++;
        }
        return cnt;
    }
}
