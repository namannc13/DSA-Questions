package PatternSearchingAlgorithms;

public class KMPalgorithm {
    public static void main(String[] args) {
        char[] mainArray = { 'a', 'b', 'x', 'a', 'b', 'c', 'a', 'b', 'y' };
        char[] arr = {'b', 'x' };

        int[] ans = makeArray(arr);
        int answer = search(ans, arr, mainArray);

        for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i] + " ");
        }

        System.out.println();
        System.out.print(answer);
    }
    
    public static int[] makeArray(char[] arr) {
        int i = 1;
        int j = 0;
        int x = 1;
        int[] ans = new int[arr.length];
        ans[0] = 0;
        while (i < arr.length) {
            if (arr[j] == arr[i]) {
                ans[x] = j + 1;
                x++;
                i++;
                j++;
            } else {
                if (j != 0) {
                    j = ans[j - 1];
                } else {
                    ans[x] = 0;
                    x++;
                    i++;
                }
            }
        }
        return ans;
    }

    public static int search(int[] ans, char[] arr, char[] mainArray) {
        int i = 0;
        int j = 0;
        while (j < ans.length) {
            if (mainArray[i] == arr[j]) {
                i++;
                j++;
            } else {
                if (j != 0) {
                    j = ans[j - 1];
                } else {
                    while (mainArray[i] != arr[j]) {
                        i++;
                    }
                }
            }
        }
        return i - j;

    }

    
}
