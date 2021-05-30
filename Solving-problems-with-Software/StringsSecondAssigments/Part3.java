
/**
 * Write a description of Part2 here.
 * 
 * @crazyozzy aka KDA
 * @0.0.0
 */
public class Part3 {
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

    public int countGenes (String dna) {
	int tempIndex = dna.indexOf("ATG");
	int counter = 0;

	System.outprintln("DNA string: " + dna);

	if (tempIndex == -1) {
	    System.out.println("No genes found: there is no ATG");
	    return counter;
	}

	while (tempIndex != -1) {
	    if ( !("".equals(findGene(dna, tempIndex)) )) { counter++; }
	    tempIndex = dna.indexOf("ATG", tempIndex + 1);
	}

	return counter;
    }

    public void printcountGenes (String dna) {
	System.out.println("DNA string: " + dna);
	//System.out.println("Count of genes founded: " + 1 + "\n");
    }

    public void testcountGenes () {
	String st1 = "ATCTATAGATCGCTAATT"; //no ATG
	String st2 = "TACATGATCCTATAATTAGC"; //
	String st3 = "ATGCTATAGTAACTTCG"; //TAG first
	String st4 = "ATCATGCGTTAATAGCGG"; //TAA first
	String st5 = "ATGCGTAATAGCCGGTA"; //no TAA TAG
	String st6 = "ATGCTATAACGATGTACTAGCGCGTAGATGTAG"; //several genes

	printcountGenes(st1);
	/*printcountGenes(st1);
        printcountGenes(st1);
	printcountGenes(st1);
	printcountGenes(st1);
	printcountGenes(st1);*/
    }
    
    public static void main (String[] args) {
        Part3 pt3 = new Part3();
        //pt1.testfindStopCodon();
	pt3.testcountGenes();
    }
}
