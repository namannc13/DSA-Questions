package GreedyAlgorithms;

import java.util.Arrays;
import java.util.Comparator;

public class jobSequencing {
    public static class job {
        int Deadline;
        int Profit;
        char id;

        public job(char id,int Deadline, int Profit) {
            this.id = id;
            this.Deadline = Deadline;
            this.Profit = Profit;
        }
    }
    
    public static void main(String[] args) {
        job[] jobs = {
                new job('a',2, 100),  // Access the job class through the instance of jobSequencing
                new job('b',2, 27),
                new job('d',1, 19),
                new job('c',1, 25),
        };
        jobSequencingAlgorithm(jobs);
    }

    private static void jobSequencingAlgorithm(job[] jobs) {
        Arrays.sort(jobs, Comparator.comparingInt(job -> job.Profit));
        // Arrays.sort(jobs, Comparator.comparingInt(job -> job.Profit).reversed());
        for(int i = 0; i < jobs.length/2; i++){
            job j = jobs[jobs.length-i-1];
            jobs[jobs.length-i-1] = jobs[i];
            jobs[i] = j;
        }


        for(int i = 0; i < jobs.length; i ++){
            System.out.println(jobs[i].Profit);
        }
        int[] result = new int[jobs.length];
        boolean[] slot = new boolean[jobs.length];
        for(int i = 0; i < slot.length; i ++){
            slot[i] = false;
        }
        for(int i = 0; i < jobs.length; i++){
            for(int j = Math.min(jobs.length, jobs[i].Deadline) -1; j>=0; j --){
                if(!slot[j]){ // false means free slot
                    result[j] = i;
                    slot[j] = true;
                    break;
                }
            }
        }

        int totalProfit = 0;
        for(int i = 0; i < jobs.length; i++){
            if(slot[i]){
                System.out.println(jobs[result[i]].id + " ");
                totalProfit += jobs[result[i]].Profit;
            }
        }

        System.out.println("Profit " + totalProfit);
    }
}
