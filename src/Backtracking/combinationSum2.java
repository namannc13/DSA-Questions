package Backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class combinationSum2 {
    public static List<List<Integer>> combSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        combSum(candidates, target, candidates.length-1, ans, new ArrayList<>());
        return ans;
    }
    public static void combSum(int[] candidates, int target, int i, List<List<Integer>> ans, List<Integer> res){
        if(target == 0){
            List<Integer> sortedRes = new ArrayList<>(res);
            Collections.sort(sortedRes);
            if(!ans.contains(sortedRes)){
                ans.add(new ArrayList<>(sortedRes));
            }
            return;
        }
        if(i < 0){
            return;
        }

        if(candidates[i] <= target){
            res.add(candidates[i]);
            combSum(candidates , target-candidates[i], i-1, ans, res);
            res.remove(res.size()-1);
        }
        int j = i;
        while(j>0 && candidates[j]==candidates[j-1]) j--; 
        combSum(candidates, target, j-1, ans, res);
        return;
    }
    public static void main(String[] args) {
        int[] arr = {10,1,2,7,6,1,5};
        int target = 8;
        System.out.println(combSum2(arr, target));
    }
}
