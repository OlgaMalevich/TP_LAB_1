import java.awt.*;

public abstract class Figure {
    protected Point center;
    protected Color borderColor;

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point centerP) {
        center = centerP;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color cl) {
        borderColor = cl;
    }

    abstract public void draw(Graphics g);

    abstract public void move(Point newCenter);

    abstract public String location();

    protected void setStartPoint(Point p) {
        ;
    }

    protected void setEndPoint(Point p) {
        ;
    }

}