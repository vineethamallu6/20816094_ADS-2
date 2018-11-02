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
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		if (token.equals("Queries")) {
			String[] array = StdIn.readString().split(" ");
			if (array[0].equals("null")) {
				System.out.println("IllegalArgumentException");
			}
		}
		// switch(token) {
		// 	case "Graph":
		// 	break;
		// 	case "Queries":
		// 	break;
		// 	default:
		// 	break;
		}

	}
