package ADI.Hashtable;

import java.util.Enumeration;
import java.util.Hashtable;

public class HashtableMain {
    public static void main(String[] args) {
        Hashtable<String, Integer> hash = new Hashtable<>();
        
        hash.put("naman", 1);
        hash.put("nikhil", 2);
        System.out.print(hash.containsKey("naman"));
        System.out.println();
        
        
        Enumeration<String> eNum = hash.keys(); // we are using Enumeration here to store the hashkeys seperately 
        
        while(eNum.hasMoreElements()){
            System.out.print(eNum.nextElement() + " ");
        }
    }
}
