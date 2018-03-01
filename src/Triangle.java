import java.awt.*;
import java.util.LinkedList;

public class Triangle extends Polygon {
    public Triangle(Point point1, Point point2, Point point3, Color borColor, Color bkColor) {
        super(borColor, bkColor);
        LinkedList<Point> listP = new LinkedList<>();
        listP.add(point1);
        listP.add(point2);
        listP.add(point3);
        setPoints(listP);
    }
}