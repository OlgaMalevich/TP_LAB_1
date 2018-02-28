import java.awt.*;
import java.util.*;
public class Zigzag {
    private LinkedList<Segment> segmets;
    public Zigzag(Stack<Point> pointList, Color color) {
        segmets = new LinkedList<>();
        Point prevP = pointList.pop(), nextP;
        while(pointList.size()!=0){
            nextP = pointList.pop();
            segmets.add(new Segment(prevP,nextP,color));
            System.out.println("from: ("+prevP.x+", "+prevP.y+"), to: ("+nextP.x+", "+nextP.y+")");
            prevP = nextP;
        }
    }
    public void draw(Graphics g) {
        for(Segment s:segmets){
            s.draw(g);
        }
    }

}