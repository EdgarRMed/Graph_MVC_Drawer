/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Grafo implements Serializable{
    private int orden; //orden del grafo
	private ArrayList <Vertice> vertices; 
        //private ArrayList<Flecha> flechas; // Lista que guarda todas las flechas
	private Arista [][]M;  //matriz de adyacencia
	
	
	public Grafo(){
		this(10);
	}
	
	public Grafo(int orden){
		this.orden = orden;
		vertices = new ArrayList<> ();
		M = new Arista[orden][orden];
               // flechas = new ArrayList<>();
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
            
            vertices.removeIf(e->(e.getCirculo().contains(x)));
            //Comprobación de la matriz
            for (Arista[] A: M){
                for(Arista a: A){
                    if(a!=null){
                        System.out.print(a.getPeso()+" ");
                    }
                    else
                        System.out.print("0 ");
                }
                System.out.println();
            }
            System.out.println("");
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
        // Se crea el metodo nuevo para agragar las flechas a un arrylist
      /*  public void agregarFlecha(Point2D po, Point2D pd){
            Flecha f = new Flecha(po,pd);
            for (Vertice v: vertices){
                if (v.getCirculo().contains(po)){
                    flechas.add(f);
                    //System.out.println(flechas);
                }
            }
        }*/
            
	
         //mostrar la lista de vertices
        public String mostarVertices(){
            String cad = " ";
            for(Vertice v: vertices) 
                cad += v.getNombre()+", ";
            return cad;
        }   
        public String mostrarMatriz(){
            String mat=" ";
               for (Arista[] A: M){
                for(Arista a: A){
                    if(a!=null){
                        mat+="1 ";
                    }
                    else
                        mat+="0 ";
                }
                mat+="\n";
            }
            return mat;
           
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
                /*
        // Se dibujan las flechas
                for(Flecha v: flechas)
                v.drawArrow(g2);
*/
	}

}
