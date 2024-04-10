package Arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class twoSum {
    public static int[] twoSumfunc(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            map.put(nums[i],i);
        }
        int[] ans = new int[2];
        for(int i = 0; i < nums.length; i++){
            int sum = target - nums[i];
            if(map.containsKey(sum)){
                int value = map.get(sum);
                if(i != value){
                    ans[0] = i;
                    ans[1] = value;
                }
                
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 5, 5};
        System.out.println(Arrays.toString(twoSumfunc(arr, 5)));
    }
}
