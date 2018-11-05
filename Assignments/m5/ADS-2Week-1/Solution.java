//import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
/**
 * Class for page rank.
 */
class PageRank {
    /**
     * { var_description }.
     */
    private static final int THOUSAND = 1000;
    /**
     * { var_description }.
     */
    private Digraph dg;
    /**
     * { var_description }.
     */
    private Digraph reversedg;
    /**
     * { var_description }.
     */
    private HashMap<Integer, Double> pageMap;
    /**
     * Constructs the object.
     *
     * @param      d     { parameter_description }.
     */
    PageRank(final Digraph d) {
        this.dg = d;
        this.reversedg = d.reverse();
        pageMap = new HashMap<Integer, Double>();
        //lb = new LinearProbingHashST<Integer, Double>();
    }
    /**
     * { function_description }.
     */
    public void pageRank() {
        // double prevpr = 0.25;
        //
        // for (int i = 0; i < dg.V(); i++) {
        //     lb.put(i, 0.25);
        // }
        double v = (double) dg.V();

        double initialVal = (1 / v);
        for (int i = 0; i < dg.V(); i++) {
            if (dg.indegree(i) == 0) {
                pageMap.put(i, 0.0);
            } else {
                pageMap.put(i, initialVal);
            }
        }
        int rank;
        Double[] pagerank = new Double[dg.V()];
        for (int i = 0; i < THOUSAND; i++) {
            for (int j = 0; j < dg.V(); j++) {
                double temp = 0.0;
                 //double temp1 = 0.0;
                 for (int k : reversedg.adj(j)) {
                     double val = pageMap.get(k);
                     double p = dg.outdegree(k);
                     temp += (val / p);
                 }
                pagerank[j] = temp;
            }
            for (int n = 0; n < dg.V(); n++) {
                pageMap.put(n, pagerank[n]);
            }
         }
     }

    // }
    /**.
     * { function_description }.
     */
    public void tostring() {
        for (int i = 0; i < dg.V(); i++) {
            String s = "";
            s += String.valueOf(i) + " - " + pageMap.get(i);
            System.out.println(s);
        }
    }
}

// /**
//  * Class for web search.
//  */
// class WebSearch {
//     /**
//      * Constructs the object.
//      */
//     WebSearch() {

//     }

// }

/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    protected Solution() {
        //check style purpose.
    }
    /**
     * { function_description }.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = Integer.parseInt(scan.nextLine());
        int vertices = n;
        Digraph dg = new Digraph(n);
        Digraph d = new Digraph(n);

        for (int i = 0; i < n; i++) {
            String[] tokens = scan.nextLine().split(" ");
            if (tokens.length == 1) {
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        dg.addEdge(i, j);
                    }
                }
            } else {
                for (int j = 1; j < tokens.length; j++) {
                    dg.addEdge(Integer.parseInt(tokens[0]),
                     Integer.parseInt(tokens[j]));
                    d.addEdge(Integer.parseInt(tokens[0]),
                        Integer.parseInt(tokens[j]));
                }
            }
        }
        System.out.println(d.toString());
        // System.out.println(d.V() + " vertices, " + d.E() + " edges ");
        // for (int i = 0; i < vertices; i++) {
        //  System.out.print(i + ": ");
        //  for (int j : d.adj(i)) {
        //      System.out.print(j + " ");
        //  }
        //  System.out.println();
        // }
        PageRank p = new PageRank(dg);
        p.pageRank();
    p.tostring();
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

