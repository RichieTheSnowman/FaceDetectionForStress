import processing.core.PApplet;

import javax.swing.*;


public class Blur implements PixelFilter {
    private short blurIndex;

    public Blur() {
        this.blurIndex = Short.parseShort(JOptionPane.showInputDialog("Enter an blurring index (integer) from 0 to 20"));
    }

    @Override
    public DImage processImage(DImage img) {
        short[][] pixels = img.getBWPixelGrid();
        short[][] newImage = new short[pixels.length][pixels[0].length];
        int output;
        for (int row = 0; row < pixels.length - blurIndex + 1; row++) {
            for (int col = 0; col < pixels[0].length - blurIndex + 1; col++) {
                output = getOutput(pixels, row, col);


                newImage[row + blurIndex - 2][col + blurIndex - 2] = (short) output;
            }
        }

        img.setPixels(newImage);
        return img;
    }

    public short getOutput(short[][] pixels, int row, int col) {
        short output = 0;
        for (int roffset = 0; roffset < blurIndex; roffset++) {
            for (int coffset = 0; coffset < blurIndex; coffset++) {
                int pixelVal = pixels[roffset + row][coffset + col];

                output += pixelVal;
            }

        }

        output = (short) (output / (double) getWeight());
        return output;
    }

    public int getWeight() {
        return blurIndex * blurIndex;
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {

    }


}
