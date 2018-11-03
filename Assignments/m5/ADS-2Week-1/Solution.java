import java.util.ArrayList;
import java.util.Scanner;
class PageRank {

}

class WebSearch {

}


public class Solution {
	Solution() {
		//check style purpose.
	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = Integer.parseInt(scan.nextLine());
		int vertices = n;
		Digraph d = new Digraph(n);
		for (int i = 0; i < n; i++) {
			String[] tokens = scan.nextLine().split(" ");
			int id = Integer.parseInt(tokens[0]);
			for (int j = 1; j < tokens.length; j++) {
				d.addEdge(id, Integer.parseInt(tokens[i]));
			}
		}
		System.out.println(d.V() + " vertices, " + d.E() + " edges ");
		for (int i = 0; i < vertices; i++) {
			System.out.println(i + ": ");
			for (int j : d.adj(i)) {
				System.out.println(j + " ");
			}
			System.out.println();
		}
		// read the first line of the input to get the number of vertices

		// iterate count of vertices times
		// to read the adjacency list from std input
		// and build the graph


		// Create page rank object and pass the graph object to the constructor

		// print the page rank object

		// This part is only for the final test case

		// File path to the web content
		String file = "WebContent.txt";

		// instantiate web search object
		// and pass the page rank object and the file path to the constructor

		// read the search queries from std in
		// remove the q= prefix and extract the search word
		// pass the word to iAmFeelingLucky method of web search
		// print the return value of iAmFeelingLucky

	}
}
