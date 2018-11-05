import java.util.ArrayList;
import java.util.Scanner;
class PageRank {
	private Digraph dg;
	private LinearProbingHashST<Integer, Double>  lb;
	private int size = 0;
	PageRank(Digraph d) {
		this.dg = d;
		lb = new LinearProbingHashST<Integer, Double>();
	}
	public void PageRank() {
        // double prevpr = 0.25;
        for (int i = 0; i < dg.V(); i++) {
            lb.put(i, 0.25);
        }
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < dg.V(); j++) {
                double temp = 0.0;
                double temp1 = 0.0;
                for (int k : dg.adj(j)) {
                    //int cnt = 0;
                    if (k == i) {
                        temp = lb.get(k) / dg.outdegree(k) + 1;
                        temp1 = temp1 + temp;
                    } else {
                        temp = lb.get(j) / dg.outdegree(j);
                        temp1 = temp1 + temp;
                    }
                    //System.out.println(finaltemp);
                }
                lb.put(j, temp1);
            }
        }
    }
    /**.
     * { function_description }
     */
    public void string() {
        for (int i : lb.keys()) {
            String s = "";
            s += String.valueOf(i) + " - " + lb.get(i);
            System.out.println(s);
        }
    }
}


class WebSearch {
	WebSearch() {

	}

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

		for (int i = 0; i < n; i++){
			String[] tokens = scan.nextLine().split(" ");
			int id = Integer.parseInt(tokens[0]);
			for (int j = 1; j < tokens.length; j++) {
				d.addEdge(id, Integer.parseInt(tokens[j]));
			}
		}
		System.out.println(d.toString());
		// System.out.println(d.V() + " vertices, " + d.E() + " edges ");
		// for (int i = 0; i < vertices; i++) {
		// 	System.out.print(i + ": ");
		// 	for (int j : d.adj(i)) {
		// 		System.out.print(j + " ");
		// 	}
		// 	System.out.println();
		// }
		PageRank p = new PageRank(d);
		p.PageRank();
	p.string();
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
