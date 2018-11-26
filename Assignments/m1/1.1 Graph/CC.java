public class CC {
    private boolean[] marked;   // marked[v] = has vertex v been marked?
    private int[] id;           // id[v] = id of connected component containing v
    //private int[] size;         // size[id] = number of vertices in given component
    private int count;          // number of connected components

    /**
     * Computes the connected components of the undirected graph {@code G}.
     *
     * @param G the undirected graph
     */
    public CC(Graph g, int s) {
        marked = new boolean[g.vertices()];
        id = new int[g.vertices()];
       // size = new int[G.V()];
        for (int v = 0; v < g.vertices(); v++) {
            if (!marked[v]) {
                dfs(g, v);
                count++;
            }
        }
    }

    /**
     * Computes the connected components of the edge-weighted graph {@code G}.
     *
     * @param G the edge-weighted graph
     */
    public int id(int v) {
        return id[v];
    }


    // depth-first search for a Graph
    private void dfs(Graph G, int v) {
        marked[v] = true;
        id[v] = count;
       // size[count]++;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
        }



    /**
     * Returns the number of connected components in the graph {@code G}.
     *
     * @return the number of connected components in the graph {@code G}
     */
    public int count() {
        return count;
    }
    public boolean percolate() {
        if (count > 1) {
            return false;
        } else {
            return true;
        }
    }
}
