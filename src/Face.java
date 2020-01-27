import processing.core.PApplet;

public class Face implements PixelFilter, Clickable {
    ColorThreshold color;
    Blur blur;
    FK2 kmeans;
    FrameDifference fd;



    public Face() {
        color = new ColorThreshold();
        blur = new Blur();
        kmeans = new FK2();
        fd = new FrameDifference();



    }

    @Override
    public DImage processImage(DImage img) {
        DImage c = color.processImage(img);
        DImage b = blur.processImage(c);
        DImage k = new DImage(kmeans.processImage(b));
        DImage FD = new DImage(fd.processImage(k));


    //color.processImage(b);
        return FD;
    }


    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {
        kmeans.drawOverlay(window, original, filtered);

    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, DImage img) {
        short[][] red = img.getRedChannel();
        short[][] green = img.getGreenChannel();
        short[][] blue = img.getBlueChannel();

        Ball x = new Ball(red[mouseY][mouseX], green[mouseY][mouseX], blue[mouseY][mouseX]);
        color.balls.add(x);
    }

    @Override
    public void keyPressed(char key) {
        color.keyPressed(key);
    }

}

