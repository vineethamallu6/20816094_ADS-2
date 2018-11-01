import java.util.Scanner;
public final class Solution {
	Solution() {

	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int vertices = Integer.parseInt(scan.nextLine());
		int edges = Integer.parseInt(scan.nextLine());
		Graph g = new Graph(vertices);
		for (int i = 0; i < edges; i++) {
			String[] tokens = scan.nextLine().split(" ");
			g.addEdge(Integer.parseInt(tokens[0]),
             Integer.parseInt(tokens[1]));
        }
        DirectedCycle dc = new DirectedCycle(g);
        if (dc.isBipartite()) {
            System.out.println("Graph is bipartite");
        } else {
            System.out.println("Graph is not a bipartite");

		}
	}
}