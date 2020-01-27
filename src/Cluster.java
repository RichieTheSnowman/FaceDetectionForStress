import java.util.ArrayList;

public class Cluster {
    private Point center;
    ArrayList<Point> points;
    private int x, y;
    private int minx, miny, maxX, maxY;


    public Cluster(int x, int y) {
        this.x = x;
        this.y = y;
        this.minx = minx;
        this.miny = miny;
        this.maxX = maxX;
        this.maxY = maxY;
        this.center = new Point(x, y);

        points = new ArrayList<>();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void add(int x, int y) {
        Point p = new Point(x, y);
        points.add(p);
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(int x, int y) {
        Point c = new Point(x, y);
        center = c;
    }

    public void calcNewCenter() {
        int sumX = 0;
        int sumY = 0;

        if (points.size() != 0) {
            for (int i = 0; i < points.size(); i++) {
                if (points.get(i) != null) {
                    sumX += points.get(i).getX();
                    sumY += points.get(i).getY();
                }

            }
            setCenter(sumX / points.size() + 1, sumY / points.size() + 1);
        } else {
            setCenter((int) (Math.random() * 640), (int) (Math.random() * 480));
        }

        points.clear();
    }

    public int getMinx() {
        return minx;
    }

    public void setMinx() {
        if (points.size() != 0) {
            int min = points.get(0).getX();
            for (int i = 1; i < points.size(); i++) {
                if (min > points.get(i).getX()) {
                    min = points.get(i).getX();
                }
            }
            minx = min;
        } else {
            minx = (int) (Math.random() * 640);
        }
        points.clear();
    }

    public int getMiny() {
        return miny;
    }

    public void setMiny() {
        if (points.size() != 0) {
            int min = points.get(0).getX();
            for (int i = 1; i < points.size(); i++) {
                if (min > points.get(i).getY()) {
                    min = points.get(i).getY();
                }
            }
            miny = min;
        } else {
            miny = (int) (Math.random() * 480);
        }
        points.clear();
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }


    public void clear() {
        points.clear();
    }

}
