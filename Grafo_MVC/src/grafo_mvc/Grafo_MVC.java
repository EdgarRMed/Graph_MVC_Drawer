
package grafo_mvc;

import Controler.Controler;
import Model.Model;
import View.MainView;
import javax.swing.JFrame;


public class Grafo_MVC {

    
    // Aquí va el test del proyecto
    public static void main(String[] args) {
        // Se inicializan los componentes y se pasan al controlador
        MainView view = new MainView();
        Model model = new Model();
        Controler controler = new Controler(view, model);
        controler.start();
        controler.view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        controler.view.setLocationRelativeTo(null);
        controler.view.setVisible(true);
        
    }
    
}
