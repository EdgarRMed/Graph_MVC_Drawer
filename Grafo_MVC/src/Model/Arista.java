
package Model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.Serializable;


public class Arista implements Serializable{
    private int peso;
    private Point2D po, pd; //punto origen y destino
    private Line2D arco;  //linea
    private Color color;

    public Arista(Point2D po, Point2D pd, int peso){
            this.po = po;
            this.pd = pd;
            arco = new Line2D.Double(po, pd);
            this.peso =peso;
            color = Color.black;
    }

    public Arista(Point2D po, Point2D pd){
        this(po, pd, 1);  //costo por default
    }

    public void dibujar(Graphics2D g2){
            g2.setPaint(color);
            g2.setStroke(new BasicStroke(3));
            g2.draw(arco);
            g2.setPaint(Color.BLACK);
    }

    public int getPeso() {
        return peso;
    }

    public Point2D getPo() {
        return po;
    }

    public Point2D getPd() {
        return pd;
    }

    public void setPo(Point2D po) {
        this.po = po;
        arco=new Line2D.Double(po, pd);
    }

    public void setPd(Point2D pd) {
        this.pd = pd;
        arco=new Line2D.Double(po, pd);
    }
       
}
