package Controler;

import Model.Grafo;
import Model.Model;
import View.FMatriz;
import View.Lienzo;
import View.MainView;
import View.Informacion;
import View.guardarComoFrame;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public class Controler implements ActionListener{
    
    // Atributos del controlador
    private Ellipse2D actual;
    private Point2D po, pd;
    public MainView view;
    public Model model;
    public Lienzo lienzo, lienzoComplemento;
    public FMatriz matriz;
    public Informacion infoFrame;
    public guardarComoFrame saveAsFrame;
    // Fin atributos
    
    public Controler(MainView view, Model model) {
        
        this.model = model;
        this.view = view;
    }
    
    public void start(){    // Se inicia la aplicación
       view.setTitle("Graficador");
       view.setSize(1200, 700);
       initComponents();

    }

     // Agregar Escuchas

    public void initComponents(){
        //Se instancian los componentes
        lienzoComplemento = new Lienzo();
        lienzo = new Lienzo();
        matriz= new FMatriz();
        infoFrame = new Informacion();
        saveAsFrame = new guardarComoFrame();
        saveAsFrame.setDefaultCloseOperation(guardarComoFrame.DISPOSE_ON_CLOSE);
        //Se agrega el panel para la matriz
        matriz.setResizable(false);
        matriz.setVisible(false);
        matriz.txMatriz.setEditable(false);
        
        // Se agrea el lienzo al panel principal
        lienzo.setSize(1000,400);
        lienzo.setLocation(0, 0);
        view.MainPanelForLienzo.removeAll();
        view.MainPanelForLienzo.add(lienzo, BorderLayout.CENTER);
        view.MainPanelForLienzo.revalidate();
        lienzo.setGrafo(model.grafo);       
        view.repaint();
        // Fin de la agregacion
        // Se agrea el lienzo al panel
        lienzoComplemento.setSize(500,400);
        lienzoComplemento.setLocation(0, 0);
        infoFrame.complementPanel.removeAll();
        infoFrame.complementPanel.add(lienzoComplemento, BorderLayout.CENTER);
        infoFrame.complementPanel.revalidate();
        lienzoComplemento.setGrafo(model.grafo);       
        infoFrame.repaint();
        lienzoComplemento.repaint();
        infoFrame.setDefaultCloseOperation(Informacion.DISPOSE_ON_CLOSE);
        // Fin de la agregacion

        // Se agreagan los escuchas
        view.abrirMenuItem.addActionListener(this);
        view.guardarMenuItem.addActionListener(this);
        view.guardarComoMenuItem.addActionListener(this);
        view.salirMenuItem.addActionListener(this);
        view.showMatrixBtn.addActionListener(this);
        view.infoBtn.addActionListener(this);
        view.limpiarBtn.addActionListener(this);
        saveAsFrame.guardarBtn.addActionListener(this);
        saveAsFrame.cancelBtn.addActionListener(this);
        // Escucha de los eventos del raton en el lienzo   
        lienzo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evento){
                Point2D x = evento.getPoint();
                if (evento.getButton() == 1){
                    if(model.grafo.getVertice(x) == null) //no existe vertice
                        model.grafo.agregarVertice(x);
                    lienzo.setGrafo(model.grafo);
                    lienzo.repaint();
                }

                if (evento.getButton() == 3){
                    if(model.grafo.getVertice(x)!=null){
                        model.grafo.eliminarVertice(x);
                    }
                    lienzo.setGrafo(model.grafo);
                    lienzo.repaint();
                }
            }

            //es llamado cuando se presiona el raton sobre un componente
            //se utiliza para indicar el inicio de la arista
            @Override
            public void mousePressed(MouseEvent evento){
                if (evento.getButton() == 1){
                   Point2D x = evento.getPoint();

                //si de presiona sobre algun vertice este se marca como inicial(po)
                if(model.grafo.getVertice(x) != null)
                    po = x;
                else
                    po = null;
                }
            }

            //es llamado cuando se suelta el raton sobre un componente
            //se utiliza para indicar el fin de la arista
            @Override
            public void mouseReleased(MouseEvent evento){
                 if (evento.getButton() == 1){
                    Point2D x = evento.getPoint();
                    if(model.grafo.getVertice(x) != null){
                    //ya existe el vertice origen marcado
                        if(po != null) {
                            pd = x;
                            model.grafo.agregarArista(po, pd);
                            //model.grafo.agregarFlecha(po,pd);
                        }
                    }
                    else{
                        if(po!=null){
                            pd=x;
                            model.grafo.moverVertice(po, pd);
                        }
                    }
                    lienzo.setGrafo(model.grafo);
                    lienzo.repaint();
                 }
            }
});


            lienzo.addMouseMotionListener(new MouseMotionAdapter(){
                //Va a cambiar de icono del cursos al pasar por los vertices
                @Override
                public void mouseMoved(MouseEvent evento){
                 if(model.grafo.getVertice(evento.getPoint()) == null){
                     lienzo.setCursor(Cursor.getDefaultCursor());

                 }else
                      lienzo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }
           }); 
        //Fin de los escuchas

    } // Fin de initComponents


    @Override
    public void actionPerformed(ActionEvent e) {
        // Eventos de los items del menu
     
        if(e.getSource() == view.abrirMenuItem){
            JFileChooser fileChooser = new JFileChooser(".");
            fileChooser.setCurrentDirectory(new File("Grafos/"));
            int status = fileChooser.showOpenDialog(null);// Da un entero
            // Si apretamos en aceptar ocurrirá esto
            if (status == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            try{
                    FileInputStream entrada = new FileInputStream(archivo);
                    ObjectInputStream reader = new   ObjectInputStream(entrada);
                    //Lectura de objetor vector de archivo
                    model.grafo = (Grafo)reader.readObject();
                    lienzo.setGrafo(model.grafo);
                    lienzo.repaint();
                    }catch(IOException | ClassNotFoundException ex){}
            // Si apretamos en cancelar o cerramos la ventana ocurrirá esto
            } else if (status == JFileChooser.CANCEL_OPTION) {
            System.out.println("Cancelar");
            }
        }

        if(e.getSource() == view.guardarMenuItem){
            if(model.grafo.nombre == null){
                saveAsFrame.setLocationRelativeTo(view);
                saveAsFrame.setVisible(true);
            }
            else{
                File grafo = new File("Grafos/"+model.grafo.nombre+".bin"); // Se crea el archivo
                try {
                    FileOutputStream fileOS = new FileOutputStream(grafo); // Se pasa el archivo
                    try (ObjectOutputStream objectOS = new ObjectOutputStream(fileOS)) {
                        objectOS.writeObject(model.grafo); // Se esecribe
                        objectOS.close(); // Se cierra el archivo
                    } 
                    JOptionPane.showMessageDialog(null,"Se guardo correctamente "+ model.grafo.nombre);

                } catch (FileNotFoundException ex) { 
                Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
        }
        
        if(e.getSource() == view.guardarComoMenuItem){
            saveAsFrame.setLocationRelativeTo(view);
            saveAsFrame.setVisible(true);
        
        }
        // Botnoes de guardarComoFrame
        if(e.getSource() == saveAsFrame.guardarBtn){
            model.grafo.nombre = saveAsFrame.nombreTxF.getText();
            model.grafo.autor = saveAsFrame.autorTxF.getText();
            model.grafo.descripcion = saveAsFrame.descripcionTxA.getText();
            File grafo = new File("Grafos/"+model.grafo.nombre+".bin"); // Se crea el archivo
            try {
                FileOutputStream fileOS = new FileOutputStream(grafo); // Se pasa el archivo
                try (ObjectOutputStream objectOS = new ObjectOutputStream(fileOS)) {
                    objectOS.writeObject(model.grafo); // Se esecribe
                    objectOS.close(); // Se cierra el archivo
                    saveAsFrame.dispose(); // Se cierra la ventana
                } 
                JOptionPane.showMessageDialog(null,"Se guardo correctamente "+ model.grafo.nombre);

            } catch (FileNotFoundException ex) { 
            Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
            } 
        
        }
        
        if(e.getSource() == saveAsFrame.cancelBtn){
            saveAsFrame.dispose();
        }
        // Fin botones de guardar como 
        
        //Fin items del menú
        
        if(e.getSource() == view.salirMenuItem)
            view.dispose();
        
        if(e.getSource()==view.showMatrixBtn){
            matriz.txMatriz.setText(model.grafo.mostrarMatriz());
            matriz.setVisible(true);
        }
        
        if(e.getSource() == view. limpiarBtn){
            model.grafo = new Grafo();
            lienzo.limpiar(model.grafo);
        }
        
        if(e.getSource() == view.infoBtn){
            infoFrame.nombreTxF.setEditable(false);
            infoFrame.autorTxF.setEditable(false);
            if(model.grafo.nombre == null && model.grafo.autor == null){
                infoFrame.nombreTxF.setText("Sin nombre");
                infoFrame.autorTxF.setText("Sin autor");
                infoFrame.jTextArea1.setText("No hay descripción");
                
            }
            else{
                infoFrame.nombreTxF.setText(model.grafo.nombre);
                infoFrame.autorTxF.setText(model.grafo.autor);
                infoFrame.jTextArea1.setText(model.grafo.descripcion);
            }
        infoFrame.setVisible(true);
        }
        
       
        
    }
} // Fin del controlador
