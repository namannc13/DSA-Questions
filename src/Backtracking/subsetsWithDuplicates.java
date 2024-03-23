package Backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class subsetsWithDuplicates {
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        subsets(nums, nums.length-1, ans, new ArrayList<>());
        return ans;
    }
    public static void subsets(int[] nums, int i, List<List<Integer>> ans, List<Integer> res){
        if (i < 0) {
            List<Integer> sortedRes = new ArrayList<>(res); // Create a copy of res
            Collections.sort(sortedRes); // Sort the copy
            if (!ans.contains(sortedRes)) { // Check for presence of sortedRes in ans
                ans.add(new ArrayList<>(sortedRes));
            }
            return;
        }
        subsets(nums, i-1, ans,res);
        res.add(nums[i]);
        subsets(nums, i-1, ans,res);
        res.remove(res.size()-1);
        return;
    }
    public static void main(String[] args) {
        int[] arr = { 2, 4, 5};
        System.out.println(subsetsWithDup(arr));
    }
}
