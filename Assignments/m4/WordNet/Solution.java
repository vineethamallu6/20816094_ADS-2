//import java.util.Scanner;
class Solution {
	Solution() {

	}
	public static void main(String[] args) {
		//Scanner scan = new Scanner(System.in);
		String synFile = StdIn.readString();
		String hyperFile = StdIn.readString();
		WordNet wn = new WordNet(synFile, hyperFile);
		String token = StdIn.readString();
		switch(token) {
			case "Graph":
			break;
			case "Queries":
			break;
			default:
			break;
		}

	}
}