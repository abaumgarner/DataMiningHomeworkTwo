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

		// printAra(araData);

		localizingAra(araData, araTest);

		saveToFile(araTest);

		accuracy();

	}

	private static void accuracy() throws FileNotFoundException {
		Scanner outText = new Scanner(new File("out.txt"));
		Scanner keyText = new Scanner(new File("keys.txt"));
		int total = 0;
		int correct = 0;

		ArrayList<String[]> araOut = new ArrayList<String[]>();
		ArrayList<String[]> araKeys = new ArrayList<String[]>();

		while (outText.hasNext()) {
			String str = outText.nextLine();
			String[] tokens = str.split(",");
			araOut.add(tokens);
			araOut.trimToSize();
		}

		while (keyText.hasNext()) {
			String str = keyText.nextLine();
			String[] tokens = str.split(",");
			araKeys.add(tokens);
			araKeys.trimToSize();
		}

		for (int i = 0; i < araOut.size(); i++) {
			for (int j = 0; j < araKeys.size(); j++) {
				if (araOut.get(i)[0].equals(araKeys.get(j)[0]) && araOut.get(i)[1].equals(araKeys.get(j)[1])) {
					correct++;
				}
			}
			total++;
		}
		System.out.println("Number correct: " + correct);
		System.out.println("Number total: " + total);
		float acc = ((float) correct / total) * 100;
		System.out.println("Accuracy: " + acc + "%");
	}

	private static void localizingAra(ArrayList<Relation> araData, ArrayList<Relation> araTest) {
		int x, y, maxWeight = 0;

		int index = 0;
		for (x = 0; x < araTest.size(); x++) {
			Relation testRel = araTest.get(x);

			for (y = 0; y < araData.size(); y++) {
				int curWeight = 0;
				Relation dataRel = araData.get(y);

				if (testRel.getGeneNum().equals(dataRel.getGeneNum())) {
					curWeight += 10000;
				}
				if (testRel.getComplex().equals(dataRel.getComplex()) && !testRel.getComplex().equals("?")) {
					curWeight += 1000;
					// System.out.println("Complex found at gene: " +
					// dataRel.getGeneNum());
				}
				if (testRel.getGeneClass().equals(dataRel.getGeneClass()) && !testRel.getGeneClass().equals("?")) {
					curWeight += 100;
					// System.out.println("Class found at gene: " +
					// dataRel.getGeneNum());
				}
				if (testRel.getMotif().equals(dataRel.getMotif()) && !testRel.getMotif().equals("?")) {
					curWeight += 10;
					// System.out.println("Motif found at gene: " +
					// dataRel.getGeneNum());
				}
				if (curWeight > maxWeight) {
					index = y;
					maxWeight = curWeight;
				}
			}

			// System.out.println("Localization is " +
			// araData.get(index).getLocalization());
			araTest.get(x).setLocalization(araData.get(index).getLocalization());
			index = 0;
			maxWeight = 0;
		}

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
