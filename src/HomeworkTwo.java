import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

public class HomeworkTwo {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		Scanner finTest = openFile("Genes_relation.test");
		Scanner finData = openFile("Genes_relation.data");
		// printFile(fin);

		ArrayList<Relation> araData = new ArrayList<Relation>();
		ArrayList<Relation> araTest = new ArrayList<Relation>();

		araData = fillArray(araData, finData);
		finData.close();
		araTest = fillArray(araTest, finTest);
		finTest.close();

		printAra(araTest);

		saveToFile(araTest);

	}

	private static void saveToFile(ArrayList<Relation> ara) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter("out.txt", "UTF-8");
		Relation temp;
		
		for (int i = 0; i < ara.size(); i++) {
			temp = ara.get(i);
			writer.println(temp.getGeneNum() + "," + temp.getLocalization());
		}

	}

	private static void printAra(ArrayList<Relation> ara) {
		Relation temp;
		for (int i = 0; i < ara.size(); i++) {
			temp = ara.get(i);
			temp.printRelation();
		}
	}

	private static ArrayList<Relation> fillArray(ArrayList<Relation> ara, Scanner fin) {
		

		while (fin.hasNext()) {
			String str = fin.nextLine();
			Relation temp = new Relation();
			String[] tokens = str.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

			temp.setGeneNum(tokens[0]);
			temp.setEssential(tokens[1]);
			temp.setGeneClass(tokens[2]);
			temp.setComplex(tokens[3]);
			temp.setPhenotype(tokens[4]);
			temp.setMotif(tokens[5]);
			temp.setChromNum(tokens[6]);
			temp.setFunction(tokens[7]);
			String[] rem = tokens[8].split("\\.");
			temp.setLocalization(rem[0]);

			ara.add(temp);
			ara.trimToSize();
		}

		return ara;
	}

	private static void printFile(Scanner fin) {
		while (fin.hasNext()) {
			System.out.println(fin.nextLine());
		}

	}

	private static Scanner openFile(String str) throws FileNotFoundException {
		Scanner fin = new Scanner(new File(str));
		return fin;
	}

}
