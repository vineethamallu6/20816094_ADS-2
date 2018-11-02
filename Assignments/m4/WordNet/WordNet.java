import java.util.Arrays;
public class WordNet {
    // private String[] synString;
    // private String[] hypString;
    private int vertices;
    private LinearProbingHashST <String, Integer> lb;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {

        readFilestoString(synsets, hypernyms);
        // readHyperToString(hypernyms);
    }
    public void readFilestoString(String str, String hype) {
        //String[] word = null;
        int id = 0;
        try {
            In in = new In("./Files/" + str);
            while (!in.isEmpty()) {
            vertices++;
            String[] tokens = in.readString().split(",");
           //System.out.println(Arrays.toString(tokens));
            id = Integer.parseInt(tokens[0]);
            //if (tokens.length > 1) {
            String[] words = tokens[1].split(" ");
            }
        Digraph d = new Digraph(vertices);
        readHyperToString(hype, d);
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
}

void readHyperToString(String hype, Digraph obj) {
    try {
        In in = new In("./Files/" + hype);
        while (!in.isEmpty()) {
            String[] str = in.readString().split(",");
            //int v = Integer.parseInt(str[0]);
            //int w = Integer.parseInt(str[1]);
            for (int i = 1; i < str.length; i++) {
            	obj.addEdge(Integer.parseInt(str[0]), Integer.parseInt(str[i]));
            }

        }
        DirectedCycle dc = new DirectedCycle(obj);
        int count = 0;
        for (int i = 0; i < vertices; i++) {
        	if (obj.outdegree(i) == 0) {
        		count++;
        	}
        }
        if (count > 1) {
        	throw new IllegalArgumentException("Multiple roots");
        }
        if (dc.hasCycle()) {
            System.out.println("Cycle detected");
        } else {
            System.out.println(obj);
        }
        //System.out.println(obj);

    } catch (Exception e) {
    	System.out.println(e.getMessage());

    }
}

    // returns all WordNet nouns
//     public Iterable<String> nouns() {

//     }

//     // is the word a WordNet noun?
public boolean isNoun(String word) {
	return lb.contains(word);

}

//     // distance between nounA and nounB (defined below)
//public int distance(String nounA, String nounB)

//     // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
//     // in a shortest ancestral path (defined below)
//public String sap(String nounA, String nounB)

//     // do unit testing of this class
//     public static void main(String[] args)
}
