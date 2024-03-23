package Backtracking;

import java.util.ArrayList;
import java.util.List;

public class letterCombinations {
    public static List<String> lc(String digits) {
        String[] str = digits.split("");
        List<String> ans = new ArrayList<>();
        if (digits.length() == 0)
            return ans;
        lc(str, 0, ans, "");
        return ans;
    }

    public static void lc(String[] str, int i, List<String> ans, String s) {
        if (i == str.length) {
            ans.add(s);
            return;
        }

        String s1 = str[i];
        int n = Integer.parseInt(s1);
        if (n == 7 || n == 9) {
            int num = n == 9 ? (n - 2) * (3) + 97 + 1 : (n - 2) * (3) + 97;
            char ch = (char) num;
            char ch1 = (char) (num + 1);
            char ch2 = (char) (num + 2);
            char ch3 = (char) (num + 3);

            lc(str, i + 1, ans, s + ch);
            lc(str, i + 1, ans, s + ch1);
            lc(str, i + 1, ans, s + ch2);
            lc(str, i + 1, ans, s + ch3);
            return;
        } else {
            int num = n == 8 ? (n - 2) * (3) + 97 + 1 : (n - 2) * (3) + 97;
            char ch = (char) num;
            char ch1 = (char) (num + 1);
            char ch2 = (char) (num + 2);

            lc(str, i + 1, ans, s + ch);
            lc(str, i + 1, ans, s + ch1);
            lc(str, i + 1, ans, s + ch2);
            return;
        }
    }
    public static void main(String[] args) {
        System.out.println(lc("23"));
    }
}
