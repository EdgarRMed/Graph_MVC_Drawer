
package Model;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.Serializable;


public class Flecha implements Serializable{

    private final Point2D po; //punto origen 
    private final Point2D pd; // puto destino
    private AffineTransform at;
        
    public Flecha(Point2D po,Point2D pd){
        this.pd = pd;
        this.po = po;
    }

    
    public void drawArrow(Graphics2D g2){
        int ARR_SIZE = 15;
        double dx = pd.getX() - po.getX(), dy = pd.getY() - po.getY();
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx*dx + dy*dy);
        at = AffineTransform.getTranslateInstance(po.getX(), po.getY());
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g2.transform(at);
        //g2.drawLine(0, 0, len, 0);
        g2.fillPolygon(new int[] {len, len-ARR_SIZE, len-ARR_SIZE, len},
        new int[] {0, -ARR_SIZE, ARR_SIZE, 0}, 4);
    }
}
