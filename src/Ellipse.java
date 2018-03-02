import java.awt.*;

public class Ellipse extends TwoDimension {
    protected Point point1, point2;
    protected int hw, hh;

    public Ellipse(Point centerP, Point firstP, Point secondP, Color borColor, Color bkColor) {
        center = centerP;
        if((firstP.x!=secondP.x)||(firstP.y!=secondP.y)){
        if (Math.abs((centerP.x - firstP.x)) < Math.abs(centerP.x - secondP.x)) {
            point2 = firstP;
            point1 = secondP;
        } else {
            point1 = firstP;
            point2 = secondP;
        }
        hw = Math.abs(point1.x - center.x);
        hh = Math.abs(point2.y - center.y);
        point1.x = center.x+hw;
        point1.y = center.y;
        point2.x = center.x;
        point2.y = center.y+hh;
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
        g.setColor(backgroundColor);
        g.fillOval((int) center.getX() - hw, (int) center.getY() - hh, 2*hw, 2*hh);
        g.setColor(borderColor);
        g.drawOval((int) center.getX() - hw, (int) center.getY() -hh, 2*hw, 2*hh);
    }

    public String location() {
        return "" + super.location() + ", ширина = " + 2*hw + ", высота = " + 2*hh;
    }

    public void move(Point newCenter) {
        center = newCenter;
        point1.x = center.x+hw;
        point1.y = center.y;
        point2.x = center.x;
        point2.y = center.y+hh;
    }

}