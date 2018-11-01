import java.util.Scanner;
class Solution {
	Solution() {

	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int vertices = Integer.parseInt(scan.nextLine());
		int edges = Integer.parseInt(scan.nextLine());
		DiGraph d = new DiGraph(vertices);
		for (int i = 0; i < edges; i++) {
			String[] tokens = scan.nextLine().split(" ");
			int n1 = Integer.parseInt(tokens[0]);
			int n2 = Integer.parseInt(tokens[1]);
			d.addEdge(n1, n2);
		}
		DirectedCycle dc = new DirectedCycle(d);
		if (dc.hasCycle()) {
			System.out.println("Cycle exists");
		} else {
			System.out.println("Cycle doesn't exist");
		}
	}
}