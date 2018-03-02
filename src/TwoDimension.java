import java.awt.*;

public abstract class TwoDimension extends Figure {
    protected Color backgroundColor;

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String location() {
        return "Центр: (" + center.x + ", " + center.y + ")";
    }
}