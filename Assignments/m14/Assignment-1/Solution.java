import java.util.Scanner;
public class Solution {
	public static void main(String[] args) {
		String[] words = loadWords();
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		TST<Integer> tst = new TST<Integer>();
		int j = 0;
		for (String s : words) {
			SuffixArray suf = new SuffixArray(s);
			for (int i = 0; i < s.length(); i++) {
				tst.put(suf.select(i), j++);
			}
		}
		for (String word : tst.keysWithPrefix(input)) {
			System.out.println(word);
		}

		//Your code goes here...
	}

	public static String[] loadWords() {
		In in = new In("/Files/dictionary-algs4.txt");
		String[] words = in.readAllStrings();
		return words;
	}
}