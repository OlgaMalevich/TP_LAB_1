import java.awt.*;
import java.util.LinkedList;

public class Triangle extends Polygon {
    public Triangle(Point point1, Point point2, Point point3, Color borColor, Color bkColor) {
        super(borColor, bkColor);
        int minX = Math.min(point1.x, Math.min(point2.x, point3.x));
        int maxX = Math.max(point1.x, Math.max(point2.x, point3.x));
        int minY = Math.min(point1.y, Math.min(point2.y, point3.y));
        int maxY = Math.max(point1.y, Math.max(point2.y, point3.y));
        center = new Point(minX + (maxX-minX)/2, minY+(maxY-minY)/2);
        LinkedList<Point> listP = new LinkedList<>();
        listP.add(point1);
        listP.add(point2);
        listP.add(point3);
        setPoints(listP);
    }
}