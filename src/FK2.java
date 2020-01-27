import processing.core.PApplet;

import javax.swing.*;
import java.util.ArrayList;

public class FK2 implements PixelFilter {
    private int k;
    ArrayList<Cluster> clusters;

    public FK2() {
        k = Integer.parseInt(JOptionPane.showInputDialog("How many colors?"));
        clusters = new ArrayList<>();
        initClusters(k);

    }

    @Override
    public DImage processImage(DImage img) {
        short[][] bw = img.getBWPixelGrid();
        for (int i = 0; i < 12; i++) {
            for (int r = 0; r < bw.length; r++) {
                for (int c = 0; c < bw[0].length; c++) {
                    if (bw[r][c] == 255) {
                        findClosest(r, c);
                    }
                }
            }
        }
        calcCenter();

        return img;
    }

    private void initClusters(int k) {
        for (int i = 0; i < k; i++) {
            int x = (int) (Math.random() * 640);
            int y = (int) (Math.random() * 480);
            Cluster c = new Cluster(x, y);
            clusters.add(c);
        }
    }

    public void findClosest(int x, int y) {
        int dx = clusters.get(0).getCenter().getX() - x;
        int dy = clusters.get(0).getCenter().getY() - y;
        int minDist = dx * dx + dy * dy;
        int minIndex = 0;
        double dist;

        for (int i = 1; i < clusters.size(); i++) {
            dist = calcDistance(x, y, clusters.get(i).getX(), clusters.get(i).getY());
            if (dist < minDist) {
                dx = clusters.get(minIndex).getCenter().getX();
                dy = clusters.get(minIndex).getCenter().getY();
                minDist = dx * dx + dy * dy;
                minIndex = i;
            }
        }
        clusters.get(minIndex).add(x, y);
    }


    public double calcDistance(double x, double y, double x1, double y1) {
        return (x1 - x) * (x1 - x) + (y1 - y) * (y1 - y);
    }

    public void calcCenter() {
        for (int i = 0; i < clusters.size(); i++) {
            clusters.get(i).calcNewCenter();
        }
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {
        window.fill(0);
        for (int i = 0; i < clusters.size(); i++) {
            window.rect(clusters.get(i).getCenter().getY()-100, clusters.get(i).getCenter().getX()-45, 200, 80);

            window.ellipse(clusters.get(i).getCenter().getY(), clusters.get(i).getCenter().getX(), 10, 10);
            window.ellipse(clusters.get(i).getMinx(), clusters.get(i).getMiny(), 10, 10);
            clusters.get(i).clear();
        }
    }
}
