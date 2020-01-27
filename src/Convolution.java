import processing.core.PApplet;

import javax.swing.*;

public class Convolution implements PixelFilter{
    private static short[][] BOX_BLUR;
    private int kernelWeight;
    private int threshold;
    private int k;

    public Convolution() {
        k = Integer.parseInt(JOptionPane.showInputDialog("How many colors?"));
        this.BOX_BLUR = gaussianBlur();
        this.kernelWeight = kernelWeight = getKernelWeight();
        this.threshold = 10;
    }

    @Override
    public DImage processImage(DImage img) {
        short[][] pixels = img.getBWPixelGrid();
        short[][] newImage = new short[pixels.length][pixels[0].length];
        int output;
        for (int row = 0; row < pixels.length - 2; row++) {
            for (int col = 0; col < pixels[0].length - 2; col++) {
                output = getOutput(pixels, row, col);

                if (output < threshold) output = 0;
                if (output > 255) output = 255;
                newImage[row + 1][col + 1] = (short) output;
            }
        }

        img.setPixels(newImage);
        return img;
    }

    public short getOutput(short[][] pixels, int row, int col) {
        short output = 0;
        for (int roffset = 0; roffset < 3; roffset++) {
            for (int coffset = 0; coffset < 3; coffset++) {
                int kernalVal = BOX_BLUR[roffset][coffset];
                int pixelVal = pixels[roffset + row][coffset + col];

                output += kernalVal * pixelVal;
            }

        }

        output = (short) (output / (double) kernelWeight);
        return output;
    }

    public static short[][] standard3by3() {
        short[][] kernel = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        return kernel;
    }

    public static short[][] gaussianBlur() {
        short[][] kernel = {{1, 2, 1}, {2, 4, 2}, {1, 2, 1}};
        return kernel;
    }

    public static short[][] sharpen() {
        short[][] kernel = {{0, -1, 0}, {-1, 5, -1}, {0, -1, 0}};
        return kernel;
    }

    public static short[][] prewittEdge() {
        short[][] kernel = {{-1, -1, -1}, {-1, 8, -1}, {-1, -1, -1}};
        return kernel;
    }

    public static short[][] sobelEdge() {
        short[][] kernel = {{-1, -1, -1}, {-1, 8, -1}, {-1, -1, -1}};
        return kernel;
    }

//    public static short[][] scharr() {
//        short[][] kernel = {{3, -10, -3}, {10, 0, 10}, {-3, -10, 3}};
//        return kernel;
//    }

    public static short[][] scharr() {
        short[][] kernel = {{3, 10, 3}, {0, 0, 0}, {-3, -10, -3}};
        return kernel;
    }

    public int getKernelWeight() {
        int sum = 0;
        for (int i = 0; i < BOX_BLUR.length; i++) {
            for (int j = 0; j < BOX_BLUR[0].length; j++) {
                sum += BOX_BLUR[i][j];
            }
        }

        if (sum <= 0) {
            sum = 1;
        }
        return sum;
    }


    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {

    }
}
