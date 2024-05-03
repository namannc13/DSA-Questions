package Backtracking;

import java.util.ArrayList;
import java.util.List;

public class combinationSum {
    public static List<List<Integer>> combSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        combSum(candidates, target, candidates.length-1, ans, new ArrayList<>());
        return ans;
    }
    public static void combSum(int[] candidates, int target, int i, List<List<Integer>> ans, List<Integer> res){
        if(target == 0){
            ans.add(new ArrayList<>(res));
            return;
        }
        if(i < 0){
            return;
        }

        combSum(candidates, target, i-1, ans, res);
        if(candidates[i] <= target){
            res.add(candidates[i]);
            combSum(candidates , target-candidates[i], i, ans, res);
            res.remove(res.size()-1);
        }
    }
    public static void main(String[] args) {
        int[] arr = {2, 3, 6, 7};
        int target = 7;
        System.out.println(combSum(arr, target));
    }
}
