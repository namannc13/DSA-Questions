package Arrays;

import java.util.ArrayList;
import java.util.Arrays;

public class productExceptSelf{
    public static int[] productExcSelf(int[] nums) {
        int product = 1;
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i< nums.length; i++){
            if(nums[i] == 0) list.add(i);
            else product *= nums[i];
        }
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0){
                if(list.size() > 0){
                    nums[i] = 0;
                }
                else{
                    nums[i] = product/nums[i];
                }
            } 
            else{
                if(list.size() > 1){
                    nums[i] = 0;
                }
                else{
                    nums[i] = product;
                }
            } 
        }
        return nums;
    }
    public static void main(String[] args) {
        int[] arr = {2,3};
        System.out.println(Arrays.toString(productExcSelf(arr)));
    }
}