import java.util.Scanner;
class Graph {
    /**
     *the tokens array is to.
     *store all the keys.
     */
    //private String[] tokens;
    /**
     *matrix to store the realtion.
     *between two vertices.
     */
    private int[][] matrix;
    /**
     *the variable to store number.
     *of vertices.
     */
    private int vertices;
    /**
     *the variable to store edges of graph.
     */
    private int edges;
    /**
     *the constructor to initialize the class variables.
     */
    Graph(final int v) {
    	matrix = new int[v+2][v+2];
        //edges = 0;
    }

     public int vertices() {
        return vertices;
    }
    /**
     *the method is to add an edge between two vertices.
     * @param      vertexOne  The vertex one
     * @param      vertexTwo  The vertex two
     * because we use has next method.
     */
    public void addEdge(final int vertexOne, final int vertexTwo) {
        if (vertexOne != vertexTwo) {
            if (!hasEdge(vertexOne, vertexTwo)) {
                matrix[vertexOne][vertexTwo] = 1;
                matrix[vertexTwo][vertexOne] = 1;
                edges++;
            }
        }
    }
    /**
     *the method is check whether there is a connection between two given.
     *vertices the time complexity is O(1)
     * @param      vertexOne  The vertex one
     * @param      vertexTwo  The vertex two
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(final int vertexOne, final int vertexTwo) {
        if (matrix[vertexOne][vertexTwo] == 1) {
            return true;
        }
        return false;
    }
    /**
     *the method is to printMat the string format of graph the time complexity
     *will be O(N^2)
     *N is the vertices here.
     */

    public int[] adj(final int v) {
        return matrix[v];
    }
}

public final class Solution {
	private Solution() {
	}
		public static void main(String[] args) {
			Scanner scan = new Scanner(System.in);
			int n = Integer.parseInt(scan.nextLine());
			Graph p = new Graph(n);
			while (scan.hasNext()) {
				String[] tokens = scan.nextLine().split(" ");
				p.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
			}
			CC c = new CC(p, n);
			System.out.println(c.percolate());
		}
	}
