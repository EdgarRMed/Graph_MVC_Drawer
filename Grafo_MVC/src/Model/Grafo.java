
package Model;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Grafo implements Serializable{
    public String autor, nombre,descripcion;
    private int orden; //orden del grafo
	private ArrayList <Vertice> vertices; 
	private Arista [][]M;  //matriz de adyacencia
	public Grafo(){
		this(10);
	}
	
	public Grafo(int orden){
            this.orden = orden;
            vertices = new ArrayList<> ();
            M = new Arista[orden][orden];
       	}
        
	public void agregarVertice(Point2D p){		
		Vertice v = new Vertice(p);
		vertices.add(v);
	}

        public void agregarVertice(Point2D p, String nombre){		
                
        }
        
        public void eliminarVertice(Point2D x){
            Vertice v=getVertice(x);
            //Eliminación de todas las aristas del vertice   
            for (int i = 0; i< 10; i++){
                for (int j = 0; j<10; j++){
                    if(M[i][j]!=null){
                        if(M[i][j].getPd()==v.getOrigen()||M[i][j].getPo()==v.getOrigen()){
                            M[i][j] = null;
                        }
                    }
                }
            }
            // Se elimina el vértice
            vertices.removeIf(e->(e.getCirculo().contains(x)));   
        }

	
	//obtiene el primer Vertice que contenga el punto p
	public Vertice getVertice(Point2D p)
	{
		for(Vertice v: vertices)
			if(v.getCirculo().contains(p)) return v;
		return null;
	}

	private int getIndex(Point2D p){
                for(int i=0; i< vertices.size(); i++) {
                    if((vertices.get(i)).getCirculo().contains(p))
                            return i;
                }
                return -1;
	}
	public void moverVertice(Point2D a, Point2D b){
	    Vertice v=getVertice(a);
            int index = Integer.parseInt(v.getNombre());
            for (Arista[]e: M){
                for (Arista n: e){
                    if (n!= null){
                        if (n.getPo() == v.getOrigen()){
                            n.setPo(b);
                        }
                        if(n.getPd()==v.getOrigen()){
                            n.setPd(b);
                        }
                    }
                        
                }
            
            }
	    v.mover(b);

        }
	public void agregarArista(Point2D po, Point2D pd){
            //TODO: crear una arista y agregarla a la matriz
            //  donde corresponde de acuerdo a los vertices
            // po y pd, son los puntos origen de los vertices
            
            Vertice a = null;
            Vertice b = null;
            for(Vertice v: vertices){
                if(v.getCirculo().contains(po)){
                    a=v;
                }
                if(v.getCirculo().contains(pd)){
                    b=v;
                }
            }
            Arista e = new Arista(a.getOrigen(), b.getOrigen());
            M[Integer.parseInt(a.getNombre())][Integer.parseInt(b.getNombre())] = e;
	}
        
        public void eliminarArista(Point2D x){
            
        }

        //mostrar la lista de vertices
        public String mostarVertices(){
            String cad = " ";
            for(Vertice v: vertices) 
                cad += v.getNombre()+", ";
            return cad;
        }   
        
        /*
        Se crea un strig que va guardando la matriz y después se le pasa
        al frame que contiene la matriz.
        */
        public String mostrarMatriz(){
            Integer indexi=0,indexj=1;
            String mat="";
               for (int i = 0; i<Vertice.nVertices; i++){
                for(int j = 0; j< Vertice.nVertices; j++){
                    if(M[i][j]!=null){
                        mat+="1  ";
                    }
                    else{
                        if(i == 0){
                        mat+= String.valueOf(indexi)+"  ";
                        indexi++;
                        }
                        else{
                            if(j == 0 && i > 0){
                                mat+= String.valueOf(indexj)+"  ";
                                indexj++;
                            }
                            else
                                mat+="0  ";
                        }
                        
                    }   
                }
                mat+='\n';
            }
            return mat;   
        }
        
        public void complemento(){
            for (Arista[] e: M){
                    for(Arista b: e){
                        if(b!=null){
                            
                            
                        }
                    }
                }
        }

	public void dibujar(Graphics2D g2, JPanel l){
                
       //pintamos los vertices
	 	for(Vertice v: vertices){ 
	 		v.dibujar(g2);      
                        g2.drawString(v.getNombre(),(int)v.getOrigen().getX(),(int)v.getOrigen().getY());
                }
		//TODO: pintar aristas
		//Tener cuidado! solo pintar aristas existentes!
                for (Arista[] e: M){
                    for(Arista b: e){
                        if(b!=null){
                            b.dibujar(g2);
                        }
                    }
                }
                

	}

}
