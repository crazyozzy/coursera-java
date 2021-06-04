
/**
 * Write a description of Part2 here.
 * 
 * @crazyozzy aka KDA
 * @0.0.0
 */
import edu.duke.*;

public class Part1 {
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

    public StorageResource getAllGenes (String dna) {
	int tempIndex = dna.indexOf("ATG");
	StorageResource sr = new StorageResource();
	String foundedGene = "";

	if (tempIndex == -1) {
	    System.out.println("No genes found: there is no ATG");
	}

	while (tempIndex != -1) {
	    //System.out.println("Founded DNA gene: " + findGene(dna, tempIndex));
	    foundedGene = findGene(dna, tempIndex);
	    if (foundedGene.length() > 0) { sr.add(foundedGene); }
	    //sr.add(findGene(dna, tempIndex));
	    tempIndex = dna.indexOf("ATG", tempIndex + 1);
	}

	return sr;
    }

    public double cgRatio (String dna) {
	double counter = 0;
	int index = dna.indexOf("C");

	while (true) {
	    if (index == -1) { break; }

	    counter++;
	    index = dna.indexOf("C", index + 1);
	}

	index = dna.indexOf("G");
	while (true) {
	    if (index == -1) { break; }

	    counter++;
	    index = dna.indexOf("G", index + 1);
	}

	return counter / dna.length();
    }

    public void processGenes (StorageResource sr) {
	int counter = 0;
	int cgcounter = 0;
	int geneLength = 0;

	for (String str : sr.data()) {
	    if (str.length() > 60) {
		System.out.println("Gene longer than 60: " + str);
		counter++;
	    }

	    if (cgRatio(str) > 0.35) { cgcounter++; }
            
	    if (str.length() > geneLength) { geneLength = str.length(); }
	}

	System.out.println("60 count: " + counter);
	System.out.println("CG's count: " + cgcounter);
    }

    public void testfindGene () {
	StorageResource sr = new StorageResource();
	/*
	sr.add("ATCTATAGATCGCTAATT"); //no ATG
	sr.add("TACATGATCCTATAATTAGC"); //
	sr.add("ATGCTATAGTAACTTCG"); //TAG first
	sr.add("ATCATGCGTTAATAGCGG"); //TAA first
	sr.add("ATGCGTAATAGCCGGTA"); //no TAA TAG
	sr.add("ATGCTATAACGATGTACTAGCGCGTAGATGTAG"); //several genes
	*/

	FileResource fr = new FileResource("brca1line.fa");
	sr.add((fr.asString()).toUpperCase());
	int countGene = 0;

	for (String str : sr.data()) {
	    //System.out.println("DNA string: " + str);
	    StorageResource srGene = getAllGenes(str);
	    for (String strGene : srGene.data()) {
                System.out.println("Founded gene: " + strGene);
		System.out.println("C G ration: " + cgRatio(strGene));
		countGene++;
	    }
	}
	
	System.out.println("Genes count: " + countGene);
	//processGenes(sr);
    }
    
    public static void main (String[] args) {
        Part1 pt1 = new Part1();
        //pt1.testfindStopCodon();
	pt1.testfindGene();
    }
}
