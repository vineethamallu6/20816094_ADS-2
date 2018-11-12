import java.util.Scanner;
import java.util.Arrays;
/**
 * Class for solution.
 */
class Solution {
    /**
     * Constructs the object.
     */
	Solution() {
        //check style purpose.

	}
    /**
     * main method.
     *
     * @param      args  The arguments
     */
	public static void main(final String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = Integer.parseInt(scan.nextLine());
		String[] lines = new String[n];
		for (int i = 0; i < n; i++) {
			lines[i] = scan.nextLine();
		}
		LSD l = new LSD();
		l.sort(lines, lines[0].length());
		System.out.println(Arrays.toString(lines));

	}
}
