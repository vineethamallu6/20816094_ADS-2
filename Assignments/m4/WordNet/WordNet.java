import java.util.Arrays;
public class WordNet {
    // private String[] synString;
    // private String[] hypString;
    //private LinearProbing <String, Integer> lb;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {

        readFilestoString(synsets, hypernyms);
        // readHyperToString(hypernyms);
    }
    public void readFilestoString(String str, String hype) {

        int vertices = 0;;
        //String[] word = null;
        int id = 0;
        try {
            In in = new In(str);
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
        System.out.println("File not found");
    }
}

void readHyperToString(String hype, Digraph obj) {
    try {
        In in = new In(hype);
        while (!in.isEmpty()) {
            String[] str = in.readString().split(",");
            int v = Integer.parseInt(str[0]);
            int w = Integer.parseInt(str[1]);
            obj.addEdge(v, w);
        }
        System.out.println(obj);
    } catch (Exception e) {

    }
}

    // returns all WordNet nouns
//     public Iterable<String> nouns() {

//     }

//     // is the word a WordNet noun?
//     public boolean isNoun(String word) {

//     }

//     // distance between nounA and nounB (defined below)
//     public int distance(String nounA, String nounB)

//     // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
//     // in a shortest ancestral path (defined below)
//     public String sap(String nounA, String nounB)

//     // do unit testing of this class
//     public static void main(String[] args)
}
