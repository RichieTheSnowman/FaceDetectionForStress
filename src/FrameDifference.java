import processing.core.PApplet;

public class FrameDifference implements PixelFilter {


    DImage previousImg = null;
    DImage currentImg = null;
    private int thres;
    int closex;
    int closey;
    int closecounter;

    public FrameDifference(){
        this.thres = 60;

    }

    @Override
    public DImage processImage(DImage img) {
        currentImg = new DImage(img);

        if (previousImg == null) {
            previousImg = new DImage(img); // save current frame
            return img;
        }

        short[][] current = currentImg.getBWPixelGrid();
        short[][] previous = previousImg.getBWPixelGrid();

        for (int r = 0; r < current.length; r++) {
            for (int c = 0; c < current[0].length; c++) {
                int diff = Math.abs(current[r][c] - previous[r][c]);

                if (diff > thres) current[r][c] = 255;
                else {current[r][c] = 0; }

            }
        }
        previousImg = new DImage(img);
        img.setPixels(current);

        for (int row = 0; row < current.length; row++) {
            for (int col = 0; col < current[0].length; col++) {
                if (current[row][col] == 255) {
                    closex = closex + col;
                    closey = closey + row;
                    closecounter++;
                }
            }
        }

        for (int row = 0; row < current.length; row++) {
            for (int col = 0; col < current[0].length; col++) {
                if (row < 100 || row > 250 && col < 200 || col > 250) {
                    current[row][col] = 255;
                    previous[row][col] = 255;
                }
            }
        }

        //img.setPixels(current);

        int c1 = 0;
        for (int row = 0; row < previous.length; row++) {
            for (int col = 0; col < previous[0].length; col++) {
                if (previous[row][col] == 255) {
                    c1++;
                }
            }
        }

        int c2 = 0;
        for (int row = 0; row < current.length; row++) {
            for (int col = 0; col < current[0].length; col++) {
                if (current[row][col] == 255) {
                    c2++;
                }
            }
        }


        double faceTwichVal = Math.abs(((double)(c2 - c1)/1600.0));
        System.out.println("frequency of twitches per second : " + faceTwichVal);

        return img;


    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {

    }
}
