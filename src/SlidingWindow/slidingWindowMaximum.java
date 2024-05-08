package SlidingWindow;

import java.util.ArrayDeque;
import java.util.Deque;

public class slidingWindowMaximum {
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int x = nums.length;
        Deque<Integer> dq = new ArrayDeque<>();
        int[] ans = new int[x-k+1];
        int ansIndex = 0;
        for(int i = 0; i< x; i++){
            if(!dq.isEmpty() && dq.peek() == i-k) dq.poll();

            while(!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]){
                dq.pollLast();
            }
            dq.offer(i);
            if(i >= k-1){
                ans[ansIndex++] = nums[dq.peek()];
            }
        }
        return ans;
    }
}
