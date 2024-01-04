package DP.Questions2D.StringQuestions;

public class minimumInsertionstoMakeStringPalindrome {
    public static void main(String[] args) {
        char[] arr1 = {'b','b','a','b','c','b','c','a','b'};

        System.out.println(minimumInsertionstoMakeStringPalindromeTabulationHelper(arr1));
    }

    private static int minimumInsertionstoMakeStringPalindromeTabulationHelper(char[] arr1) {
        //We will find the longestPalindromicSubsequence in this array then we will keep them intact and just add the charaters which are not included in the palindromic subsequence ( we don't have to worry about the order in which we will them as we only need to return the number of insertions )
        char[] arr2 = {'b','a','c','b','c','b','a','b','b'}; // reverse of  arr1
        int lps = longestPalindromicSubsequence.longestPalindromicSubsequenceTabulationHelper(arr1,arr2);
        return arr1.length - lps;
    }
}
