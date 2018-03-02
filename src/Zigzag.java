import java.awt.*;
import java.util.*;
public class Zigzag {
    private LinkedList<Segment> segmets;
    public Zigzag(Stack<Point> pointList, Color color) {
        segmets = new LinkedList<>();
        Point prevP = pointList.pop(), nextP;
        while(pointList.size()!=0){
            nextP = pointList.pop();
            segmets.addFirst(new Segment(nextP,prevP,color));
            prevP = new Point(nextP);
        }
    }
    public void draw(Graphics g) {
        for(Segment s:segmets){
            s.draw(g);
        }
    }
    public void move(Point newP){
        for(Segment s:segmets){
            s.move(newP);
            newP = new Point(s.endPoint);
        }
    }
    public String location(){
        StringBuilder str = new StringBuilder();
        str.append("Зигзаг проходит через точки: ");
        for(Segment s:segmets){
            str.append("("+s.startPoint.x+", "+s.startPoint.y+"), ");
        }
        str.append("("+segmets.getLast().endPoint.x+", "+segmets.getLast().endPoint.y+")");
        return str.toString();
    }

}