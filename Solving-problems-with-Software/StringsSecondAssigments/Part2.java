
/**
 * Write a description of Part2 here.
 * 
 * @crazyozzy aka KDA
 * @0.0.0
 */
public class Part2 {
	/*
    	public int findStopCodon (String dna, int startIndex, String stopCodon) {
        int tempIndex = dna.indexOf(stopCodon, startIndex);

	if (tempIndex == -1) {
	    return dna.length();
	}

	while (dna.indexOf(stopCodon, tempIndex) != -1) {
	    String tempSubstring = dna.substring(startIndex, tempIndex + 3);

	    if (tempSubstring.length() % 3 == 0) {
	        return tempIndex + stopCodon.length();
	    }

	    tempIndex++;
	}

	return dna.length();
    }

    public String findGene (String dna) {
        String startCodon = "ATG";
	int startIndex = dna.indexOf(startCodon);

	if (startIndex == -1) {
	    return "";
	}

	int dnaLength = dna.length();
	int indexTAA = findStopCodon(dna, startIndex, "TAA");
	int indexTAG = findStopCodon(dna, startIndex, "TAG");

	String geneTAA = dna.substring(startIndex, indexTAA);
	String geneTAG = dna.substring(startIndex, indexTAG);

	if ("TAA".equals(dna.substring(indexTAA - 3, indexTAA)) && (geneTAA.length() <= geneTAG.length())) {
	    return geneTAA;
	}

	if ("TAG".equals(dna.substring(indexTAG - 3, indexTAG))) {
	    return geneTAG;
	}
	
	return "";
    }

    public String findGene (String dna, int startIndex) {
	if (startIndex == -1) {
	    return "";
	}

	int dnaLength = dna.length();
	int indexTAA = findStopCodon(dna, startIndex, "TAA");
	//System.out.println("indexTAA " + indexTAA);
	int indexTAG = findStopCodon(dna, startIndex, "TAG");
        //System.out.println("indexTAG " + indexTAG);


	String geneTAA = dna.substring(startIndex, indexTAA);
	String geneTAG = dna.substring(startIndex, indexTAG);

	if ("TAA".equals(dna.substring(indexTAA - 3, indexTAA)) && (geneTAA.length() <= geneTAG.length())) {
	    return geneTAA;
	}

	if ("TAG".equals(dna.substring(indexTAG - 3, indexTAG))) {
	    return geneTAG;
	}
	
	return "";
    }

    public void testfindStopCodon () {
        String st1 = "ATGCGGCTATAA"; //yes
        String st2 = "GCTATCATGTGTAACGGTAT"; //no mod 3
	String st3 = "ATCATGCGCTTGTACC"; //no TAA

	String startCodon = "ATG";
	String stopCodon = "TAA";

	System.out.println("DNA sequence: " + st1);
	System.out.println("DNA string: " + st1.substring(st1.indexOf(startCodon), findStopCodon(st1, st1.indexOf(startCodon), stopCodon)));

	System.out.println("DNA sequence: " + st2);
	System.out.println("DNA string: " + st2.substring(st2.indexOf(startCodon), findStopCodon(st2, st2.indexOf(startCodon), stopCodon)));

	System.out.println("DNA sequence: " + st3);
	System.out.println("DNA string: " + st3.substring(st3.indexOf(startCodon), findStopCodon(st3, st3.indexOf(startCodon), stopCodon)));

    }

    public void printGene (String dna) {
	System.out.println("DNA sequence: " + dna);
	System.out.println("DNA gene: " + findGene(dna) + "\n");
    }

    public void printAllGenes (String dna) {
	int tempIndex = dna.indexOf("ATG");
	System.out.println("DNA string: " + dna);

	if (tempIndex == -1) {
	    System.out.println("No genes found: there is no ATG");
	}

	while (tempIndex != -1) {
	    System.out.println("Founded DNA gene: " + findGene(dna, tempIndex));
	    tempIndex = dna.indexOf("ATG", tempIndex + 1);
	}
    }

    public void testfindGene () {
	String st1 = "ATCTATAGATCGCTAATT"; //no ATG
	String st2 = "TACATGATCCTATAATTAGC"; //
	String st3 = "ATGCTATAGTAACTTCG"; //TAG first
	String st4 = "ATCATGCGTTAATAGCGG"; //TAA first
	String st5 = "ATGCGTAATAGCCGGTA"; //no TAA TAG
	String st6 = "ATGCTATAACGATGTACTAGCGCGTAGATGTAG"; //several genes

	/*
	printGene(st1);
	printGene(st2);
	printGene(st3);
	printGene(st4);
	printGene(st5);
	*/
	/*
	printAllGenes(st1);
	printAllGenes(st2);
	printAllGenes(st3);
	printAllGenes(st4);
	printAllGenes(st5);
	printAllGenes(st6);
    }
    */

    public int howMany (String stringA, String stringB) {
	int tempIndex = stringB.indexOf(stringA);
	if (tempIndex == -1) { return 0; }

	int counter = 0;

	while (tempIndex != -1) {
	    counter++;
	    tempIndex = stringB.indexOf(stringA, tempIndex + stringA.length());
	}

	return counter;
    }

    public void printhowMany (String stringA, String stringB) {
	System.out.println("String A: " + stringA);
	System.out.println("String B: " + stringB);
	System.out.println("A in B count: " + howMany(stringA, stringB) + "\n");
    }

    public void testhowMany () {
	String st1A = "GAAB";
	String st1B = "ATGAACGAATTGAATC";

	String st2A = "AA";
	String st2B = "ATAAAA";

	String st3A = "12";
	String st3B = "1234512121234512";

	printhowMany(st1A, st1B);
	printhowMany(st2A, st2B);
	printhowMany(st3A, st3B);
    }

    public static void main (String[] args) {
        Part2 pt2 = new Part2();
	pt2.testhowMany();
    }
}
