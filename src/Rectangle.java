import java.awt.*;
import java.util.LinkedList;

public class Rectangle extends Polygon {
    public Rectangle(Point centerP, Point secondPoint, Color borColor, Color bkColor) {
        super(borColor, bkColor);
        center = centerP;
        LinkedList<Point> pList = new LinkedList<>();
        int hh = centerP.y - secondPoint.y;
        int hw = centerP.x - secondPoint.x;
        pList.add(secondPoint);
        pList.add(new Point(centerP.x-hw, centerP.y + hh));
        pList.add(new Point(centerP.x+hw, centerP.y + hh));
        pList.add(new Point(centerP.x+hw, centerP.y - hh));
        setPoints(pList);
    }
}