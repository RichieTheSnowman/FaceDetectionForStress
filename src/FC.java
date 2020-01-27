import processing.core.PApplet;

public class FC implements PixelFilter{
    FrameDifference f;
    EdgeD g;

    public FC(){
        f = new FrameDifference();
        g = new EdgeD();
    }

    @Override
    public DImage processImage(DImage img) {
        DImage e = new DImage(g.processImage(img));
        DImage frame = new DImage(f.processImage(e));

        return frame;
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {

    }
}
