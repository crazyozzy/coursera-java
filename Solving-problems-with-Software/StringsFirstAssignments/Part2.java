
/**
 * Write a description of Part2 here.
 * 
 * @crazyozzy aka KDA
 * @0.0.0
 */
public class Part2 {
    public String findSimpleGene (String dna, String startCodon, String stopCodon){
        int startIndex = 0;
        int stopIndex = 0;
        
        startIndex = dna.indexOf(startCodon);
        if (startIndex == -1) {
            return "";
        };
        
        stopIndex = dna.indexOf(stopCodon, startIndex + 3);
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
        String st4 = "AAATGCCCTAACTAGATTAAGAAACC";
        String st5 = "AFRWFATGFSARWTAAFFDQER";
        
        String startCodon = "ATG";
        String stopCodon = "TAA";
        
        System.out.println(st1);
        System.out.println("Founded DNA: " + findSimpleGene(st1, startCodon, stopCodon) + "\n");
        
        System.out.println(st2);
        System.out.println("Founded DNA: " + findSimpleGene(st2, startCodon, stopCodon) + "\n");
        
        System.out.println(st3);
        System.out.println("Founded DNA: " + findSimpleGene(st3, startCodon, stopCodon) + "\n");
        
        System.out.println(st4);
        System.out.println("Founded DNA: " + findSimpleGene(st4, startCodon, stopCodon) + "\n");
        
        System.out.println(st5);
        System.out.println("Founded DNA: " + findSimpleGene(st5, startCodon, stopCodon) + "\n");
    }
    
    public static void main (String[] args) {
        Part2 pt2 = new Part2();
        pt2.testSimpleGene();
    }
}
