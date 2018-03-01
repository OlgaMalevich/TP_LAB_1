import java.awt.*;

public class Ellipse extends TwoDimension {
    public Point point1;
    public Point point2;

    public Ellipse(Point centerP, Point firstP, Point secondP, Color borColor, Color bkColor) {
        center = centerP;
        if(Math.abs((centerP.x - firstP.x)) < Math.abs(centerP.x - secondP.x)){
            point2 = firstP;
            point1 = secondP;
        }else {
            point1 = firstP;
            point2 = secondP;
        }
        borderColor = borColor;
        backgroundColor = bkColor;
    }

    public Point getPoint1() {
        return point1;
    }

    public void setPoint1(Point point1) {
        this.point1 = point1;
    }

    public Point getPoint2() {
        return point2;
    }

    public void setPoint2(Point point2) {
        this.point2 = point2;
    }

    public void draw(Graphics g) {
        int w = (2 * Math.abs(point1.x - center.x));
        int h = (2 * Math.abs(point2.y - center.y));
        g.setColor(backgroundColor);
        g.fillOval((int) center.getX() - w/2, (int) center.getY()-h/2, w, h);
        g.setColor(borderColor);
        g.drawOval((int) center.getX()-w/2, (int) center.getY()-h/2, w, h);
    }

    public String getLocation() {
        int w = (int) (2 * Math.abs(point1.getX() - center.getX()));
        int h = (int) (2*Math.abs(point2.getY() - center.getY()));
        String str = "Центр: ("+center.getX()+", "+center.getY()+"), ширина = "+w+", высота = "+h;
        return str;
    }

}