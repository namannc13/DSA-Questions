package Arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class topKFrequenctElements {
    public static int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        for(int i = 0; i < nums.length; i ++){
            if(hm.containsKey(nums[i])){
                int getValue = hm.get(nums[i]);
                hm.put(nums[i], getValue+1);
            }else hm.put(nums[i], 1);
        }
        List<Integer>list=new ArrayList<>(hm.keySet());
        list.sort((a, b) -> hm.get(b) - hm.get(a));
        int[] ans = new int[k];
        for(int i = 0; i < k; i++){
            ans[i] = list.get(i);
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5 };
        System.out.println(Arrays.toString(topKFrequent(arr, 2)));
    }
}
