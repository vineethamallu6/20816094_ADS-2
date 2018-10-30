import java.util.Scanner;
/**
 * Interface for graph.
 */
interface Graph {
    /**
     * { function_description }.
     *
     * @return     { description_of_the_return_value }.
     */
    int getVertices();
    /**
     * { function_description }.
     *
     * @return     { description_of_the_return_value }.
     */
    int getEdges();
    /**
     * Adds an edge.
     *
     * @param      v     { parameter_description }.
     * @param      w     { parameter_description }.
     */
    void addEdge(int v, int w);
    /**
     * { function_description }.
     *
     * @param      v     { parameter_description }.
     *
     * @return     { description_of_the_return_value }.
     */
    Iterable<Integer> adj(int v);
    /**
     * Determines if it has edge.
     *
     * @param      v     { parameter_description }.
     * @param      w     { parameter_description }.
     *
     * @return     True if has edge, False otherwise.
     */
    boolean hasEdge(int v, int w);
}
/**
 * Class for graph adt.
 */
class GraphADT implements Graph {
    /**
     * { var_description }.
     */
    private int v;
    /**
     * { var_description }.
     */
    private int e;
    /**
     * { var_description }.
     */
    private Bag<Integer>[] adj;
    /**
     * Constructs the object.
     */
    GraphADT() {

    }
    /**
     * Constructs the object.
     *
     * @param      v1    The v 1.
     */
    GraphADT(final int v1) {
        this.v = v1;
        this.e = 0;
        adj = (Bag<Integer>[]) new Bag[v1];
        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<Integer>();
        }
    }
    /**
     * { function_description }.
     *
     * @return     { description_of_the_return_value }.
     */
    public int getVertices() {
        return v;
    }
    /**
     * { function_description }.
     *
     * @return     { description_of_the_return_value }.
     */

    public int getEdges() {
        return e;
    }
    /**
     * { function_description }.
     *
     * @return     { description_of_the_return_value }.
     */
     public int vertices() {
        return v;
    }
    /**
     * { function_description }.
     *
     * @return     { description_of_the_return_value }.
     */
     public int edges() {
        return e;
    }
    /**
     * { function_description }.
     *
     * @return     { description_of_the_return_value }.
     */
    public Bag<Integer>[] getadj() {
        return adj;
    }
    /**
     * Adds an edge.
     *
     * @param      v1     { parameter_description }.
     * @param      w     { parameter_description }.
     */
    public void addEdge(final int v1, final int w) {
        if (v1 == w) {
            return;
        }
        if (!hasEdge(v1, w)) {
            e++;
        }
        adj[v1].add(w);
        adj[w].add(v1);
    }
    /**
     * { function_description }.
     *
     * @param      v1     { parameter_description }.
     *
     * @return     { description_of_the_return_value }.
     */
    public Iterable<Integer> adj(final int v1) {
        return adj[v1];
    }
    /**
     * Determines if it has edge.
     *
     * @param      v1     { parameter_description }.
     * @param      w     { parameter_description }.
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(final int v1, final int w) {
        for (int i: adj[v1]) {
            if (i == w) {
                return true;
            }
        }
        return false;
    }
    /**
     * { function_description }.
     *
     * @param      v1         The v 1.
     * @param      e1         The e 1.
     * @param      tokens     The tokens.
     *
     * @throws     Exception  { exception_description }.
     */
public void listDisplay(final int v1, final int e1,
    final String[] tokens) throws Exception {
    if (e1 <= 1 && v1 <= 1) {
        System.out.println(vertices() + " vertices" + ", "
            + edges() + " edges");
        throw new Exception("No edges");
    } else {
        System.out.println(vertices() + " vertices" + ", "
            + edges() + " edges");
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
/**
 * { function_description }.
 *
 * @param      v1         The v 1.
 * @param      e1         The e 1.
 *
 * @throws     Exception  { exception_description }.
 */
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
/**
 * Class for solution.
 */

public class Solution {
    /**
     * Constructs the object.
     */
    protected Solution() {

    }
    /**
     * { function_description }.
     *
     * @param      args  The arguments.
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        GraphADT gd = new GraphADT();
        String type = scan.nextLine();
        int vertices = Integer.parseInt(scan.nextLine());
        int edges = Integer.parseInt(scan.nextLine());
        // if (edges == 0) {
        //  System.out.println("No edges");
        // }

        String[] line = scan.nextLine().split(",");
        //System.out.println(Arrays.toString(line));
        gd = new GraphADT(vertices);
        for (int i = 0; i < edges; i++) {
            String[] tokens = scan.nextLine().split(" ");
            //System.out.println(Arrays.toString(tokens));
            gd.addEdge(Integer.parseInt(tokens[0]),
                Integer.parseInt(tokens[1]));
        }
        switch (type) {
            case "List":
            try {
                gd.listDisplay(vertices, edges, line);
            } catch (Exception e) {
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
            default :
            break;
        }

    }
}

