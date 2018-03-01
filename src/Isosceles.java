import java.awt.*;
import java.util.LinkedList;

public class Isosceles extends Polygon {
    public Isosceles(Point point1, Point point2, Color borColor, Color bkColor) {
        super(borColor, bkColor);
        LinkedList <Point> pointL = new LinkedList<>();
        center = point1;
        Point point3 = new Point(2*point1.x -point2.x, point2.y);
        pointL.add(point1);
        pointL.add(point2);
        pointL.add(point3);
        setPoints(pointL);
    }

}