
public class Relation {
	private String geneNum;
	private String essential;
	private String geneClass;
	private String complex;
	private String phenotype;
	private String motif;
	private String chromNum;
	private String function;
	private String localization;

	public Relation() {

	}

	public void printRelation() {
		System.out.println("Gene ID: " + this.geneNum);
		System.out.println("Essential: " + this.essential);
		System.out.println("Class: " + this.geneClass);
		System.out.println("Complex: " + this.complex);
		System.out.println("Phenotype: " + this.phenotype);
		System.out.println("Motif: " + this.motif);
		System.out.println("Chromosome Number: " + this.chromNum);
		System.out.println("Function: " + this.function);
		System.out.println("Localization: " + this.localization);
		System.out.println();
	}

	public String getGeneNum() {
		return geneNum;
	}

	public void setGeneNum(String geneNum) {
		this.geneNum = geneNum;
	}

	public String getEssential() {
		return essential;
	}

	public void setEssential(String essential) {
		this.essential = essential;
	}

	public String getGeneClass() {
		return geneClass;
	}

	public void setGeneClass(String geneClass) {
		this.geneClass = geneClass;
	}

	public String getComplex() {
		return complex;
	}

	public void setComplex(String complex) {
		this.complex = complex;
	}

	public String getPhenotype() {
		return phenotype;
	}

	public void setPhenotype(String phenotype) {
		this.phenotype = phenotype;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public String getChromNum() {
		return chromNum;
	}

	public void setChromNum(String chromNumber) {
		this.chromNum = chromNumber;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getLocalization() {
		return localization;
	}

	public void setLocalization(String localization) {
		this.localization = localization;
	}
}
