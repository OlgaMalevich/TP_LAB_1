import java.awt.*;

public class Circle extends Ellipse {
    public Circle(Point centerP, Point firstP, Color borColor, Color bkColor) {
        super(centerP, firstP, firstP, borColor, bkColor);
        int radius = (int) Math.sqrt((firstP.x - centerP.x) * (firstP.x - centerP.x) + (firstP.y - centerP.y) * (firstP.y - centerP.y));
        point1 = new Point(center.x + radius, center.y);
        point2 = new Point(center.x, center.y + radius);
        hh = radius;
        hw = radius;
    }
    public String location() {
        return "Центр: (" + center.x + ", " + center.y + "), радиус = " + hh;
    }
}