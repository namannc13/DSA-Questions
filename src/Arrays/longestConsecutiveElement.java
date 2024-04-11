package Arrays;

import java.util.Arrays;

public class longestConsecutiveElement {
    public static int longestConsecutive(int[] nums) {
        if (nums.length == 0)
            return 0;
        Arrays.sort(nums);
        int count = 1;
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] == 1) {
                count++;
                max = Math.max(max, count);
            } else if (nums[i] == nums[i - 1]) {
                continue;
            } else {
                count = 1;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = { 0, 2, 1, 1 };
        System.out.println(longestConsecutive(arr));
    }
}
