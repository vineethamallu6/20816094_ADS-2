import java.util.Scanner;
public final class Solution {
	Solution() {
		//ckeck style purpose.
	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int vertices = Integer.parseInt(scan.nextLine());
		int edges = Integer.parseInt(scan.nextLine());
		EdgeWeightedGraph ewg = new EdgeWeightedGraph(vertices);
		while (scan.hasNext()) {
			String[] array = scan.nextLine().split(" ");
			Edge ed = new Edge(Integer.parseInt(array[0]), Integer.parseInt(array[1]), Double.parseDouble(array[2]));
			ewg.addEdge(ed);
		}
		LazyPrimMST mst = new LazyPrimMST(ewg);
		System.out.printf("%.5f\n", mst.weight());
	}
}
