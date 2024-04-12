package Arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class groupAnagrams{
    public static List<List<String>> grpAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        HashMap<String, List<String>> hm = new HashMap<>();
        for(int i = 0 ; i < strs.length; i++){
            char[] arr = strs[i].toCharArray();
            Arrays.sort(arr);
            String s = Arrays.toString(arr);
            if(hm.containsKey(s)){
                List<String> a = hm.get(s);
                a.add(strs[i]);
                hm.put(s, a);
            }else{
                List<String> a = new ArrayList<>();
                a.add(strs[i]);
                hm.put(s, a);
            }
        }
        ans.addAll(hm.values());
        return ans;
    }
    public static void main(String[] args) {
        String[] str = {"ate", "eat", "ant"};
        System.out.println(grpAnagrams(str));
    }
}