/*public class SAP {

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G)

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w)

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w)

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w)

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w)

    // do unit testing of this class
    public static void main(String[] args)
}*/
/**
 * Class for sap.
 */
public class SAP {
    /**
     * { var_description }.
     */
    private Digraph dg;
    /**
     * { var_description }.
     */
    private BreadthFirstDirectedPaths[] bfs;

    /**
    * constructor takes a digraph (not necessarily a DAG).
    **/
    public SAP(final Digraph d) {
        this.dg = new Digraph(d);
        bfs = new BreadthFirstDirectedPaths[this.dg.V()];
    }

    /**
     * length of shortest ancestral path between v and w.
     * -1 if no such path
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int length(final int v, final int w) {
        int length = Integer.MAX_VALUE;

        for (int i = 0; i < dg.V(); i++) {
            if (bfs[v].hasPathTo(i) && bfs[w].hasPathTo(i)) {
                int l = bfs[v].distTo(i) + bfs[w].distTo(i);
                if (l < length) {
                    length = l;
                }
            }
        }

        if (length != Integer.MAX_VALUE) {
            return length;
        } else {
            return -1;
        }
    }

    /**
     * a common ancestor of v and w that participates in a shortest ancestral.
     * path; -1 if no such path
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int ancestor(final int v, final int w) {
        int length = Integer.MAX_VALUE;
        int ancestor = -1;

        for (int i = 0; i < dg.V(); i++) {
            if (bfs[v].hasPathTo(i) && bfs[w].hasPathTo(i)) {
                int l = bfs[v].distTo(i) + bfs[w].distTo(i);
                if (l < length) {
                    length = l;
                    ancestor = i;
                }
            }
        }

        return ancestor;
    }

    /**
     * length of shortest ancestral path between any vertex in v and any.
     * vertex in w; -1 if no such path
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int length(final Iterable<Integer> v, final Iterable<Integer> w) {
        int length = Integer.MAX_VALUE;
        for (int i : v) {
            for (int j : w) {
                int l = length(i, j);
                if (l != -1 && l < length) {
                    length = l;
                }
            }
        }

        assert length != -1;

        if (length != Integer.MAX_VALUE) {
            return length;
        } else {
            return -1;
        }
    }
    /**
     * a common ancestor that participates in shortest ancestral path.
     * -1 if no such path
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int ancestor(final Iterable<Integer> v, final
                        Iterable<Integer> w) {
        int length = Integer.MAX_VALUE;
        int ancestor = -1;

        for (int i : v) {
            for (int j : w) {
                int l = length(i, j);
                if (l != -1 && l < length) {
                    length = l;
                    ancestor = ancestor(i, j);
                }
            }
        }
        assert length != -1;
        return ancestor;
    }
}
