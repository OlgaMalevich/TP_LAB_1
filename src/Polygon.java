import java.awt.*;
import java.util.LinkedList;

public class Polygon extends TwoDimension {
    protected int[] xPoints, yPoints;
    protected int count;

    Polygon(Color borColor, Color bkColor) {
        borderColor = borColor;
        backgroundColor = bkColor;
    }

    protected void setPoints(LinkedList<Point> pointsList) {
        count = pointsList.size();
        xPoints = new int[count];
        yPoints = new int[count];
        int i = 0;
        for (Point p : pointsList) {
            xPoints[i] = p.x;
            yPoints[i] = p.y;
            i++;
        }
    }

    public void draw(Graphics g) {
        g.setColor(backgroundColor);
        g.fillPolygon(xPoints, yPoints, count);
        g.setColor(borderColor);
        g.drawPolygon(xPoints, yPoints, count);
    }

    public String getLocation() {
        StringBuilder str = new StringBuilder();
        str.append("Полигон проходит через точки: ");
        for (int i = 0; i < count; i++) {
            str.append("(").append(xPoints[i]).append(", ").append(yPoints[i]).append(")");
            if (i + 1 < count)
                str.append(", ");
        }
        return str.toString();
    }

    public void move(Point newCenter) {
        int difX = center.x - newCenter.x;
        int difY = center.y - newCenter.y;
        for (int i = 0; i < count; i++) {
            xPoints[i] -= difX;
            xPoints[i] -= difY;
        }
    }
}