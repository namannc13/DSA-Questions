package GreedyAlgorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class knapsack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<List<Double>> list = new ArrayList<>();
        
        String input = sc.nextLine();
        String[] answer = input.split(" ");
        // System.out.print(Arrays.toString(answer));
        
        for(int i =0; i< answer.length; i+=2){
                List<Double> list2 = new ArrayList<>();
                
                Double a =Double.parseDouble(answer[i]);
                Double b= Double.parseDouble(answer[i+1]);
                
                list2.add(a);
                list2.add(b);
                list2.add(a/b);
                
                list.add(list2);
        }
        // for(int i = 0; i<list.size(); i++){
        //     System.out.println(list.get(i));
        // }
        
        list.sort((a, b) -> Double.compare(b.get(2), a.get(2)));
        System.out.println(list);
        
        Double Capacity = sc.nextDouble();
        Double Profit=0.0;
        for(int i = 0; i < list.size(); i++){
            if(Capacity > 0.0 && list.get(i).get(1) <= Capacity){
                Capacity -= list.get(i).get(1); //5
                Profit +=  list.get(i).get(0); //24
            }
            else if(Capacity > 0.0) {
                Profit += list.get(i).get(0)*(Capacity/list.get(i).get(1));
                Capacity = 0.0;
                break;
            }else{
                break;
            }
        }
        System.out.printf("%.2f",Profit);

        sc.close();
    }
}
