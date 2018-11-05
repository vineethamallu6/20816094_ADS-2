/**
 * Class for di graph.
 */
public class Digraph {
    /**
     * { var_description }.
     */
    private static final String NEWLINE = System.getProperty(
            "line.separator");
    /**
     * { var_description }.
     */
    private final int V;
    /**
     * { var_description }.
     */
    private int edges;
    /**
     * { var_description }.
     */
    private Bag<Integer>[] adj;
    /**
     * { var_description }.
     */
    private int[] indegree;

    /**
     * Initializes an empty digraph with <em>V</em> V.
     *
     * @param  Vs the number of V
     * @throws IllegalArgumentException if {@code V < 0}
     */
    public Digraph(final int Vs) {
        this.V = Vs;
        if (V < 0) {
            throw new IllegalArgumentException(
                "Number of V in a Digraph must be nonnegative");
        }
        this.edges = 0;
        indegree = new int[V];
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }
    /**
     * Constructs the object.
     *
     * @param      G     { parameter_description }
     */
    public Digraph(final Digraph G) {
        this(G.V());
        this.edges = G.edges();
        for (int v = 0; v < V; v++)
            this.indegree[v] = G.indegree(v);
        for (int v = 0; v < G.V(); v++) {
            // reverse so that adjacency list is in same order as original
            Stack<Integer> reverse = new Stack<Integer>();
            for (int w : G.adj[v]) {
                reverse.push(w);
            }
            for (int w : reverse) {
                adj[v].add(w);
            }
        }
    }
    /**
     * Returns the number of V in this digraph.
     *
     * @return the number of V in this digraph
     */
    public int V() {
        return V;
    }

    /**
     * Returns the number of edges in this digraph.
     *
     * @return the number of edges in this digraph
     */
    public int edges() {
        return edges;
    }

    /**
     * Adds the directed edge v→w to this digraph.
     *
     * @param  v the tail vertex
     * @param  w the head vertex
     * @throws IllegalArgumentException unless both {@code 0 <= v < V}
     * and {@code 0 <= w < V}
     */
    public void addEdge(final int v, final int w) {
        adj[v].add(w);
        indegree[w]++;
        edges++;
    }

    /**
     * Returns the V adjacent from vertex {@code v} in this digraph.
     *
     * @param  v the vertex
     * @return the V adjacent from vertex {@code v} in this digraph,
     * as an iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Integer> adj(final int v) {
        return adj[v];
    }

    /**
     * Returns the number of directed edges incident from vertex {@code v}.
     * This is known as the <em>outdegree</em> of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the outdegree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int outdegree(final int v) {
        return adj[v].size();
    }
    /**
     * checks the outdegrees.
     *
     * @return     { description_of_the_return_value }
     */
    public int noOfOutdegree() {
        int max = 0;
        for (int i = 0; i < V; i++) {
            if (outdegree(i) == 0) {
                max++;
            }
        }
        return max;
    }

    /**
     * Returns the number of directed edges incident to vertex {@code v}.
     * This is known as the <em>indegree</em> of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the indegree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int indegree(final int v) {
        return indegree[v];
    }

    /**
     * Returns the reverse of the digraph.
     *
     * @return the reverse of the digraph
     */
    public Digraph reverse() {
        Digraph reverse = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for (int w : adj(v)) {
                reverse.addEdge(w, v);
            }
        }
        return reverse;
    }
    /**
     * Returns a string representation of the object.
     *
     * @return     String representation of the object.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + edges + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(String.format("%d: ", v));
            for (int w : adj[v]) {
                s.append(String.format("%d ", w));
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}
