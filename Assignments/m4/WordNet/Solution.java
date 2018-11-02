//import java.util.Scanner;
class Solution {
	Solution() {

	}
	public static void main(String[] args) {
		//Scanner scan = new Scanner(System.in);
		String synFile = StdIn.readString();
		String hyperFile = StdIn.readString();
		//WordNet wn = new WordNet(synFile, hyperFile);
		String token = StdIn.readString();
		try {
			if (token.equals("Graph")) {
				WordNet wn = new WordNet(synFile, hyperFile);
				wn.display();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
		if (token.equals("Queries")) {
			WordNet wnq = new WordNet(synFile, hyperFile);
			while (StdIn.hasNextLine()) {
			String[] array = StdIn.readString().split(" ");
			if (array[0].equals("null")) {
				System.out.println("IllegalArgumentException");
			}
			System.out.println("distane = " + wnq.distance(array[0], array[1]) + ", ancestor = " + wnq.sap(array[0], array[1]));
		}
	}
} catch (Exception e) {
                System.out.println(e.getMessage());
            }
}


	}
