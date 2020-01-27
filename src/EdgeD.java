import processing.core.PApplet;

public class EdgeD implements PixelFilter {
    private static final short[][] LineGrid = {{-1, -1, -1}, {-1, 8, -1}, {-1, -1, -1}};
    int xval = 320;
    int yval = 240;
    @Override
    public DImage processImage(DImage img) {
        short[][] pixels = img.getBWPixelGrid();
        short[][] newGrid = new short[pixels.length][pixels[0].length];

        for (int row = 1; row < pixels.length - 1; row++) {
            for (int col = 1; col < pixels[0].length - 1; col++) {
                int output = 0;
                for (int r = -1; r < 2; r++) {
                    for (int c = -1; c < 2; c++) {

                        int kernalval = LineGrid[r + 1][c + 1];
                        int pixelval = pixels[row + r][col + c];

                        output += kernalval*pixelval;
                    }
                }

                output = output/1;
                if( output < 0) output = 0;
                if( output > 255) output = 255;
                newGrid[row][col] = (short)output;
            }
        }

        //  for (int i = 0; i < ; i++) {

        //}

        img.setPixels(newGrid);
        return img;
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {
        window.ellipse(xval, yval, 10, 10);
    }

}

