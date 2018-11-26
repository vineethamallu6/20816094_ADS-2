import java.util.Scanner;
/**
 *  The {@code GraphGenerator} class provides static methods for creating
 *  various graphs, including Erdos-Renyi random graphs, random bipartite
 *  graphs, random k-regular graphs, and random rooted trees.
 *  <p>
 *  For additional documentation, see <a href="https://algs4.cs.princeton.edu/41graph">Section 4.1</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class GraphGenerator {
    // this class cannot be instantiated
    private GraphGenerator() { }

    /**
     * Returns a random simple graph containing {@code V} vertices and {@code E} edges.
     * @param V the number of vertices
     * @param E the number of vertices
     * @return a random simple graph on {@code V} vertices, containing a total
     *     of {@code E} edges
     * @throws IllegalArgumentException if no such simple graph exists
     */
    public static EdgeWeightedGraph simple(int V, int E) {
        if (E > (long) V*(V-1)/2) throw new IllegalArgumentException("Too many edges");
        if (E < 0)                throw new IllegalArgumentException("Too few edges");
        EdgeWeightedGraph G = new EdgeWeightedGraph(V);
        SET<Edge> set = new SET<Edge>();
        while (G.E() < E) {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            int weight = StdRandom.uniform(100);
            Edge e = new Edge(v, w, weight);
            if ((v != w) && !set.contains(e)) {
                set.add(e);
                G.addEdge(e);
            }
        }
        return G;
    }

    /**
     * Returns a random simple Graph on {@code V} vertices, with an
     * edge between any two vertices with probability {@code p}. This is sometimes
     * referred to as the Erdos-Renyi random graph model.
     * @param V the number of vertices
     * @param p the probability of choosing an edge
     * @return a random simple graph on {@code V} vertices, with an edge between
     *     any two vertices with probability {@code p}
     * @throws IllegalArgumentException if probability is not between 0 and 1
     */
    public static EdgeWeightedGraph simple(int V, double p) {
        int weight;
        if (p < 0.0 || p > 1.0)
            throw new IllegalArgumentException("Probability must be between 0 and 1");
        EdgeWeightedGraph G = new EdgeWeightedGraph(V);
        for (int v = 0; v < V; v++) {
            for (int w = v+1; w < V; w++) {
                if (StdRandom.bernoulli(p)) {
                    weight =StdRandom.uniform(100);
                    G.addEdge(new Edge(v, w, weight));
                }
            }
        }
        return G;
    }

    /**
     * Returns the complete graph on {@code V} vertices.
     * @param V the number of vertices
     * @return the complete graph on {@code V} vertices
     */
    public static EdgeWeightedGraph complete(int V) {
        return simple(V, 1.0);
    }

    /**
     * Returns a complete bipartite graph on {@code V1} and {@code V2} vertices.
     * @param V1 the number of vertices in one partition
     * @param V2 the number of vertices in the other partition
     * @return a complete bipartite graph on {@code V1} and {@code V2} vertices
     * @throws IllegalArgumentException if probability is not between 0 and 1
     */
    public static EdgeWeightedGraph completeBipartite(int V1, int V2) {
        return bipartite(V1, V2, V1*V2);
    }

    /**
     * Returns a random simple bipartite graph on {@code V1} and {@code V2} vertices
     * with {@code E} edges.
     * @param V1 the number of vertices in one partition
     * @param V2 the number of vertices in the other partition
     * @param E the number of edges
     * @return a random simple bipartite graph on {@code V1} and {@code V2} vertices,
     *    containing a total of {@code E} edges
     * @throws IllegalArgumentException if no such simple bipartite graph exists
     */
    public static EdgeWeightedGraph bipartite(int V1, int V2, int E) {
        if (E > (long) V1*V2) throw new IllegalArgumentException("Too many edges");
        if (E < 0)            throw new IllegalArgumentException("Too few edges");
        EdgeWeightedGraph G = new EdgeWeightedGraph(V1 + V2);

        int[] vertices = new int[V1 + V2];
        for (int i = 0; i < V1 + V2; i++)
            vertices[i] = i;
        StdRandom.shuffle(vertices);

        SET<Edge> set = new SET<Edge>();
        while (G.E() < E) {
            int i = StdRandom.uniform(V1);
            int j = V1 + StdRandom.uniform(V2);
            int weight = StdRandom.uniform(100);
            Edge e = new Edge(vertices[i], vertices[j], weight);
            if (!set.contains(e)) {
                set.add(e);
                G.addEdge(e);
            }
        }
        return G;
    }

    /**
     * Returns a random simple bipartite graph on {@code V1} and {@code V2} vertices,
     * containing each possible edge with probability {@code p}.
     * @param V1 the number of vertices in one partition
     * @param V2 the number of vertices in the other partition
     * @param p the probability that the graph contains an edge with one endpoint in either side
     * @return a random simple bipartite graph on {@code V1} and {@code V2} vertices,
     *    containing each possible edge with probability {@code p}
     * @throws IllegalArgumentException if probability is not between 0 and 1
     */
    public static EdgeWeightedGraph bipartite(int V1, int V2, double p) {
        int weight;
        if (p < 0.0 || p > 1.0)
            throw new IllegalArgumentException("Probability must be between 0 and 1");
        int[] vertices = new int[V1 + V2];
        for (int i = 0; i < V1 + V2; i++)
            vertices[i] = i;
        StdRandom.shuffle(vertices);
        EdgeWeightedGraph G = new EdgeWeightedGraph(V1 + V2);
        for (int i = 0; i < V1; i++) {
            for (int j = 0; j < V2; j++) {
                weight = StdRandom.uniform(100);
                if (StdRandom.bernoulli(p)) {
                    G.addEdge(new Edge(vertices[i], vertices[V1+j], weight));
                }
            }
        }
        return G;
    }

    /**
     * Returns a path graph on {@code V} vertices.
     * @param V the number of vertices in the path
     * @return a path graph on {@code V} vertices
     */
    public static EdgeWeightedGraph path(int V) {
        int weight;
        EdgeWeightedGraph G = new EdgeWeightedGraph(V);
        int[] vertices = new int[V];
        for (int i = 0; i < V; i++)
            vertices[i] = i;
        StdRandom.shuffle(vertices);
        for (int i = 0; i < V-1; i++) {
            weight = StdRandom.uniform(100);
            G.addEdge(new Edge(vertices[i], vertices[i+1], weight));
        }
        return G;
    }

    /**
     * Returns a complete binary tree graph on {@code V} vertices.
     * @param V the number of vertices in the binary tree
     * @return a complete binary tree graph on {@code V} vertices
     */
    public static EdgeWeightedGraph binaryTree(int V) {
        int weight;
        EdgeWeightedGraph G = new EdgeWeightedGraph(V);
        int[] vertices = new int[V];
        for (int i = 0; i < V; i++)
            vertices[i] = i;
        StdRandom.shuffle(vertices);
        for (int i = 1; i < V; i++) {
            weight = StdRandom.uniform(100);
            G.addEdge(new Edge(vertices[i], vertices[(i-1)/2], weight));
        }
        return G;
    }

    /**
     * Returns a cycle graph on {@code V} vertices.
     * @param V the number of vertices in the cycle
     * @return a cycle graph on {@code V} vertices
     */
    public static EdgeWeightedGraph cycle(int V) {
        int weight;
        EdgeWeightedGraph G = new EdgeWeightedGraph(V);
        int[] vertices = new int[V];
        for (int i = 0; i < V; i++)
            vertices[i] = i;
        StdRandom.shuffle(vertices);
        for (int i = 0; i < V-1; i++) {
            weight = StdRandom.uniform(100);
            G.addEdge(new Edge(vertices[i], vertices[i+1], weight));
        }
        weight = StdRandom.uniform(100);
        G.addEdge(new Edge(vertices[V-1], vertices[0], weight));
        return G;
    }

    /**
     * Returns an Eulerian cycle graph on {@code V} vertices.
     *
     * @param  V the number of vertices in the cycle
     * @param  E the number of edges in the cycle
     * @return a graph that is an Eulerian cycle on {@code V} vertices
     *         and {@code E} edges
     * @throws IllegalArgumentException if either {@code V <= 0} or {@code E <= 0}
     */
    public static EdgeWeightedGraph eulerianCycle(int V, int E) {
        int weight;
        if (E <= 0)
            throw new IllegalArgumentException("An Eulerian cycle must have at least one edge");
        if (V <= 0)
            throw new IllegalArgumentException("An Eulerian cycle must have at least one vertex");
        EdgeWeightedGraph G = new EdgeWeightedGraph(V);
        int[] vertices = new int[E];
        for (int i = 0; i < E; i++)
            vertices[i] = StdRandom.uniform(V);
        for (int i = 0; i < E-1; i++) {
            weight = StdRandom.uniform(100);
            G.addEdge(new Edge(vertices[i], vertices[i+1], weight));
        }
        weight = StdRandom.uniform(100);
        G.addEdge(new Edge(vertices[E-1], vertices[0], weight));
        return G;
    }

    /**
     * Returns an Eulerian path graph on {@code V} vertices.
     *
     * @param  V the number of vertices in the path
     * @param  E the number of edges in the path
     * @return a graph that is an Eulerian path on {@code V} vertices
     *         and {@code E} edges
     * @throws IllegalArgumentException if either {@code V <= 0} or {@code E < 0}
     */
    public static EdgeWeightedGraph eulerianPath(int V, int E) {
        int weight;
        if (E < 0)
            throw new IllegalArgumentException("negative number of edges");
        if (V <= 0)
            throw new IllegalArgumentException("An Eulerian path must have at least one vertex");
        EdgeWeightedGraph G = new EdgeWeightedGraph(V);
        int[] vertices = new int[E+1];
        for (int i = 0; i < E+1; i++)
            vertices[i] = StdRandom.uniform(V);
        for (int i = 0; i < E; i++) {
            weight = StdRandom.uniform(100);
            G.addEdge(new Edge(vertices[i], vertices[i+1], weight));
        }
        return G;
    }

    /**
     * Returns a wheel graph on {@code V} vertices.
     * @param V the number of vertices in the wheel
     * @return a wheel graph on {@code V} vertices: a single vertex connected to
     *     every vertex in a cycle on {@code V-1} vertices
     */
    public static EdgeWeightedGraph wheel(int V) {
        int weight;
        if (V <= 1) throw new IllegalArgumentException("Number of vertices must be at least 2");
        EdgeWeightedGraph G = new EdgeWeightedGraph(V);
        int[] vertices = new int[V];
        for (int i = 0; i < V; i++)
            vertices[i] = i;
        StdRandom.shuffle(vertices);

        // simple cycle on V-1 vertices
        for (int i = 1; i < V-1; i++) {
            weight = StdRandom.uniform(100);
            G.addEdge(new Edge(vertices[i], vertices[i+1], weight));
        }
        weight = StdRandom.uniform(100);
        G.addEdge(new Edge(vertices[V-1], vertices[1], weight));

        // connect vertices[0] to every vertex on cycle
        for (int i = 1; i < V; i++) {
            weight = StdRandom.uniform(100);
            G.addEdge(new Edge(vertices[0], vertices[i], weight));
        }
        return G;
    }

    /**
     * Returns a star graph on {@code V} vertices.
     * @param V the number of vertices in the star
     * @return a star graph on {@code V} vertices: a single vertex connected to
     *     every other vertex
     */
    public static EdgeWeightedGraph star(int V) {
        int weight;
        if (V <= 0) throw new IllegalArgumentException("Number of vertices must be at least 1");
        EdgeWeightedGraph G = new EdgeWeightedGraph(V);
        int[] vertices = new int[V];
        for (int i = 0; i < V; i++)
            vertices[i] = i;
        StdRandom.shuffle(vertices);

        // connect vertices[0] to every other vertex
        for (int i = 1; i < V; i++) {
            weight = StdRandom.uniform(100);
            G.addEdge(new Edge(vertices[0], vertices[i], weight));
        }

        return G;
    }

    /**
     * Returns a uniformly random {@code k}-regular graph on {@code V} vertices
     * (not necessarily simple). The graph is simple with probability only about e^(-k^2/4),
     * which is tiny when k = 14.
     *
     * @param V the number of vertices in the graph
     * @param k degree of each vertex
     * @return a uniformly random {@code k}-regular graph on {@code V} vertices.
     */
    public static EdgeWeightedGraph regular(int V, int k) {
        int weight;
        if (V*k % 2 != 0) throw new IllegalArgumentException("Number of vertices * k must be even");
        EdgeWeightedGraph G = new EdgeWeightedGraph(V);

        // create k copies of each vertex
        int[] vertices = new int[V*k];
        for (int v = 0; v < V; v++) {
            for (int j = 0; j < k; j++) {
                vertices[v + V*j] = v;
            }
        }

        // pick a random perfect matching
        StdRandom.shuffle(vertices);
        for (int i = 0; i < V*k/2; i++) {
            weight = StdRandom.uniform(100);
            G.addEdge(new Edge(vertices[2*i], vertices[2*i + 1], weight));
        }
        return G;
    }

    // http://www.proofwiki.org/wiki/Labeled_Tree_from_Prüfer_Sequence
    // http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.36.6484&rep=rep1&type=pdf
    /**
     * Returns a uniformly random tree on {@code V} vertices.
     * This algorithm uses a Prufer sequence and takes time proportional to <em>V log V</em>.
     * @param V the number of vertices in the tree
     * @return a uniformly random tree on {@code V} vertices
     */
    public static EdgeWeightedGraph tree(int V) {
        EdgeWeightedGraph G = new EdgeWeightedGraph(V);
        int weight;
        // special case
        if (V == 1) return G;

        // Cayley's theorem: there are V^(V-2) labeled trees on V vertices
        // Prufer sequence: sequence of V-2 values between 0 and V-1
        // Prufer's proof of Cayley's theorem: Prufer sequences are in 1-1
        // with labeled trees on V vertices
        int[] prufer = new int[V-2];
        for (int i = 0; i < V-2; i++)
            prufer[i] = StdRandom.uniform(V);

        // degree of vertex v = 1 + number of times it appers in Prufer sequence
        int[] degree = new int[V];
        for (int v = 0; v < V; v++)
            degree[v] = 1;
        for (int i = 0; i < V-2; i++)
            degree[prufer[i]]++;

        // pq contains all vertices of degree 1
        MinPQ<Integer> pq = new MinPQ<Integer>();
        for (int v = 0; v < V; v++)
            if (degree[v] == 1) pq.insert(v);

        // repeatedly delMin() degree 1 vertex that has the minimum index
        for (int i = 0; i < V-2; i++) {
            int v = pq.delMin();
            weight = StdRandom.uniform(100);
            G.addEdge(new Edge(v, prufer[i], weight));
            degree[v]--;
            degree[prufer[i]]--;
            if (degree[prufer[i]] == 1) pq.insert(prufer[i]);
        }
        weight = StdRandom.uniform(100);
        G.addEdge(new Edge(pq.delMin(), pq.delMin(), weight));
        return G;
    }

    /**
     * Unit tests the {@code GraphGenerator} library.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int V = Integer.parseInt(scan.nextLine());
        int E =Integer.parseInt(scan.nextLine());
        int V1 = V/2;
        int V2 = V - V1;
        int count = 0;
        String type = scan.nextLine();
        switch (type) {
            case "complete graph":
            EdgeWeightedGraph ewg = complete(V);
            System.out.println(ewg);
            LazyPrimMST lp = new LazyPrimMST(ewg);
            MaxPQ<Integer> maxpq = new MaxPQ<Integer>();
            for (Edge edge : lp.edges()) {
                maxpq.insert(edge.weight());
            }
         System.out.print("highest weight : ");
        System.out.println(maxpq.max());
        int max = maxpq.delMax();
        for (Edge edge : ewg.edges()) {
            if (edge.weight() < max) {
                count = count + 1;
            }
        }
        System.out.println(count);
        System.out.println();
        break;

    case "simple" :
    EdgeWeightedGraph ewg1 = simple(V, E);
        System.out.println(ewg1);
        LazyPrimMST lp1 = new LazyPrimMST(ewg1);
        MaxPQ<Integer> maxpq1 = new MaxPQ<Integer>();
        for (Edge edge : lp1.edges()) {
            maxpq1.insert(edge.weight());
        }
        System.out.print("highest weight : ");
        int max1 = maxpq1.max();
        System.out.println(max1);
        int count1 = 0;
        for (Edge edge : ewg1.edges()) {
            if (edge.weight() < max1) {
                count1 = count1 + 1;
            }
        }
        System.out.println(count1);
        System.out.println();
        break;
        case "Erdos-Renyl" :
        double p = (double) E / (V*(V-1)/2.0);
        EdgeWeightedGraph ewg2 = simple(V, p);
       System.out.println(ewg2);
        LazyPrimMST lp2 = new LazyPrimMST(ewg2);
        MaxPQ<Integer> maxpq2 = new MaxPQ<Integer>();
        for (Edge edge : lp2.edges()) {
            maxpq2.insert(edge.weight());
        }
        System.out.print("highest weight : ");
        int max2 = maxpq2.max();
        System.out.println(max2);
        int c1 = 0;
        for (Edge edge : ewg2.edges()) {
            if (edge.weight() < max2) {
                c1 = c1 + 1;
            }
        }
        System.out.println(c1);
        System.out.println();
        break;
        case "complete bipartite" :
        EdgeWeightedGraph ewg3 = completeBipartite(V1, V2);
        System.out.println(ewg3);
        LazyPrimMST lp3 = new LazyPrimMST(ewg3);
        MaxPQ<Integer> maxpq3 = new MaxPQ<Integer>();
        for (Edge edge : lp3.edges()) {
            maxpq3.insert(edge.weight());
        }
        System.out.print("highest weight : ");
        int max3 = maxpq3.max();
        System.out.println(max3);
        int c2 = 0;
        for (Edge edge : ewg3.edges()) {
            if (edge.weight() < max3) {
                c2 = c2 + 1;
            }
        }
        System.out.println(c2);
        System.out.println();
        break;
        case "bipartite" :
        EdgeWeightedGraph ewg4 = bipartite(V1, V2, E);
       System.out.println(ewg4);
       LazyPrimMST lp4 = new LazyPrimMST(ewg4);
        MaxPQ<Integer> maxpq4 = new MaxPQ<Integer>();
        for (Edge edge : lp4.edges()) {
            maxpq4.insert(edge.weight());
        }
        System.out.print("highest weight : ");
        int max4 = maxpq4.max();
        System.out.println(max4);
        int c3 = 0;
        for (Edge edge : ewg4.edges()) {
            if (edge.weight() < max4) {
                c3 = c3 + 1;
            }
        }
        System.out.println(c3);
        System.out.println();
        break;
        case "Erdos Renyi bipartite" :
        double q = (double) E / (V1*V2);
        EdgeWeightedGraph ewg5 = bipartite(V1, V2, q);
        System.out.println(ewg5);
        LazyPrimMST lp5 = new LazyPrimMST(ewg5);
        MaxPQ<Integer> maxpq5 = new MaxPQ<Integer>();
        for (Edge edge : lp5.edges()) {
            maxpq5.insert(edge.weight());
        }
        System.out.print("highest weight : ");
        int max5 = maxpq5.delMax();
        System.out.println(max5);
        int c4 = 0;
        for (Edge edge : ewg5.edges()) {
            if (edge.weight() < max5) {
                c4 = c4 + 1;
            }
        }
        System.out.println(c4);
        System.out.println();
        break;
        case "path" :
        EdgeWeightedGraph ewg6 = path(V);
        System.out.println(ewg6);
        LazyPrimMST lp6 = new LazyPrimMST(ewg6);
        MaxPQ<Integer> maxpq6 = new MaxPQ<Integer>();
        for (Edge edge : lp6.edges()) {
            maxpq6.insert(edge.weight());
        }
        System.out.print("highest weight : ");
        int max6 = maxpq6.delMax();
        System.out.println(max6);
        int c5 = 0;
        for (Edge edge : ewg6.edges()) {
            if (edge.weight() < max6) {
                c5 = c5 + 1;
            }
        }
        System.out.println(c5);
        System.out.println();
        break;
        case "cycle" :
        EdgeWeightedGraph ewg7 = cycle(V);
        System.out.println(ewg7);
        LazyPrimMST lp7 = new LazyPrimMST(ewg7);
        MaxPQ<Integer> maxpq7 = new MaxPQ<Integer>();
        for (Edge edge : lp7.edges()) {
            maxpq7.insert(edge.weight());
        }
        System.out.print("highest weight : ");
        int max7 = maxpq7.delMax();
        System.out.println(max7);
        int c6 = 0;
        for (Edge edge : ewg7.edges()) {
            if (edge.weight() < max7) {
                c6 = c6 + 1;
            }
        }
        System.out.println(c6);
        System.out.println();
        break;
        case "binary tree" :
        EdgeWeightedGraph ewg8 = binaryTree(V);
        System.out.println(ewg8);
        LazyPrimMST lp8 = new LazyPrimMST(ewg8);
        MaxPQ<Integer> maxpq8 = new MaxPQ<Integer>();
        for (Edge edge : lp8.edges()) {
            maxpq8.insert(edge.weight());
        }
        System.out.print("highest weight : ");
        int max8 = maxpq8.delMax();
        System.out.println(max8);
        int c7 = 0;
        for (Edge edge : ewg8.edges()) {
            if (edge.weight() < max8) {
                c7 = c7 + 1;
            }
        }
        System.out.println(c7);
        System.out.println();
        break;
        case "tree" :
         EdgeWeightedGraph ewg9 = tree(V);
         System.out.println(ewg9);
         LazyPrimMST lp9 = new LazyPrimMST(ewg9);
        MaxPQ<Integer> maxpq9 = new MaxPQ<Integer>();
        for (Edge edge : lp9.edges()) {
            maxpq9.insert(edge.weight());
        }
         System.out.print("highest weight : ");
        int max9 = maxpq9.delMax();
        System.out.println(max9);
        int c8 = 0;
        for (Edge edge : ewg9.edges()) {
            if (edge.weight() < max9) {
                c8 = c8 + 1;
            }
        }
        System.out.println(c8);
        System.out.println();
        break;
        case "4-regular" :
        EdgeWeightedGraph ewg10 = regular(V, 4);
        System.out.println(ewg10);
        LazyPrimMST lp10 = new LazyPrimMST(ewg10);
        MaxPQ<Integer> maxpq10 = new MaxPQ<Integer>();
        for (Edge edge : lp10.edges()) {
            maxpq10.insert(edge.weight());
        }
         System.out.print("highest weight : ");
        int max10 = maxpq10.delMax();
        System.out.println(max10);
        int c9 = 0;
        for (Edge edge : ewg10.edges()) {
            if (edge.weight() < max10) {
                c9 = c9 + 1;
            }
        }
        System.out.println(c9);
        System.out.println();
        break;
        case "star" :
        EdgeWeightedGraph ewg11 = star(V);
        System.out.println(ewg11);
        LazyPrimMST lp11 = new LazyPrimMST(ewg11);
        MaxPQ<Integer> maxpq11 = new MaxPQ<Integer>();
        for (Edge edge : lp11.edges()) {
            maxpq11.insert(edge.weight());
        }
         System.out.print("highest weight : ");
        int max11 = maxpq11.delMax();
        System.out.println(max11);
        int c10 = 0;
        for (Edge edge : ewg11.edges()) {
            if (edge.weight() < max11) {
                c10 = c10 + 1;
            }
        }
        System.out.println(c10);
        System.out.println();
        break;
        case "wheel" :
        EdgeWeightedGraph ewg12 = wheel(V);
        System.out.println(ewg12);
        LazyPrimMST lp12 = new LazyPrimMST(ewg12);
        MaxPQ<Integer> maxpq12 = new MaxPQ<Integer>();
        for (Edge edge : lp12.edges()) {
            maxpq12.insert(edge.weight());
        }
         System.out.print("highest weight : ");
        int max12 = maxpq12.delMax();
        System.out.println(max12);
        int c11 = 0;
        for (Edge edge : ewg12.edges()) {
            if (edge.weight() < max12) {
                c11 = c11 + 1;
            }
        }
        System.out.println(c11);
        System.out.println();
        break;
        default:
        break;

    }

}

}

