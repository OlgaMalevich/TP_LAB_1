import java.awt.*;
import java.util.LinkedList;

public class Romb extends Polygon {
    public Romb(Point centerP, Point firstP, Point secondP, Color borColor, Color bkColor) {
        super(borColor, bkColor);
        center = centerP;
        LinkedList<Point> points = new LinkedList<>();
        if(Math.abs((centerP.x - firstP.x)) < Math.abs(centerP.x - secondP.x)){
            Point p = firstP;
            firstP = secondP;
            secondP = p;
        }
        int hw = centerP.x - firstP.x;
        int hh = centerP.y - secondP.y;
        points.add(new Point (centerP.x, centerP.y-hh));
        points.add(new Point (centerP.x - hw, centerP.y));
        points.add(new Point (centerP.x, centerP.y+hh));
        points.add(new Point (centerP.x + hw, centerP.y));
        setPoints(points);
    }

}