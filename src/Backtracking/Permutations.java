package Backtracking;

import java.util.ArrayList;
import java.util.List;

public class Permutations{
    public static List<List<Integer>> permute(int[] nums) {
        List<Integer> numsList = new ArrayList<>();
        for(int i: nums) numsList.add(i);
        List<List<Integer>> ans = new ArrayList<>();
        permute(numsList, ans, new ArrayList<>());
        return ans;
    }
    public static void permute(List<Integer> nums, List<List<Integer>> ans, List<Integer> res){
        if(nums.size() == 0){
            ans.add(new ArrayList<>(res));
            return;
        }
        for(int i=0; i<nums.size(); i++){
            res.add(nums.get(i));
            int removed = nums.get(i);
            nums.remove(i);
            permute(nums, ans, res);
            nums.add(i,removed);
            res.remove(res.size()-1);
        }
        return;
    }
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        System.out.println(permute(arr));
    }
}