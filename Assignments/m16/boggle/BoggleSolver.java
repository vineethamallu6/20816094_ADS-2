import java.util.Set;
import java.util.HashSet;
/**
 * Class for boggle solver.
 */
public final class BoggleSolver {
    /**
     * { var_description }.
     */
    // private static final int FOUR = 4;
    private static final int FIVE = 5;
    /**
     * { var_description }.
     */
    private static final int ELEVEN = 11;
    /**
     * { var_description }.
     */
    private static final int EIGHT = 8;
    /**
     * { var_description }.
     */
    private TrieST<Integer> dictionaryTrie;
    /**
     * { var_description }.
     */
    private Set<String> words;
    /**
     * { var_description }.
     */
    private boolean[][] marked;
    // Initializes the data structure using the given
    // array of strings as the dictionary.
    // (You can assume each word in the dictionary
    // contains only the uppercase letters A through Z.)
    /**
     * Constructs the object.
     *
     * @param      dictionary  The dictionary
     */
    public BoggleSolver(final String[] dictionary) {
        words = new HashSet<String>();
        dictionaryTrie = new TrieST<Integer>();
        int[] points = {0, 0, 0, 1, 1, 2, 2 + 1, FIVE, ELEVEN};
        for (String word : dictionary) {
            if (word.length() >= EIGHT) {
                dictionaryTrie.put(word, ELEVEN);
            } else {
                dictionaryTrie.put(word, points[word.length()]);
            }
        }
    }
    // Returns the set of all valid words in
    //the given Boggle board, as an Iterable.
    /**
     * Gets all valid words.
     *
     * @param      board  The board
     *
     * @return     All valid words.
     */
    public Iterable<String> getAllValidWords(final BoggleBoard board) {
        if (board == null) {
            throw new IllegalArgumentException("board is null");
        }
        marked = new boolean[board.rows()][board.cols()];
        for (int i = 0; i < board.rows(); i++) {
            for (int j = 0; j < board.cols(); j++) {
                String word = appendCharacter("", board.getLetter(i, j));
                dfs(i, j, board, word);
            }
        }
        return words;
    }
    /**
     * Appends a character.
     *
     * @param      word1   The word
     * @param      letter  The letter
     *
     * @return     { description_of_the_return_value }
     */
    private String appendCharacter(final String word1,
        final char letter) {
        String word = word1;
        if (letter == 'Q') {
            word += "QU";
            // return word + "QU";
        } else {
            word += letter;
            // return word + letter;
        }
        return word;
    }
    /**
     * Determines if validword.
     *
     * @param      word  The word
     *
     * @return     True if validword, False otherwise.
     */
    private boolean isValidword(final String word) {
        if (word.length() < 2 + 1) {
            return false;
        }
        return dictionaryTrie.contains(word);
    }
    /**
     * { function_description }.
     *
     * @param      rows   The rows
     * @param      cols   The cols
     * @param      board  The board
     * @param      word   The word
     */
    private void dfs(final int rows, final int cols,
        final BoggleBoard board, final String word) {
        if (!dictionaryTrie.hasPrefix(word)) {
            return;
        }
        if (isValidword(word)) {
            words.add(word);
        }
        marked[rows][cols] = true;
        for (int row = rows - 1; row <= rows + 1; row++) {
            for (int col = cols - 1; col <= cols + 1; col++) {
                if (row >= 0 && row < board.rows() && col >= 0
                    && col < board.cols() && !marked[row][col]) {
                    String sequence = appendCharacter(word,
                        board.getLetter(row, col));
                    dfs(row, col, board, sequence);
                }
            }
        }
        marked[rows][cols] = false;
    }
    // Returns the score of the given word if
    // it is in the dictionary, zero otherwise.
    // (You can assume the word contains only
    // the uppercase letters A through Z.)
    /**
     * { function_description }.
     *
     * @param      word  The word
     *
     * @return     { description_of_the_return_value }
     */
    public int scoreOf(final String word) {
        if (word == null) {
            return 0;
        }
        if (dictionaryTrie.contains(word)) {
            return dictionaryTrie.get(word);
        } else {
            return 0;
        }
    }
}
