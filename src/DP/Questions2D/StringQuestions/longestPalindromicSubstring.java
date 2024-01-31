package DP.Questions2D.StringQuestions;

// Wrong Answer
// 135 / 142 testcases passed
// 
// Editorial
// Input
// s =
// "aacabdkacaa"
// 
// Use Testcase
// Output
// 4/"aaca"
// Expected
// 3/"aca"

public class longestPalindromicSubstring {
    public static void main(String[] args) {
        String a = "aacabdkacaa";
        StringBuilder a2 = new StringBuilder(a);
        String b = a2.reverse().toString();
        char[] arr1 = a.toCharArray();  
        char[] arr2 = b.toCharArray();

        System.out.println(longestPalindromicSubstringHelper(arr1,arr2));
    }

    private static int longestPalindromicSubstringHelper(char[] arr1, char[] arr2) {
        return longestCommonSubstring.longestCommonSubstringOptimal(arr1, arr2);
    }
    
}
