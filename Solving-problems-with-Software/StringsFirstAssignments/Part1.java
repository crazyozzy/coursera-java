
/**
 * Finding a DNA sequence in string.
 * 
 * @crazyozzy aka KDA (your name) 
 * @0.0.0 (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene (String dna){
        int startIndex = 0;
        int stopIndex = 0;
        
        startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return "";
        };
        
        stopIndex = dna.indexOf("TAA", startIndex + 3);
        if (stopIndex == -1) {
            return "";
        };
        
        if ((stopIndex + 3 - startIndex) % 3 == 0) {
            return dna.substring(startIndex, stopIndex + 3);
        };
        
        return "";
    }
    
    public void testSimpleGene() {
        String st1 = "GTFSDQWTERTGDFDSTAAGVCSFE";
        String st2 = "AFWRATGFSDQWWASFDDERQWREWFAS";
        String st3 = "ASDASFWQWFVZXASFWRQVVGAXDFTEQ";
        String st4 = "ASDFATGHYTSDFTAAKEQRIFASFQ";
        String st5 = "AFRWFATGFSARWTAAFFDQER";
        
        System.out.println(st1);
        System.out.println("Founded DNA: " + findSimpleGene(st1) + "\n");
        
        System.out.println(st2);
        System.out.println("Founded DNA: " + findSimpleGene(st2) + "\n");
        
        System.out.println(st3);
        System.out.println("Founded DNA: " + findSimpleGene(st3) + "\n");
        
        System.out.println(st4);
        System.out.println("Founded DNA: " + findSimpleGene(st4) + "\n");
        
        System.out.println(st5);
        System.out.println("Founded DNA: " + findSimpleGene(st5) + "\n");
    }
    
    public static void main (String[] args) {
        Part1 pt1 = new Part1();
        pt1.testSimpleGene();
    }
}
