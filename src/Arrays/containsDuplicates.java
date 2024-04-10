package Arrays;

import java.util.HashSet;

public class containsDuplicates{
    public static boolean containsDuplicate(int[] nums) {
        HashSet<Integer> map = new HashSet<>();
        for(int i = 0; i < nums.length; i++){
            if(map.contains(nums[i])) return true;
            map.add(nums[i]);
        }
        return map.size() != nums.length ? true: false;
    }
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        System.out.println(containsDuplicate(arr));
    }
}