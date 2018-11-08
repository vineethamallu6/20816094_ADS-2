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
		if (x == 0 || x == width() - 1 || y == 0 || y == height() - 1)
            return 1000;
        Color top = picture.get(x, y - 1);
        Color bottom = picture.get(x, y + 1);
        Color left = picture.get(x - 1, y);
        Color right = picture.get(x + 1, y);
        int r = right.getRed() - left.getRed();
        int b = right.getBlue() - left.getBlue();
        int g = right.getGreen() - left.getGreen();
        int ver_red = top.getRed() - bottom.getRed();
        int ver_blue = top.getBlue() - bottom.getBlue();
        int ver_green = top.getGreen() - bottom.getGreen();
        int horizontal = r * r + b * b  +  g * g;
        int vertical = ver_red * ver_red + ver_blue * ver_blue + ver_green * ver_green;
        double energy = Math.sqrt(horizontal + vertical);
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