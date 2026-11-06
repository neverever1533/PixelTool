package cn.imaginary.toolkit;

import cn.imaginary.toolkit.image.RGBTool;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageTool {
    public static int Color_Size_2 = 2;
    public static int Color_Size_4 = 4;
    public static int Color_Size_8 = 8;
    public static int Color_Size_16 = 16;
    public static int Color_Size_32 = 32;
    public static int Color_Size_64 = 64;
    public static int Color_Size_128 = 128;
    public static int Color_Size_256 = 256;
    public static int Depth_Default = 8;

    private int depth = 4;
    private int depth_item = 2;
    private int rgb = -1;

    private RGBTool rgbTool = new RGBTool();

    public BufferedImage read(File imageFile) {
        if (null != imageFile) {
            try {
                return ImageIO.read(imageFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public void write(BufferedImage image, String formatName, File file) {
        if (null != image && null != formatName && null != file) {
            try {
                ImageIO.write(image, formatName, file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setStrokeColor(Color color) {
        if (null != color) {
            setStrokeColor(color.getAlpha(), color.getRed(), color.getGreen(), color.getBlue());
        }
    }

    public void setStrokeColor(int alpha, int red, int green, int blue) {
        setStrokeRGB(rgbTool.getRGB(alpha, red, green, blue));
    }

    public void setStrokeRGB(int rgb) {
        this.rgb = rgb;
    }

    public int getStrokeBlack() {
        return rgbTool.getRGB(255, 0, 0, 0);
    }

    public int getStrokeRGB() {
        if (rgb == -1) {
            rgb = getStrokeBlack();
        }
        return rgb;
    }

    public BufferedImage stroke(BufferedImage root) {
        boolean isOutside = true;
        return stroke(root, isOutside);
    }

    public BufferedImage stroke(BufferedImage root, boolean isOutside) {
        if (null != root) {
            int w = root.getWidth();
            int h = root.getHeight();
            int rgb_stroke = getStrokeRGB();
//            System.out.println("it90 a:" + rgbTool.getAlpha(rgb) + "/r:" + rgbTool.getRed(rgb) + "/g:" + rgbTool.getGreen(rgb) + "/b:" + rgbTool.getBlue(rgb));
            BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            for (int x = 0; x < w; x++) {
                for (int y = 0; y < h; y++) {
                    if (isOutside) {
                        if (rgbTool.isAlpha(root, x, y) && ((x > 0 && !rgbTool.isAlpha(root, x - 1, y)) || (x < w - 1 && !rgbTool.isAlpha(root, x + 1, y)) || (y > 0 && !rgbTool.isAlpha(root, x, y - 1)) || (y < h - 1 && !rgbTool.isAlpha(root, x, y + 1)))) {
                            image.setRGB(x, y, rgb_stroke);
                        }
                    } else {
                        if (!rgbTool.isAlpha(root, x, y) && ((x > 0 && rgbTool.isAlpha(root, x - 1, y)) || (x < w - 1 && rgbTool.isAlpha(root, x + 1, y)) || (y > 0 && rgbTool.isAlpha(root, x, y - 1)) || (y < h - 1 && rgbTool.isAlpha(root, x, y + 1)))) {
                            image.setRGB(x, y, rgb_stroke);
                        }
                    }
                }
            }
            drawImage(root, image);
            return root;
        }
        return null;
    }

    private void drawImage(BufferedImage root, BufferedImage image) {
        if (null != root && null != image) {
            Graphics2D graphics2D = root.createGraphics();
            graphics2D.drawImage(image, 0, 0, null);
            graphics2D.dispose();
        }
    }

    public BufferedImage toPixel(BufferedImage root, int pixelSize) {
        return toPixel(root, pixelSize, 0, 0, -1, -1);
    }

    public BufferedImage toPixel(BufferedImage root, int pixelSize, int offsetX, int offsetY, int width, int height) {
        if (null != root) {
            int w = root.getWidth();
            int h = root.getHeight();
            if (offsetX < 0) {
                offsetX = 0;
            }
            if (offsetY < 0) {
                offsetY = 0;
            }
            if (width <= 0) {
                width = w;
            }
            if (height <= 0) {
                height = h;
            }
            if (pixelSize <= 1 || width > w || height > h || offsetX + width > w || offsetY + height > h) {
                return root;
            }
            w = offsetX + width;
            h = offsetY + height;
//            System.out.println("it70:" + offsetX + "/" + offsetY + "/" + w + "/" + h);
            for (int x = w - 1; x > offsetX; x--) {
                for (int y = h - 1; y > offsetY; y--) {
                    int rgb = root.getRGB(x, y);
                    if (x % pixelSize == 0 && y % pixelSize == 0) {
                        for (int i = 0; i < pixelSize; i++) {
                            for (int j = 0; j < pixelSize; j++) {
                                root.setRGB(x - i, y - j, rgb);
                            }
                        }
                    }
                }
            }
            return root;
        }
        return null;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        if (depth > 0 && depth <= Depth_Default) {
            this.depth = depth;
        }
    }

    public BufferedImage updateColors(BufferedImage image, int colorSize) {
        if (colorSize >= Color_Size_256) {
            colorSize = Color_Size_256;
        } else if (colorSize >= Color_Size_128) {
            colorSize = Color_Size_128;
        } else if (colorSize >= Color_Size_64) {
            colorSize = Color_Size_64;
        } else if (colorSize >= Color_Size_32) {
            colorSize = Color_Size_32;
        } else if (colorSize >= Color_Size_16) {
            colorSize = Color_Size_16;
        } else if (colorSize >= Color_Size_8) {
            colorSize = Color_Size_8;
        } else if (colorSize >= Color_Size_4) {
            colorSize = Color_Size_4;
        } else if (colorSize >= Color_Size_2) {
            colorSize = Color_Size_2;
        }
        int depth = (int) (Math.log(colorSize) / Math.log(depth_item));
        return updateRGB(image, depth);
    }

    public BufferedImage getImage(BufferedImage image, int depth) {
        return updateRGB(image, depth);
    }

    private BufferedImage updateRGB(BufferedImage image, int depth) {
        if (null != image) {
            setDepth(depth);
            depth = getDepth();
            int size = (int) Math.pow(depth_item, depth);
            int item = Color_Size_256 / size;
            int w = image.getWidth();
            int h = image.getHeight();
            BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            int rgb;
            int a;
            int r;
            int b;
            int g;
            for (int i = 0; i < w; i++) {
                for (int j = 0; j < h; j++) {
                    rgb = image.getRGB(i, j);
                    a = rgbTool.getAlpha(rgb);
                    r = updateDepth(rgbTool.getRed(rgb), size, item);
                    g = updateDepth(rgbTool.getGreen(rgb), size, item);
                    b = updateDepth(rgbTool.getBlue(rgb), size, item);
                    img.setRGB(i, j, rgbTool.getRGB(a, r, g, b));
                }
            }
            return img;
        }
        return null;
    }

    private int updateDepth(int pixel, int size, int item) {
        int p = -1;
        for (int i = size; i > 0; i--) {
            int temp = item * i - 1;
            if (pixel >= temp) {
                p = temp;
            }
            if (p != -1) {
                pixel = p;
                break;
            }
        }
        return pixel;
    }
}
