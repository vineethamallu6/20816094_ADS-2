import java.util.Scanner;
public class Solution {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		//String input = scan.nextLine().split("")
		int n = Integer.parseInt(scan.nextLine());
		int k = Integer.parseInt(scan.nextLine());
		EdgeWeightedGraph ewg = new EdgeWeightedGraph(n);
		for (int i = 0; i < k; i++) {
			String[] line = scan.nextLine().split(" ");
			Edge e = new Edge(Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2]));
			ewg.addEdge(e);
		}
		// Self loops are not allowed...
		// Parallel Edges are allowed...
		// Take the Graph input here...

		String caseToGo = scan.nextLine();
		switch (caseToGo) {
		case "Graph":
			//Print the Graph Object.
		System.out.println(ewg);

			break;

		case "DirectedPaths":
		String[] path = scan.nextLine().split(" ");
		int s = Integer.parseInt(path[0]);
		int d = Integer.parseInt(path[1]);
		DijkstraUndirectedSP dsp = new DijkstraUndirectedSP(ewg, s);
		if (dsp.hasPathTo(d)) {
                System.out.println(dsp.distTo(d));
            } else {
            	System.out.println("No Path Found.");
            }

			// Handle the case of DirectedPaths, where two integers are given.
			// First is the source and second is the destination.
			// If the path exists print the distance between them.
			// Other wise print "No Path Found."
			break;

		case "ViaPaths":
			// Handle the case of ViaPaths, where three integers are given.
			// First is the source and second is the via is the one where path should pass throuh.
			// third is the destination.
			// If the path exists print the distance between them.
			// Other wise print "No Path Found."
			break;

		default:
			break;
		}

	}
}