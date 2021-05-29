
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurrences (String stringa, String stringb) {
        int currPos = stringb.indexOf(stringa);
        int counter = 0;
        
        if (currPos != -1) {
            counter++;
        }
        else return false;
        
        while (currPos < stringb.lastIndexOf(stringa)) {
            counter++;
            currPos = stringb.indexOf(stringa, currPos + stringa.length());
        }
        
        if (counter > 1) {
            return true;
        }
        
        return false;
    }
    
    public String lastPart (String stringa, String stringb) {
        int indexOfa = stringb.indexOf(stringa);
        
        if (indexOfa != -1) {
            return stringb.substring(indexOfa + stringa.length());
        }
        
        return stringb;
    }
    
    public void testing () {
        String st1a = "23";
        String st1b = "123452345";
        
        String st2a = "ABC";
        String st2b = "JFSABCJSWWIOQD";
        
        String st3a = "fsdaf";
        String st3b = "fahsjkhetqwuhvxbgsajshwueoqr";
        
        System.out.println("String a: " + st1a + "\tString b: " + st1b);
        System.out.println("Is b contains a two times or more: " + twoOccurrences(st1a, st1b));
        System.out.println(lastPart(st1a, st1b) + "\n");
        
        System.out.println("String a: " + st2a + "\tString b: " + st2b);
        System.out.println("Is b contains a two times or more: " + twoOccurrences(st2a, st2b));
        System.out.println(lastPart(st2a, st2b) + "\n");
        
        System.out.println("String a: " + st3a + "\tString b: " + st3b);
        System.out.println("Is b contains a two times or more: " + twoOccurrences(st3a, st3b));
        System.out.println(lastPart(st3a, st3b) + "\n");
    }
    
    public static void main (String[] args) {
        Part3 pr3 = new Part3();
        
        pr3.testing();
    }
}
