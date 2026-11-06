package cn.imaginary.toolkit;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class PixelTool {
    private String tag_png = "png";

    private ImageTool imageTool = new ImageTool();

    private Rectangle rectangle;

    private boolean is_stroke_outside = true;

    public void setPixelClip(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public void setPixelClip(int x, int y, int width, int height) {
        setPixelClip(new Rectangle(x, y, width, height));
    }

    public Rectangle getPixelClip() {
        return rectangle;
    }

    public BufferedImage read(File file) {
        return imageTool.read(file);
    }

    public void write(BufferedImage image, File file) {
        write(image, tag_png, file);
    }

    public void write(BufferedImage image, String formatName, File file) {
        imageTool.write(image, formatName, file);
    }

    private BufferedImage getImage(BufferedImage image, int depth) {
        return imageTool.getImage(image, depth);
    }

    public void getImage(File inFile, File outFile, int depth) {
        getImage(inFile, tag_png, outFile, depth);
    }

    public void getImage(File inFile, String formatName, File outFile, int depth) {
        write(getImage(read(inFile), depth), formatName, outFile);
    }

    private BufferedImage updateColors(BufferedImage image, int colorSize) {
        return imageTool.updateColors(image, colorSize);
    }

    public void updateColors(File inFile, File outFile, int colorSize) {
        updateColors(inFile, tag_png, outFile, colorSize);
    }

    public void updateColors(File inFile, String formatName, File outFile, int colorSize) {
        write(updateColors(read(inFile), colorSize), formatName, outFile);
    }

    public void stroke(File inFile, File outFile) {
        stroke(inFile, tag_png, outFile);
    }

    public void stroke(File inFile, String formatName, File outFile) {
        write(stroke(read(inFile)), formatName, outFile);
    }

    private BufferedImage stroke(BufferedImage image) {
        return imageTool.stroke(image, isStrokeOutside());
    }

    public boolean isStrokeOutside() {
        return is_stroke_outside;
    }

    public void setStrokeOutside(boolean isOutside) {
        is_stroke_outside = isOutside;
    }

    public void setStrokeColor(Color color) {
        imageTool.setStrokeColor(color);
    }

    public void setStrokeColor(int alpha, int red, int green, int blue) {
        imageTool.setStrokeColor(alpha, red, green, blue);
    }

    public void toPixel(File inFile, File outFile, int pixelSize) {
        toPixel(inFile, tag_png, outFile, pixelSize);
    }

    public void toPixel(File inFile, String formatName, File outFile, int pixelSize) {
        write(toPixel(read(inFile), pixelSize), formatName, outFile);
    }

    private BufferedImage toPixel(BufferedImage image, int pixelSize) {
        Rectangle rectangle = getPixelClip();
        if (null == rectangle) {
            return imageTool.toPixel(image, pixelSize);
        } else {
            return imageTool.toPixel(image, pixelSize, rectangle.x, rectangle.y, rectangle.width, rectangle.height);

        }
    }
}
