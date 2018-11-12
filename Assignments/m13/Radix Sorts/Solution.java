import java.util.Scanner;
import java.util.Arrays;
class Solution {
	Solution() {

	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = Integer.parseInt(scan.nextLine());
		int[] lines = new int[n];
		for (int i = 0; i < n; i++) {
			lines[i] = Integer.parseInt(scan.nextLine());
		}
		LSD m = new LSD();
		m.sort(lines);
		System.out.println(Arrays.toString(lines));

	}
}