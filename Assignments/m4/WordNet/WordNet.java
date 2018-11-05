import java.util.Arrays;
import java.util.ArrayList;
/**
 * Class for word net.
 */
public class WordNet {
    /**
     * { var_description }
     */
    private int vertices;
    /**
     * { item_description }
     */
    private LinearProbingHashST <String, ArrayList<Integer>> nounLb;
    /**
     * { var_description }
     */
    private LinearProbingHashST <Integer, String> idLb;
    /**
     * { var_description }
     */
    private Digraph dg;
    /**
     * { var_description }
     */
    private SAP sp;
    /**
     * Constructs the object.
     *
     * @param      synsets    The synsets
     * @param      hypernyms  The hypernyms
     */
    public WordNet(final String synsets, final String hypernyms) {
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

/**
 * { function_description }
 */
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
    /**
     * { function_description }
     *
     * @return     { description_of_the_return_value }
     */
public Iterable<String> nouns() {
	return nounLb.keys();

}

/**
 * Determines if noun.
 *
 * @param      word  The word
 *
 * @return     True if noun, False otherwise.
 */
public boolean isNoun(final String word) {
	return nounLb.contains(word);

}

/**
 * { function_description }
 *
 * @param      nounA  The noun a
 * @param      nounB  The noun b
 *
 * @return     { description_of_the_return_value }
 */
public int distance(final String nounA, final String nounB) {
	if (!isNoun(nounA) || !isNoun(nounB)) {
		throw new IllegalArgumentException();
	}
	ArrayList<Integer> idA = nounLb.get(nounA);
	ArrayList<Integer> idB = nounLb.get(nounB);
	return sp.length(idA, idB);
}

/**
 * { function_description }
 *
 * @param      nounA  The noun a
 * @param      nounB  The noun b
 *
 * @return     { description_of_the_return_value }
 */
public String sap(final String nounA, final String nounB) {
	if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException();
        }
        ArrayList<Integer> idA = nounLb.get(nounA);
        ArrayList<Integer> idB = nounLb.get(nounB);

        int anc = sp.ancestor(idA, idB);
        return idLb.get(anc);
    }
}
