/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;

import Model.Grafo;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Lienzo extends JPanel {

    Grafo grafo;
    
    public void setGrafo (Grafo grafo){
        this.grafo = grafo;
    }
	
		
        @Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	 	Graphics2D g2 = (Graphics2D)g; 
	 	setBackground(Color.white);
                try{
		grafo.dibujar(g2, this);
                }catch(NullPointerException e){
                    System.out.println(e.getMessage());
                }
	}		
        
	

    
}
