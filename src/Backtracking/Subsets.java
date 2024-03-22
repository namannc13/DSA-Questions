package Backtracking;

import java.util.ArrayList;
import java.util.List;

public class Subsets {
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        subsets(nums, nums.length-1, ans, new ArrayList<>());
        return ans;
    }
    public static void subsets(int[] nums, int i, List<List<Integer>> ans, List<Integer> res){
        if(i < 0){
            ans.add(new ArrayList<>(res));
            return;
        }
        subsets(nums, i-1, ans,res);
        res.add(nums[i]);
        subsets(nums, i-1, ans,res);
        res.remove(res.size()-1);
        return;
    }
    public static void main(String[] args) {
        int[] arr = { 2, 4, 5, 6};
        System.out.println(subsets(arr));
    }
}


