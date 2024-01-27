package ADI.Hashtable;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;

public class HashtableMain {
    public static void main(String[] args) {
        Hashtable<String, Integer> hash = new Hashtable<>();
        
        hash.put("naman", 1);
        hash.put("nikhil", 2);

        System.out.print("contains naman or not " + hash.containsKey("naman"));
        System.out.println();

        System.out.println("get value of key naman " + hash.get("naman"));
        
        System.out.println(hash.remove("naman"));
        
        Enumeration<String> eNum = hash.keys(); // we are using Enumeration here to store the hashkeys seperately 
        
        while(eNum.hasMoreElements()){
            String key = eNum.nextElement();
            System.out.print("key : " + key + " value : " + hash.get(key));
        }

        System.out.println();

        System.out.println("Printing the hastable : " + hash);

        System.out.println();

        for (Map.Entry<String, Integer> e : hash.entrySet()) System.out.println(e.getKey() + " " + e.getValue());
    }
}
