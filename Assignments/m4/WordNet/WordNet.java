import java.util.Arrays;
import java.util.ArrayList;
public class WordNet {
    // private String[] synString;
    // private String[] hypString;
    private int vertices;
    private LinearProbingHashST <String, ArrayList<Integer>> nounLb;
    private LinearProbingHashST <Integer, String> idLb;
    private Digraph dg;
    private SAP sp;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
    	nounLb = new LinearProbingHashST<String, ArrayList<Integer>>();
    	idLb = new LinearProbingHashST<Integer, String>();
    	try {
    		In in = new In("./Files/" + synsets);
    		int id = 0;
    		while (!in.isEmpty()) {
    			String[] tokens = in.readLine().split(",");
    			id = Integer.parseInt(tokens[0]);
    			String[] words = tokens[1].split(" ");
    			ArrayList<String> wordList = new ArrayList<String>();
    			for (String noun : words) {
    				wordList.add(noun);
    			}
    			idLb.put(id, tokens[1]);
    			for (String noun : words) {
    				if (nounLb.contains(noun)) {
    					nounLb.get(noun).add(id);
    				} else {
    					ArrayList<Integer> list = new ArrayList<Integer>();
    					list.add(id);
    					nounLb.put(noun, list);
    				}
    			}
    		}
    		this.dg = new Digraph(id + 1);
    		in = new In("./Files/" + hypernyms);
    		while (!in.isEmpty()) {
    			String[] tokens = in.readLine().split(",");
    			int synId = Integer.parseInt(tokens[0]);
    			for (int i = 1; i < tokens.length; i++) {
    				dg.addEdge(synId, Integer.parseInt(tokens[i]));
    			}
    		}
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
    	sp = new SAP(dg);

        //readFilestoString(synsets, hypernyms);
        // readHyperToString(hypernyms);
    }
//     public void readFilestoString(String str, String hype) {
//         //String[] word = null;
//         int id = 0;
//         try {
//             In in = new In("./Files/" + str);
//             while (!in.isEmpty()) {
//             vertices++;
//             String[] tokens = in.readString().split(",");
//            //System.out.println(Arrays.toString(tokens));
//             id = Integer.parseInt(tokens[0]);
//             //if (tokens.length > 1) {
//             String[] words = tokens[1].split(" ");
//             }
//         Digraph d = new Digraph(vertices);
//         readHyperToString(hype, d);
//     } catch (Exception e) {
//         System.out.println(e.getMessage());
//     }
// }

// void readHyperToString(String hype, Digraph obj) {
//     try {
//         In in = new In("./Files/" + hype);
//         while (!in.isEmpty()) {
//             String[] str = in.readString().split(",");
//             //int v = Integer.parseInt(str[0]);
//             //int w = Integer.parseInt(str[1]);
//             for (int i = 1; i < str.length; i++) {
//             	obj.addEdge(Integer.parseInt(str[0]), Integer.parseInt(str[i]));
//             }

//         }
//         DirectedCycle dc = new DirectedCycle(obj);
//         int count = 0;
//         for (int i = 0; i < vertices; i++) {
//         	if (obj.outdegree(i) == 0) {
//         		count++;
//         	}
//         }
//         if (count > 1) {
//         	throw new IllegalArgumentException("Multiple roots");
//         }
//         if (dc.hasCycle()) {
//             System.out.println("Cycle detected");
//         } else {
//             System.out.println(obj);
//         }
//         //System.out.println(obj);

//     } catch (Exception e) {
//     	System.out.println(e.getMessage());

//     }
// }

//     // returns all WordNet nouns
    public void display() {
    	DirectedCycle dc = new DirectedCycle(dg);
    	if (dc.hasCycle()) {
    		throw new IllegalArgumentException("Cycle detected");
    	} else if (dg.noOfOutdegree() > 1) {
    		throw new IllegalArgumentException("Multiple roots");
    	} else {
    		System.out.println(dg.toString());
    	}
    }
public Iterable<String> nouns() {
	return nounLb.keys();

}

//     // is the word a WordNet noun?
public boolean isNoun(String word) {
	return nounLb.contains(word);

}

//     // distance between nounA and nounB (defined below)
public int distance(String nounA, String nounB) {
	if (!isNoun(nounA) || !isNoun(nounB)) {
		throw new IllegalArgumentException();
	}
	ArrayList<Integer> idA = nounLb.get(nounA);
	ArrayList<Integer> idB = nounLb.get(nounB);
	return sp.length(idA, idB);
}

//     // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
//     // in a shortest ancestral path (defined below)
public String sap(String nounA, String nounB) {
	if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException();
        }
        ArrayList<Integer> idA = nounLb.get(nounA);
        ArrayList<Integer> idB = nounLb.get(nounB);

        int anc = sp.ancestor(idA, idB);
        return idLb.get(anc);
    }
}

//     // do unit testing of this class
//     public static void main(String[] args)

