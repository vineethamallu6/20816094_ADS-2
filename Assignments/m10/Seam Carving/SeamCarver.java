import java.awt.Color;
public class SeamCarver {
	// create a seam carver object based on the given picture
	private Picture picture;
	public SeamCarver(Picture picture) {
		if (picture == null) {
		throw new IllegalArgumentException("picture is null");
		}
		this.picture = picture;
	}
	// current picture
	public Picture picture() {
		return this.picture;
	}
	// width of current picture
	public int width() {
		return this.picture.width();
	}

	// height of current picture
	public int height() {
		return this.picture.height();
	}

	// energy of pixel at column x and row y
	public double energy(int x, int y) {
		if (x == 0 || x == width() - 1 || y == 0 || y == height() - 1) {
            return 1000;
		}
        int t = picture.getRGB(x - 1, y);
		int b = picture.getRGB(x + 1, y );
		int l = picture.getRGB(x, y - 1);
		int r = picture.getRGB(x, y + 1);
		int[] top = new int[]{(t>>16 & 0xFF), (t>>8 & 0xFF), (t & 0xFF)};
		int[] bottom = new int[]{(b>>16 & 0xFF), (b>>8 & 0xFF), (b & 0xFF)};
		int[] left = new int[]{(l>>16 & 0xFF), (l>>8 & 0xFF), (l & 0xFF)};
		int[] right = new int[]{(r>>16 & 0xFF), (r>>8 & 0xFF), (r & 0xFF)};
		int ver_red = Math.abs(top[0] - bottom[0]);
		int ver_green = Math.abs(top[1] - bottom[1]);
		int ver_blue = Math.abs(top[2] - bottom[2]);
		int vertical = (ver_red * ver_red) + (ver_green * ver_green) + (ver_blue * ver_blue);
		int hor_red = Math.abs(left[0] - right[0]);
		int hor_green = Math.abs(left[1] - right[1]);
		int hor_blue = Math.abs(left[2] - right[2]);
		int horizontal = (hor_red * hor_red) + (hor_green * hor_green) + (hor_blue * hor_blue);
		double energy = Math.sqrt(vertical + horizontal);
		//(or)
        // Color top = picture.get(x, y - 1);
        // Color bottom = picture.get(x, y + 1);
        // Color left = picture.get(x - 1, y);
        // Color right = picture.get(x + 1, y);
        // int r = right.getRed() - left.getRed();
        // int b = right.getBlue() - left.getBlue();
        // int g = right.getGreen() - left.getGreen();
        // int ver_red = top.getRed() - bottom.getRed();
        // int ver_blue = top.getBlue() - bottom.getBlue();
        // int ver_green = top.getGreen() - bottom.getGreen();
        // int horizontal = r * r + b * b  +  g * g;
        // int vertical = ver_red * ver_red + ver_blue * ver_blue + ver_green * ver_green;
        // double energy = Math.sqrt(horizontal + vertical);
        return energy;
		// int right = picture.getRGB(x + 1, y);
		// int left = picture.getRGB(x-1, y);
		// int top = picture.getRGB(x, y-1);
		// int dowm = picture.getRGB(x, y+1);

	}

	// sequence of indices for horizontal seam
	public int[] findHorizontalSeam() {
		return new int[0];
	}

	// sequence of indices for vertical seam
	public int[] findVerticalSeam() {
		return new int[0];
	}

	// remove horizontal seam from current picture
	public void removeHorizontalSeam(int[] seam) {

	}

	// remove vertical seam from current picture
	public void removeVerticalSeam(int[] seam) {

	}
}