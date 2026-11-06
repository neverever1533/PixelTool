package cn.imaginary.toolkit.image;

import java.awt.image.BufferedImage;

public class RGBTool {
    public int getAlpha(int rgb) {
        return (rgb >> 24) & 0xff;
    }

    public int getRed(int rgb) {
        return (rgb >> 16) & 0xff;
    }

    public int getGreen(int rgb) {
        return (rgb >> 8) & 0xff;
    }

    public int getBlue(int rgb) {
        return rgb & 0xff;
    }

    public int getRGB(int alpha, int red, int green, int blue) {
        return ((alpha & 0xff) << 24) | ((red & 0xff) << 16) | ((green & 0xff) << 8) | (blue & 0xff);
    }

    public boolean isAlpha(BufferedImage image, int x, int y) {
        return !isRGB(image, x, y);
    }

    public boolean isAlpha(int[][] array, int x, int y) {
        return !isRGB(array, x, y);
    }

    public boolean isRGB(BufferedImage image, int x, int y) {
        if (null != image) {
            if (x >= 0 && x < image.getWidth() && y >= 0 && y < image.getHeight()) {
                return isRGB(image.getRGB(x, y));
            }
        }
        return false;
    }

    public boolean isRGB(int[][] array, int x, int y) {
        if (null != array && x < array.length && y < array[x].length) {
            return isRGB(array[x][y]);
        }
        return false;
    }

    public boolean isRGB(int rgb) {
        return !isAlpha(rgb);
    }

    public boolean isAlpha(int rgb) {
        return getAlpha(rgb) == 0;
    }
}
