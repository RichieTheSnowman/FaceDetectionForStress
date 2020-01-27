
import processing.core.PApplet;

import javax.swing.*;

    public class Gaussian implements PixelFilter{
        private static short[][] BOX_BLUR;
        private int kernelWeight;
        private int threshold;
        //private int k;

        public Gaussian() {
            //k = Integer.parseInt(JOptionPane.showInputDialog("How many colors?"));
            this.BOX_BLUR = gaussianBlur();
            this.kernelWeight = kernelWeight = getKernelWeight();
            this.threshold = 10;
        }

        @Override
        public DImage processImage(DImage img) {
            short[][] pixels = img.getBWPixelGrid();
            short[][] newImage = new short[pixels.length][pixels[0].length];
            int output;
            for (int row = 0; row < pixels.length - 4; row++) {
                for (int col = 0; col < pixels[0].length - 4; col++) {
                    output = getOutput(pixels, row, col);

                    if (output < threshold) output = 0;
                    if (output > 255) output = 255;
                    newImage[row + 1][col + 1] = (short) output;
                }
            }

            System.out.println(kernelWeight);

            img.setPixels(newImage);
            return img;
        }



        public short getOutput(short[][] pixels, int row, int col) {
            short output = 0;
            for (int roffset = 0; roffset < 5; roffset++) {
                for (int coffset = 0; coffset < 5; coffset++) {
                    int kernalVal = BOX_BLUR[roffset][coffset];
                    int pixelVal = pixels[roffset + row][coffset + col];

                    output += kernalVal * pixelVal;
                }

            }

            output = (short) (output / (double) kernelWeight);
            return output;
        }


        public static short[][] gaussianBlur() {
            /*int[][] kernel = {  {1,   10,   45,    120,   210,   252,   210,   120,   45,   10,     1},
                                {10,  100,  450,   1200,  2100,  2520,  2100,  1200,  450,   10,    1},
                                {45,  450,  2025,  5400,  9450,  11340, 9450,  5400,  2025,  450,   45},
                                {120, 1200, 5400,  14400, 25200, 30240, 25200, 14400, 5400,  1200,  120},
                                {210, 2100, 9450,  25200, 44100, 52920, 44100, 25200, 9450,  2100,  210},
                                {0, 2, 4, 30240, 52920, 63504, 52920, 30240, 11340, 2520,  252},
                                {210, 2100, 9450,  25200, 44100, 52920, 44100, 25200, 9450,  2100,  210},
                                {120, 1200, 5400,  14400, 25200, 30240, 25200, 14400, 5400,  1200,  120},
                                {45,  450,  2025,  5400,  9450,  11340, 9450,  5400,  2025,  450,   45},
                                {10,  100,  450,   1200,  2100,  2520,  2100,  1200,  450,   100,   10},
                                {1,   10,   45,    120,   210,   252,   210,   120,   45,    10,    1},};*/

            short[][] kernel = { {1, 4, 6, 4, 1},
                                 {4, 16, 24, 16, 4},
                                 {6, 24, 36, 24, 6},
                                 {4, 16, 24, 16, 4},
                                 {1, 4, 6, 4, 1}};
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


