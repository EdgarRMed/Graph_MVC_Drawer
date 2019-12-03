
package Model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.Serializable;


public class Arista implements Serializable{
    private int peso;
    private Point2D po, pd; //punto origen y destino
    private Line2D arco;  //linea
    private Ellipse2D bucle; // Bucle
    private Color color;
    private int diametro = 30;
    private Flecha arrow;

    public Arista(Point2D po, Point2D pd, int peso){
        this.po = po;
        this.pd = pd;
        arrow = new Flecha(po, pd);
        arco = new Line2D.Double(po, pd);
        bucle = new Ellipse2D.Double(po.getX()-diametro/4, po.getY()-diametro/4,diametro+20, diametro+20);
        this.peso =peso;
        color = Color.black;
    }

    public Arista(Point2D po, Point2D pd){
        this(po, pd, 1);  //costo por default
    }

    public void dibujar(Graphics2D g2){
            g2.setPaint(color);
            g2.setStroke(new BasicStroke(3));
            if (po != pd){
                g2.draw(arco);
            }
            else
                g2.draw(bucle);
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
        bucle = new Ellipse2D.Double(po.getX()-diametro/4, po.getY()-diametro/4,diametro+20, diametro+20);

    }

    public void setPd(Point2D pd) {
        this.pd = pd;
        arco=new Line2D.Double(po, pd);
        bucle = new Ellipse2D.Double(po.getX()-diametro/4, po.getY()-diametro/4,diametro+20, diametro+20);

    }
       
}
