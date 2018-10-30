import java.util.Scanner;
interface Graph {
    public int V();
    public int E();
    public void addEdge(int v, int w);
    public Iterable<Integer> adj(int v);
    public boolean hasEdge(int v, int w);
}
class GraphADT implements Graph{
    private int v;
    private int e;
    private Bag<Integer>[] adj;
    GraphADT() {

    }
    GraphADT(int v1) {
        this.v = v1;
        this.e = 0;
        adj = (Bag<Integer>[]) new Bag[v1];
        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<Integer>();
        }
    }
    public int V() {
        return v;
    }

    public int E() {
        return e;
    }
     public int vertices() {
        return v;
    }
     public int edges() {
        return e;
    }
    public Bag<Integer>[] getadj() {
        return adj;
    }
    public void addEdge(int v, int w) {
    	if (v == w) {
    		return;
    	}
    	if (!hasEdge(v, w)) {
    		e++;
    	}
        adj[v].add(w);
        adj[w].add(v);
    }
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
    public boolean hasEdge(int v, int w) {
    	for (int i: adj[v]) {
    		if (i == w) {
    			return true;
    		}
    	}
    	return false;
    }
public void listDisplay(final int v1, final int e1, final String[] tokens) throws Exception {
	if (e1 <= 1 && v1 <= 1) {
		System.out.println(vertices() + " vertices" + ", " + edges() + " edges");
		throw new Exception("No edges");
	} else {
		System.out.println(vertices() + " vertices" + ", " + edges() + "edges");
		for (int i = 0; i < tokens.length; i++) {
			String str = "";
			str = tokens[i] + ": ";
			for (int k : adj(i)) {
				str = str + tokens[k] + " ";
			}
			System.out.println(str);
		}
	}

}
public void matrixDisplay(final int v1, final int e1) throws Exception {
        if (e1 <= 1 && v1 <= 1) {
            System.out.println(vertices() + " vertices" + ", "
                + edges() + " edges");
            throw new Exception("No edges");
        } else {
            System.out.println(vertices() + " vertices" + ", "
                + edges() + " edges");
            int[][] display = new int[v1][v1];
            for (int i = 0; i  < v1; i++) {
                for (int j = 0; j < v1; j++) {
                    if (hasEdge(i, j)) {
                        display[i][j] = 1;
                    }
                }
            }

            for (int i = 0; i < v1; i++) {
                for (int j = 0; j < v1; j++) {
                    System.out.print(display[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}

class Solution {
	Solution() {

	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		GraphADT gd = new GraphADT();
		String type = scan.nextLine();
		int vertices = Integer.parseInt(scan.nextLine());
		int edges = Integer.parseInt(scan.nextLine());
		// if (edges == 0) {
		// 	System.out.println("No edges");
		// }

		String[] line = scan.nextLine().split(",");
		//System.out.println(Arrays.toString(line));
		gd = new GraphADT(vertices);
		for (int i = 0; i < edges; i++) {
			String[] tokens = scan.nextLine().split(" ");
			//System.out.println(Arrays.toString(tokens));
			gd.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
		}
		switch (type) {
			case "List":
			try {
				gd.listDisplay(vertices, edges, line);
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;
			case "Matrix":
			try {
				gd.matrixDisplay(vertices, edges);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		}

	}
}
