
package Model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.io.Serializable;


public class Vertice implements Serializable{
    
    private String nombre;
    private boolean visitado;
    private Point2D origen; //punto origen
    private Ellipse2D circulo;  //circulo
    private Color color;
    private int diametro = 30;
    public static int nVertices = 1;  //contador  de vertices creados
	
    public Vertice(){
            this(new Point2D.Double(0,0));
    }

    public Vertice(Point2D p){
        this(p, ""+nVertices);
    }

    public Vertice(Point2D p, String nombre){
        double x = p.getX();
        double y = p.getY();
        origen = p;
        color = Color.BLUE;
        circulo = new Ellipse2D.Double(x-diametro/2,y-diametro/2,
            diametro,diametro);
        this.nombre = nombre;
        nVertices++;
    }
        

    public int getDiametro() {
        return diametro;
    }

        
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
    }
	
    public void setColor(Color c){
          color = c;
    }
   
    public void dibujar(Graphics2D g2){
                 g2.setPaint(color);
                 g2.fill(circulo);
                 g2.setPaint(Color.white);
    }

    public Ellipse2D getCirculo() {
        return circulo;
    }

    public void setCirculo(Ellipse2D circulo) {
        this.circulo = circulo;
    }

    public Point2D getOrigen() {
        return origen;
    }
    
    
    
    public void mover(Point2D c){
        double x, y;
        x=c.getX();
        y=c.getY();
        origen=c;
        circulo=new Ellipse2D.Double(x-diametro/2,y-diametro/2, diametro,diametro);
    }
}
