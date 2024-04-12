package Arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class groupAnagrams{
    public static List<List<String>> grpAnagrams(String[] strs) {
        Map<String, List<String>> hm = new HashMap<>();
        for(int i = 0 ; i < strs.length; i++){
            char[] arr = strs[i].toCharArray();
            Arrays.sort(arr);
            String s = Arrays.toString(arr);
            hm.putIfAbsent(s, new ArrayList<>());
            hm.get(s).add(strs[i]);
        }
        return new ArrayList<>(hm.values());
    }
    public static void main(String[] args) {
        String[] str = {"ate", "eat", "ant"};
        System.out.println(grpAnagrams(str));
    }
}