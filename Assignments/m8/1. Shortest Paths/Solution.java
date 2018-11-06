import java.util.Scanner;
import java.util.HashMap;
/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    protected Solution() {
    //check style.
    }
    /**
     * main method.
     *
     * @param      args  The arguments.
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] integers = scan.nextLine().split(" ");
        int vertices = Integer.parseInt(integers[0]);
        int edges = Integer.parseInt(integers[1]);
        String[] str = scan.nextLine().split(" ");
        EdgeWeightedGraph ewg = new EdgeWeightedGraph(vertices);
        HashMap<String, Integer> hash = new HashMap<String, Integer>();
        for (int i = 0; i < str.length; i++) {
            hash.put(str[i], i);
        }
        for (int i = 0; i < edges; i++) {
            String[] line = scan.nextLine().split(" ");
            ewg.addEdge(new Edge(hash.get(line[0]),
                hash.get(line[1]),
                Double.parseDouble(line[2])));
        }
        int n = Integer.parseInt(scan.nextLine());
        for (int i = 0; i < n; i++) {
            String[] query = scan.nextLine().split(" ");
            int m = hash.get(query[0]);
            DijkstraUndirectedSP dsp = new DijkstraUndirectedSP(ewg, m);
            if (dsp.hasPathTo(hash.get(query[1]))) {
                System.out.println((int) dsp.distTo(hash.get(query[1])));
            }
        }
    }
}
